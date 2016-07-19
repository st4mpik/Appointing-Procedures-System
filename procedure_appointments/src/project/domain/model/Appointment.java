package project.domain.model;

import java.time.LocalDate;

public class Appointment {
	
	private Long patientNum;
	private String procedureName;
	private LocalDate date;
	private Interval interval;
	private int numberOfList;
	
	public Appointment(Long patientNum, String procedureName, LocalDate date, Interval interval, int numberOfList) {
		this.patientNum = patientNum;
		this.procedureName = procedureName;
		this.date = date;
		this.interval = interval;
		this.numberOfList = numberOfList;
	}

	public Long getPatientNum() {
		return patientNum;
	}

	public void setPatientNum(Long patientNum) {
		this.patientNum = patientNum;
	}

	public LocalDate getDateOfAppointment() {
		return date;
	}

	public void setDateOfAppointment(LocalDate dateOfAppointment) {
		this.date = dateOfAppointment;
	}

	public Interval getIntervalOfAppointment() {
		return interval;
	}

	public void setIntervalOfAppointment(Interval intervalOfAppointment) {
		this.interval = intervalOfAppointment;
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	
	public int getNumberOfList() {
		return numberOfList;
	}
	
	public void setNumberOfList(int number) {
		this.numberOfList = number;
	}
	
	
}
