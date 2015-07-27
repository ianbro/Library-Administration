/**
 * 
 */
package frontEnd.designs.classes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * @author Ian
 * @date Jul 27, 2015
 * @project library_system
 * @todo TODO
 */
public class Checkout {

public AnchorPane value;
	
	public Checkout() throws IOException{
		this.value = FXMLLoader.load(getClass().getResource("/frontEnd/designs/fxml/Checkout.fxml"));
	}
}
