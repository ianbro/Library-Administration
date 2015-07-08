/**
 * 
 */
package dbModels;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dbModels.auth.Card;
import application.Main;
import application.apps.Storage;

/**
 * @author Ian
 * @date Jun 21, 2015
 * @project library_system
 * @todo TODO
 */
public class Book {

	public String title;
	public String subject;
	public Author author;
	public String genra;
	public String isbn;
	public String callNumber;
	public Date publishDate;
	public String description;
	public Date dueDate;
	public Card owner;
	public int copies;
	
	public Book(String title, String subject, int authorid, String genra, String isbn, String callNumber, Date publishDate, String description, Date dueDate, int copies){
		this.title = title;
		this.subject = subject;
		try {
			this.author = Author.get(authorid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.genra = genra;
		this.isbn = isbn;
		this.callNumber = callNumber;
		this.publishDate = publishDate;
		this.description = description;
		this.dueDate = dueDate;
		this.copies = copies;
	}
	
	public static Book get(String isbnNumber) throws SQLException{
		
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select * from library.book where isbn='" + isbnNumber + "';");
		
		myResult.next();
		String title = myResult.getString("title");
		String subject = myResult.getString("subject");
		int authorid = myResult.getInt("authorid");
		String genra = myResult.getString("genra");
		String isbn = myResult.getString("isbn");
		String callNumber = myResult.getString("callNumber");
		Date publishDate = myResult.getDate("publishDate");
		String description = myResult.getString("description");
		Date dueDate = myResult.getDate("dueDate");
		int copies = myResult.getInt("copies");
		
		Book retVal = new Book(title, subject, authorid, genra, isbn, callNumber, publishDate, description, dueDate, copies);
		
		if(!Storage.books.contains(retVal)){
			Storage.books.add(retVal);
		}
			
		System.out.println("Loaded book: " + retVal.title + " successfully");
		return retVal;
	}
	
	public void update(ArrayList<Object[]> args) throws SQLException{
		String updates = "set ";
		for(Object[] update: args){
			if(update[1].getClass().getSimpleName().equals("String")){
				update[1] = "'" + update[1] + "'";
			}
			else if (update[1].getClass().getSimpleName().equals("Date")){
				update[1] = "'" + update[1] + "'";
			}
			updates = updates.concat(update[0] + "=" + update[1]);
			if(args.indexOf(update) != (args.size()-1)){
				updates = updates.concat(", ");
			}
		}
		System.out.println(updates);
		
		Statement statement = Main.connection.createStatement();
		
		String sql = "update library.book " + updates + " where isbn= '" + this.isbn + "';";
		System.out.println(sql);
		statement.execute(sql);
	}
	
	public void save() throws SQLException{
		Statement statement = Main.connection.createStatement();
		statement.execute("insert into library.book (title, subject, authorid, genra, isbn, callnumber, publishDate, description, dueDate, copies) values ('" + this.title + "', '" + this.subject + "', " + this.author.id + ", '" + this.genra + "', '" + this.isbn + "', '" + this.callNumber + "', '" + this.publishDate.toString() + "', '" + this.description + "', '" +this.dueDate.toString() + "', " + this.copies + " );");
		System.out.println("Saved successfully");
	}
	
	public String toString(){
		return this.title + "; " + this.author.firstName + " " + this.author.lastName + " (" + this.callNumber + ")";
	}
}
