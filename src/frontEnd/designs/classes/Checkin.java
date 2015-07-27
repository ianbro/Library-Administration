/**
 * 
 */
package frontEnd.designs.classes;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * @author Ian
 * @date Jul 26, 2015
 * @project library_system
 * @todo TODO
 */
public class Checkin {

	public AnchorPane value;
	
	public Checkin() throws IOException{
		this.value = FXMLLoader.load(getClass().getResource("/frontEnd/designs/fxml/Checkin.fxml"));
	}
}
