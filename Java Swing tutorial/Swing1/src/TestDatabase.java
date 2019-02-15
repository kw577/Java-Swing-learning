import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

// Testowanie bazy danych w osobnej klasie 


public class TestDatabase {

	public static void main(String[] args) {
		System.out.println("Running database test");
	
		Database db = new Database();
		
		try {
			db.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// dodanie 2 obiektow Person do listy
		db.addPerson(new Person("Joe", "builder", AgeCategory.adult, 
				EmploymentCategory.emplyed, "777777", true, Gender.male));
		
		
		db.addPerson(new Person("Sue", "artist", AgeCategory.senior, 
				EmploymentCategory.selfEmplyed, null, false, Gender.female));
		
		
		
		try {
			db.save();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnect();
	
	}
	
	

	
	
}
