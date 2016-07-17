package project.domain.model;

import java.time.LocalDate;

public class Patient {
	
	private String fullname;
	private String gender;
	private String clientType;
	private String accomodation;
	private String partnership;
	private int age;
	private int stayDuration;
	private long accomodationClientID;
	private long personIdNum;
	private LocalDate dateOfArrival;
	private	LocalDate dateOfDeparture;
	
	
	public Patient(String fullname, String gender, String clientType, String accomodation, String partnership, int age,
			int stayDuration, long accomodationClientID, long personIdNum, LocalDate dateOfArrival,
			LocalDate dateOfDeparture) {
		this.fullname = fullname;
		this.gender = gender;
		this.clientType = clientType;
		this.accomodation = accomodation;
		this.partnership = partnership;
		this.age = age;
		this.stayDuration = stayDuration;
		this.accomodationClientID = accomodationClientID;
		this.personIdNum = personIdNum;
		this.dateOfArrival = dateOfArrival;
		this.dateOfDeparture = dateOfDeparture;
	}

	


	public String getFullname() {
		return fullname;
	}




	public String getGender() {
		return gender;
	}




	public String getClientType() {
		return clientType;
	}




	public String getAccomodation() {
		return accomodation;
	}




	public String getPartnership() {
		return partnership;
	}




	public int getAge() {
		return age;
	}




	public int getStayDuration() {
		return stayDuration;
	}




	public long getAccomodationClientID() {
		return accomodationClientID;
	}




	public long getPersonIdNum() {
		return personIdNum;
	}




	public LocalDate getDateOfArrival() {
		return dateOfArrival;
	}




	public LocalDate getDateOfDeparture() {
		return dateOfDeparture;
	}




	public void setFullname(String fullname) {
		this.fullname = fullname;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public void setClientType(String clientType) {
		this.clientType = clientType;
	}




	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}




	public void setPartnership(String partnership) {
		this.partnership = partnership;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public void setStayDuration(int stayDuration) {
		this.stayDuration = stayDuration;
	}




	public void setAccomodationClientID(long accomodationClientID) {
		this.accomodationClientID = accomodationClientID;
	}




	public void setPersonIdNum(long personIdNum) {
		this.personIdNum = personIdNum;
	}




	public void setDateOfArrival(LocalDate dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}




	public void setDateOfDeparture(LocalDate dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}




	public boolean equals(Patient patient) {
		return personIdNum == patient.getPersonIdNum();
	}
}
