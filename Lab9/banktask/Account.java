package banktask;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final Lock lock = new ReentrantLock();
    private static int idCounter = 0;
    private final int id;
    private int balance;

    public Account(int balance) {
        id = ++idCounter;
        this.balance = balance;
    }

    public void withdraw(int amount) {
        if (balance >= amount) balance -= amount;
    }

    public void deposit(int amount) {
        if (amount > 0) balance += amount;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }
}
