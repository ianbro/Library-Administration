/**
 * 
 */
package application.apps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbModels.Author;
import dbModels.Book;
import dbModels.Desk;
import dbModels.auth.Employee;
import dbModels.auth.Person;
import application.Main;

/**
 * @author Ian
 * @date Jun 24, 2015
 * @project library_system
 * @todo TODO
 */
public abstract class Finder {

	public static ArrayList<Book> searchBookInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		String sql = "select isbn from library.book b join library.author a on a.id=b.authorid where " + condition + ";";
		System.out.println(sql);
		ResultSet myResult = statement.executeQuery(sql);
		ArrayList<Book> results = new ArrayList<Book>();
		while(myResult.next()){
			System.out.println("Loading book with ISBN: " + myResult.getString("isbn"));
			results.add(Book.get(myResult.getString("isbn")));
		}
		return results;
	}
	
	public static ArrayList<Author> searchAuthorInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select id from library.author where " + condition + ";");
		ArrayList<Author> results = new ArrayList<Author>();
		while(myResult.next()){
			System.out.println("Loading author with id: " + myResult.getInt("id"));
			results.add(Author.get(myResult.getInt("id")));
		}
		return results;
	}
	
	public static ArrayList<Employee> searchEmployeeInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select id from library.employee where " + condition + ";");
		ArrayList<Employee> results = new ArrayList<Employee>();
		while(myResult.next()){
			System.out.println("Loading employee with id: " + myResult.getInt("id"));
			results.add(Employee.get(myResult.getInt("id")));
		}
		return results;
	}
	
	public static ArrayList<Person> searchPersonInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select id from library.person where " + condition + ";");
		ArrayList<Person> results = new ArrayList<Person>();
		while(myResult.next()){
			System.out.println("Loading person with id: " + myResult.getInt("id"));
			results.add(Person.get(myResult.getInt("id")));
		}
		return results;
	}
	
	public static ArrayList<Desk> searchDeskInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select id from library.desk where " + condition + ";");
		ArrayList<Desk> results = new ArrayList<Desk>();
		while(myResult.next()){
			System.out.println("Loading desk with id: " + myResult.getInt("id"));
			results.add(Desk.get(myResult.getInt("id")));
		}
		return results;
	}
}
