package project.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import project.controller.ProcChoosingControl;

public class ProcChoosingScene {
	
	public static Scene getScene(ProcChoosingControl controller) throws IOException, SQLException {

		FXMLLoader loader = new FXMLLoader(ProcChoosingScene.class.getResource("procChoosingGui.fxml"));
		loader.setController(controller);
		Pane mainPane = (Pane) loader.load();
		return new Scene(mainPane);
	
	}
}
