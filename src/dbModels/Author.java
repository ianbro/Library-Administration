/**
 * 
 */
package dbModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Main;
import application.apps.Storage;

/**
 * @author Ian
 * @date Jun 21, 2015
 * @project library_system
 * @todo TODO
 */
public class Author {

	public int id;
	public String firstName;
	public String lastName;
	public ArrayList<Book> books;
	
	public static Author get(int authorid) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select * from library.author where id=" + authorid + ";");
		
		Author retVal = new Author();
		myResult.next();
		retVal.id = myResult.getInt("id");
		retVal.firstName = myResult.getString("firstName");
		retVal.lastName = myResult.getString("lastName");
		
		if(!Storage.authors.contains(retVal)){
			Storage.authors.add(retVal);
		}
		
		System.out.println("Loaded author: " + retVal.firstName + " " + retVal.lastName + " successfully");
		return retVal;
	}
	
	public void update(ArrayList<Object[]> args) throws SQLException{
		String updates = "set ";
		for(Object[] update: args){
			if(update[1].getClass().getSimpleName().equals("String")){
				update[1] = "'" + update[1] + "'";
			}
			updates = updates.concat(update[0] + "=" + update[1]);
			if(args.indexOf(update) != (args.size()-1)){
				updates = updates.concat(", ");
			}
		}
		System.out.println(updates);
		
		Statement statement = Main.connection.createStatement();
		statement.execute("update library.author " + updates + " where id=" + this.id + ";");
	}
	
	public void retrieveBooks() throws SQLException{
		if(this.books == null){
			this.books = new ArrayList<Book>();
		}
		
		for(Book book: Storage.books){
			if(book.author.equals(this) && !this.books.contains(book)){
				this.books.add(book);
			}
		}
		
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select isbn from library.book where authorid=" + this.id);
		
		while(myResult.next()){
			Book book = Book.get(myResult.getString("isbn"));
			if(!this.books.contains(book)){
				this.books.add(book);
			}
		}
	}
	
	public void save() throws SQLException{
		Statement statement = Main.connection.createStatement();
		statement.execute("insert into library.author (id, firstName, lastName) values (1, '" + this.firstName + "', '" + this.lastName + "');");
		System.out.println("Saved successfully");
	}
	
	public String fulName(){
		return this.firstName + " " + this.lastName;
	}
	
	public String toString(){
		return this.firstName + " " + this.lastName;
	}
}
