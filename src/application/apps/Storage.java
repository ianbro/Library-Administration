/**
 * 
 */
package application.apps;

import java.util.ArrayList;

import dbModels.Author;
import dbModels.Book;
import dbModels.auth.Employee;

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
}
