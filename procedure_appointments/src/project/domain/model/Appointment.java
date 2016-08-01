package project.domain.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment  {
	
	private LongProperty patientNum;
	private StringProperty procedureName;
	private ObjectProperty<LocalDate> date;
	private Interval interval;
	private IntegerProperty numberOfList;
	
	public Appointment(Long patientNum, String procedureName, LocalDate date, Interval interval, int numberOfList) {
		this.patientNum = new SimpleLongProperty(patientNum);
		this.procedureName = new SimpleStringProperty(procedureName);
		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.interval = interval;
		this.numberOfList = new SimpleIntegerProperty(numberOfList);
	}

	public Long getPatientNum() {
		return patientNum.get();
	}

	public void setPatientNum(Long patientNum) {
		this.patientNum.set(patientNum);
	}
	
	public LongProperty patientNumProperty() {
		return patientNum;
	}

	public LocalDate getDateOfAppointment() {
		return date.get();
	}

	public void setDateOfAppointment(LocalDate dateOfAppointment) {
		this.date.set(dateOfAppointment);
	}
	
	public ObjectProperty<LocalDate> dateOfAppointmentProperty() {
		return date;
	}
	
	public Interval getIntervalOfAppointment() {
		return interval;
	}

	public void setIntervalOfAppointment(Interval intervalOfAppointment) {
		this.interval = intervalOfAppointment;
	}

	public String getProcedureName() {
		return procedureName.get();
	}

	public void setProcedureName(String procedureName) {
		this.procedureName.set(procedureName);
	}
	
	public	StringProperty procedureNameProperty() {
		return procedureName;
	}
	
	public int getNumberOfList() {
		return numberOfList.get();
	}
	
	public void setNumberOfList(int number) {
		this.numberOfList.set(number);
	}
	
	public IntegerProperty numberOfListProperty() {
		return numberOfList;
	}

	
	
}
