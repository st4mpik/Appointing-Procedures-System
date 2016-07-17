package project.domain.mediator;

import java.util.ArrayList;

import project.domain.model.ListOfProc;
import project.domain.model.Procedure;

public class Generator {
	
	private ListOfProc listOfProc;
	
	public Generator(ArrayList<Procedure> listOfProcedures) {
		listOfProc = new ListOfProc(listOfProcedures);
	}
}
