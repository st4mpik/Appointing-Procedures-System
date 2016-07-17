package project.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import project.domain.mediator.Manager;
import project.domain.model.AppointmentTable;
import project.domain.model.Interval;
import project.domain.model.MyTime;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public class SettingsControl {

	// CLASS AND DEFAULT VARIABLES
	private Manager manager;

	// FXML INSTANCE VARIABLES
	@FXML
	private Button changeButtonPatient;
	@FXML
	private Button deleteButtonPatients;
	@FXML
	private Button addButtonPatient;
	@FXML
	private TextField fullNameField;
	@FXML
	private ChoiceBox<String> genderChoice;
	@FXML
	private TextField accomodationField;
	@FXML
	private ChoiceBox<String> clientTypeChoice;
	@FXML
	private ChoiceBox<String> partnershipChoice;
	@FXML
	private Spinner<Integer> ageSpinner;
	@FXML
	private Spinner<Integer> stayDurationSpinner;
	@FXML
	private TextField acClientIdField;
	@FXML
	private TextField personIdNumField;
	@FXML
	private DatePicker dateOfArrivalPicker;
	@FXML
	private DatePicker dateOfDeparturePicker;
	@FXML
	private TableView<Patient> patientsTableView;
	@FXML
	private TableColumn<Patient, String> fullnameCol;
	@FXML
	private TableColumn<Patient, String> genderCol;
	@FXML
	private TableColumn<Patient, String> clientTypeCol;
	@FXML
	private TableColumn<Patient, String> accomodationCol;
	@FXML
	private TableColumn<Patient, String> partnershipCol;
	@FXML
	private TableColumn<Patient, Number> ageCol;
	@FXML
	private TableColumn<Patient, Number> stayDurationCol;
	@FXML
	private TableColumn<Patient, Number> acClientIdCol;
	@FXML
	private TableColumn<Patient, Number> personIdNumCol;
	@FXML
	private TableColumn<Patient, LocalDate> dateOfArrivalCol;
	@FXML
	private TableColumn<Patient, LocalDate> dateOfDepartureCol;
	@FXML
	private Button changeButtonProcedure;
	@FXML
	private Button deleteButtonProcedure;
	@FXML
	private Button addButtonProcedure;
	@FXML
	private TextField procedureIdField;
	@FXML
	private TextField nameField;
	@FXML
	private ChoiceBox<String> departmentChoice;
	@FXML
	private Spinner<Integer> capacitySpinner;
	@FXML
	private Spinner<Integer> durationSpinner;
	@FXML
	private Spinner<Integer> procStartTimeHoursSpinner;
	@FXML
	private Spinner<Integer> procStartTimeMinSpinner;
	@FXML
	private Spinner<Integer> procEndTimeHoursSpinner;
	@FXML
	private Spinner<Integer> procEndTimeMinSpinner;
	@FXML
	private TableView<Procedure> proceduresTableView;
	@FXML
	private TableColumn<Procedure, Integer> procedureIdCol;
	@FXML
	private TableColumn<Procedure, String> nameCol;
	@FXML
	private TableColumn<Procedure, String> departmentCol;
	@FXML
	private TableColumn<Procedure, Number> capacityCol;
	@FXML
	private TableColumn<Procedure, Number> durationCol;
	@FXML
	private TableColumn<Procedure, MyTime> procStartTimeCol;
	@FXML
	private TableColumn<Procedure, MyTime> procEndTimeCol;
	@FXML
	private AnchorPane apEndTimeCol;
	@FXML
	private Button changeButtonAppointment;
	@FXML
	private Button deleteButtonAppointment;
	@FXML
	private Button addButtonAppointment;
	@FXML
	private TextField appointmentIdField;
	@FXML
	private TextField patientIdNumField;
	@FXML
	private TextField procedureNameField;
	@FXML
	private DatePicker appointmentDatePicker;
	@FXML
	private Spinner<Integer> apStartTimeHorusSpinner;
	@FXML
	private Spinner<Integer> apStartTimeMinsSpinner;
	@FXML
	private Spinner<Integer> apEndTimeHoursSpinner;
	@FXML
	private Spinner<Integer> apEndTimeMinsSpinner;
	@FXML
	private TableView<AppointmentTable> appointmentsTableView;
	@FXML
	private TableColumn<AppointmentTable, Long> appointmentIdCol;
	@FXML
	private TableColumn<AppointmentTable, Long> patientIdNumCol;
	@FXML
	private TableColumn<AppointmentTable, String> procedureNameCol;
	@FXML
	private TableColumn<AppointmentTable, LocalDate> appointmentDateCol;
	@FXML
	private TableColumn<AppointmentTable, LocalDate> apStartTimeCol;

	public SettingsControl(Manager manager) {
		this.manager = manager;
	}

	@FXML
	public void initialize() throws SQLException {
		initAllTables();
		initAllChoiceBoxes();
		initAllSpinners();
	}

	// INTIT METHODS FOR CHOICEBOXES
	private void initAllChoiceBoxes() {
		initChoiceBoxGender();
		initChoiceBoxClientType();
		initChoiceBoxPartnership();
		initChoiceBoxDepartment();
	}

	private void initChoiceBoxGender() {
		List<String> list = new ArrayList<String>();
		list.add("Muž");
		list.add("Žena");
		ObservableList<String> obList = FXCollections.observableList(list);
		genderChoice.getItems().clear();
		genderChoice.setItems(obList);
		genderChoice.getSelectionModel().selectFirst();
	}

	private void initChoiceBoxClientType() {
		List<String> list = new ArrayList<String>();
		list.add("U_DOSP");
		list.add("U_DETI");
		list.add("A");
		list.add("AD");
		list.add("ADD");
		list.add("B");
		list.add("FBLR");
		list.add("ND");
		ObservableList<String> obList = FXCollections.observableList(list);
		clientTypeChoice.getItems().clear();
		clientTypeChoice.setItems(obList);
		clientTypeChoice.getSelectionModel().selectFirst();
	}

	private void initChoiceBoxPartnership() {
		List<String> list = new ArrayList<String>();
		list.add("24");
		list.add("25");
		list.add("27");
		list.add("SAM");
		ObservableList<String> obList = FXCollections.observableList(list);
		partnershipChoice.getItems().clear();
		partnershipChoice.setItems(obList);
		partnershipChoice.getSelectionModel().selectFirst();
	}

	private void initChoiceBoxDepartment() {
		List<String> list = new ArrayList<String>();
		list.add("Rehabilitacia");
		list.add("Vodoliecba");
		list.add("Termoliecba / Fotoliecba / Inhalacia");
		list.add("Speleoterapia");
		ObservableList<String> obList = FXCollections.observableList(list);
		departmentChoice.getItems().clear();
		departmentChoice.setItems(obList);
		departmentChoice.getSelectionModel().selectFirst();
	}

	// INIT METHODS FOR SPINNERS
	private void initAllSpinners() {
		initAgeSpinner();
		initStayDurationSpinner();
		initCapacitySpinner();
		initDurationSpinner();
		initProcStartTimeHoursSpinner();
		initProcStartTimeMinSpinner();
		initProcEndTimeHoursSpinner();
		initProcEndTimeMinSpinner();
		initApStartTimeHoursSpinner();
		initApStartTimeMinSpinner();
		initApEndTimeHoursSpinner();
		initApEndTimeMinSpinner();
	}

	private void initAgeSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99);
		ageSpinner.setValueFactory(svf);
	}

	private void initStayDurationSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30);
		stayDurationSpinner.setValueFactory(svf);
	}

	private void initCapacitySpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
		capacitySpinner.setValueFactory(svf);
	}

	private void initDurationSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 50);
		durationSpinner.setValueFactory(svf);
	}

	private void initProcStartTimeHoursSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		procStartTimeHoursSpinner.setValueFactory(svf);
	}

	private void initProcStartTimeMinSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		procStartTimeMinSpinner.setValueFactory(svf);
	}

	private void initProcEndTimeHoursSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		procEndTimeHoursSpinner.setValueFactory(svf);
	}

	private void initProcEndTimeMinSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		procEndTimeMinSpinner.setValueFactory(svf);
	}
	
	private void initApStartTimeHoursSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		apStartTimeHorusSpinner.setValueFactory(svf);
	}

	private void initApStartTimeMinSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		apStartTimeMinsSpinner.setValueFactory(svf);
	}

	private void initApEndTimeHoursSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		apEndTimeHoursSpinner.setValueFactory(svf);
	}

	private void initApEndTimeMinSpinner() {
		SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		apEndTimeMinsSpinner.setValueFactory(svf);
	}

	// INIT TABLE VIEWS
	private void initAllTables() throws SQLException {
		initPatientsTable();
		initProceduresTable();
		initAppointmentsTable();
	}

	private void initPatientsTable() throws SQLException {
		fullnameCol.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
		genderCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
		clientTypeCol.setCellValueFactory(cellData -> cellData.getValue().clientTypeProperty());
		accomodationCol.setCellValueFactory(cellData -> cellData.getValue().accomodationProperty());
		partnershipCol.setCellValueFactory(cellData -> cellData.getValue().partnershipProperty());
		ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
		stayDurationCol.setCellValueFactory(cellData -> cellData.getValue().stayDurationProperty());
		acClientIdCol.setCellValueFactory(cellData -> cellData.getValue().accomodationClientIDProperty());
		personIdNumCol.setCellValueFactory(cellData -> cellData.getValue().personIdNumProperty());
		dateOfArrivalCol.setCellValueFactory(cellData -> cellData.getValue().dateOfArrivalProperty());
		dateOfDepartureCol.setCellValueFactory(cellData -> cellData.getValue().dateOfDepartureProperty());
	
		patientsTableView.setItems(manager.getAllPatients());
	}

	private void initProceduresTable() throws SQLException {
		nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		departmentCol.setCellValueFactory(cellData -> cellData.getValue().departmentProperty());
		capacityCol.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
		durationCol.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
		procStartTimeCol.setCellValueFactory(cellData -> cellData.getValue().getIntervalOfProcedure().startProperty());
		procEndTimeCol.setCellValueFactory(cellData -> cellData.getValue().getIntervalOfProcedure().endProperty());
		
		proceduresTableView.setItems(manager.getAllProcedures());
	}
	
	private void initAppointmentsTable() throws SQLException {
		appointmentsTableView.setItems(manager.getAllAppointments());
	}

	// UPDATE TABLES METHODS
	private void updatePatientsTable() throws SQLException {
		patientsTableView.setItems(manager.getAllPatients());
	}
	
	private void updateProceduresTable() throws SQLException {
		proceduresTableView.setItems(manager.getAllProcedures());
	}
	
	// BUTTON LISTENERS ------------------------------------------

	// PATIENTS----------------------
	@FXML
	void onAddPatientClick(ActionEvent event) throws SQLException {
		manager.addPatient(convertInputToPatient());
		updatePatientsTable();
	}

	// HELPER METHOD TO CONVERT INPUT TO PATIENT
	private Patient convertInputToPatient() {
		String fullname = fullNameField.getText();
		String gender = genderChoice.getValue();
		String accomodation = accomodationField.getText();
		String clientType = clientTypeChoice.getValue();
		String partnership = partnershipChoice.getValue();
		int age = ageSpinner.getValue();
		int stayDuration = stayDurationSpinner.getValue();
		long accomodationClientId = Long.parseLong(acClientIdField.getText());
		long personIdNum = Long.parseLong(personIdNumField.getText());
		LocalDate dateOfArrival = dateOfArrivalPicker.getValue();
		LocalDate dateOfDeparture = dateOfDeparturePicker.getValue();

		return new Patient(fullname, gender, clientType, accomodation, partnership, age, stayDuration,
				accomodationClientId, personIdNum, dateOfArrival, dateOfDeparture);

	}

	@FXML
	void onDeletePatientClick(ActionEvent event) throws SQLException {
		Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
		manager.deletePatient(patient.getPersonIdNum());
		updatePatientsTable();
	}

	@FXML
	void onChangePatientClick(ActionEvent event) throws SQLException {
		Patient patient = patientsTableView.getSelectionModel().getSelectedItem();
		convertFromPatientToInput(patient);
		manager.deletePatient(patient.getPersonIdNum());
	}

	// HELPER METHOD TO CONVERT PATIENT TO INPUT FORMS
	private void convertFromPatientToInput(Patient patient) {
		fullNameField.setText(patient.getFullname());
		genderChoice.getSelectionModel().select(patient.getGender());
		accomodationField.setText(patient.getAccomodation());
		clientTypeChoice.getSelectionModel().select(patient.getClientType());
		partnershipChoice.getSelectionModel().select(patient.getPartnership());
		ageSpinner.getValueFactory().setValue(patient.getAge());
		stayDurationSpinner.getValueFactory().setValue(patient.getStayDuration());
		acClientIdField.setText("" + patient.getAccomodationClientID());
		personIdNumField.setText("" + patient.getPersonIdNum());
		dateOfArrivalPicker.setValue(patient.getDateOfArrival());
		dateOfDeparturePicker.setValue(patient.getDateOfDeparture());
	}

	// PROCEDURES---------------------------------
	@FXML
	void onAddProcedureClick(ActionEvent event) throws SQLException {
		Procedure procedure = convertInputToProcedure();
		manager.addProcedure(procedure);
		updateProceduresTable();
	}

	// HELPER METHOD TO CONVERT INPUT TO PROCEDURE
	private Procedure convertInputToProcedure() {
		String name = nameField.getText();
		String department = departmentChoice.getValue();
		int capacity = capacitySpinner.getValue();
		int duration = durationSpinner.getValue();
		MyTime start = new MyTime(procStartTimeHoursSpinner.getValue(), procStartTimeMinSpinner.getValue());
		MyTime end = new MyTime(procEndTimeHoursSpinner.getValue(), procEndTimeMinSpinner.getValue());

		Interval intervalOfProcedure = new Interval(start, end);

		return new Procedure(name, department, capacity, duration, intervalOfProcedure);

	}

	@FXML
	void onDeleteProcedureClick(ActionEvent event) throws SQLException {
		Procedure procedure = proceduresTableView.getSelectionModel().getSelectedItem();
		manager.deleteProcedure(procedure.getName());
		updateProceduresTable();
	}

	@FXML
	void onChangeProcedureClick(ActionEvent event) throws SQLException {
		Procedure procedure = proceduresTableView.getSelectionModel().getSelectedItem();
		convertFromProcedureToInput(procedure);
		manager.deleteProcedure(procedure.getName());
	}

	// HELPER METHOD TO CONVERt PROCEDURE TO INPUT
	private void convertFromProcedureToInput(Procedure procedure) {
		nameField.setText(procedure.getName());
		departmentChoice.setValue(procedure.getDepartment());
		capacitySpinner.getValueFactory().setValue(procedure.getCapacity());
		durationSpinner.getValueFactory().setValue(procedure.getDuration());
		procStartTimeHoursSpinner.getValueFactory().setValue(procedure.getIntervalOfProcedure().getStart().getHour()); 
		procStartTimeMinSpinner.getValueFactory().setValue(procedure.getIntervalOfProcedure().getStart().getMin());
		procEndTimeHoursSpinner.getValueFactory().setValue(procedure.getIntervalOfProcedure().getEnd().getHour());
		procEndTimeMinSpinner.getValueFactory().setValue(procedure.getIntervalOfProcedure().getEnd().getMin());
	}

	//
	// APPOINTMENTS------------------
	@FXML
	void onAddAppointmentClick(ActionEvent event) {

	}

	@FXML
	void onDeleteAppointmentClick(ActionEvent event) throws NumberFormatException, SQLException {
		manager.deleteAppointment(Long.parseLong(appointmentIdField.getText()));
	}

	@FXML
	void onChangeAppointmentClick(ActionEvent event) {

	}

}
