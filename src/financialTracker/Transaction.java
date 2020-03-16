package financialTracker;

import java.util.Date;

public class Transaction {
	private Date date;
	private String description;
	private double debit;
	private double credit;
	
	public Transaction(Date date, String description, double debit, double credit) {
		this.date = date;
		this.description = description;
		this.debit = debit;
		this.credit = credit;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getDebit() {
		return debit;
	}
	
	public double getCredit() {
		return credit;
	}

	public String toString() {
		String s = "Date : " + date + ", Description: " + description + ", Debit: " + debit + ", Credit: "+ credit + "\n";
		return s;
	}
}
