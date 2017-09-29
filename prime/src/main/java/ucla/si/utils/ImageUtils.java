package ucla.si.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageUtils {

	/*
	 * Obtiene el nuevo alto/ancho de una imagen cuando se quieren
	 * mantener las proporciones originales
	 * Sacado de algún lugar de internet, que es este:
	 * http://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio
	 */
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

		int original_width = imgSize.width;
		int original_height = imgSize.height;
		int bound_width = boundary.width;
		int bound_height = boundary.height;
		int new_width = original_width;
		int new_height = original_height;

		// first check if we need to scale width
		if (original_width > bound_width) {
			// scale width to fit
			new_width = bound_width;
			// scale height to maintain aspect ratio
			new_height = (new_width * original_height) / original_width;
		}

		// then check if we need to scale even with the new height
		if (new_height > bound_height) {
			// scale height to fit instead
			new_height = bound_height;
			// scale width to maintain aspect ratio
			new_width = (new_height * original_width) / original_height;
		}

		return new Dimension(new_width, new_height);
	}

	public static BufferedImage redimensionarImagen(BufferedImage bi, int ancho, int alto) {
		/*
		 * Metodo de redimensión + convertir en bufferedimage robado de algún
		 * lugar de internet, que es este:
		 * https://community.oracle.com/thread/1267139?start=0&tstart=0
		 * 
		 * Modificado por Kleiver: 29-01-2016
		 */

		Dimension newSize = getScaledDimension(new Dimension(bi.getWidth(), bi.getHeight()),
				new Dimension(ancho, alto));
		Image im = bi.getScaledInstance(newSize.width, newSize.height, Image.SCALE_SMOOTH);
		BufferedImage bImage = new BufferedImage(ancho, alto, bi.getType());
		// obtain it's graphics
		Graphics2D bImageGraphics = bImage.createGraphics();
		
		// cambia el fondo a color blanco
		bImageGraphics.setBackground(Color.WHITE);
		bImageGraphics.clearRect(0, 0, ancho, alto);
		
		// dibuja la imagen centrada
		bImageGraphics.drawImage(im, (ancho-newSize.width)/2, (alto-newSize.height)/2, null);
		return bImage;
	}
}
