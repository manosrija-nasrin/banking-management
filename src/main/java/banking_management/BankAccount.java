package banking_management;

public class BankAccount {
    private String accountNumber;
    private double balance;
    BalanceControl balanceControl;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        balanceControl = new BalanceControl();
        this.balance = balanceControl.getBalance();  /* read from file */
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.printf("%s: Deposit successful. New balance: Rs. %.2f\n", accountNumber, balance);
        balanceControl.setBalance(balance);
        balanceControl.commitBalance();
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println(accountNumber + "Insufficient funds. Withdrawal failed.");
        } else {
            balance -= amount;
            System.out.printf("%s: Withdrawal successful. New balance: Rs. %.2f\n", accountNumber, balance);
        }
        balanceControl.setBalance(balance);
        balanceControl.commitBalance();
    }

    public void checkBalance() {
        balance = balanceControl.getBalance();
        System.out.printf("Account balance: Rs. %.2f\n", balance);
    }
}
