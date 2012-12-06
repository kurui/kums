package com.kurui.kums.base.chart;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import net.jmge.gif.Gif89Encoder;

import com.gif4j.GifEncoder;
import com.kurui.kums.base.exception.AppException;
import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;

/**
 * @author Erick Kong
 * @see ImageUtil.java
 * @createDate: 2007-6-22
 * @version 1.0
 */
public class ImageUtil {

	public static final String TYPE_GIF = "gif";
	public static final String TYPE_JPEG = "jpeg";
	public static final String TYPE_PNG = "png";
	public static final String TYPE_BMP = "bmp";
	public static final String TYPE_NOT_AVAILABLE = "na";

	private static ColorModel getColorModel(Image image)
			throws InterruptedException, IllegalArgumentException {
		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		if (!pg.grabPixels())
			throw new IllegalArgumentException();
		return pg.getColorModel();
	}

	private static void loadImage(Image image) throws InterruptedException,
			IllegalArgumentException {
		Component dummy = new Component() {
			private static final long serialVersionUID = 1L;
		};
		MediaTracker tracker = new MediaTracker(dummy);
		tracker.addImage(image, 0);
		tracker.waitForID(0);
		if (tracker.isErrorID(0))
			throw new IllegalArgumentException();
	}

	public static BufferedImage createBufferedImage(Image image)
			throws InterruptedException, IllegalArgumentException {
		loadImage(image);
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		ColorModel cm = getColorModel(image);
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		BufferedImage bi = gc.createCompatibleImage(w, h, cm.getTransparency());
		Graphics2D g = bi.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return bi;
	}

	public static BufferedImage readImage(InputStream is) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public static BufferedImage readImage(byte[] imageByte) {
		ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
		BufferedImage image = readImage(bais);
		return image;
	}

	public static void encodeGIF(BufferedImage image, OutputStream out)
			throws IOException {
		Gif89Encoder encoder = new Gif89Encoder(image);
		encoder.encode(out);
	}

	/**
	 * Get image type from byte[]
	 * 
	 * @param textObj
	 *            image byte[]
	 * @return String image type
	 */
	public static String getImageType(byte[] textObj) {
		String type = TYPE_NOT_AVAILABLE;
		ByteArrayInputStream bais = null;
		MemoryCacheImageInputStream mcis = null;
		try {
			bais = new ByteArrayInputStream(textObj);
			mcis = new MemoryCacheImageInputStream(bais);
			Iterator itr = ImageIO.getImageReaders(mcis);
			while (itr.hasNext()) {
				ImageReader reader = (ImageReader) itr.next();
				if (reader instanceof GIFImageReader) {
					type = TYPE_GIF;
				} else if (reader instanceof JPEGImageReader) {
					type = TYPE_JPEG;
				} else if (reader instanceof PNGImageReader) {
					type = TYPE_PNG;
				} else if (reader instanceof BMPImageReader) {
					type = TYPE_BMP;
				}
				reader.dispose();
			}
		} finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException ioe) {

				}
			}
			if (mcis != null) {
				try {
					mcis.close();
				} catch (IOException ioe) {

				}
			}
		}
		return type;
	}

	/**
	 * 
	 * @param bi
	 * @param type
	 * @param out
	 */
	public static void printImage(BufferedImage bi, String type,
			OutputStream out) {
		try {
			if (type.equals(TYPE_GIF))
				encodeGIF(bi, out);
			else
				ImageIO.write(bi, type, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printImage(byte[] bytes, String file)
			throws AppException {
		try {
			FileImageOutputStream imgout = new FileImageOutputStream(new File(
					file));
			imgout.write(bytes);
			imgout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  扩展：输出图片
	 * @param content  适用于BLOB
	 * @param height
	 * @param width
	 * @param out=HttpServletResponse.getOutputStream();
	 * @throws IOException
	 */
	public static void printImageLibrary(byte[] content, int height, int width,
			OutputStream out) throws IOException {
		BufferedImage img = ImageUtil.readImage(content);
		int h = img.getHeight();
		int w = img.getWidth();

		if(height==0&&width==0){
			//按原图输出
		}else{
//			按比例输出
			if (h >= height && w >= width) {
				int nw = width;
				int nh = (nw * h) / w;
				if (nh > height) {
					nh = height;
					nw = (nh * w) / h;
				}			
				
				BufferedImage dest = new BufferedImage(nw, nh,
						BufferedImage.TYPE_4BYTE_ABGR);
				dest.getGraphics().drawImage(img, 0, 0, nw, nh, null);
				GifEncoder.encode(dest, out);			
			}
		}
		

		out.write(content);
		out.flush();
		out.close();
	}
}
