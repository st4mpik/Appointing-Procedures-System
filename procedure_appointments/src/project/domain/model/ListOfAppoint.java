package project.domain.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class ListOfAppoint {
	
	private ArrayList<Appointment> listOfAppoint;
	private ArrayList<Interval> freeTimes;
	
	public ListOfAppoint() {
		ArrayList<Appointment> listOfAppoint = new ArrayList<Appointment>(); 
	}
	
	public void addAll(ArrayList<Appointment> listOfAppoint) {
		listOfAppoint.addAll(listOfAppoint); 
	}
	
	private ArrayList<Interval> getAllAvailableTimes(Interval intervalOfProcedure, int duration) {
		ArrayList<Interval> availableTimes = new ArrayList<Interval>();
		MyTime startTimeCounter = intervalOfProcedure.getStart();
		
		do {
			MyTime temp = startTimeCounter;
			startTimeCounter.plus(duration);
			availableTimes.add(new Interval(temp, startTimeCounter));
		}
		while(!(startTimeCounter.isOver(intervalOfProcedure.getEnd())));
		
		return availableTimes;
	}
	
	public void setFreeTimes(Interval intervalOfProcedure, int duration) {
		ArrayList<Interval> allAvailabe = getAllAvailableTimes(intervalOfProcedure, duration);
		ArrayList<Interval> freeTimes = new ArrayList<Interval>();
		for(int i = 0; i < listOfAppoint.size(); i++) {
			for(int j = 0; j < freeTimes.size(); i++) {
				if(!(allAvailabe.get(j).getStart().equals(listOfAppoint.get(i).getIntervalOfAppointment().getStart()))) {
					freeTimes.add(new Interval(allAvailabe.get(j).getStart(), allAvailabe.get(j).getEnd()));
				}
			}
		}
		this.freeTimes.addAll(freeTimes);
	}
	
	public ArrayList<Interval> getFreeTimes() {
		return freeTimes;
	}
}
