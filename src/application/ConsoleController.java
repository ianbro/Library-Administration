/**
 * 
 */
package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import application.apps.Finder;
import dbModels.Author;
import dbModels.auth.Employee;
import dbModels.auth.Person;

/**
 * @author Ian
 * @date Jul 3, 2015
 * @project library_system
 * @todo TODO
 */
public abstract class ConsoleController {

	public static Scanner kbReader;
	
	public static Person login() throws SQLException{
		System.out.println("Username: ");
		kbReader = new Scanner(System.in);
		String username = kbReader.next();
		
		System.out.println("Password ");
		kbReader = new Scanner(System.in);
		String password = kbReader.next();
		
		Person retVal = Person.get(Integer.valueOf(username));
		
		return retVal;
	}
	
	public static void showMainMenu(){
		System.out.println("Main Menu(Type corosponding number):");
		System.out.println("1.	Book Checkin");
		System.out.println("2.	Book Checkout");
		System.out.println("3.	Book Search");
		System.out.println("4.	Administration");
	}
	
	public static void mainMenu(){
		showMainMenu();
		kbReader = new Scanner(System.in);
		int choice = kbReader.nextInt();
		
	
		runMenuChoice(choice);
	}
	
	public static void runMenuChoice(int choice){
		if(choice == 5){
			System.exit(1);
		}
		System.out.println("Choice: " + choice);
		
		switch(choice){
		case 1:
			Main.checkinBook(getISBN());
			break;
		case 2:
			Main.checkoutBook(getISBN());
			break;
		case 3:
			searchBook();
			break;
		case 4:
			showAdministrationMenu();
			break;
		default:
			System.out.println("Please enter a valid number 1-4.");
			break;
		}
	}
	
	public static void searchBook(){
		System.out.println("What would you like to search by(Type it out.)?");
		System.out.println("1.	Title");
		System.out.println("2.	Author");
		System.out.println("3.	Subject");
		System.out.println("4.	Genra");	
		
		kbReader = new Scanner(System.in);
		
		String varToSearchBy = kbReader.next().toLowerCase();
		
		System.out.println("Key word here.");
		
		kbReader = new Scanner(System.in);
		String keyWord = kbReader.nextLine();
		
		if(varToSearchBy.equals("author")){
			ArrayList<Author> authors = null;
			try {
				authors = Finder.searchAuthorInfo("lastName='" + (keyWord.split("\\s")[1]) +"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(Author a: authors){
				if(keyWord.equals(a.fulName())){
					keyWord = String.valueOf(a.id);
				}
			}
			varToSearchBy = "authorid";
		}
		else{
			keyWord = "'" + keyWord + "'";
		}
		
		String condition = varToSearchBy + "=" + keyWord;
		try {
			System.out.println(Finder.searchBookInfo(condition));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getISBN(){
		System.out.println("Please enter the isbn of the book");
		kbReader = new Scanner(System.in);
		return kbReader.next();
	}
	
	public static void showAdministrationMenu(){
		System.out.println("Administration Menu(Type corosponding number):");
		System.out.println("1.	Search for Employee");
		System.out.println("2.	Search for Desk");
		System.out.println("3.	New Employee");
		System.out.println("4.	New Customer");
		System.out.println("5.	New Book");
		System.out.println("6.	New Author");
		
		kbReader = new Scanner(System.in);
		int choice = kbReader.nextInt();
		
		runAdminChoice(choice);
	}
	
	public static void runAdminChoice(int choice){
		switch(choice){
		case 1:
			searchEmployee();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
			System.out.println("Please enter a valid number 1-6.");
		}
	}
	
	public static void searchEmployee(){
		System.out.println("Please enter the name of the employee.");
		kbReader = new Scanner(System.in);
		String name = kbReader.nextLine();
		
		String nameFirst = name.split("\\s")[0];
		String nameLast = name.split("\\s")[1];
		
		Person employeePerson = null;
		try {
			employeePerson = Finder.searchPersonInfo("lastName='" + nameLast + "' and firstName='" + nameFirst + "'").get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Employee emp = null;
		try {
			emp = Finder.searchEmployeeInfo("personid=" + employeePerson.id).get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(emp);
	}
	
	public static void searchDesk(){
		
	}
}
