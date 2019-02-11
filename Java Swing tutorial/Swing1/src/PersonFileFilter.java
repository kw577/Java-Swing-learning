import java.io.File;

import javax.swing.filechooser.FileFilter;

// klasa uzywana do filtrowania typu plikow dla funkcji import/export

// pliki rozszerzajace klase FileFilter musza implementowac metody: accept() i getDescription()
public class PersonFileFilter extends FileFilter {

	// funkcja okresla kiedy plik bedzie widoczny dla wyszukiwarki plikow (zwraca wtedy true)
	@Override
	public boolean accept(File file) {
		
		//Aby byly widoczne foldery podczas przegladania z uzyciem tego filtra
		if(file.isDirectory()) {
			return true;
		}
		
		String name = file.getName();
		
		String extension = Utils.getFileExtension(name);
		
		if(extension == null) {
			return false;
		}
		
		if(extension.contentEquals("per")) {
			return true;
		}
		
		
		return false;
	}

	
	// ustawia opis w oknie otwierania plikow - dotyczacy jakie pliki sa dostepne
	@Override
	public String getDescription() {
		return "Person database files (*.per)";
	}

}
