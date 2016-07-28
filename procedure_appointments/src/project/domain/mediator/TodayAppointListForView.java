package project.domain.mediator;

import java.time.LocalDate;
import java.util.ArrayList;

import project.domain.model.Appointment;

public class TodayAppointListForView {
	
		private LocalDate day;
		private ArrayList<Appointment> thisdayList;
		
		public TodayAppointListForView(LocalDate day, ArrayList<Appointment> thisdayList) {
			this.day = day;
			this.thisdayList = thisdayList;
		}

		public LocalDate getDay() {
			return day;
		}

		public void setDay(LocalDate day) {
			this.day = day;
		}

		public ArrayList<Appointment> getThisdayList() {
			return thisdayList;
		}

		public void setThisdayList(ArrayList<Appointment> thisdayList) {
			this.thisdayList = thisdayList;
		}
 }
