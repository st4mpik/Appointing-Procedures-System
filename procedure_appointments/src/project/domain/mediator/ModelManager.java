package project.domain.mediator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import project.domain.model.AppointmentTable;
import project.domain.model.Patient;
import project.domain.model.Procedure;

@SuppressWarnings("unused")
public class ModelManager implements Manager {
	
	private XLSReader reader;
	private Storage database;
	
	public ModelManager() throws SQLException, FileNotFoundException, IOException {
		this.database = new Database();
	}
	
	//XLS READING
	
	public void loadPatientsFromXLS(String filepath) throws IOException, SQLException {
		this.reader = new XLSReader(filepath, 4);
		ArrayList<Patient> patients = reader.loadPatientsFromAllRows();
		for(int i = 0; i < patients.size(); i++) {
			database.addPatient(patients.get(i));
		}
	}
	
	// DATABSE PATIENTS -----------------------------------------------------
	public void addPatient(Patient patient) throws SQLException {
		database.addPatient(patient);
	}
	
	public Patient searchForInPatients(long personIdNum) throws SQLException {
		return database.searchForPatient(personIdNum);
	}
	
	public ObservableList<Patient> getAllPatients() throws SQLException {
		return database.getAllPatients();
	}
	
	public void deletePatient(long personIdNum) throws SQLException {
		database.deletePatient(personIdNum);
	}
	//DATABASE PROCEDURES-----------------------------------------------------
	public void addProcedure(Procedure procedure) throws SQLException {
		database.addProcedure(procedure);
	}
	
	public void deleteProcedure(String name) throws SQLException {
		database.deleteProcedure(name);
	}
	
	public Procedure searchForInProcedures(String name) throws SQLException {
		return database.searchForProcedure(name);
	}
	
	public ObservableList<Procedure> getAllProcedures() throws SQLException {
		return database.getAllProcedures();
	}
	//APPOINTMENTS--------------------------------------------------------------
	
	public ObservableList<AppointmentTable> getAllAppointments() throws SQLException {
		return database.getAllAppointments();
	}
	
	public void deleteAppointment(long appointmentId) throws SQLException {
		database.deleteAppointment(appointmentId);
	}
	// GENERATOR ----------------------------------------------------------------
	
	public void generate(ArrayList<Procedure> chosenProcedures) {
		Generator generator = new Generator(chosenProcedures);
	}
}
