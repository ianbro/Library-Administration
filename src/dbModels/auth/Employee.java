/**
 * 
 */
package dbModels.auth;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Main;
import application.apps.Storage;

/**
 * @author Ian
 * @date Jun 26, 2015
 * @project library_system
 * @todo TODO
 */
public class Employee{

	public double salary;
	public String username;
	public String password;
	public int id;
	public int personid;
	
	public static Employee get(int id) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select * from library.employee where id=" + id + ";");
		Employee retVal = new Employee();
		myResult.next();
		retVal.salary = myResult.getDouble("salary");
		retVal.username = myResult.getString("username");
		retVal.password = myResult.getString("password");
		retVal.id = id;
		retVal.personid = myResult.getInt("personid");
		
		Storage.employees.add(retVal);
		
		System.out.println("loaded employee: " + Person.get(retVal.personid).firstName + " " + Person.get(retVal.personid).lastName + " succesfully.");
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
		statement.execute("update library.employee " + updates + " where id=" + this.id + ";");
	}
	
	public void save() throws SQLException{
		Statement statement = Main.connection.createStatement();
		this.id = Storage.getAllEmployees().size() + 1;
		statement.execute("insert into library.employee (salary, username, password, id, personid) values (" + this.salary + ", '" + this.username + "', '" + this.password + "', " + this.id + ", " + this.personid + ");");
	}
	
	public String toString(){
		Person p = null;
		try {
			p = Person.get(this.personid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.toString() + "(" + this.username + ")";
	}
	
	public double addToSalary(double amount){
		return this.salary += amount;
	}
}
