/**
 * 
 */
package frontEnd.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import application.apps.Finder;
import dbModels.auth.Employee;
import dbModels.auth.Person;
import frontEnd.designs.classes.Root;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 * @author Ian
 * @date Jul 30, 2015
 * @project library_system
 * @todo TODO
 */
public class LoginController implements Initializable{
	
	public FlowPane errorMessage;
	public Button login;
	public TextField username;
	public PasswordField password;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void loginPressed() {
		String usersName = username.getText();
		String usersPass = password.getText();
		
		Employee emp = null;
		try {
			emp = Finder.searchEmployeeInfo("username='" + usersName + "'").get(0);
			Person user = null;
			if(usersPass.equals(emp.password)){
				System.out.println("logged in");
				try {
					user = Person.get(emp.personid);
					Main.user = user;
					Main.mainScene.setRoot(new Root().value);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				password.clear();
				errorMessage.setVisible(true);
			}
		} catch (SQLException | IndexOutOfBoundsException e) {
			password.clear();
			errorMessage.setVisible(true);
		}
	}

}
