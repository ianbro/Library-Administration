/**
 * 
 */
package frontEnd.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.fabric.xmlrpc.base.Data;

import application.apps.Finder;
import dbModels.Book;
import frontEnd.designs.classes.CatologueBookCard;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * @author Ian
 * @date Aug 10, 2015
 * @project library_system
 * @todo TODO
 */
public class CatologueController implements Initializable {
	
	public Button btnSearch;
	public TextField titleField;
	public TextField subjectField;
	public TextField publishedField;
	public TextField authorField;
	public TextField genraField;
	public VBox bookListDisplay;

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void searched(){
		bookListDisplay.getChildren().clear();
		String title = titleField.getText();
		String subject = subjectField.getText();
		String publishString = publishedField.getText();
		String authorName = authorField.getText();
		String genra = genraField.getText();
		
		String condition = "title like '%" + title + "%' and subject like '%" + subject + "%' and publishDate like '%" + publishString + "%' and (firstName like '%" + authorName + "%' or lastname like '%" + authorName + "%') and genra like '%" + genra + "%'";
		
		ArrayList<Book> results = new ArrayList<Book>();
		try {
			results = Finder.searchBookInfo(condition);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Book book: results){
			CatologueBookCard card;
			try {
				card = new CatologueBookCard(book);
				bookListDisplay.getChildren().add(card.value);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
