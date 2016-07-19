package project.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListOfLists {

	private ArrayList<ListOfAppoint> listOfLists;
	private int numberOfList;
	
	public ListOfLists(int capacity) {
		listOfLists = new ArrayList<ListOfAppoint>(capacity);
		initListOfLists();
	}

	private void initListOfLists() {
		for (int i = 0; i < listOfLists.size(); i++) {
			listOfLists.add(new ListOfAppoint());
		}
	}

	public ListOfAppoint getListOfAppoint(int number) {
		return listOfLists.get(number);
	}

	public Appointment generateAppointment(Procedure procedure, Patient patient, LocalDate date) {
		Interval interval = findSuitableInterval(procedure);
		System.out.println(patient.getPersonIdNum() + ", " + procedure.getName() + ", " + interval.getStart().getHour() + ", " + numberOfList);
		return new Appointment(patient.getPersonIdNum(), procedure.getName(), date, interval, numberOfList + 1);
	}

	private Interval findSuitableInterval(Procedure procedure) {
		for (int i = 0; i < listOfLists.size(); i++) {
			listOfLists.get(i).setFreeTimes(procedure.getIntervalOfProcedure(), procedure.getDuration());
			if (listOfLists.get(i).getFreeTimes() != null) {
				numberOfList = i;
				return listOfLists.get(i).getFreeTimes().get(0);
			}
		}
		return null;
	}
}
