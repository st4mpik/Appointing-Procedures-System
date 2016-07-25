package project.domain.model;

import java.time.LocalTime;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Procedure {

	private StringProperty name;
	private StringProperty department;
	private IntegerProperty capacity;
	private IntegerProperty duration;
	private Interval intervalOfProcedure;
	private ArrayList<ArrayList<Appointment>> bigList;
	private int numberOfListAvailable;

	public Procedure(String name, String department, int capacity, int duration, Interval intervalOfProcedure) {
		this.name = new SimpleStringProperty(name);
		this.department = new SimpleStringProperty(department);
		this.capacity = new SimpleIntegerProperty(capacity);
		this.duration = new SimpleIntegerProperty(duration);
		this.intervalOfProcedure = intervalOfProcedure;
		this.bigList = new ArrayList<ArrayList<Appointment>>(capacity);
		this.numberOfListAvailable = 1;
	}

	public void addToTheBigList(int listNumber, ArrayList<Appointment> appointments) {
		this.bigList.add(listNumber, appointments);
	}

	public ArrayList<Appointment> getListOfListNumber(int listNumber) {
		return bigList.get(listNumber);
	}

	public ArrayList<Interval> getAllAvailableTimes() {
		ArrayList<Interval> availableTimes = new ArrayList<Interval>();
		LocalTime startTimeCounter = LocalTime.of(intervalOfProcedure.getStart().getHour(),
				intervalOfProcedure.getStart().getMin());
		LocalTime endTime = LocalTime.of(intervalOfProcedure.getEnd().getHour(), intervalOfProcedure.getEnd().getMin());

		do {
			LocalTime temp = startTimeCounter;
			startTimeCounter = startTimeCounter.plusMinutes(duration.get());
			MyTime timos = new MyTime(temp.getHour(), temp.getMinute());
			MyTime timos2 = new MyTime(startTimeCounter.getHour(), startTimeCounter.getMinute());
			if (startTimeCounter.isBefore(endTime)) {
				availableTimes.add(new Interval(timos, timos2));
			}
		} while (!(startTimeCounter.isAfter(endTime)));

		return availableTimes;
	}

	public ArrayList<LocalTime> getFreeIntervalsStarts() {
		ArrayList<Interval> intervals = getFreeIntervals();
		ArrayList<LocalTime> temp = new ArrayList<LocalTime>();
		for (int i = 0; i < intervals.size(); i++) {
			MyTime time = intervals.get(i).getStart();
			LocalTime times = LocalTime.of(time.getHour(), time.getMin());
			temp.add(times);
		}
		return temp;
	}

	public ArrayList<Interval> getFreeIntervals() {
		ArrayList<Interval> allAvailabe = getAllAvailableTimes();
		ArrayList<Interval> nowAvailabe = allAvailabe;
		for (int i = 0; i < bigList.size(); i++) {
			nowAvailabe.removeAll(convertSmallListToIntervals(getListOfListNumber(i)));
		}
		return nowAvailabe;
	}

	public ArrayList<Interval> convertSmallListToIntervals(ArrayList<Appointment> smallList) {
		ArrayList<Interval> temp = new ArrayList<Interval>();
		for (int i = 0; i < smallList.size(); i++) {
			temp.add(smallList.get(i).getIntervalOfAppointment());
		}
		return temp;
	}

	/*
	 * public Interval getFirstFreeInterval(ArrayList<Appointment>
	 * allAppointments) { ArrayList<Interval> allAvailabe =
	 * getAllAvailableTimes(); if(allAppointments == null) {
	 * getFirstFreeInterval(); } for(int i = 0; i < allAvailabe.size(); i++) {
	 * for(int j = 0; j < bigList.size(); i++) { for(int k = 0; k <
	 * bigList.get(j).size(); i++) {
	 * if(!(allAvailabe.get(i).getStart().equals(bigList.get(j).get(k)))) {
	 * for(int l = 0; l < allAppointments.size(); l++) {
	 * if(!(allAppointments.get(l).getIntervalOfAppointment().getEnd().isOver(
	 * allAvailabe.get(i).getStart()))) { setNumberOfListAvialable(j); return
	 * allAvailabe.get(i); }
	 * 
	 * } }
	 * 
	 * } } } return null; }
	 * 
	 * public ArrayList<Interval> getAllFreeIntervals() { ArrayList<Interval>
	 * allAvailabe = getAllAvailableTimes(); ArrayList<Interval>
	 * allFreeIntervals = new ArrayList<Interval>();
	 * 
	 * for(int i = 0; i < allAvailabe.size(); i++) { for(int j = 0; j <
	 * bigList.size(); i++) { for(int k = 0; k < bigList.get(j).size(); i++) {
	 * if(!(allAvailabe.get(i).getStart().equals(bigList.get(j).get(k)))) {
	 * setNumberOfListAvialable(j); allFreeIntervals.add(allAvailabe.get(i)); }
	 * } } } return null; }
	 */

	public int getNumberOfListAvialable() {
		return numberOfListAvailable;
	}

	public void setNumberOfListAvialable(int number) {
		this.numberOfListAvailable = number;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getDepartment() {
		return department.get();
	}

	public void setDepartment(String department) {
		this.department.set(department);
	}

	public StringProperty departmentProperty() {
		return department;
	}

	public int getCapacity() {
		return capacity.get();
	}

	public void setCapacity(int capacity) {
		this.capacity.set(capacity);
	}

	public IntegerProperty capacityProperty() {
		return capacity;
	}

	public Interval getIntervalOfProcedure() {
		return intervalOfProcedure;
	}

	public void setIntervalOfProcedure(Interval intervalOfProcedure) {
		this.intervalOfProcedure = intervalOfProcedure;
	}

	public int getDuration() {
		return duration.get();
	}

	public void setDuration(int duration) {
		this.duration.set(duration);
	}

	public IntegerProperty durationProperty() {
		return duration;
	}

}
