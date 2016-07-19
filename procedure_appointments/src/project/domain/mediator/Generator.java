package project.domain.mediator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import project.domain.model.Appointment;
import project.domain.model.ListOfLists;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public class Generator {
	
	private ArrayList<Procedure> listOfChosenProc;
	private Storage database;
	private Patient patient;
	private ArrayList<LocalDate> datesOfStay;
	private ArrayList<Appointment> genratedAppointments;
	
	public Generator(ArrayList<Procedure> listOfProcedures, Patient patient, Storage database) throws SQLException {
		listOfChosenProc = listOfProcedures;
		this.database = database;	
	}
	
	public void generate() throws SQLException {
		getDatesOfStay();
		createAppointments();
	}
	
	private void initListsListOfLists(LocalDate date) throws SQLException {
		for(int i = 0; i < listOfChosenProc.size(); i++) {
			int capacity = listOfChosenProc.get(i).getCapacity();
			listOfChosenProc.get(i).setListOfLists(new ListOfLists(capacity)); 
			initListOfAppoints(i, capacity, date, listOfChosenProc.get(i).getName());
		}
	}
	
	private void initListOfAppoints(int i, int capacity, LocalDate date, String procedureName) throws SQLException {
		for(int j = 1; j < capacity; i++) {
			ArrayList<Appointment> temp = database.searchForAppointments(date, procedureName, j); 
			listOfChosenProc.get(i).getListOfLists().getListOfAppoint(j-1).addAll(temp);
		}
	}
	
	private ArrayList<LocalDate> getDatesOfStay() {
		ArrayList<LocalDate> totalDates = new ArrayList<LocalDate>();
		LocalDate start = patient.getDateOfDeparture();
		LocalDate end = patient.getDateOfDeparture();
		
		while (!start.isAfter(end)) {
		    totalDates.add(start);
		    start = start.plusDays(1);
		}
		return totalDates;
	}
	
	public void createAppointments() throws SQLException {
		for(int i = 0; i < datesOfStay.size(); i++) {
			initListsListOfLists(datesOfStay.get(i));
			for(int j = 0; j < listOfChosenProc.size(); j++) {
				genratedAppointments.add(listOfChosenProc.get(j).getListOfLists().generateAppointment(listOfChosenProc.get(j), 
						patient, datesOfStay.get(i)));
			}
		}
	}
	
	public ArrayList<Appointment> getGeneratedAppointments() {
		return genratedAppointments;
	}
	
}
