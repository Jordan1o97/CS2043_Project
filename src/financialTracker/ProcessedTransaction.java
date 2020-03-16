package financialTracker;

import java.util.Date;

public class ProcessedTransaction {
	private Date date;
	private String description;
	private double debit;
	private double credit;
	private Buyer buyer;
	private Category category;
	
	public ProcessedTransaction(Date date, String description, double debit, double credit, Buyer buyer, Category category) {
		this.date = date;
		this.description = description;
		this.debit = debit;
		this.credit = credit;
		this.buyer = buyer;
		this.category = category;
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
	
	public String getBuyer() {
		return buyer.getName();
	}
	
	public String getCategory() {
		return category.getName();
	}

	public String toString() {
		String s = "Date : " + date + ", Description: " + description + ", Debit: " + debit + ", Credit: "+ credit + "\n";
		return s;
	}
}
