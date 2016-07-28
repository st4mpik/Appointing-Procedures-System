package project.controller;

import java.sql.SQLException;
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
import project.domain.mediator.Manager;
import project.domain.mediator.TodayAppointListForView;
import project.domain.model.Appointment;
import project.view.Agenda2Scene;

public class bigAgendaControl {

	@FXML
	private VBox downVBox;
	
	private ArrayList<TodayAppointListForView> generatedNewList;
	private Manager manager;
	
	
	ObservableList<String> data = FXCollections.observableArrayList("chocolate", "salmon", "gold", "coral",
			"darkorchid", "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue", "blueviolet", "brown");

	public bigAgendaControl(ArrayList<TodayAppointListForView> arrayList, Manager manager) {
		this.generatedNewList = arrayList;
		this.manager = manager;
	}
	
	public void initialize() {
		for(int i = 0; i < generatedNewList.size(); i++) {
			downVBox.getChildren().addAll(hboxFactory(generatedNewList.get(i)));
		}
	}
	
	private ArrayList<HBox> hboxFactory(TodayAppointListForView todayAppointListForView) {
		ArrayList<HBox> hboxes = new ArrayList<HBox>();
		//System.out.println("I am here");

		/*
		  for(int i = 0; i < todayAppointListForView.getThisdayList().size(); i++) {
		
		
			*/
			ListView<Appointment> list = new ListView<Appointment>();
			list.setOrientation(Orientation.HORIZONTAL);
			
			list.setCellFactory(new Callback<ListView<Appointment>, ListCell<Appointment>>() {
				@Override
				public ListCell<Appointment> call(ListView<Appointment> list) {
					return new ColorRectCell();
				}
			});
			
			ObservableList<Appointment> data2 = FXCollections.observableArrayList(todayAppointListForView.getThisdayList());
			list.setItems(data2);
			
			list.setOnMouseClicked(new EventHandler<MouseEvent>() {

			        @Override
			        public void handle(MouseEvent event) {
			        	if (event.getButton() == MouseButton.SECONDARY) {
			        		Alert alert = new Alert(AlertType.INFORMATION);
			        		alert.setTitle("Information Dialog");
			        		alert.setHeaderText(null);
			        		alert.setContentText("" + list.getSelectionModel().getSelectedItem().getProcedureName() + "\n" +
			        				"" + list.getSelectionModel().getSelectedItem().getPatientNum() + "\n" 
			        				//"" + list.getSelectionModel().getSelectedItem().getIntervalOfAppointment().getStart() + "\n" +
			        				/*"" + list.getSelectionModel().getSelectedItem().getIntervalOfAppointment().getEnd() + "\n"*/);

			        		alert.showAndWait();
			        	}
			        	 if(event.getButton().equals(MouseButton.PRIMARY)){
			                 if(event.getClickCount() == 2){
			                	Stage stage = new Stage();
			             		Agenda2Scene scene = new Agenda2Scene();
			             		try {
									stage.setScene(scene.getScene(manager, list.getSelectionModel().getSelectedItem().getProcedureName(),
											list.getSelectionModel().getSelectedItem()));
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			             		stage.setTitle("Change");
			             		stage.setResizable(false);
			             		stage.show();
			                 }
			             }
			        }
			    });
			Insets insets = new Insets(0);
			list.setPadding(insets);
			Label label = new Label(todayAppointListForView.getDay().toString());
			label.setMinWidth(125);
			Insets insetsTop = new Insets(15,0,0,10);
			label.setPadding(insetsTop);
			list.setPrefWidth(2400);
			list.setPrefHeight(50);
			HBox hboxForListAndLabel = new HBox(label, list);
			hboxes.add(hboxForListAndLabel);
	
			return hboxes;
		}
		


	static class ColorRectCell extends ListCell<Appointment> {
		@Override
		public void updateItem(Appointment item, boolean empty) {
			super.updateItem(item, empty);
			
			Insets insets = new Insets(0,0,0,10);
			super.setPadding(insets);
			if (item != null) {
				String color = "white";
				if(item.getProcedureName().equals("Oxygenoterapia")) {
					color = "chocolate";
				}
				if(item.getProcedureName().equals("LTV")) {
					color = "gold";
				}
				if(item.getProcedureName().equals("Lymfodrenaz")) {
					color = "salmon";
				}
				if(item.getProcedureName().equals("Biolampa")) {
					color = "blue";
				}
				if(item.getProcedureName().equals("Solux")) {
					color = "blueviolet";
				}
				if(item.getProcedureName().equals("Perlickovy kupel")) {
					color = "darkgoldenrod";
				}
				int width = Math.abs(item.getIntervalOfAppointment().getEnd().getMin() - item.getIntervalOfAppointment().getStart().getMin() * 3 );
				System.out.println(width);
				Rectangle rect = new Rectangle(width, 30);
				rect.setFill(Color.web(color));
				setGraphic(rect);
			}
		}
		
	}
	


}