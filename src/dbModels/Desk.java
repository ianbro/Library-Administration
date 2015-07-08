/**
 * 
 */
package dbModels;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.Main;

/**
 * @author Ian
 * @date Jun 26, 2015
 * @project library_system
 * @todo TODO
 */
public class Desk {

	public int id;
	public String name;
	
	public static Desk get(int id) throws SQLException{
		Statement statement = Main.connection.createStatement();
		ResultSet myResult = statement.executeQuery("select * from library.desk where id=" + id + ";");
		Desk retVal = new Desk();
		myResult.next();
		retVal.id = id;
		retVal.name = myResult.getString("name");
		
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
		statement.execute("update library.desk " + updates + " where id=" + this.id + ";");
	}
	
	public void save() throws SQLException{
		Statement statement = Main.connection.createStatement();
		statement.execute("insert into library.desk (id, name) values (" + this.id + ", '" + this.name + "');");
	}
}
