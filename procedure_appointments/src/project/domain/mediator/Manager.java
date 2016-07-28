package project.domain.mediator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import project.domain.model.Appointment;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public interface Manager {
	
	void loadPatientsFromXLS(String filepath) throws IOException, SQLException;
	
	void updatePatientStatusToArchived() throws SQLException;
	void addPatient(Patient patient) throws SQLException;
	Patient searchForInPatients(long personIdNum) throws SQLException;
	ObservableList<Patient> getAllPatients() throws SQLException;
	Procedure searchForInProcedures(String name) throws SQLException;
	void deletePatient(long personIdNum) throws SQLException;
	ObservableList<Patient> getAllTodayPatients() throws SQLException;
	ObservableList<Patient> getAllInProgressPatients() throws SQLException;
	ObservableList<Patient> getAllArchivedPatients() throws SQLException;
	
	void addProcedure(Procedure procedure) throws SQLException;
	void deleteProcedure(String name) throws SQLException;
	ObservableList<Procedure> getAllProcedures() throws SQLException;
	
	void addAppointment(Appointment appointment) throws SQLException;
	ObservableList<Appointment> getAllAppointments() throws SQLException;
	void deleteAppointment(long appointmentId) throws SQLException;
	
	ArrayList<Appointment> generate(ArrayList<Procedure> chosenProcedures, Patient patient) throws SQLException;
	 Generator generatetor(ArrayList<Procedure> chosenProcedures, Patient patient) throws SQLException;
}
