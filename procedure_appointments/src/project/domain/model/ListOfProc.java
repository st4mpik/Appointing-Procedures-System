package project.domain.model;

import java.util.ArrayList;

public class ListOfProc {
	
	private ArrayList<Procedure> listOfProcedures;
	
	public ListOfProc(ArrayList<Procedure> listOfProcedures) {
		this.listOfProcedures = listOfProcedures;
	}
	
	public void addProcedure(Procedure procedure) {
		listOfProcedures.add(procedure);
	}
	
	
	public void getProcedure(int index) {
		listOfProcedures.get(index);
	}
	
	public ArrayList<Procedure> getListOfProcedures() {
		return listOfProcedures;
	}
}
