package project.domain.model;

import java.time.LocalDate;

public class AppointmentTable {
	
	private long patientIdNum;
	private String procedureName;
	private LocalDate appointmentDate;
	private Interval intervalOfAppointment;
	
	
	
	public AppointmentTable(long patientIdNum, String procedureName, LocalDate appointmentDate,
			Interval intervalOfAppointment) {

		this.patientIdNum = patientIdNum;
		this.procedureName = procedureName;
		this.appointmentDate = appointmentDate;
		this.intervalOfAppointment = intervalOfAppointment;
	}
	public long getPatientIdNum() {
		return patientIdNum;
	}
	public String getProcedureName() {
		return procedureName;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public Interval getIntervalOfAppointment() {
		return intervalOfAppointment;
	}
	public void setPatientIdNum(long patientIdNum) {
		this.patientIdNum = patientIdNum;
	}
	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public void setIntervalOfAppointment(Interval intervalOfAppointment) {
		this.intervalOfAppointment = intervalOfAppointment;
	}
	
	
}
