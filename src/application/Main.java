/**
 * 
 */
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import application.administration.CheckoutManagement;
import application.apps.Finder;
import application.apps.Storage;
import dbModels.Author;
import dbModels.Book;
import dbModels.Desk;
import dbModels.auth.Employee;
import dbModels.auth.Person;
import frontEnd.designs.classes.CatologueBookCard;
import frontEnd.designs.classes.Login;
import frontEnd.designs.classes.Root;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author Ian
 * @date Jun 21, 2015
 * @project library_system
 * @todo TODO
 */
public class Main extends Application {

	public static Person user = null;
	public static Connection connection;
	public static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			BorderPane root = new Login().value;
			mainScene = new Scene(root);
			primaryStage.setScene(mainScene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		primaryStage.show();
	}
	
	public static void main(String[] args) throws SQLException, FileNotFoundException, ParseException{
		String[] db = Settings.DATABASES[Settings.DB_INDEX];
		String dbConnection = "jdbc:mysql://" + db[3] + ":" + db[4] + "/" + db[0];
		System.out.println(dbConnection);
		connection = DriverManager.getConnection("jdbc:mysql://" + db[3] + ":" + db[4] + "/" + db[0], db[1], db[2]);
		launch(args);
	}
	
	public static void checkinBook(String isbn, Person customer){
		try {
			CheckoutManagement.returnBook(customer, isbn);
		} catch (FileNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void checkoutBook(String isbn, Person customer){
		try {
			CheckoutManagement.checkoutBook(customer, isbn);
		} catch (FileNotFoundException | ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
