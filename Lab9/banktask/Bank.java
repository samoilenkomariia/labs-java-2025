package banktask;

import java.util.List;
import java.util.concurrent.locks.Lock;

public class Bank {
    public void transfer(Account from, Account to, int amount) {
        Lock firstLock = from.getId() < to.getId() ? from.getLock() : to.getLock();
        Lock secondLock = from.getId() < to.getId() ? to.getLock() : from.getLock();
        firstLock.lock();
        secondLock.lock();
        try {
            if (from.getBalance() >= amount) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        } finally {
            firstLock.unlock();
            secondLock.unlock();
        }
    }

    public long getTotalBalance(List<Account> accounts) {
        long total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
}
