/**
 * 
 */
package frontEnd.controllers;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.json.simple.parser.ParseException;

import application.administration.CheckoutManagement;
import application.apps.Finder;
import dbModels.auth.Person;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Ian
 * @date Aug 1, 2015
 * @project library_system
 * @todo TODO
 */
public class CheckoutController implements Initializable {

	public TextField isbnField;
	public TextField returneeNameField;
	public Button submit;
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {}

	public void checkinPressed(){
		try {
			String firstName = returneeNameField.getText().split("\\s")[0];
			String lastName = returneeNameField.getText().split("\\s")[1];
			Person person;
			try {
				person = Finder.searchPersonInfo("firstName='" + firstName + "' and lastName='" + lastName + "'").get(0);
				CheckoutManagement.returnBook(person, isbnField.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	public void checkoutPressed(){
		try{
			String firstName = returneeNameField.getText().split("\\s")[0];
			String lastName = returneeNameField.getText().split("\\s")[1];
			Person person;
			try {
				person = Finder.searchPersonInfo("firstName='" + firstName + "' and lastName='" + lastName + "'").get(0);
				CheckoutManagement.checkoutBook(person, isbnField.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
}
