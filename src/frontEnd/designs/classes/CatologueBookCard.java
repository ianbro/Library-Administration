/**
 * 
 */
package frontEnd.designs.classes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import dbModels.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Ian
 * @date Aug 2, 2015
 * @project library_system
 * @todo TODO
 */
public class CatologueBookCard {

	private Book book;
	public BorderPane value;
	public ListView<String> infoView;
	public Label title;
	public ImageView image;
	
	public CatologueBookCard(Book book) throws IOException{
		this.value = FXMLLoader.load(getClass().getResource("/frontEnd/designs/fxml/CatologueBookCard.fxml"));
		this.infoView = (ListView<String>) (this.value.getChildren().get(1));
		this.image = (ImageView) (this.value.getChildren().get(0));
		this.title = (Label) ((AnchorPane) (this.value.getChildren().get(2))).getChildren().get(0);
		this.book = book;
		
		this.setTitle(this.book.title);
		try{
			this.setImage(this.book.title + ".png");
		} catch(NullPointerException e){
			this.setImage("book_generic.png");
		}
		this.displayInfo();
	}
	
	public void setTitle(String title){
		this.title.setText(title);
	}
	
	public void setImage(String url){
		Image bookImage = new Image(this.getClass().getClassLoader().getResourceAsStream("resources/images/" + url));
		image.setImage(bookImage);
	}
	
	public void displayInfo(){
		String author = this.book.author.toString();
		String callNumber = "Call Number:  " + this.book.callNumber;
		String publishDate = this.book.publishDate.toString();
		String description = "Blurb:  " + this.book.description;
		
		String firstLine = "Author: " + author + ", Publised on: " + publishDate;
		ObservableList<String> info = FXCollections.observableArrayList(firstLine, description, callNumber);
		
		this.infoView.setItems(info);
	}
	
	public Book getBook(){
		return this.book;
	}
}
