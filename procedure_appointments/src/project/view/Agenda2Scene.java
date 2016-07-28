package project.view;


import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.domain.mediator.Manager;
import project.domain.model.Appointment;
import project.domain.model.Interval;
import project.domain.model.MyTime;

public class Agenda2Scene {

	private final static TreeView<String> treeView = new TreeView<>();

	private ListView<Interval> appointList;

	public Scene getScene(Manager manager, String procedureName, Appointment appointment) throws SQLException {
		ArrayList<Interval> intervals = manager.searchForInProcedures(procedureName).getFreeIntervals();
		
		int x = 0;
		for(int i = 0; i < intervals.size(); i++) {
			System.out.println(intervals.get(i).toString());
			System.out.println("------------");
			System.out.println(appointment.getIntervalOfAppointment().toString());
			if(intervals.get(i).getStart().equals((appointment.getIntervalOfAppointment().getStart()))) {
				System.out.println("i am here " + x ); 
				x = i;
			}
		}
		ObservableList<Interval> appoints =  FXCollections.observableArrayList(intervals);
		// Select the root node
		treeView.getSelectionModel().selectFirst();
		// Create the root node and adds event handler to it
		TreeItem<String> rootItem = new TreeItem<>(procedureName + ", " +  appointment.getDateOfAppointment());
		// Add children to the root
		//rootItem.getChildren().add(test);

		// Set the Root Node
		treeView.setRoot(rootItem);

		// List view
		appointList = new ListView<Interval>();
		appointList.setItems(appoints);
		MyTime test = new MyTime(0, 0);
		appointList.getItems().get(x).setStart(test);
		appointList.getItems().get(x).setEnd(test);
		// VBox for Listview
		VBox appointBox = new VBox();
		appointBox.getChildren().add(appointList);
		appointBox.setPrefWidth(400);
		
		// Create the HBox
		HBox root = new HBox();
		// Set the horizontal space between each child in the HBox
		// Add the TreeView to the HBox
		root.getChildren().addAll(treeView, appointBox);
		Scene scene = new Scene(root, 600, 500);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case NUMPAD8: up(); break;
					case NUMPAD2: down(); break;
					default: break;

				}
			}
		});
		
		// Create the Scene
		return scene;
	}
	private void up() {
		int selectIndex = appointList.getSelectionModel().getSelectedIndex();
		Interval selectItem = appointList.getSelectionModel().getSelectedItem();
		int swapIndex = selectIndex - 1;
		Interval swapItem = appointList.getItems().get(swapIndex);
		appointList.getItems().set(selectIndex, swapItem);
		appointList.getItems().set(swapIndex, selectItem);
		appointList.getSelectionModel().select(swapIndex);
	}

	private void down() {
		int selectIndex = appointList.getSelectionModel().getSelectedIndex();
		Interval selectItem = appointList.getSelectionModel().getSelectedItem();
		int swapIndex = selectIndex + 1;
		Interval swapItem = appointList.getItems().get(swapIndex);
		appointList.getItems().set(selectIndex, swapItem);
		appointList.getItems().set(swapIndex, selectItem);
		appointList.getSelectionModel().select(swapIndex);
	}
	
}
