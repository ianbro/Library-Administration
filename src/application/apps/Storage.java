/**
 * 
 */
package application.apps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Main;
import dbModels.Author;
import dbModels.Book;
import dbModels.auth.Card;
import dbModels.auth.Employee;
import dbModels.auth.Person;

/**
 * @author Ian
 * @date Jun 25, 2015
 * @project library_system
 * @todo TODO
 */
public abstract class Storage {

	public static ArrayList<Book> books = new ArrayList<Book>();
	
	public static ArrayList<Author> authors = new ArrayList<Author>();
	
	public static ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public static ArrayList<Person> people = new ArrayList<Person>();
	
	public static ArrayList<Card> cards = new ArrayList<Card>();
	
	public static String getAllStorage(){
		String retVal = "";
		
		for(Book b: books){
			retVal = retVal.concat(b.toString() + "\n");
		}
		for(Author a: authors){
			retVal = retVal.concat(a.toString() + "\n");
		}
		for(Employee e: employees){
			retVal = retVal.concat(e.toString() + "\n");
		}
		
		return retVal;
	}
	
	public static ArrayList<Person> getAllPeople() throws SQLException{
		Statement statement = Main.connection.createStatement();
		
		ResultSet myResult = statement.executeQuery("select id from library.person");
		
		if(people == null){
			people = new ArrayList<Person>();
		}
		
		while(myResult.next()){
			people.add(Person.get(myResult.getInt("id")));
		}
		
		return people;
	}
	
	public static ArrayList<Employee> getAllEmployees() throws SQLException{
		Statement statement = Main.connection.createStatement();
		
		ResultSet myResult = statement.executeQuery("select id from library.employee");
		
		if(employees == null){
			employees = new ArrayList<Employee>();
		}
		
		while(myResult.next()){
			employees.add(Employee.get(myResult.getInt("id")));
		}
		
		return employees;
	}
	
	public static ArrayList<Card> getAllCards() throws SQLException{
		Statement statement = Main.connection.createStatement();
		
		people = getAllPeople();
		
		cards = new ArrayList<Card>();
		for(Person p: people){
			cards.add(p.card);
		}
		
		return cards;
	}
	
	public static ArrayList<Author> getAllAuthors() throws SQLException{
		Statement statement = Main.connection.createStatement();
		
		ResultSet myResult = statement.executeQuery("select id from library.author");
		
		if(authors == null){
			authors = new ArrayList<Author>();
		}
		
		while(myResult.next()){
			System.out.println(myResult.getInt("id"));
			authors.add(Author.get(myResult.getInt("id")));
		}
		
		return authors;
	}
}
