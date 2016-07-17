package project.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import project.controller.AgendaControl;

public class AgendaScene {

	public static Scene getScene(AgendaControl agencontrol) throws IOException, SQLException {

		FXMLLoader loader = new FXMLLoader(AgendaScene.class.getResource("agendaGUI.fxml"));
		loader.setController(agencontrol);

		Pane mainPane = (Pane) loader.load();
		return new Scene(mainPane);

	}
}
