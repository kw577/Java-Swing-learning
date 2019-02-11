
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
	
}
