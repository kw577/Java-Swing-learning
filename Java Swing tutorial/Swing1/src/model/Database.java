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
		
		for(Person person: people) {
			int id = person.getId();
			checkStmt.setInt(1, id); // zamienia "?" na id okreslonej osoby
			
			
			//uzyskanie odpowiedzi z bazy danych
			ResultSet checkResult = checkStmt.executeQuery();
			
			checkResult.next();
			int count = checkResult.getInt(1); // sprawdzenie ile jest uztkownikow o okreslonym id
			
			System.out.println("Count for person with ID = " + id + " is " + count);
			
		}
		
		
		checkStmt.close();
		
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
