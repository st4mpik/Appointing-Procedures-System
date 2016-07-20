package project.domain.mediator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import project.domain.model.Appointment;
import project.domain.model.Interval;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public class Generator {

	private Patient patient;
	private ArrayList<Procedure> listOfChosenProc;
	private Storage database;

	public Generator(ArrayList<Procedure> listOfChosenProc, Patient patient, Storage database) throws SQLException {
		this.patient = patient;
		this.listOfChosenProc = listOfChosenProc;
		this.database = database;
	}

	public void inputDataFromDbToCertainProcedure(LocalDate date, int procedureIndex) throws SQLException {

		int capacity = listOfChosenProc.get(procedureIndex).getCapacity();
		Procedure procedure = listOfChosenProc.get(procedureIndex);
		for (int i = 0; i < capacity; i++) {
			ArrayList<Appointment> appointments = database.searchForAppointments(date, procedure.getName(), i);
			procedure.addToTheBigList(i, appointments);
		}
	}

	// METHOD TO GET DATES DURING THE STAY
	private ArrayList<LocalDate> getDatesOfStay() {
		ArrayList<LocalDate> totalDates = new ArrayList<LocalDate>();
		LocalDate start = patient.getDateOfArrival();
		LocalDate end = patient.getDateOfDeparture();

		while (!start.isAfter(end)) {
			totalDates.add(start);
			start = start.plusDays(1);
		}
		return totalDates;
	}
	
	
	public ArrayList<Appointment> generate() throws SQLException {
		ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();
		ArrayList<LocalDate> totalDates = getDatesOfStay();
		for (int i = 0; i < listOfChosenProc.size(); i++) {
			for (int j = 0; j < totalDates.size(); j++) {
				inputDataFromDbToCertainProcedure(totalDates.get(j), i);
				
				if(listOfChosenProc.get(i).getFirstFreeInterval() == null) {
					return null;
				}
				Interval interval = listOfChosenProc.get(i).getFirstFreeInterval(allAppointments);
				String procedureName = listOfChosenProc.get(i).getName();
				int numberOfList = listOfChosenProc.get(i).getNumberOfListAvialable();
				Appointment appointment = new Appointment(patient.getPersonIdNum(), procedureName , 
						totalDates.get(j), interval, numberOfList);
				allAppointments.add(appointment);
				database.addAppointment(appointment);
			}
		}
		return allAppointments;
	}

}
