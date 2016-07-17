package project.domain.model;

public class Speleotherapy extends Procedure {
	
	private int count;
	
	public Speleotherapy(String name, String department, int capacity, int duration, Interval intervalOfProcedure,
			int count) {
		super(name, department, capacity, duration, intervalOfProcedure);
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
