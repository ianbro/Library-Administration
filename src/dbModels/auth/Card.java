/**
 * 
 */
package dbModels.auth;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import application.Main;
import application.administration.CheckoutManagement;

import com.mysql.fabric.xmlrpc.base.Array;

import dbModels.Book;

/**
 * @author Ian
 * @date Jun 22, 2015
 * @project library_system
 * @todo TODO
 */
public class Card {

	public int id;
	public Date expireDate;
	public LinkedList<Book> books;
	
	public void save(Connection con) throws SQLException{
		Statement statement = con.createStatement();
		statement.execute("insert into library.card (id, expiredDate) values (" + this.id + ", '" + this.expireDate.toString() + "');");
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
		statement.execute("update library.card " + updates + " where id=" + this.id + ";");
	}
	
	public static Card get(int id) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select expireDate from library.card where id=" + id + ";");
		myResult.next();
		Card retVal = new Card();
		retVal.id = id;
		retVal.expireDate = myResult.getDate("expireDate");
		
		return retVal;
	}
	
	public void retrieveBooksCheckedOut() throws ParseException, FileNotFoundException, SQLException{
		books = new LinkedList<Book>();
		Scanner jsonReader = new Scanner(CheckoutManagement.RECORDS);
		String json = "";
		while(jsonReader.hasNext()){
			json = json.concat(jsonReader.nextLine());
		}
		
		JSONParser parser = new JSONParser();
		JSONObject wholeJSON = (JSONObject) parser.parse(json);
		JSONArray personsBooks = (JSONArray) wholeJSON.get(String.valueOf(this.id));
		if(personsBooks == null){
			return;
		}
		for(Object book: personsBooks){
			String isbn = (String)book;
			books.add(Book.get(isbn));
		}
	}
}
