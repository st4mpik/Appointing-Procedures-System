package project.view;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.scene.Scene;
import jfxtras.scene.control.agenda.Agenda;
import project.domain.model.Appointment;
import project.domain.model.MyTime;

public class AgendaScene {

	private static Agenda agenda;
	
	public static Scene getScene(ArrayList<Appointment> generatedAppointments) throws IOException, SQLException {
		
		   	agenda = new Agenda();
		   	
		   	for(int i = 0; i < generatedAppointments.size(); i++) {
		   		addAppointment(generatedAppointments.get(i));
		   	}
		   		
		   return new Scene(agenda, 1200, 800);	
	}
	public static void addAppointment(Appointment appointment) {
		MyTime startTime = appointment.getIntervalOfAppointment().getStart();
		MyTime endTime = appointment.getIntervalOfAppointment().getEnd();
		
		LocalDateTime start = appointment.getDateOfAppointment().atTime(startTime.getHour(), startTime.getMin());
		LocalDateTime end = appointment.getDateOfAppointment().atTime(endTime.getHour(), endTime.getMin());
		
		String description =  appointment.getProcedureName();
		Long summary = appointment.getPatientNum();
		String group = getStyleClass(description);
		
		agenda.appointments().addAll(
		        new Agenda.AppointmentImplLocal()
		            .withStartLocalDateTime(start)
		            .withEndLocalDateTime(end)
		            .withDescription(description)
		            .withSummary(summary + "")
		            .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass(group))
		    );
	}
	
	
	// I KNOW IT IS FUCKING UGLY, YES I DO, YES I SHOULD USE SWITCH, BUT I HAVE GONE TOO FAR TO CHANGE IT NOW
	public static String getStyleClass(String procedureName) {
		
		if (procedureName.equals("Ultrazvuk")) {
			return "group1";
		}
		if (procedureName.equals("Magnet")) {
			return "group2";
		}
		if (procedureName.equals("Diadynamic")) {
			return "group3";
		}
		if (procedureName.equals("Oxygenoterapia")) {
			return "group4";
		}
		if (procedureName.equals("Laser")) {
			return "group5";
		}
		if (procedureName.equals("Virivka vodna")) {
			return "group6";
		}
		if (procedureName.equals("Galvan, RS prudy")) {
			return "group7";
		}
		if (procedureName.equals("LTV")) {
			return "group8";
		}
		if (procedureName.equals("Reflexna masaz")) {
			return "group9";
		}
		if (procedureName.equals("Lymfodrenaz")) {
			return "group10";
		}
		if (procedureName.equals("Makke techniky")) {
			return "group11";
		}
		if (procedureName.equals("Perlickovy kupel")) {
			return "group12";
		}
		if (procedureName.equals("Hydromasazna vana")) {
			return "group13";
		}
		if (procedureName.equals("Skotsky postrek")) {
			return "group14";
		}
		if (procedureName.equals("Suchy zabal")) {
			return "group15";
		}
		if (procedureName.equals("Masaz klasicka")) {
			return "group16";
		}
		if (procedureName.equals("Parafin, raselina")) {
			return "group17";
		}
		if (procedureName.equals("Biolampa")) {
			return "group18";
		}	
		if (procedureName.equals("Diathermia")) {
			return "group19";
		}	
		if (procedureName.equals("Solux")) {
			return "group20";
		}
		if (procedureName.equals("Inhalacia")) {
			return "group21";
		}
		if (procedureName.equals("Speleoterapia")) {
			return "group22";
		}
		return "group0";
	}
}
