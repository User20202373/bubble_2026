package thread;

public class Father extends Thread {
    BankAccount bankAccount;

    public Father(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        bankAccount.saveMoney(10_000);
    }
}
