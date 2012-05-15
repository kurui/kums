// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileHandler.java

package com.kurui.kums.base.log;

import java.io.*;
import java.sql.Timestamp;
import java.util.logging.*;

public class FileHandler extends Handler {

	private String date;
	private String directory;
	private String prefix;
	private String suffix;
	private PrintWriter writer;

	public FileHandler() {
		this(null, null, null);
	}

	public FileHandler(String directory, String prefix, String suffix) {
		date = "";
		this.directory = null;
		this.prefix = null;
		this.suffix = null;
		writer = null;
		this.directory = directory;
		this.prefix = prefix;
		this.suffix = suffix;
		configure();
		open();
	}

	public void publish(LogRecord record) {
		if (!isLoggable(record))
			return;
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsString = ts.toString().substring(0, 19);
		String tsDate = tsString.substring(0, 10);
		if (!date.equals(tsDate))
			synchronized (this) {
				if (!date.equals(tsDate)) {
					close();
					date = tsDate;
					open();
				}
			}
		String result = null;
		try {
			result = getFormatter().format(record);
		} catch (Exception e) {
			reportError(null, e, 5);
			return;
		}
		try {
			writer.write(result);
			writer.flush();
		} catch (Exception e) {
			reportError(null, e, 1);
			return;
		}
	}

	public void close() {
		if (writer == null)
			return;
		try {
			writer.write(getFormatter().getTail(this));
			writer.flush();
			writer.close();
			writer = null;
			date = "";
		} catch (Exception e) {
			reportError(null, e, 3);
		}
		return;
	}

	public void flush() {
		try {
			writer.flush();
		} catch (Exception e) {
			reportError(null, e, 2);
		}
	}

	private void configure() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String tsString = ts.toString().substring(0, 19);
		date = tsString.substring(0, 10);
		String className = (com.kurui.kums.base.log.FileHandler.class)
				.getName();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if (directory == null)
			directory = getProperty(className + ".directory", "logs");
		if (prefix == null)
			prefix = getProperty(className + ".prefix", "juli.");
		if (suffix == null)
			suffix = getProperty(className + ".suffix", ".log");
		String encoding = getProperty(className + ".encoding", null);
		if (encoding != null && encoding.length() > 0)
			try {
				setEncoding(encoding);
			} catch (UnsupportedEncodingException ex) {
			}
		setLevel(Level.parse(getProperty(className + ".level", "" + Level.ALL)));
		String filterName = getProperty(className + ".filter", null);
		if (filterName != null)
			try {
				setFilter((Filter) cl.loadClass(filterName).newInstance());
			} catch (Exception e) {
			}
		String formatterName = getProperty(className + ".formatter", null);
		if (formatterName != null)
			try {
				setFormatter((Formatter) cl.loadClass(formatterName)
						.newInstance());
			} catch (Exception e) {
			}
		else
			setFormatter(new SimpleFormatter());
		setErrorManager(new ErrorManager());
	}

	private String getProperty(String name, String defaultValue) {
		String value = LogManager.getLogManager().getProperty(name);
		if (value == null)
			value = defaultValue;
		else
			value = value.trim();
		return value;
	}

	private void open() {
		File dir = new File(directory);
		dir.mkdirs();
		try {
			String pathname = dir.getAbsolutePath() + File.separator + prefix
					+ date + suffix;
			String encoding = getEncoding();
			java.io.OutputStream os = new BufferedOutputStream(
					new FileOutputStream(pathname, true));
			writer = new PrintWriter(
					encoding == null ? ((java.io.Writer) (new OutputStreamWriter(
							os)))
							: ((java.io.Writer) (new OutputStreamWriter(os,
									encoding))), true);
			writer.write(getFormatter().getHead(this));
		} catch (Exception e) {
			reportError(null, e, 4);
			writer = null;
		}
	}
}
