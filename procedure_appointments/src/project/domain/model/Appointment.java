package project.domain.model;

import java.time.LocalDate;

public class Appointment {
	
	private Patient patient;
	private LocalDate date;
	private Interval interval;
	
	public Appointment(Patient patient, LocalDate date, Interval interval) {
		this.patient = patient;
		this.date = date;
		this.interval = interval;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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
	
}
