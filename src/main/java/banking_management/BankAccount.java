package banking_management;

import java.util.Date;

public class BankAccount {
    private String accountNumber;
    private Double balance;
    private BalanceControl balanceControl;
    private PassbookEntry passbookEntry;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balanceControl = new BalanceControl();
        this.balance = balanceControl.getBalance();  /* read from file */
        this.passbookEntry = new PassbookEntry();
    }

    public void deposit(Double amount) {
        Date timestamp = new Date();
        balance += amount;
        System.out.printf("%s: Deposit successful. New balance: Rs. %.2f\n", accountNumber, balance);
        balanceControl.setBalance(balance);
        passbookEntry.createTransactionEntry(timestamp, "Credit", accountNumber, amount, balance);
        balanceControl.commitBalance();
    }

    public void withdraw(Double amount) throws IllegalStateException {
        Date timestamp = new Date();
        if (amount > balance) {
            throw new IllegalStateException(accountNumber + ": Insufficient funds. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.printf("%s: Withdrawal successful. New balance: Rs. %.2f\n", accountNumber, balance);
        }
        balanceControl.setBalance(balance);
        passbookEntry.createTransactionEntry(timestamp, "Debit", accountNumber, amount, balance);
        balanceControl.commitBalance();
    }

    public void checkBalance() {
        balance = balanceControl.getBalance();
        System.out.printf("Account balance: Rs. %.2f\n", balance);
    }
}
