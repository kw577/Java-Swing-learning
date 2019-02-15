package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// klasa testowa !!!

public class Database {
	
	private List<Person> people;
	private Connection con;
	
	
	public Database() {
		people = new LinkedList<Person>();
	}
	
	
	public void connect() throws Exception {
		
		// jesli juz istnieje polaczenie z baza
		if(con != null) return;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Exception("Driver not found");
		}
		
		
		String connectionUrl = "jdbc:mysql://localhost:3306/swingtest";
		String user = "root";
		String password = "";
		con = DriverManager.getConnection(connectionUrl, user, password);
		System.out.println("Connected");
	}
	
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't close connection");
			}
		}
	}
	
	
	//zapisywanie danych do bazy
	public void save() throws SQLException {
		
		// sprawdzenie czy uzytkownik jest juz w bazie
		String checkSQL = "SELECT count(*) AS count FROM people WHERE id=?";
		PreparedStatement checkStmt = con.prepareStatement(checkSQL);
		
		// komenda dodawania nowego rekordu do bazy
		// ? oznaczaja parametry zapytania (tu atrybuty) takie podejscie zapobiega sql injection
		String insertSql = "INSERT INTO people (id, name, age, employment_status, tax_id, us_citizen, gender, occupation) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		
					
		
		// komenda do update'u rekordu
		String updateSql = "UPDATE people SET name=?, age=?, employment_status=?, tax_id=?, us_citizen=?, gender=?, occupation=? WHERE id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		
		
		
		for(Person person: people) {
			int id = person.getId();
			String name = person.getName();
			String occupation = person.getOccupation();
			AgeCategory age = person.getAgeCategory();
			EmploymentCategory emp = person.getEmpCat();
			String tax = person.getTaxId();
			boolean isUs = person.isUsCitizen();
			Gender gender = person.getGender();
		
			
			checkStmt.setInt(1, id); // zamienia "?" na id okreslonej osoby
			
			
			//uzyskanie odpowiedzi z bazy danych
			ResultSet checkResult = checkStmt.executeQuery();
			
			checkResult.next();
			int count = checkResult.getInt(1); // sprawdzenie ile jest uztkownikow o okreslonym id
			
			System.out.println("\nCount for person with ID = " + id + " is " + count);
			
			if(count == 0) {
				System.out.println("Inserting person with ID = " + id);
				
				// dodawanie osoby do bazy danych (gdy w bazie nie ma osoby o takim id (UWAGA sprawdzane jest tylko id zeby stweirdzic czy dana osoba jes w bazie))
				insertStatement.setInt(1, id); // zamieni pierwszy znak "?" na wartosc = id
				insertStatement.setString(2, name);
				insertStatement.setString(3, age.name());
				insertStatement.setString(4, emp.name());
				insertStatement.setString(5, tax);
				insertStatement.setBoolean(6, isUs);
				insertStatement.setString(7, gender.name());
				insertStatement.setString(8, occupation);
	
				
				insertStatement.executeUpdate();
				
			}
			else {
				// update danych osoby gdy jest ana juz w bazie (UWAGA sprawdzane jest tylko id zeby stweirdzic czy dana osoba jest w bazie))
				System.out.println("Updating person with ID = " + id);
				updateStatement.setString(1, name);
				updateStatement.setString(2, age.name());
				updateStatement.setString(3, emp.name());
				updateStatement.setString(4, tax);
				updateStatement.setBoolean(5, isUs);
				updateStatement.setString(6, gender.name());
				updateStatement.setString(7, occupation);
				updateStatement.setInt(8, id);
				
				updateStatement.executeUpdate();
				
			}
			
		}
		
		updateStatement.close();
		insertStatement.close();
		checkStmt.close();
		
	}
	
	
	public void load() throws SQLException {
		people.clear();
		
		String sql = "SELECT id, name, age, employment_status, tax_id, us_citizen, gender, occupation FROM people ORDER BY name";
		Statement selectStatement = con.createStatement();
		ResultSet results = selectStatement.executeQuery(sql);
		
		
		while(results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String emp = results.getString("employment_status");
			String tax = results.getString("tax_id");
			boolean isUs = results.getBoolean("us_citizen");
			String gender = results.getString("gender");
			String occ = results.getString("occupation");
			
			//System.out.println(isUs);
			
			people.add(new Person(id, name, occ, AgeCategory.valueOf(age), 
					EmploymentCategory.valueOf(emp), tax, isUs, 
					Gender.valueOf(gender)));
			
			
			
		}
		
		
		results.close();
		selectStatement.close();
	}
	
	
	public void addPerson(Person person) {
		people.add(person);
	}
	
	// zwraca liste osob w bazie
	public List<Person> getPeople(){
		return Collections.unmodifiableList(people);
	}
	
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] persons = people.toArray(new Person[people.size()]);
	
		oos.writeObject(persons);
		
		oos.close();
		
	}
	
	
	public void loadFromFile(File file) throws IOException {
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] persons = (Person[])ois.readObject();
			
			// wyczyszczenie aktualnej listy obiektow
			people.clear();
			
			// dodanie obiektow z pliku
			people.addAll(Arrays.asList(persons));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
		
		
	}

	public void removePerson(int index) {
		// TODO Auto-generated method stub
		people.remove(index);
	}
	
	
}
