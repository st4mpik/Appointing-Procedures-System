package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import project.domain.mediator.Manager;

public class AgendaControl {

	// CLASS AND DEFAULT VARIABLES
	private Manager manager;

	// FXML INSTANCE VARIABLES
	@FXML
	private Button printButton;

	
	public AgendaControl(Manager manager) {
		this.manager = manager;
	}

	@FXML
	public void initialize() {
	
	}

	
	@FXML
	void onPrintClik(ActionEvent event) {

	}

}
