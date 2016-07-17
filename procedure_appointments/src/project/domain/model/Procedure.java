package project.domain.model;

public class Procedure {
	
	private String name;
	private String department;
	private int capacity;
	private int duration;
	private Interval intervalOfProcedure;
	private ListOfListOfAppoint listOfListOfAppoint;
	
	
	public Procedure(String name, String department, int capacity, int duration, Interval intervalOfProcedure) {
		this.name = name;
		this.department = department;
		this.capacity = capacity;
		this.duration = duration;
		this.intervalOfProcedure = intervalOfProcedure;
		this.listOfListOfAppoint = new ListOfListOfAppoint(capacity);
	}
	
	public Procedure(String name, String department, int capacity, int duration, Interval intervalOfProcedure,
			ListOfListOfAppoint listOfLists) {
		this.name = name;
		this.department = department;
		this.capacity = capacity;
		this.duration = duration;
		this.intervalOfProcedure = intervalOfProcedure;
		this.listOfListOfAppoint = listOfLists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Interval getIntervalOfProcedure() {
		return intervalOfProcedure;
	}

	public void setIntervalOfProcedure(Interval intervalOfProcedure) {
		this.intervalOfProcedure = intervalOfProcedure;
	}

	public ListOfListOfAppoint getListOfLists() {
		return listOfListOfAppoint;
	}

	public void setListOfLists(ListOfListOfAppoint listOfLists) {
		this.listOfListOfAppoint = listOfLists;
	}

	public int getDuration() {
		return duration;
	}

	public ListOfListOfAppoint getListOfListOfAppoint() {
		return listOfListOfAppoint;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setListOfListOfAppoint(ListOfListOfAppoint listOfListOfAppoint) {
		this.listOfListOfAppoint = listOfListOfAppoint;
	}
	
	
	
}
