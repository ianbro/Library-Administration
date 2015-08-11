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
	private static String databaseName;
	private static String dbUserName;
	private static String dbPassword;
	private static String dbPort;
	private static String dbDomain;
	
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
		getSettings();
		connection = DriverManager.getConnection(dbDomain + ":" + dbPort + "/" + databaseName, dbUserName, dbPassword);
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
	
	public static void getSettings(){
		try {
			Scanner settingsReader = new Scanner(new File("settings.txt"));
			while(settingsReader.hasNextLine()){
				String setting = settingsReader.nextLine();
				String varName = setting.split("\\s=\\s")[0];
				String varValue = setting.split("\\s=\\s")[1];
				setSetting(varName, varValue);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setSetting(String varName, String varValue){
		switch(varName){
		case "DB_PORT":
			dbPort = varValue;
			break;
		case "DB_USERNAME":
			dbUserName = varValue;
			break;
		case "DB_PASSWORD":
			dbPassword = varValue;
			break;
		case "DB_DOMAIN":
			dbDomain = varValue;
			break;
		case "SCHEMA_NAME":
			databaseName = varValue;
			break;
		default:
			System.out.println(varName + " is not a setting");
			break;	
		}
	}
}
