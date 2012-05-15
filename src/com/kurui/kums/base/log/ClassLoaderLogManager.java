package com.kurui.kums.base.log;

import java.io.*;
import java.net.URLClassLoader;
import java.security.*;
import java.util.*;
import java.util.logging.*;

public class ClassLoaderLogManager extends LogManager {
	protected class RootLogger extends Logger {

		public RootLogger() {
			super("", null);
		}
	}

	protected static final class ClassLoaderLogInfo {

		final LogNode rootNode;
		final Map loggers = new HashMap();
		final Map handlers = new HashMap();
		final Properties props = new Properties();

		ClassLoaderLogInfo(LogNode rootNode) {
			this.rootNode = rootNode;
		}
	}

	protected static final class LogNode {

		Logger logger;
		protected final Map children;
		protected final LogNode parent;

		LogNode findNode(String name) {
			LogNode currentNode = this;
			if (logger.getName().equals(name))
				return this;
			while (name != null) {
				int dotIndex = name.indexOf('.');
				String nextName;
				if (dotIndex < 0) {
					nextName = name;
					name = null;
				} else {
					nextName = name.substring(0, dotIndex);
					name = name.substring(dotIndex + 1);
				}
				LogNode childNode = (LogNode) currentNode.children
						.get(nextName);
				if (childNode == null) {
					childNode = new LogNode(currentNode);
					currentNode.children.put(nextName, childNode);
				}
				currentNode = childNode;
			}
			return currentNode;
		}

		Logger findParentLogger() {
			Logger logger = null;
			for (LogNode node = parent; node != null && logger == null; node = node.parent)
				logger = node.logger;

			return logger;
		}

		void setParentLogger(Logger parent) {
			for (Iterator iter = children.values().iterator(); iter.hasNext();) {
				LogNode childNode = (LogNode) iter.next();
				if (childNode.logger == null)
					childNode.setParentLogger(parent);
				else
					ClassLoaderLogManager.doSetParentLogger(childNode.logger,
							parent);
			}

		}

		LogNode(LogNode parent, Logger logger) {
			children = new HashMap();
			this.parent = parent;
			this.logger = logger;
		}

		LogNode(LogNode parent) {
			this(parent, null);
		}
	}

	protected final Map classLoaderLoggers = new WeakHashMap();
	protected ThreadLocal prefix;

	public ClassLoaderLogManager() {
		prefix = new ThreadLocal();
	}

	public synchronized boolean addLogger(final Logger logger) {
		String loggerName = logger.getName();
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		ClassLoaderLogInfo info = getClassLoaderInfo(classLoader);
		if (info.loggers.containsKey(loggerName))
			return false;
		info.loggers.put(loggerName, logger);
		final String levelString = getProperty(loggerName + ".level");
		if (levelString != null)
			try {
				AccessController.doPrivileged(new PrivilegedAction() {

					public Object run() {
						logger.setLevel(Level.parse(levelString.trim()));
						return null;
					}

					{
						// super();
					}
				});
			} catch (IllegalArgumentException e) {
			}
		int dotIndex = loggerName.lastIndexOf('.');
		do {
			if (dotIndex < 0)
				break;
			String parentName = loggerName.substring(0, dotIndex);
			if (getProperty(parentName + ".level") != null) {
				Logger.getLogger(parentName);
				break;
			}
			dotIndex = loggerName.lastIndexOf('.', dotIndex - 1);
		} while (true);
		LogNode node = info.rootNode.findNode(loggerName);
		node.logger = logger;
		Logger parentLogger = node.findParentLogger();
		if (parentLogger != null)
			doSetParentLogger(logger, parentLogger);
		node.setParentLogger(logger);
		String handlers = getProperty(loggerName + ".handlers");
		if (handlers != null) {
			logger.setUseParentHandlers(false);
			StringTokenizer tok = new StringTokenizer(handlers, ",");
			do {
				if (!tok.hasMoreTokens())
					break;
				String handlerName = tok.nextToken().trim();
				Handler handler = null;
				for (ClassLoader current = classLoader; current != null; current = current
						.getParent()) {
					info = (ClassLoaderLogInfo) classLoaderLoggers.get(current);
					if (info == null)
						continue;
					handler = (Handler) info.handlers.get(handlerName);
					if (handler != null)
						break;
				}

				if (handler != null)
					logger.addHandler(handler);
			} while (true);
		}
		String useParentHandlersString = getProperty(loggerName
				+ ".useParentHandlers");
		if (Boolean.valueOf(useParentHandlersString).booleanValue())
			logger.setUseParentHandlers(true);
		return true;
	}

	public synchronized Logger getLogger(String name) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return (Logger) getClassLoaderInfo(classLoader).loggers.get(name);
	}

	public synchronized Enumeration getLoggerNames() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return Collections.enumeration(getClassLoaderInfo(classLoader).loggers
				.keySet());
	}

	public String getProperty(String name) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String prefix = (String) this.prefix.get();
		if (prefix != null)
			name = prefix + name;
		ClassLoaderLogInfo info = getClassLoaderInfo(classLoader);
		String result = info.props.getProperty(name);
		if (result == null && info.props.isEmpty()) {
			for (ClassLoader current = classLoader.getParent(); current != null; current = current
					.getParent()) {
				info = (ClassLoaderLogInfo) classLoaderLoggers.get(current);
				if (info == null)
					continue;
				result = info.props.getProperty(name);
				if (result != null || !info.props.isEmpty())
					break;
			}

			if (result == null)
				result = super.getProperty(name);
		}
		if (result != null)
			result = replace(result);
		return result;
	}

	public void readConfiguration() throws IOException, SecurityException {
		checkAccess();
		readConfiguration(Thread.currentThread().getContextClassLoader());
	}

	public void readConfiguration(InputStream is) throws IOException,
			SecurityException {
		checkAccess();
		reset();
		readConfiguration(is, Thread.currentThread().getContextClassLoader());
	}

	protected ClassLoaderLogInfo getClassLoaderInfo(ClassLoader classLoader) {
		if (classLoader == null)
			classLoader = ClassLoader.getSystemClassLoader();
		ClassLoaderLogInfo info = (ClassLoaderLogInfo) classLoaderLoggers
				.get(classLoader);
		if (info == null) {
			final ClassLoader classLoaderParam = classLoader;
			AccessController.doPrivileged(new PrivilegedAction() {

				public Object run() {
					try {
						readConfiguration(classLoaderParam);
					} catch (IOException e) {
					}
					return null;
				}

				{
					// super();
				}
			});
			info = (ClassLoaderLogInfo) classLoaderLoggers.get(classLoader);
		}
		return info;
	}

	protected void readConfiguration(ClassLoader classLoader)
			throws IOException {
		InputStream is = null;
		ClassLoaderLogInfo info;
		try {
			if ((classLoader instanceof URLClassLoader)
					&& ((URLClassLoader) classLoader)
							.findResource("logging.properties") != null)
				is = classLoader.getResourceAsStream("logging.properties");
		} catch (AccessControlException ace) {
			info = (ClassLoaderLogInfo) classLoaderLoggers.get(ClassLoader
					.getSystemClassLoader());
			if (info != null) {
				Logger log = (Logger) info.loggers.get("");
				if (log != null) {
					Permission perm = ace.getPermission();
					if ((perm instanceof FilePermission)
							&& perm.getActions().equals("read")) {
						log
								.warning("Reading "
										+ perm.getName()
										+ " is not permitted. See \"per context logging\" in the default catalina.policy file.");
					} else {
						log
								.warning("Reading logging.properties is not permitted in some context. See \"per context logging\" in the default catalina.policy file.");
						log.warning("Original error was: " + ace.getMessage());
					}
				}
			}
		}
		if (is == null && classLoader == ClassLoader.getSystemClassLoader()) {
			String configFileStr = System
					.getProperty("java.util.logging.config.file");
			if (configFileStr != null)
				try {
					is = new FileInputStream(replace(configFileStr));
				} catch (IOException e) {
				}
			if (is == null) {
				File defaultFile = new File(new File(System
						.getProperty("java.home"), "lib"), "logging.properties");
				try {
					is = new FileInputStream(defaultFile);
				} catch (IOException e) {
				}
			}
		}
		Logger localRootLogger = new RootLogger();
		if (is == null) {
			ClassLoader current = classLoader.getParent();
			// ClassLoaderLogInfo info;
			for (info = null; current != null && info == null; current = current
					.getParent())
				info = getClassLoaderInfo(current);

			if (info != null)
				localRootLogger.setParent(info.rootNode.logger);

			// current = new ClassLoaderLogInfo(new LogNode(null,
			// localRootLogger));
			info = new ClassLoaderLogInfo(new LogNode(null, localRootLogger));

			classLoaderLoggers.put(classLoader, current);
		}
		if (is != null)
			readConfiguration(is, classLoader);
		addLogger(localRootLogger);
	}

	protected void readConfiguration(InputStream is, ClassLoader classLoader)
			throws IOException {
		ClassLoaderLogInfo info = (ClassLoaderLogInfo) classLoaderLoggers
				.get(classLoader);
		info.props.load(is);
		try {
			is.close();
		} catch (IOException e) {
			System.err.println("Configuration error");
			e.printStackTrace();
		}

		try {
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rootHandlers = info.props.getProperty(".handlers");
		String handlers = info.props.getProperty("handlers");
		Logger localRootLogger = info.rootNode.logger;
		if (handlers != null) {
			StringTokenizer tok = new StringTokenizer(handlers, ",");
			do {
				if (!tok.hasMoreTokens())
					break;
				String handlerName = tok.nextToken().trim();
				String handlerClassName = handlerName;
				String prefix = "";
				if (handlerClassName.length() > 0) {
					if (Character.isDigit(handlerClassName.charAt(0))) {
						int pos = handlerClassName.indexOf('.');
						if (pos >= 0) {
							prefix = handlerClassName.substring(0, pos + 1);
							handlerClassName = handlerClassName
									.substring(pos + 1);
						}
					}
					try {
						this.prefix.set(prefix);
						Handler handler = (Handler) classLoader.loadClass(
								handlerClassName).newInstance();
						this.prefix.set(null);
						info.handlers.put(handlerName, handler);
						if (rootHandlers == null)
							localRootLogger.addHandler(handler);
					} catch (Exception e2) {
						System.err.println("Handler error");
						e2.printStackTrace();
					}
				}
			} while (true);
			if (rootHandlers != null) {
				StringTokenizer tok2 = new StringTokenizer(rootHandlers, ",");
				do {
					if (!tok2.hasMoreTokens())
						break;
					String handlerName = tok2.nextToken().trim();
					Handler handler = (Handler) info.handlers.get(handlerName);
					if (handler != null)
						localRootLogger.addHandler(handler);
				} while (true);
			}
		}
		return;
	}

	protected static void doSetParentLogger(Logger logger, Logger parent) {
		// AccessController.doPrivileged(new PrivilegedAction(logger, parent) {
		//
		// public Object run() {
		// logger.setParent(parent);
		// return null;
		// }

		// {
		// super();
		// }
		// });
	}

	protected String replace(String str) {
		String result = str;
		int pos_start = result.indexOf("${");
		if (pos_start != -1) {
			int pos_end = result.indexOf('}');
			if (pos_end != -1) {
				String propName = result.substring(pos_start + 2, pos_end);
				String replacement = System.getProperty(propName);
				if (replacement != null)
					if (pos_start > 0)
						result = result.substring(0, pos_start) + replacement
								+ replace(result.substring(pos_end + 1));
					else
						result = replacement
								+ replace(result.substring(pos_end + 1));
			}
		}
		return result;
	}
}
