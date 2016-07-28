
package project.domain.model;

import java.util.Date;

public class MyTime {
	
	private int hour;
	private int min;
	
	public MyTime(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}
	@SuppressWarnings("deprecation")
	public MyTime() {
		Date date = new Date();
		this.hour = date.getHours();
		this.min = date.getMinutes();
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public void setTime(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}
	public void plus(int duration) {
		if(min + duration >= 60) {
			hour++;
			min = (min + duration) - 60;
		}
	}
	
	public boolean equals(MyTime time) {
		return hour == time.getHour() && min == time.getMin();
	}
	
	public boolean isOver(MyTime time) {
		return time.getHour() >= hour || time.getMin() > time.getMin();
	}
	
	public String toString() {
		return String.format("%02d", hour) + ":" + String.format("%02d", min);
	}
}
