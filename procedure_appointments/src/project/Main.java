package project;

import javafx.application.Application;
import javafx.stage.Stage;
import project.controller.ProcChoosingControl;
import project.domain.mediator.Manager;
import project.domain.mediator.ModelManager;
import project.view.ProcChoosingScene;


public class Main extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception {
		Manager manager = new ModelManager();
		ProcChoosingControl procChoosingControl = new ProcChoosingControl(manager);
		primaryStage.setScene(ProcChoosingScene.getScene(procChoosingControl));
		primaryStage.setTitle("Procedure Appointments ALPHA 1.0");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
