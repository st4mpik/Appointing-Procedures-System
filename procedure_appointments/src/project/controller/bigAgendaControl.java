package project.controller;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Callback;
import project.domain.mediator.Manager;
import project.domain.mediator.TodayAppointListForView;
import project.domain.model.Appointment;
import project.view.Agenda2Scene;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.awt.print.PrinterJob;

public class bigAgendaControl {

	@FXML
	private VBox downVBox;
	
	private ArrayList<TodayAppointListForView> generatedNewList;
	private Manager manager;
	

	public bigAgendaControl(ArrayList<TodayAppointListForView> arrayList, Manager manager) {
		this.generatedNewList = arrayList;
		this.manager = manager;
	}
	
	public void initialize() {
		for(int i = 0; i < generatedNewList.size(); i++) {
			downVBox.getChildren().addAll(hboxFactory(generatedNewList.get(i)));
		}
		Button print = new Button("Tlacit");
		print.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ListView<Appointment> printList = new ListView<>();
				ArrayList<Appointment> appointList = new ArrayList<>();
				for(int i = 0; i < generatedNewList.size(); i++) {
					for(int j = 0; j < generatedNewList.get(i).getThisdayList().size(); j++) {
						appointList.add(generatedNewList.get(i).getThisdayList().get(j));
					}
				}
				ObservableList<Appointment> dataPrint = FXCollections.observableArrayList(appointList);
				printList.setItems(dataPrint);
				Printer printer = Printer.getDefaultPrinter();
		        PageLayout pageLayout = printer.createPageLayout(Paper.NA_LETTER, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
		        double scaleX = pageLayout.getPrintableWidth() / printList.getBoundsInParent().getWidth();
		        double scaleY = pageLayout.getPrintableHeight() / printList.getBoundsInParent().getHeight();
		        printList.getTransforms().add(new Scale(scaleX, scaleY));
		 
		        javafx.print.PrinterJob job = javafx.print.PrinterJob.createPrinterJob();
		        if (job != null) {
		            boolean success = job.printPage(printList);
		            if (success) {
		                job.endJob();
		            }
		        }
		        for(int x = 0; x < appointList.size(); x++) {
		    		try {
						manager.addAppointment(appointList.get(x));
						//manager.updatePatientStatus(appointList.get(x).getPatientNum(), "progress");
					} catch (SQLException e) {
						
					}
		        }
			}
		});
		downVBox.getChildren().add(print);
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
			        				"" + list.getSelectionModel().getSelectedItem().getPatientNum() + "\n" +
			        				"" + list.getSelectionModel().getSelectedItem().getIntervalOfAppointment().getStart() + "\n" +
			        				"" + list.getSelectionModel().getSelectedItem().getIntervalOfAppointment().getEnd() + "\n");

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
		LocalTime previosLcEnd = LocalTime.of(8, 0);
		@Override
		public void updateItem(Appointment item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				String color = "white";
				if(item.getProcedureName().equals("Ultrazvuk")) {
					color = "ALICEBLUE";
				}
				if(item.getProcedureName().equals("Magnet")) {
					color = "gold";
				}
				if(item.getProcedureName().equals("Diadynamic")) {
					color = "salmon";
				}
				if(item.getProcedureName().equals("Oxygenoterapia")) {
					color = "blue";
				}
				if(item.getProcedureName().equals("Laser")) {
					color = "blueviolet";
				}
				if(item.getProcedureName().equals("Virivka vodna")) {
					color = "darkgoldenrod";
				}
				if(item.getProcedureName().equals("Virivka vodna")) {
					color = "BURLYWOOD";
				}
				if(item.getProcedureName().equals("Galvan, RS prudy")) {
					color = "CHOCOLATE";
				}
				if(item.getProcedureName().equals("LTV")) {
					color = "CORAL";
				}
				if(item.getProcedureName().equals("Reflexna masaz")) {
					color = "CRIMSON";
				}
				if(item.getProcedureName().equals("Makke techniky")) {
					color = "DARKGREEN";
				}
				if(item.getProcedureName().equals("Perlickovy kupel")) {
					color = "DARKMAGENTA";
				}
				if(item.getProcedureName().equals("Hydromasazna vana")) {
					color = "FIREBRICK";
				}
				if(item.getProcedureName().equals("Skotsky postrek")) {
					color = "FUCHSIA";
				}
				if(item.getProcedureName().equals("Suchy zabal")) {
					color = "GREENYELLOW";
				}
				if(item.getProcedureName().equals("Masaz klasicka")) {
					color = "HONEYDEW";
				}
				if(item.getProcedureName().equals("Parafin, raselina")) {
					color = "KHAKI";
				}
				if(item.getProcedureName().equals("Biolampa")) {
					color = "LAVENDER";
				}
				if(item.getProcedureName().equals("Diathermia")) {
					color = "LIME";
				}
				if(item.getProcedureName().equals("Solux")) {
					color = "OLIVE";
				}
				if(item.getProcedureName().equals("Inhalacia")) {
					color = "ORANGERED";
				}
				if(item.getProcedureName().equals("Speleoterapia")) {
					color = "PEACHPUFF";
				}
				
				
				LocalTime lcstart = LocalTime.of(item.getIntervalOfAppointment().getStart().getHour(),
												item.getIntervalOfAppointment().getStart().getMin());
				LocalTime lcend = LocalTime.of(item.getIntervalOfAppointment().getEnd().getHour(),
						item.getIntervalOfAppointment().getEnd().getMin());
				int width = (int) MINUTES.between(lcend, lcstart) * 3;
				int padding = (int) MINUTES.between(previosLcEnd, lcstart) * 3;
						Insets insets = new Insets(0,0,0, padding);
				super.setPadding(insets);
				previosLcEnd = lcend;
				Rectangle rect = new Rectangle(width, 30);
				rect.setFill(Color.web(color));
				setGraphic(rect);
			}
		}
		
	}
	


}