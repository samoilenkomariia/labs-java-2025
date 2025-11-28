import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelMonteCarloPi implements Runnable {
    private final long iters;
    private long dotsInCircle;

    public  ParallelMonteCarloPi(long iters) {
        this.iters = iters;
    }

    @Override
    public void run() {
        for (long i = 0; i < iters; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            if ((x*x + y*y) <= 1)
                dotsInCircle++;
        }
    }
    public long getDotsInCircle() {
        return dotsInCircle;
    }
}
