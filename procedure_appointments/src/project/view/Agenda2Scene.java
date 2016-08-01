package project.view;


import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import project.domain.mediator.Manager;
import project.domain.model.Appointment;
import project.domain.model.Interval;

public class Agenda2Scene {

	private final static TreeView<String> treeView = new TreeView<>();

	private ListView<Interval> appointList;

	public Scene getScene(Manager manager, String procedureName, Appointment appointment) throws SQLException {
		ArrayList<Interval> intervals = manager.searchForInProcedures(procedureName).getFreeIntervals();
		
		int gadzo = 0;
		for(int i = 0; i < intervals.size(); i++) {
			if(intervals.get(i).getStart().equals((appointment.getIntervalOfAppointment().getStart()))) {
				gadzo = i;
			}
		}
		final int x = gadzo;
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

		
		appointList.setCellFactory(new Callback<ListView<Interval>, ListCell<Interval>>() {
			@Override
			public ListCell<Interval> call(ListView<Interval> list) {
				return new ColorRectCell(x);
			}
		});
		// VBox for Listview
		VBox appointBox = new VBox();
		Button saveButton = new Button("Ulozit");
		appointBox.getChildren().add(appointList);
		appointBox.setPrefWidth(400);
		
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					//manager.deleteAppointment(appointmentId);
					manager.addAppointment(appointment);
				} catch (SQLException e) {
					
				}
			}
		});
		// Create the HBox
		HBox root = new HBox();
		// Set the horizontal space between each child in the HBox
		// Add the TreeView to the HBox
		root.getChildren().addAll(treeView, appointBox);
		Scene scene = new Scene(root, 600, 500);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(appointList.getSelectionModel().getSelectedIndex() == x) {
				switch (event.getCode()) {
					case NUMPAD8: up(); break;
					case NUMPAD2: down(); break;
					default: break;

				}
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
		appointList.getItems().get(selectIndex).setStart(swapItem.getStart());
		appointList.getItems().get(selectIndex).setEnd(swapItem.getEnd());
		appointList.getItems().set(selectIndex, swapItem);
		appointList.getItems().set(swapIndex, selectItem);
		appointList.getSelectionModel().select(swapIndex);
	}

	private void down() {
		int selectIndex = appointList.getSelectionModel().getSelectedIndex();
		Interval selectItem = appointList.getSelectionModel().getSelectedItem();
		int swapIndex = selectIndex + 1;
		Interval swapItem = appointList.getItems().get(swapIndex);
		appointList.getItems().get(selectIndex).setStart(swapItem.getStart());
		appointList.getItems().get(selectIndex).setEnd(swapItem.getEnd());
		appointList.getItems().set(selectIndex, swapItem);
		appointList.getItems().set(swapIndex, selectItem);
		appointList.getSelectionModel().select(swapIndex);
	}
	
	static class ColorRectCell extends ListCell<Interval> {
		int x = 0;
		public ColorRectCell(int x) {
			this.x = x;
		}
		@Override
		public void updateItem(Interval item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				String color = "GREEN";
				if(super.getIndex() == x) {
					color = "RED";
				}
				Rectangle rect = new Rectangle(100, 20);
				rect.setFill(Color.web(color));
				Text text = new Text("" + item.getStart() + "|" + item.getEnd());
				StackPane stack = new StackPane();
				stack.getChildren().addAll(rect, text);
				setGraphic(stack);
			}
		}
	}
}
