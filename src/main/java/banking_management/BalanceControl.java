package banking_management;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BalanceControl {
	static String balanceFileName = "../../textfiles/balance.txt";
	static File file = new File(balanceFileName);
	static Double balance;

	BalanceControl() {
		readBalance();
	}

	private void readBalance() {
		if (file.exists()) {
			try {
				BufferedReader buffReader = new BufferedReader(new FileReader(file));
				balance = Double.parseDouble(buffReader.readLine());
				buffReader.close();
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void writeBalance() {
		if (file.exists()) {
			try {
				BufferedWriter buffWriter = new BufferedWriter(new FileWriter(file));
				buffWriter.write(String.valueOf(balance));
				buffWriter.close();
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public Double getBalance() {
		readBalance();  /* read updated value */
		return balance;
	}

	public void setBalance(Double balanceValue) throws IllegalArgumentException {
		if (balanceValue < 0) throw new IllegalArgumentException("Balance cannot be negative");
		balance = balanceValue;
	}

	public void commitBalance() {
		writeBalance();
	}
}
