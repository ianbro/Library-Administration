/**
 * 
 */
package frontEnd.designs.classes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

/**
 * @author Ian
 * @date Jul 30, 2015
 * @project library_system
 * @todo TODO
 */
public class Login {

	public BorderPane value;
	
	public Login() throws IOException{
		this.value = FXMLLoader.load(getClass().getResource("/frontend/designs/fxml/Login.fxml"));
	}
}
