package project.domain.mediator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import project.domain.model.Appointment;
import project.domain.model.Interval;
import project.domain.model.MyTime;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public class Generator {

	private Patient patient;
	private ArrayList<Procedure> listOfChosenProc;
	private Storage database;
	private ArrayList<TodayAppointListForView> newList;

	public Generator(ArrayList<Procedure> listOfChosenProc, Patient patient, Storage database) throws SQLException {
		this.patient = patient;
		this.listOfChosenProc = listOfChosenProc;
		this.database = database;
		this.newList = new ArrayList<TodayAppointListForView>();
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
			for (int j = 0; j < totalDates.size(); j++) {
				ArrayList<Appointment> todayAppointments = new ArrayList<>();
				for (int i = 0; i < listOfChosenProc.size(); i++) {
				inputDataFromDbToCertainProcedure(totalDates.get(j), i);
				Interval interval = listOfChosenProc.get(i).getFreeIntervals().get(0);
				if (!todayAppointments.isEmpty()) {
					
					ArrayList<Interval> appointIntervals = convertSmallListToIntervalsStarts(todayAppointments);
					ArrayList<LocalTime> intervalEnds = getAppointIntervalsEnds(appointIntervals);

					
					for (int x = 0; x < listOfChosenProc.get(i).getFreeIntervalsStarts().size(); x++) {
						if (listOfChosenProc.get(i).getFreeIntervalsStarts().get(x)
								.isAfter(intervalEnds.get(intervalEnds.size() - 1))) {
							interval = listOfChosenProc.get(i).getFreeIntervals().get(x);
							break;
						} else {
							
						}
					}

				}
				String procedureName = listOfChosenProc.get(i).getName();
				int numberOfList = listOfChosenProc.get(i).getNumberOfListAvialable();
				Appointment appointment = new Appointment(patient.getPersonIdNum(), procedureName, totalDates.get(j),
						interval, numberOfList);
				allAppointments.add(appointment);
				//database.addAppointment(appointment);
				//database.updatePatientStatus(patient.getPersonIdNum(), "progress");
				todayAppointments.add(appointment);
			}
				System.out.println(todayAppointments.get(0).getProcedureName());
				newList.add(new TodayAppointListForView(totalDates.get(j), todayAppointments));
		}

		return allAppointments;
	}
	
	public ArrayList<TodayAppointListForView> getNewList() {
		return newList;
	}
	
	public ArrayList<LocalTime> getAppointIntervalsEnds(ArrayList<Interval> appointments) {
		ArrayList<Interval> intervals = appointments;
		ArrayList<LocalTime> temp = new ArrayList<LocalTime>();
		for (int i = 0; i < intervals.size(); i++) {
			MyTime time = intervals.get(i).getEnd();
			LocalTime times = LocalTime.of(time.getHour(), time.getMin());
			temp.add(times);
		}
		return temp;
	}

	public ArrayList<Interval> convertSmallListToIntervalsStarts(ArrayList<Appointment> smallList) {
		ArrayList<Interval> temp = new ArrayList<Interval>();
		for (int i = 0; i < smallList.size(); i++) {
			temp.add(smallList.get(i).getIntervalOfAppointment());
		}
		return temp;
	}

}
