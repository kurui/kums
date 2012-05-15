package com.kurui.kums.base.chart;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import com.gif4j.GifEncoder;
import com.kurui.kums.base.exception.AppException;

public class PhotoZoomUtil {
	public static void main(String[] args) {
		String path = "E:\\project\\SA001\\Home_files\\u0.jpg";

		try {
			File file = new File(path);
			// ImageInputStream in=new FileImageInputStream(file);
			if (file.exists()) {
				byte[] bytes = zoom(file,96,96);
				bytes = zoom(file,200,200);
				String imageType = ImageUtil.getImageType(bytes);
				System.out.println("image type:" + imageType);
				String newFile = "e:\\2.gif";
				ImageUtil.printImage(bytes, newFile);
			} else {
				System.out.println("file.exists():" + file.exists());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static byte[] zoom(File file, int height, int width)
			throws AppException {
		byte[] imageThumbnail = null;
		try {
			BufferedImage img = ImageIO.read(file);
			int h = img.getHeight();
			int w = img.getWidth();

			if (h >= height && w >= width) {
				int nw = width;
				int nh = (nw * h) / w;
				if (nh > height) {
					nh = height;
					nw = (nh * w) / h;
				}
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				BufferedImage dest = new BufferedImage(nw, nh,
						BufferedImage.TYPE_4BYTE_ABGR);
				dest.getGraphics().drawImage(img, 0, 0, nw, nh, null);
				GifEncoder.encode(dest, out);
				// ImageIO.write(dest, "gif", out);
				imageThumbnail = out.toByteArray();
			}
			// else{
			// imageThumbnail = imageData;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imageThumbnail;
	}

}
