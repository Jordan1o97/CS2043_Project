package financialTracker;

import java.util.ArrayList;

public class Category {
	private int id;
	private static int idGenerator = 0;
	private String name;
	private ArrayList<String> tags = new ArrayList<String>();
	private double rewardPointRatio;
	
	
	public Category(String name) {
		id = idGenerator;
		idGenerator++;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getRewardPointRatio() {
		return rewardPointRatio;
	}

	public void setRewardPointRatio(double rewardPointRatio) {
		this.rewardPointRatio = rewardPointRatio;
	}
	
	public ArrayList<String> getTags(){
		return tags;
	}
	
	public void addTag(String tag) {
		boolean duplicateCheck = false;
		for(String i :tags) {
			if(i.equals(tag)) {
				duplicateCheck = true;
				break;
			}
		}
		if(!duplicateCheck) {
			tags.add(tag);
		}
	}
	
	public String toString() {
		String s = "Name : " + name + " id: " + id + " ratio: "+ rewardPointRatio +" Tags: [";
		for(String i: tags) {
			s+= i + ", ";
		}
		s+= "]\n";
		return s;
	}
	
}
