package project.view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Agenda2Scene {



	private final static TreeView<String> treeView = new TreeView<>();
	private static final ObservableList<String> birds = FXCollections.observableArrayList("-black", "-blue", "-red",
			"-red-2", "-yellow", "s-green", "s-green-2");

	private ListView<String> birdList;

	public Scene getScene() {

		TreeItem<String> test = new TreeItem<String>("ahoj");
		// Select the root node
		treeView.getSelectionModel().selectFirst();
		// Create the root node and adds event handler to it
		TreeItem<String> rootItem = new TreeItem<>("Procedury");
		// Add children to the root
		rootItem.getChildren().add(test);
		// Set the Root Node
		treeView.setRoot(rootItem);

		// List view
		birdList = new ListView<>();
		birdList.setItems(birds);
				// VBox for Listview
		VBox appointBox = new VBox(birdList);
		appointBox.setPrefWidth(400);
		// Create the HBox
		HBox root = new HBox();
		// Set the horizontal space between each child in the HBox
		// Add the TreeView to the HBox
		root.getChildren().addAll(treeView, appointBox);

		// Create the Scene
		return new Scene(root, 600, 500);
	}

	
}
