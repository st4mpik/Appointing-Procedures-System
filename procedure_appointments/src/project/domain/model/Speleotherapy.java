package project.domain.model;

public class Speleotherapy extends Procedure {

	private int count;
	
	public Speleotherapy(String name, String department, int capacity, int duration, Interval intervalOfProcedure) {
		super(name, department, capacity, duration, intervalOfProcedure);
		this.count = 0;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

}
