package banking_management;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import java.text.DateFormat;
import java.util.List;
import java.util.Arrays;

import utils.TableGenerator;

public class PassbookEntry {
	private String parent = "../../textfiles/";	/* relative path w.r.t. banking_management directory */
	private String filename = "passbook.txt";
	private File file;
	private List<String> entry;

	PassbookEntry() {
		file = new File(parent, filename);
	}

	PassbookEntry(String filename) {
		file = new File(parent, filename);
	}

	private void writeToPassbook(String txnEntry) throws IOException {
		List<String> headersList = Arrays.asList("Timestamp", "User ID", "Transaction Type", "Amount", "Balance");
		List<List<String>> rowsList = Arrays.asList(entry);
		TableGenerator tableGenerator = new TableGenerator();
		if (file.exists()) {
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file, true)); /* important!! open in append mode */
			buffWriter.write(tableGenerator.generateRow(headersList, rowsList));
			buffWriter.close();
		} else {
			try {
				file.createNewFile();
			} catch(IOException ioe) {
				System.out.println(ioe.getMessage());
			}
			BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file));
			buffWriter.write(tableGenerator.generateTable(headersList, rowsList));
			buffWriter.close();
		}
	}

	private String getTransactionEntry(Date timestamp, String txnType, String userName, Double amount, Double balance) {
		String amountString = String.format("Rs. %.2f", amount);
		String balanceString = String.format("Rs. %.2f", balance);
		String txnTimestamp = getTransactionTimeStamp(timestamp);
		entry = Arrays.asList(txnTimestamp, userName, txnType, amountString, balanceString);
		return "|\t"+txnTimestamp+"\t|\t"+userName+"\t|\t"+txnType+"\t|\t"+amountString+"\t|\t"+balanceString+" \t|";
	}

	public void createTransactionEntry(Date timestamp, String txnType, String userName, Double amount, Double balance) {
		String txnEntry = getTransactionEntry(timestamp, txnType, userName, amount, balance);
		try {
			writeToPassbook(txnEntry);
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}

	private String getTransactionTimeStamp(Date date) {
		Locale locale = Locale.FRANCE;  
		/* getting the instance by invoking the getDateTimeInstance(int, int, Locale) method  */
		DateFormat dFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.LONG, locale);  
		String dateFormat = dFormat.format(date);  
		return dateFormat;
	}
}
