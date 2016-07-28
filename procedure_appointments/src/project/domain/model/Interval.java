package project.domain.model;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Interval {

	private SimpleObjectProperty<MyTime> start;
	private SimpleObjectProperty<MyTime> end;
	
	public Interval(MyTime start, MyTime end) {
		this.start = new SimpleObjectProperty<MyTime>(start);
		this.end = new SimpleObjectProperty<MyTime>(end);
	}

	public MyTime getStart() {
		return start.get();
	}

	public void setStart(MyTime start) {
		this.start.set(start);
	}
	
	public ObjectProperty<MyTime> startProperty() {
		return start;
	}
	
	public MyTime getEnd() {
		return end.get();
	}

	public void setEnd(MyTime end) {
		this.end.set(end);
	}
	
	public ObjectProperty<MyTime> endProperty() {
		return end;
	}
	
	public boolean equals(Interval interval) {
		return start.equals(interval.getStart()) &&
				end.equals(interval.getEnd());
	}
	
	public String toString() {
		return "start: " + start.toString() + "\n" 
				+ "end: " + end.toString();
	}
}
