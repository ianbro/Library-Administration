/**
 * 
 */
package frontEnd.designs.classes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * @author Ian
 * @date Jul 26, 2015
 * @project library_system
 * @todo TODO
 */
public class Root {

	public BorderPane value;
	
	public Root() throws IOException {
		this.value = FXMLLoader.load(getClass().getResource("/frontEnd/designs/fxml/Root.fxml"));
	}
}
