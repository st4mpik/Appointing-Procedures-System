package project.view;
import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import project.controller.bigAgendaControl;


public class bigAgendaScene {
	
	public static Scene getScene(bigAgendaControl controller) throws IOException, SQLException {

		FXMLLoader loader = new FXMLLoader(bigAgendaScene.class.getResource("bigAgenda.fxml"));
		loader.setController(controller);
		Pane mainPane = (Pane) loader.load();
		return new Scene(mainPane);
	
	}
}