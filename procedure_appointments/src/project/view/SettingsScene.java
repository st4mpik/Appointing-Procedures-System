package project.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import project.controller.SettingsControl;

public class SettingsScene {
	
	public static Scene getScene(SettingsControl controller) throws IOException, SQLException {

		FXMLLoader loader = new FXMLLoader(SettingsScene.class.getResource("settingsGui.fxml"));
		loader.setController(controller);
		Pane mainPane = (Pane) loader.load();
		return new Scene(mainPane);
	
	}
}
