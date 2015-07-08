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
import dbModels.auth.Employee;
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
		ResultSet myResult = statement.executeQuery("select isbn from library.book where " + condition + ";");
		ArrayList<Book> results = new ArrayList<Book>();
		while(myResult.next()){
			System.out.println(1);
			results.add(Book.get(myResult.getString("isbn")));
		}
		return results;
	}
	
	public static ArrayList<Author> searchAuthorInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select id from library.author where " + condition + ";");
		ArrayList<Author> results = new ArrayList<Author>();
		while(myResult.next()){
			System.out.println(1);
			results.add(Author.get(myResult.getInt("id")));
		}
		return results;
	}
	
	public static ArrayList<Employee> searchEmployeeInfo(String condition) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select id from library.employee where " + condition + ";");
		ArrayList<Employee> results = new ArrayList<Employee>();
		while(myResult.next()){
			System.out.println(1);
			results.add(Employee.get(myResult.getInt("id")));
		}
		return results;
	}
}
