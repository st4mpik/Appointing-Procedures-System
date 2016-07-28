package project.domain.mediator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import project.domain.model.Patient;

public class XLSReader {
	
	private HSSFWorkbook workbook;
	private HSSFSheet sheet;
	private int startRow;
	private ArrayList<Patient> patients;
	
	public XLSReader(String filepath, int startRow) throws FileNotFoundException, IOException {
		this.workbook = new HSSFWorkbook(new FileInputStream(filepath));
		this.sheet = workbook.getSheetAt(0);
		this.startRow = startRow;
		this.patients = new ArrayList<Patient>();
	}
	
	public ArrayList<Patient> loadPatientsFromAllRows() throws IOException {
		int counter = startRow;
		while(true) {
			HSSFRow row = sheet.getRow(counter);
			if(row.getCell(0).getStringCellValue().equals("")) {
				workbook.close();
				break;
			};
			this.loadPatientFromRow(row);
			counter++;
		}
		return patients;
	}
	
	private void loadPatientFromRow(HSSFRow row) {
		String fullname = row.getCell(1).getStringCellValue();
		System.out.println(fullname);
		int	stayDuration = (int) row.getCell(6).getNumericCellValue();
		System.out.println(stayDuration);
		LocalDate dateOfArrival = convertToLocalDate(row.getCell(8).getDateCellValue());
		System.out.println(dateOfArrival);
		LocalDate dateOfDeparture = convertToLocalDate(row.getCell(10).getDateCellValue());
		System.out.println(dateOfDeparture);
		String accomodation = row.getCell(11).getStringCellValue();
		System.out.println(accomodation);
		String clientType = row.getCell(14).getStringCellValue();
		System.out.println(clientType);
		String partnership = row.getCell(15).getStringCellValue();
		System.out.println(partnership);
		int age = (int) row.getCell(16).getNumericCellValue();
		System.out.println(age);
		long accomodationClientID = Long.parseLong(row.getCell(19).getStringCellValue());
		System.out.println(accomodationClientID);
		long personIdNum = Long.parseLong(row.getCell(21).getStringCellValue());
		System.out.println(personIdNum);
		String gender = row.getCell(22).getStringCellValue();
		System.out.println(gender);
		
		Patient patient = new Patient(fullname, gender, clientType, 
				accomodation, partnership, age, stayDuration, accomodationClientID, 
				personIdNum, dateOfArrival, dateOfDeparture);
		
		patients.add(patient);
	}
	
	//HELPER METHOD TO CONVERT SQL DATE TO MYDATE
	@SuppressWarnings("deprecation")
	private LocalDate convertToLocalDate(Date date) {
		int day = date.getDate();
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;
		return LocalDate.of(year, month, day);
	}
}
