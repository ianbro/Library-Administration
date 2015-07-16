/**
 * 
 */
package dbModels.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import application.Main;
import application.apps.Storage;

/**
 * @author Ian
 * @date Jun 22, 2015
 * @project library_system
 * @todo TODO
 */
public class Person {

	public int id;
	public String firstName;
	public String lastName;
	public Date birthDate;
	public String email;
	public Card card;
	
	public Person(int id, String firstName, String lastName, Date birthDate, String email, Card card){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.card = card;
	}
	
	public Person(String firstName, String lastName, Date date, String email, Card card) throws SQLException{
		this.id = Storage.getAllPeople().size() + 1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = date;
		this.email = email;
		this.card = card;
	}

	public void save() throws SQLException{
		Statement statement = Main.connection.createStatement();
		statement.execute("insert into library.person (id, firstName, lastName, birthDate, email, cardid) values ('" + this.id + "', '" + this.firstName + "', '" + this.lastName + "', '" + this.birthDate.toString() + "', '" + this.email + "', " + this.card.id + " );");
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
		statement.execute("update library.person " + updates + " where id=" + this.id + ";");
	}
	
	public static Person get(int id) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select * from library.person where id=" + id + ";");
		myResult.next();
		String firstName = myResult.getString("firstName");
		String lastName = myResult.getString("lastName");
		Date birthDate = myResult.getDate("birthDate");
		String email = myResult.getString("email");
		Card card = Card.get(myResult.getInt("cardid"));
		
		Person retVal = new Person(id, firstName, lastName, birthDate, email, card);
		return retVal;
	}
	
	public String toString(){
		return this.firstName + " " + this.lastName;
	}
}
