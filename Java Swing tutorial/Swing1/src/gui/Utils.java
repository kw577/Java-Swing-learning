package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;

public class Utils {

	public static String getFileExtension(String name) {
		
		int pointIndex = name.lastIndexOf("."); // wyszukuje . w nazwie pliku. Jesli nie znajdzie to zwroci wartosc -1
		
		// jesli nie znaleziono . w nazwie pliku (brak rozszerzenia)
		if(pointIndex == -1) {
			return null;
		}
		
		if(pointIndex == name.length()-1) {
			return null;
		}
		
		// zwraca rozszerzenie pliku
		return name.substring(pointIndex+1, name.length());
	}
	
	
	//////////////////////
	
	public static ImageIcon createIcon(String path) {
		
		URL url = System.class.getResource(path);
		
		if(url == null) {
			System.err.println("Unable to load image: " + path);
		}
		
		
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}
	
	
	
	//////////////////////
	// Pobranie czcionki zdefiniowanej w pliku w formacie .ttf
	
	public static Font createFont(String path) {
		
		URL url = System.class.getResource(path);
		
		if(url == null) {
			System.err.println("Unable to load font: " + path);
		}
		
		
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, url.openStream());
		} catch (FontFormatException e) {
			System.out.println("Bad format in font file: " + path);
		} catch (IOException e) {
			System.out.println("Unable to read font file: " + path);
		}
		
		
		return font;
	}
	
	
	
	
}
