package financialTracker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Account {
	private int id;
	private double balance;
	private ArrayList<Buyer> buyerList = new ArrayList<Buyer>();
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private ArrayList<ProcessedTransaction> processedTransactionList = new ArrayList<ProcessedTransaction>();
	private  Workbook workbook;
	private final String path = "/Users/jordan/School/CS2043/SWE Project/ConfigurationWorkbook.xlsx";
	
	public Account(int id) throws IOException, InvalidFormatException {
		this.id = id;
		workbook = WorkbookFactory.create(new File(path));
		fetchBuyers();
		fetchAccountInfo();
		fetchTransactions();
		fetchCategories();
	}
	
	public int getId() {
		return id;
	}
	
	
	public void fetchAccountInfo() {
		Sheet initSheet = workbook.getSheet("Initialization");
		Row initStartingBalanceRow = initSheet.getRow(1);
		Cell initStartingBalanceCell = initStartingBalanceRow.getCell(1);
		balance = initStartingBalanceCell.getNumericCellValue();
	}
	
	public void fetchBuyers() {
		Sheet initSheet = workbook.getSheet("Identifiers");
		for(Row initBuyers: initSheet) {
			Cell nameCell = initBuyers.getCell(0);
            String name = nameCell.getStringCellValue();
            Buyer buyer = new Buyer(name);
            for(int i = 1; i< initBuyers.getLastCellNum(); i++) { 
            	Cell tempCell = initBuyers.getCell(i);
            	String identifier = tempCell.getStringCellValue();
            	buyer.addIdentifier(identifier);
            }
            buyerList.add(buyer);
		}
	}
	
	public void fetchCategories() {
		Sheet initSheet = workbook.getSheet("Categories");
		for(Row initCategories: initSheet) {
			Cell nameCell = initCategories.getCell(0);
			Cell rewardPointRatioCell = initCategories.getCell(1);
			double ratio = rewardPointRatioCell.getNumericCellValue();
            String name = nameCell.getStringCellValue();
            Category category = new Category(name);
            category.setRewardPointRatio(ratio);
            for(int i = 2; i< initCategories.getLastCellNum(); i++) { 
            	Cell tempCell = initCategories.getCell(i);
            	String tag = tempCell.getStringCellValue();
            	category.addTag(tag);
            }
            categoryList.add(category);
		}
	}
	
	public void fetchTransactions() {
		if(workbook.getSheetName(3).equals("Transactions")) {
			Sheet initSheet = workbook.getSheet("Transactions");
			for(int i = 1; i <= initSheet.getLastRowNum(); i++) {
				Row initTransactions = initSheet.getRow(i);
				Cell dateCell = initTransactions.getCell(0);
				Cell descriptionCell = initTransactions.getCell(1);
				Cell debitCell = initTransactions.getCell(2);
				Cell creditCell = initTransactions.getCell(3);
				String preDate = dateCell.getStringCellValue();
				int month;
				int day;
				int year;
				StringTokenizer st = new StringTokenizer(preDate, "/");
				month = Integer.parseInt(st.nextToken());
				day = Integer.parseInt(st.nextToken());
				year = Integer.parseInt(st.nextToken());
				Date date = new Date(year, month, day);
				String description = descriptionCell.getStringCellValue();
				double debit;
				double credit;
				if(debitCell != null) {
					debit = debitCell.getNumericCellValue();
					balance += debit;
				}
				else {
					debit = 0;
				}
				if(creditCell != null) {
					credit = creditCell.getNumericCellValue();
					balance += credit;
				}
				else {
					credit = 0;
				}
			    Transaction transaction = new Transaction(date, description, debit, credit);
			    transactionList.add(transaction);
			    
			}
		}
		else if(workbook.getSheetName(3).equals("January")) {
			
			for(int j = 3; j < workbook.getNumberOfSheets(); j ++) {
				Sheet initSheet = workbook.getSheetAt(j);
				for(int i = 1; i <= initSheet.getLastRowNum(); i++) {
					Row initTransactions = initSheet.getRow(i);
					Cell dateCell = initTransactions.getCell(0);
					Cell descriptionCell = initTransactions.getCell(1);
					Cell debitCell = initTransactions.getCell(2);
					Cell creditCell = initTransactions.getCell(3);
					String preDate = dateCell.getStringCellValue();
					int month;
					int day;
					int year;
					StringTokenizer st = new StringTokenizer(preDate, "/");
					month = Integer.parseInt(st.nextToken());
					day = Integer.parseInt(st.nextToken());
					year = Integer.parseInt(st.nextToken());
					Date date = new Date(year, month, day);
					String description = descriptionCell.getStringCellValue();
					double debit;
					double credit;
					if(debitCell != null) {
						debit = debitCell.getNumericCellValue();
						balance += debit;
					}
					else {
						debit = 0;
					}
					if(creditCell != null) {
						credit = creditCell.getNumericCellValue();
						balance += credit;
					}
					else {
						credit = 0;
					}
				    Transaction transaction = new Transaction(date, description, debit, credit);
				    transactionList.add(transaction);

				}
			}
		}

	}
	
	public Buyer parseBuyerIdentifier(Transaction t) {
		Buyer temp =  new Buyer("");
		StringTokenizer st = new StringTokenizer(t.getDescription(), " ");
		while(st.hasMoreTokens()) {
			for(Buyer b: buyerList) {
				for(String id: b.getIdentifiers()) {
					if(id.equalsIgnoreCase(st.nextToken())) {
						temp = b;
					}
				}
			}
		}
		return temp;
	}
	
	public Category parseCategoryTag(Transaction t) {
		Category temp =  new Category("");
		StringTokenizer st = new StringTokenizer(t.getDescription(), " ");
		while(st.hasMoreTokens()) {
			for(Category c: categoryList) {
				for(String tag: c.getTags()) {
					if(tag.equalsIgnoreCase(st.nextToken())) {
						temp = c;
					}
				}
			}
		}
		return temp;
	}

	public double getBalance() {
		return balance;
	}
	
	public String buyerTest() {
		String s = "";
		for(Buyer b: buyerList) {
			s+= b;
		}
		return s;
	}
	
	public String categoryTest() {
		String s = "";
		for(Category c: categoryList) {
			s+= c;
		}
		return s;
	}
	
	public String transactionTest() {
		String s = "";
		for(Transaction t: transactionList) {
			s+= t;
		}
		return s;
	}
	
}


