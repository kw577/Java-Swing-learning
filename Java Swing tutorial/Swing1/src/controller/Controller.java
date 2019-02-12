package controller;

import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	
	Database db = new Database();
	
	public List<Person> getPeople(){
		return db.getPeople();
		}
	
	
	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCatId = ev.getAgeCategory();
		String empCat = ev.getEmpCat();
		boolean isUS = ev.isUsCitizen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		
		
		// w zaleznosci od wyboru opcji przez uzytkownika programu (formularz) ageCatId przyjmuje wartosci 0 1 lub 2
		switch(ageCatId) {
		case 0: 
			ageCategory = AgeCategory.child;
			break;
			
		case 1: 
			ageCategory = AgeCategory.adult;
			break;
			
		case 2: 
			ageCategory = AgeCategory.senior;
			break;
			
		}
		
		
		EmploymentCategory empCategory;
		
		if(empCat.equals("employed")) {
			empCategory = EmploymentCategory.emplyed;
			
		}
		else if(empCat.equals("self-employed")) {
			empCategory = EmploymentCategory.selfEmplyed;
			
		}
		else if(empCat.equals("unemployed")) {
			empCategory = EmploymentCategory.unemployed;
			
		}
		else {
			empCategory = EmploymentCategory.other;
			System.err.println(empCat);
			
		}
		
		Gender genderCat;
		
		if(gender.equals("male")) {
			genderCat = Gender.male;
		}
		else {
			genderCat = Gender.female;
		}
		
		Person person = new Person(name, occupation, ageCategory, 
				empCategory, taxId, isUS, genderCat);
		db.addPerson(person);
	}
	
}
