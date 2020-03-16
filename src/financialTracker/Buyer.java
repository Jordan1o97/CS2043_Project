package financialTracker;

import java.util.ArrayList;

public class Buyer {
	private int id;
	private static int idGenerator = 0;
	private String name;
	private ArrayList<String> identifiers = new ArrayList<String>();
	
	public Buyer(String name) {
		id += idGenerator;
		idGenerator++;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getIdentifiers(){
		return identifiers;
	}
	
	public void addIdentifier(String identifier) {
		boolean duplicateCheck = false;
		for(String i :identifiers) {
			if(i.equals(identifier)) {
				duplicateCheck = true;
				break;
			}
		}
		if(!duplicateCheck) {
			identifiers.add(identifier);
		}
	}
	
	public String toString() {
		String s = "Name : " + name + " id: " + id + " Identifiers: [";
		for(String i: identifiers) {
			s+= i + ", ";
		}
		s+= "]\n";
		return s;
	}
	
	
}
