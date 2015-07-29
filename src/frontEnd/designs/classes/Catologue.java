/**
 * 
 */
package frontEnd.designs.classes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * @author Ian
 * @date Jul 27, 2015
 * @project library_system
 * @todo TODO
 */
public class Catologue {

	public BorderPane value;
	
	public Catologue() throws IOException {
		this.value = FXMLLoader.load(getClass().getResource("/frontEnd/designs/fxml/Catologue.fxml"));
	}
}
