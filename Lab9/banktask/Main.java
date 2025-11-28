package banktask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        List<Account> accounts = new ArrayList<>();
        int numOfAccounts = 1000;
        int numOfThreads = 10000;
        Random random = new Random();
        for (int i = 0; i < numOfAccounts; i++) {
            accounts.add(new Account(1000 + random.nextInt(1000)));
        }
        long totalBefore = bank.getTotalBalance(accounts);
        System.out.println("Total balance before transactions: " + totalBefore);

        ExecutorService executor = Executors.newFixedThreadPool(200);
        for (int i = 0; i < numOfThreads; i++) {
            executor.submit(() -> {
                Account from = accounts.get(random.nextInt(numOfAccounts));
                Account to = accounts.get(random.nextInt(numOfAccounts));
                int amount = random.nextInt(1, from.getBalance()+1);
                bank.transfer(from, to, amount);
            });
        }
        executor.shutdown();
        if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("All transactions have been finished");
        } else {
            System.out.println("Timed out");
        }
        long  totalAfter = bank.getTotalBalance(accounts);
        System.out.println("Total balance after transactions: " + totalAfter);
        if (totalAfter == totalBefore) {
            System.out.println("Everything is ok");
        } else {
            System.out.println("Data leak :((");
        }
    }
}
