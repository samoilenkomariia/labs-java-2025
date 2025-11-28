import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of threads: ");
            int numOfThreads = scanner.nextInt();
            long iters = 1_000_000L;
            long itersPerThread = iters / numOfThreads;
            ParallelMonteCarloPi[] dotsCounters = new ParallelMonteCarloPi[numOfThreads];
            Thread[] threads = new Thread[numOfThreads];

            long startTime = System.nanoTime();

            for (int i = 0; i < numOfThreads; i++) {
                dotsCounters[i] = new ParallelMonteCarloPi(itersPerThread);
                threads[i] = new Thread(dotsCounters[i]);
                threads[i].start();
            }
            long dots = 0L;
            for (int i = 0; i < numOfThreads; i++) {
                try {
                    threads[i].join();
                    dots += dotsCounters[i].getDotsInCircle();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            double pi = 4.0 * dots/iters;
            long endTime = System.nanoTime();

            double totalTime = (endTime - startTime)/1000000.0;

            System.out.println("PI is " + pi + "\nStandart PI " + Math.PI);
            System.out.println("THREADS " +  numOfThreads);
            System.out.println("ITERATIONS " +  iters);
            System.out.println("TIME " + totalTime + "ms");

        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
