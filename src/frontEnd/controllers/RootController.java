/**
 * 
 */
package frontEnd.controllers;

import java.io.IOException;

import frontEnd.designs.classes.Checkin;
import frontEnd.designs.classes.Checkout;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Ian
 * @date Jul 26, 2015
 * @project library_system
 * @todo TODO
 */
public class RootController {

	public AnchorPane sideBar;
	public Button bookCheckin;
	public Button bookCheckout;
	public Button search;
	public Button administration;
	public StackPane contentPane;
	
	public void btnHovered(Button btn){
		InnerShadow shadow = new InnerShadow(10, Color.valueOf("#1919FF"));
		shadow.setHeight(175.0);
		shadow.setWidth(175.0);
		btn.setEffect(shadow);
	}
	
	public void btnPressed(Button btn){
		InnerShadow shadow = new InnerShadow(10, Color.valueOf("#000000"));
		shadow.setHeight(175.0);
		shadow.setWidth(175.0);
		btn.setEffect(shadow);
	}
	
	public void checkinHovered(){
		btnHovered(bookCheckin);
	}
	public void checkoutHovered(){
		btnHovered(bookCheckout);
	}
	public void searchHovered(){
		btnHovered(search);
	}
	public void adminHovered(){
		btnHovered(administration);
	}
	
	public void checkinNotHovered(){
		bookCheckin.setEffect(null);
	}
	public void checkoutNotHovered(){
		bookCheckout.setEffect(null);
	}
	public void searchNotHovered(){
		search.setEffect(null);
	}
	public void adminNotHovered(){
		administration.setEffect(null);
	}
	
	public void checkinPressed(){
		btnPressed(bookCheckin);
	}
	
	public void checkoutPressed(){
		btnPressed(bookCheckout);
	}
	
	public void searchPressed(){
		btnPressed(search);
	}
	
	public void adminPressed(){
		btnPressed(administration);
	}
	
	public void checkinReleased() {
		btnHovered(bookCheckin);
		showCheckinMenu();
	}
	
	/**
	 * 
	 */
	private void showCheckinMenu() {
		contentPane.getChildren().clear();
		Checkin toShow = null;
		try {
			toShow = new Checkin();
			toShow.value.setPrefWidth(contentPane.getWidth());
			toShow.value.setPrefHeight(contentPane.getHeight());
			AnchorPane.setLeftAnchor(toShow.value.getChildren().get(0), (contentPane.getWidth()-305)/2);
			AnchorPane.setTopAnchor(toShow.value.getChildren().get(0), (contentPane.getHeight()-200)/2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.getChildren().add(toShow.value);
	}

	public void checkoutReleased() {
		btnHovered(bookCheckout);
		showCheckoutMenu();
	}
	
	private void showCheckoutMenu() {
		contentPane.getChildren().clear();
		Checkout toShow = null;
		try {
			toShow = new Checkout();
			toShow.value.setPrefWidth(contentPane.getWidth());
			toShow.value.setPrefHeight(contentPane.getHeight());
			AnchorPane.setLeftAnchor(toShow.value.getChildren().get(0), (contentPane.getWidth()-305)/2);
			AnchorPane.setTopAnchor(toShow.value.getChildren().get(0), (contentPane.getHeight()-200)/2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.getChildren().add(toShow.value);
	}
	
	public void searchReleased() {
		btnHovered(search);
		System.out.println("search");
	}
	
	public void adminReleased() {
		btnHovered(administration);
		System.out.println("admin");
	}
}
