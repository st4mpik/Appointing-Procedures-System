package project.domain.mediator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.model.Appointment;
import project.domain.model.Interval;
import project.domain.model.MyTime;
import project.domain.model.Patient;
import project.domain.model.Procedure;

public class Database implements Storage {

	public Database() throws SQLException {
		DriverManager.registerDriver(new org.postgresql.Driver());
	}

	public Connection getConnectionDatabase() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
				"abrakadabra2357");
		return connection;
	}

	// PATIENTS
	// --------------------------------------------------------------------------
	public void addPatient(Patient patient) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Patients VALUES(" + "'"
					+ patient.getFullname() + "' , " + "'" + patient.getGender() + "' , " + "'"
					+ patient.getClientType() + "' , " + "'" + patient.getAccomodation() + "' , " + "'"
					+ patient.getPartnership() + "' , " + patient.getAge() + " , " + patient.getStayDuration() + " , "
					+ patient.getAccomodationClientID() + " , " + patient.getPersonIdNum() + " , " + "'"
					+ patient.getDateOfArrival() + "' , " + "'" + patient.getDateOfDeparture() + "'" + ");");
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}

	public Patient searchForPatient(long personIdNum) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Patients WHERE personIDNum =" + personIdNum+ ";");
			ResultSet result = statement.executeQuery();

			return convertResultSetToPatients(result).get(0);

		} finally {
			connection.close();
		}
	}

	public ObservableList<Patient> getAllPatients() throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patients");
			ResultSet result = statement.executeQuery();
			return convertResultSetToPatients(result);

		} finally {
			connection.close();
		}
	}

	public void deletePatient(long personIdNum) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM Patients WHERE personIdNum =" + personIdNum + ";");
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}
	
	// FOR FILTERING
	
		public ObservableList<Patient> getAllTodayPatients() throws SQLException {
			Connection connection = getConnectionDatabase();

			try {
				String today = "today";
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patients WHERE status = '" + 
					today +	"';");
				ResultSet result = statement.executeQuery();
				return convertResultSetToPatients(result);

			} finally {
				connection.close();
			}
		}
		
		public ObservableList<Patient> getAllInProgressPatients() throws SQLException {
			Connection connection = getConnectionDatabase();

			try {
				String progress = "progress";
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patients WHERE status = '" + 
					progress +	"';");
				ResultSet result = statement.executeQuery();
				return convertResultSetToPatients(result);

			} finally {
				connection.close();
			}
		}
		
		public ObservableList<Patient> getAllArchivedPatients() throws SQLException {
			Connection connection = getConnectionDatabase();

			try {
				String archived = "archived";
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM Patients WHERE status = '" + 
						archived +	"';");
				ResultSet result = statement.executeQuery();
				return convertResultSetToPatients(result);

			} finally {
				connection.close();
			}
		}
		
		// UPDATE STATUS 
		// TODO GET IT TO GENERATOR
		public void updatePatientStatus(Long patientNum, String status) throws SQLException {
			Connection connection = getConnectionDatabase();

			try {
				PreparedStatement statement = connection.prepareStatement("UPDATE Patients SET status = '" + status + 
						"' WHERE patientIdNum =" + patientNum + " ;");
				statement.executeUpdate();

			} finally {
				connection.close();
			}
		}
		
		// UPDATE STATUS TO ARCHVIED
			public void updatePatientStatusToArchived() throws SQLException {
				Connection connection = getConnectionDatabase();

				try {
					String status = "archived";
					java.util.Date today = new java.util.Date();
					@SuppressWarnings("deprecation")
					java.sql.Date todaySql = new Date(today.getYear()+ 1900, today.getMonth() + 1, today.getDate());
					PreparedStatement statement = connection.prepareStatement("UPDATE Patients SET status = '" + status + 
							"' WHERE dateOfDeparture > '" + todaySql  + "' ;");
					statement.executeUpdate();

				} finally {
					connection.close();
				}
			}

	// HELPER METHODS
	private ObservableList<Patient> convertResultSetToPatients(ResultSet result) throws SQLException {
		ObservableList<Patient> patients = FXCollections.observableArrayList();
		while (result.next()) {
			patients.add(new Patient(result.getString("fullname"), result.getString("gender"),
					result.getString("clientType"), result.getString("accomodation"), result.getString("partnership"),
					result.getInt("age"), result.getInt("stayDuration"), result.getLong("accomodationClientID"),
					result.getLong("personIDNum"), convertToLocalDate(result.getDate("dateOfArrival")),
					convertToLocalDate(result.getDate("dateOfDeparture"))));

		}
		;
		return patients;
	}

	// HELPER METHOD TO CONVERT TO MY DATE FROM SQL DATE
	@SuppressWarnings("deprecation")
	private LocalDate convertToLocalDate(Date date) {
		int day = date.getDate();
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;
		return LocalDate.of(year, month, day);
	}
	@SuppressWarnings("deprecation")
	private java.sql.Date convertToSqlDate(LocalDate date) {
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		int year = date.getYear();
		return new java.sql.Date(year, month, day);
	}

	// PROCEDURES
	// ----------------------------------------------------------------------

	public void addProcedure(Procedure procedure) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO procedures VALUES("
					+ "'" + procedure.getName() + "', '" + procedure.getDepartment() + "', " +  procedure.getCapacity() + " , " +
					procedure.getDuration() + ", TIME ' " + procedure.getIntervalOfProcedure().getStart().toString() +
					"', TIME '" + procedure.getIntervalOfProcedure().getEnd().toString() + "');"
					);
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}

	public void deleteProcedure(String name) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM Procedures WHERE name =" + "'" + name + "' ;");
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}

	public Procedure searchForProcedure(String name) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Procedures WHERE name = '" + name + "';");
			ResultSet result = statement.executeQuery();

			return convertResultSetToProcedures(result).get(0);

		} finally {
			connection.close();
		}
	}

	public ObservableList<Procedure> getAllProcedures() throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Procedures");
			ResultSet result = statement.executeQuery();
			return convertResultSetToProcedures(result);

		} finally {
			connection.close();
		}
	}

	// HELPER METHODS
	private ObservableList<Procedure> convertResultSetToProcedures(ResultSet result) throws SQLException {
		ObservableList<Procedure> procedures = FXCollections.observableArrayList();
		;
		while (result.next()) {
			procedures.add(new Procedure(result.getString("name"), result.getString("department"),
					result.getInt("capacity"), result.getInt("duration"),
					convertToInterval(result.getTime("startTime"), result.getTime("endTime"))));

		}
		;
		return procedures;
	}

	// HELPER METHOD TO CONVERT START AND END TIME TO INTERVAL
	private Interval convertToInterval(Time startTime, Time endTime) {
		return new Interval(convertToMyTime(startTime), convertToMyTime(endTime));
	}

	// HELPER METHOD TO CONVERT SQL TO TIME TO MYTIME
	@SuppressWarnings("deprecation")
	private MyTime convertToMyTime(Time time) {
		return new MyTime(time.getHours(), time.getMinutes());
	}

	//
	// APPOINTMENTS----------------------------------------------------------------------------------

	public void addAppointment(Appointment appointment) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection.prepareStatement("INSERT INTO Appointments VALUES("+
					appointment.getPatientNum() + ", '" + appointment.getProcedureName() + "' , '" +
					appointment.getDateOfAppointment() + "' , " + appointment.getNumberOfList() 
					+ " , TIME ' " + appointment.getIntervalOfAppointment().getStart().toString() + "' , "
							+ "TIME ' " + appointment.getIntervalOfAppointment().getEnd().toString() + " ' " +
					");");
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}
	
	public ObservableList<Appointment> getAllAppointments() throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Appointments");
			ResultSet result = statement.executeQuery();
			return convertResultSetToAppointments(result);

		} finally {
			connection.close();
		}
	}
	
	public ArrayList<Appointment> searchForAppointments(LocalDate date, String procedureName, int numberOfList) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			java.sql.Date tempDate = convertToSqlDate(date);
			PreparedStatement statement = connection
					.prepareStatement("SELECT * FROM Appointments WHERE appointmentDate = '" + tempDate + 
							"' AND procedureName ='"+ procedureName + "' AND numberOfList = " + numberOfList + ";");
			ResultSet result = statement.executeQuery();

			return convertResultSetToAppointmentsList(result);

		} finally {
			connection.close();
		}
		
	}

	// HELPER METHODS
	private ObservableList<Appointment> convertResultSetToAppointments(ResultSet result) throws SQLException {
		ObservableList<Appointment> appointments = FXCollections.observableArrayList();
		;
		while (result.next()) {
			appointments.add(new Appointment(result.getLong("patientIDNum"), result.getString("procedureName"),
					convertToLocalDate(result.getDate("appointmentDate")),
					convertToInterval(result.getTime("startTime"), result.getTime("endTime")), 
					result.getInt("numberOfList")));

		};
		return appointments;
	}
	private ArrayList<Appointment> convertResultSetToAppointmentsList(ResultSet result) throws SQLException {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		;
		while (result.next()) {
			
			appointments.add(new Appointment(result.getLong("patientIDNum"), result.getString("procedureName"),
					convertToLocalDate(result.getDate("appointmentDate")),
					convertToInterval(result.getTime("startTime"), result.getTime("endTime")), 
					result.getInt("numberOfList")));

		}
		;
		return appointments;
	}

	public void deleteAppointment(long appointmentId) throws SQLException {
		Connection connection = getConnectionDatabase();

		try {
			PreparedStatement statement = connection
					.prepareStatement("DELETE FROM Appointments WHERE appointmentID =" + +appointmentId + " ;");
			statement.executeUpdate();

		} finally {
			connection.close();
		}
	}
}
