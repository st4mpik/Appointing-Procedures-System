package project.domain.model;


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
	private ListOfLists listOfLists;
	
	public Procedure(String name, String department, int capacity, int duration, Interval intervalOfProcedure) {
		this.name = new SimpleStringProperty(name);
		this.department = new SimpleStringProperty(department);
		this.capacity = new SimpleIntegerProperty(capacity);
		this.duration = new SimpleIntegerProperty(duration);
		this.intervalOfProcedure = intervalOfProcedure;
		this.listOfLists = null;
	}
	
	public void setListOfLists(ListOfLists listOfLists) {
		this.listOfLists = listOfLists;
	}
	
	public ListOfLists getListOfLists() {
		return listOfLists;
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
