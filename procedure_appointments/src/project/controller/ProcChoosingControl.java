package project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;
import project.domain.mediator.Manager;
import project.domain.model.Appointment;
import project.domain.model.Patient;
import project.domain.model.Procedure;
import project.domain.model.Speleotherapy;
import project.view.AgendaScene;
import project.view.SettingsScene;

public class ProcChoosingControl {

	// CLASS AND DEFAULT VARIABLES
	private Manager manager;

	// FXML INSTANCE VARIABLES
	@FXML
	private TextField personIdField;
	@FXML
	private Button moreInfoButton;
	@FXML
	private Label fullNameLabel;
	@FXML
	private Label genderLabel;
	@FXML
	private Label clientTypeLabel;
	@FXML
	private CheckBox chk1;
	@FXML
	private CheckBox chk2;
	@FXML
	private CheckBox chk3;
	@FXML
	private CheckBox chk4;
	@FXML
	private CheckBox chk5;
	@FXML
	private CheckBox chk6;
	@FXML
	private CheckBox chk7;
	@FXML
	private CheckBox chk8;
	@FXML
	private CheckBox chk9;
	@FXML
	private CheckBox chk10;
	@FXML
	private CheckBox chk11;
	@FXML
	private CheckBox chk12;
	@FXML
	private CheckBox chk13;
	@FXML
	private CheckBox chk14;
	@FXML
	private CheckBox chk15;
	@FXML
	private CheckBox chk16;
	@FXML
	private CheckBox chk17;
	@FXML
	private CheckBox chk18;
	@FXML
	private CheckBox chk19;
	@FXML
	private CheckBox chk20;
	@FXML
	private CheckBox chk21;
	@FXML
	private Spinner<Integer> spinner;
	@FXML
	private Button generateButton;

	public ProcChoosingControl(Manager manager) {
		this.manager = manager;
	}

	@FXML
	public void initialize() {
		addPersonIdNumChangeListener();
		initSpinner();
	}

	private void loginDialog(String text) {
		
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText(text);


		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals("admin")) {
				loginButton.setDisable(newValue.trim().isEmpty());
			} else {
				loginButton.setDisable(true);
			}
		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
		    validate(usernamePassword.getValue());
		});
	}
	
	private void validate(String password) {
		if(password.equals("abrakadabra2357")) {
			try {
				openSettings();
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			loginDialog("wrong password, try again");
		}
	}
	
	private void openSettings() throws IOException, SQLException {
		Stage stage = new Stage();
		SettingsControl control = new SettingsControl(manager);
		stage.setScene(SettingsScene.getScene(control));
		stage.setTitle("Admin Pane");
		stage.setResizable(false);
		stage.show();
	}
	
	private void addPersonIdNumChangeListener() {

		personIdField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String t, String t1) {
				if (t1.length() >= 9) {
					Patient patient;
					try {
						patient = manager.searchForInPatients(Long.parseLong(t1));
						setLabelsWithPatientInfo(patient.getFullname(), patient.getGender(), patient.getClientType());
					} catch (NumberFormatException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	// INITILIAZE SPINNER METHOD
	private void initSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
		spinner.setValueFactory(svf);
	}

	// IN DEVELOPMENT FUCKING CHECKBOXES, THEREFORE SO LONG, HAVE TO FIGURE OUT
	// BETTER WAY
	@FXML
	public ArrayList<Procedure> getChosenProcedures() throws SQLException {
		ArrayList<Procedure> chosenProcedures = new ArrayList<Procedure>();

		if (chk1.isSelected() == true) {
			String label = chk1.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk2.isSelected() == true) {
			String label = chk2.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk2.isSelected() == true) {
			String label = chk2.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk3.isSelected() == true) {
			String label = chk3.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk4.isSelected() == true) {
			String label = chk4.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk5.isSelected() == true) {
			String label = chk5.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk6.isSelected() == true) {
			String label = chk6.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk7.isSelected() == true) {
			String label = chk7.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk8.isSelected() == true) {
			String label = chk8.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk9.isSelected() == true) {
			String label = chk9.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk10.isSelected() == true) {
			String label = chk10.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk11.isSelected() == true) {
			String label = chk11.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk12.isSelected() == true) {
			String label = chk12.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk13.isSelected() == true) {
			String label = chk13.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk14.isSelected() == true) {
			String label = chk14.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk15.isSelected() == true) {
			String label = chk15.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk16.isSelected() == true) {
			String label = chk16.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk17.isSelected() == true) {
			String label = chk17.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk18.isSelected() == true) {
			String label = chk18.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk19.isSelected() == true) {
			String label = chk19.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk20.isSelected() == true) {
			String label = chk20.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		if (chk21.isSelected() == true) {
			String label = chk21.getText();
			chosenProcedures.add(manager.searchForInProcedures(label));
		}
		// SPELEOTHERAPY SPECIAL CASE
		if (spinner.getValue().intValue() != 0) {
			Speleotherapy speleoteraphy = (Speleotherapy) manager.searchForInProcedures("Speleoterapia");
			speleoteraphy.setCount(spinner.getValue());
			chosenProcedures.add(speleoteraphy);
		}
		return chosenProcedures;
	}

	// HELPER METHODS
	@FXML
	public void setLabelsWithPatientInfo(String fullname, String gender, String clientType) {
		fullNameLabel.setText(fullname);
		genderLabel.setText(gender);
		clientTypeLabel.setText(clientType);
	}

	// BUTTON LISTENERS
	@FXML
	void onGenerateClick(ActionEvent event) throws SQLException, IOException {
		ArrayList<Appointment> generatedAppointments = manager.generate(getChosenProcedures(), 
				manager.searchForInPatients(Long.parseLong(personIdField.getText())));
		Stage stage = new Stage();
    	stage.setScene(AgendaScene.getScene(generatedAppointments));
    	stage.show();
    	
	}

	@FXML
	void onMoreInfoClick(ActionEvent event) throws NumberFormatException, SQLException {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("More info");
		alert.setHeaderText(null);
		Patient patient = manager.searchForInPatients(Long.parseLong(personIdField.getText()));

		alert.setContentText("Fullname: " + patient.getFullname() + "\n" + "Gender: " + patient.getGender() + "\n"
				+ "ClienType: " + patient.getClientType() + "\n" + "Accomodation: " + patient.getAccomodation() + "\n"
				+ "Partnership: " + patient.getPartnership() + "\n" + "Age: " + patient.getAge() + "\n"
				+ "StayDuration: " + patient.getStayDuration() + "\n" + "AccomodationClientID: "
				+ patient.getAccomodationClientID() + "\n" + "PersonIdNum: " + patient.getPersonIdNum() + "\n"
				+ "DateOfArrival: " + patient.getDateOfArrival() + "\n" + "DateOfDeparture: "
				+ patient.getDateOfDeparture() + "\n");

		alert.showAndWait();
	}

	@FXML
	void onAdminPaneClick(ActionEvent event) throws IOException, SQLException {
		loginDialog("");
	}

}