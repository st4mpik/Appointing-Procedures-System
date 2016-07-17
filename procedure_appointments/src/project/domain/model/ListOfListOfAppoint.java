package project.domain.model;

import java.util.ArrayList;

public class ListOfListOfAppoint {
		
	private ArrayList<ListOfAppoint> listOfListAppoint;
	
	public ListOfListOfAppoint(int capacity) {
		listOfListAppoint = new ArrayList<ListOfAppoint>(capacity);
	}
	
	public ListOfAppoint getListOfAppointments(int index) {
		return listOfListAppoint.get(index);
	}
}
