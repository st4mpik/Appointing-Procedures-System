package project.controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import project.domain.model.Appointment;
import project.view.Agenda2Scene;

public class bigAgendaControl {

	@FXML
	private VBox downVBox;
	
	private ArrayList<Appointment> generatedAppointments;
	
	ListView<String> list = new ListView<String>();
	ObservableList<String> data = FXCollections.observableArrayList("chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue", "blueviolet", "brown");

	//public bigAgendaControl(ArrayList<Appointment> generatedAppointments) {
	//	this.generatedAppointments = generatedAppointments;
	//}
	
	public void initialize() {
		list.setOrientation(Orientation.HORIZONTAL);
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		});
		list.setItems(data);
		
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent event) {
		        	if (event.getButton() == MouseButton.SECONDARY) {
		        		Alert alert = new Alert(AlertType.INFORMATION);
		        		alert.setTitle("Information Dialog");
		        		alert.setHeaderText(null);
		        		alert.setContentText("I have a great message for you!");

		        		alert.showAndWait();
		        	}
		        	 if(event.getButton().equals(MouseButton.PRIMARY)){
		                 if(event.getClickCount() == 2){
		                	Stage stage = new Stage();
		             		Agenda2Scene scene = new Agenda2Scene();
		             		stage.setScene(scene.getScene());
		             		stage.setTitle("Change");
		             		stage.setResizable(false);
		             		stage.show();
		                 }
		             }
		        }
		    });
		
		Insets insets = new Insets(0);
		list.setPadding(insets);
		Label label = new Label("10-16-2016");
		label.setMinWidth(100);
		list.setPrefWidth(3075);
		list.setPrefHeight(50);
		HBox hboxForListAndLabel = new HBox(label, list);
		downVBox.getChildren().add(hboxForListAndLabel);
	}

	static class ColorRectCell extends ListCell<String> {
		@Override
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			Insets insets = new Insets(0);
			super.setPadding(insets);
			int width = 50;
			Rectangle rect = new Rectangle(width, 20);
			if (item != null) {
				rect.setFill(Color.web(item));
				setGraphic(rect);
			}
		}
	}
	

}