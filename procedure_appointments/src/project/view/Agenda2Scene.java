package project.view;

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
import project.domain.model.Appointment;

public class Agenda2Scene {

	private final static TreeView<String> treeView = new TreeView<>();
	private static ObservableList<Appointment> appoints = FXCollections.observableArrayList();

	private ListView<Appointment> appointList;

	public Scene getScene() {

		// Select the root node
		treeView.getSelectionModel().selectFirst();
		// Create the root node and adds event handler to it
		TreeItem<String> rootItem = new TreeItem<>("Datum");
		// Add children to the root
		//rootItem.getChildren().add(test);
		// event listener changing listviews
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue) {
		
				appointList.setItems(appoints);
			}

		});
		// Set the Root Node
		treeView.setRoot(rootItem);

		// List view
		appointList = new ListView<Appointment>();
		appointList.setItems(appoints);
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
		Appointment selectItem = appointList.getSelectionModel().getSelectedItem();
		int swapIndex = selectIndex - 1;
		Appointment swapItem = appointList.getItems().get(swapIndex);
		appointList.getItems().set(selectIndex, swapItem);
		appointList.getItems().set(swapIndex, selectItem);
		appointList.getSelectionModel().select(swapIndex);
	}

	private void down() {
		int selectIndex = appointList.getSelectionModel().getSelectedIndex();
		Appointment selectItem = appointList.getSelectionModel().getSelectedItem();
		int swapIndex = selectIndex + 1;
		Appointment swapItem = appointList.getItems().get(swapIndex);
		appointList.getItems().set(selectIndex, swapItem);
		appointList.getItems().set(swapIndex, selectItem);
		appointList.getSelectionModel().select(swapIndex);
	}

}
