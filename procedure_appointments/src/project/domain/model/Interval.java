package project.domain.model;

public class Interval {

	private MyTime start;
	private MyTime end;
	
	public Interval(MyTime start, MyTime end) {
		this.start = start;
		this.end = end;
	}

	public MyTime getStart() {
		return start;
	}

	public void setStart(MyTime start) {
		this.start = start;
	}

	public MyTime getEnd() {
		return end;
	}

	public void setEnd(MyTime end) {
		this.end = end;
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
