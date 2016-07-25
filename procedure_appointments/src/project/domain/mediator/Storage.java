package project.domain.mediator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import project.domain.model.Appointment;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public interface Storage {
	
	 void addPatient(Patient patient) throws SQLException;
	 Patient searchForPatient(long personIdNum) throws SQLException;
	 ObservableList<Patient> getAllPatients() throws SQLException;
	 Procedure searchForProcedure(String name) throws SQLException;
	 void deletePatient(long personIdNum) throws SQLException;
	 void updatePatientStatusToArchived() throws SQLException;
	 void updatePatientStatus(Long patientNum, String status) throws SQLException;
	 ObservableList<Patient> getAllArchivedPatients() throws SQLException;
	 ObservableList<Patient> getAllInProgressPatients() throws SQLException;
	 ObservableList<Patient> getAllTodayPatients() throws SQLException;
	 
	 
	 void addProcedure(Procedure procedure) throws SQLException;
	 void deleteProcedure(String name) throws SQLException;
	 ObservableList<Procedure> getAllProcedures() throws SQLException;
	 
	 void addAppointment(Appointment appointment) throws SQLException;
	 ArrayList<Appointment> searchForAppointments(LocalDate date, String procedureName, int numberOfList) throws SQLException;
	 ObservableList<Appointment> getAllAppointments() throws SQLException;
	 void deleteAppointment(long appointmentId) throws SQLException;
}
