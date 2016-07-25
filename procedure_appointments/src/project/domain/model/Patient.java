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

public class Patient {
	
	private StringProperty fullname;
	private StringProperty gender;
	private StringProperty clientType;
	private StringProperty accomodation;
	private StringProperty partnership;
	private IntegerProperty age;
	private IntegerProperty stayDuration;
	private LongProperty accomodationClientID;
	private LongProperty personIdNum;
	private StringProperty status;
	private ObjectProperty<LocalDate> dateOfArrival;
	private	ObjectProperty<LocalDate> dateOfDeparture;
	
	
	public Patient(String fullname, String gender, String clientType, String accomodation, String partnership, int age,
			int stayDuration, long accomodationClientID, long personIdNum, LocalDate dateOfArrival,
			LocalDate dateOfDeparture) {
		this.fullname = new SimpleStringProperty(fullname);
		this.gender =  new SimpleStringProperty(gender);
		this.clientType = new SimpleStringProperty(clientType);
		this.accomodation = new SimpleStringProperty(accomodation);
		this.partnership = new SimpleStringProperty(partnership);
		this.age =	new SimpleIntegerProperty(age);
		this.stayDuration = new SimpleIntegerProperty(stayDuration);
		this.accomodationClientID = new SimpleLongProperty(accomodationClientID);
		this.personIdNum = new SimpleLongProperty(personIdNum);
		this.dateOfArrival = new SimpleObjectProperty<LocalDate>(dateOfArrival);
		this.dateOfDeparture =  new SimpleObjectProperty<LocalDate>(dateOfDeparture);
		//status.set("today");
	}

	public Patient(String fullname, String gender, String clientType, String accomodation, String partnership, int age,
			int stayDuration, long accomodationClientID, long personIdNum, LocalDate dateOfArrival,
			LocalDate dateOfDeparture, String status) {
		this.fullname = new SimpleStringProperty(fullname);
		this.gender =  new SimpleStringProperty(gender);
		this.clientType = new SimpleStringProperty(clientType);
		this.accomodation = new SimpleStringProperty(accomodation);
		this.partnership = new SimpleStringProperty(partnership);
		this.age =	new SimpleIntegerProperty(age);
		this.stayDuration = new SimpleIntegerProperty(stayDuration);
		this.accomodationClientID = new SimpleLongProperty(accomodationClientID);
		this.personIdNum = new SimpleLongProperty(personIdNum);
		this.status = new SimpleStringProperty(status);
		this.dateOfArrival = new SimpleObjectProperty<LocalDate>(dateOfArrival);
		this.dateOfDeparture =  new SimpleObjectProperty<LocalDate>(dateOfDeparture);
	}


	public String getFullname() {
		return fullname.get();
	}

	public StringProperty fullNameProperty() {
		return fullname;
	}
	


	public String getGender() {
		return gender.get();
	}

	public StringProperty genderProperty() {
		return gender;
	}
	


	public String getClientType() {
		return clientType.get();
	}
	
	public StringProperty clientTypeProperty() {
		return clientType;
	}
	

	public String getAccomodation() {
		return accomodation.get();
	}

	public StringProperty accomodationProperty() {
		return accomodation;
	}
	
	public String getPartnership() {
		return partnership.get();
	}
	
	public StringProperty partnershipProperty() {
		return partnership;
	}
	
	public int getAge() {
		return age.get();
	}

	public IntegerProperty ageProperty() {
		return age;
	}
	
	public int getStayDuration() {
		return stayDuration.get();
	}

	public IntegerProperty stayDurationProperty() {
		return stayDuration;
	}

	public long getAccomodationClientID() {
		return accomodationClientID.get();
	}

	public LongProperty accomodationClientIDProperty() {
		return accomodationClientID;
	}
	
	public long getPersonIdNum() {
		return personIdNum.get();
	}

	public LongProperty personIdNumProperty() {
		return personIdNum;
	}

	public LocalDate getDateOfArrival() {
		return dateOfArrival.get();
	}
	
	public ObjectProperty<LocalDate> dateOfArrivalProperty() {
		return dateOfArrival;
	}

	public LocalDate getDateOfDeparture() {
		return dateOfDeparture.get();
	}

	public ObjectProperty<LocalDate> dateOfDepartureProperty() {
		return dateOfDeparture;
	}

	public void setFullname(String fullname) {
		this.fullname.set(fullname);
	}




	public void setGender(String gender) {
		this.gender.set(gender);
	}




	public void setClientType(String clientType) {
		this.clientType.set(clientType);
	}




	public void setAccomodation(String accomodation) {
		this.accomodation.set(accomodation);
	}




	public void setPartnership(String partnership) {
		this.partnership.set(partnership);
	}




	public void setAge(int age) {
		this.age.set(age);
	}




	public void setStayDuration(int stayDuration) {
		this.stayDuration.set(stayDuration);
	}

	public void setStatus(String status) {
		this.status.set(status);
	}
	
	public String getStatus() {
		return status.get();
	}

	public StringProperty statusProperty() {
		return status;
	}
	
	public void setAccomodationClientID(long accomodationClientID) {
		this.accomodationClientID.set(accomodationClientID);
	}




	public void setPersonIdNum(long personIdNum) {
		this.personIdNum.set(personIdNum);
	}




	public void setDateOfArrival(LocalDate dateOfArrival) {
		this.dateOfArrival.set(dateOfArrival);
	}




	public void setDateOfDeparture(LocalDate dateOfDeparture) {
		this.dateOfDeparture.set(dateOfDeparture);
	}

}
