/**
 * 
 */
package application;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import application.administration.CheckoutManagement;
import application.apps.Finder;
import application.apps.Storage;
import dbModels.Author;
import dbModels.Book;
import dbModels.Desk;
import dbModels.auth.Employee;
import dbModels.auth.Person;

/**
 * @author Ian
 * @date Jun 21, 2015
 * @project library_system
 * @todo TODO
 */
public class Main {

	public static Person user = null;
	public static Connection connection;
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, ParseException{
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "saline54");
		while(user == null){
			user = ConsoleController.login();
		}
		while(true){
			ConsoleController.mainMenu();
		}
	}
	
	public static void checkinBook(String isbn){
		try {
			CheckoutManagement.returnBook(user, isbn);
		} catch (FileNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void checkoutBook(String isbn){
		try {
			CheckoutManagement.checkoutBook(user, isbn);
		} catch (FileNotFoundException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
