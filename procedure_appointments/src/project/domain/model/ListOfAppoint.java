package project.domain.model;

import java.util.ArrayList;

public class ListOfAppoint {
	
	private ArrayList<Appointment> listOfAppointments;
	
	public ListOfAppoint(ArrayList<Appointment> listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	}
	
	public void addAppointment(Appointment appointment) {
		listOfAppointments.add(appointment);
	}
	
	public void getAppointment(int index) {
		listOfAppointments.get(index);
	}
	
	public ArrayList<Appointment> getListOfAppointments() {
		return listOfAppointments;
	}
}
