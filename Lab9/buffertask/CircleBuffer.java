package buffertask;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CircleBuffer {
    private final String[] buffer;
    private int readIdx = 0;
    private int writeIdx = 0;
    private int count = 0;
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull =  lock.newCondition();
    private final Condition notEmpty  =  lock.newCondition();

    public CircleBuffer(int capacity) {
        this.capacity = capacity;
        buffer = new String[capacity];
    }

    public void put(String msg) throws InterruptedException {
        lock.lock();
        try {
            while (count == capacity)
                notFull.await();

            buffer[writeIdx] = msg;
            writeIdx = (writeIdx + 1) % capacity;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String get() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            String msg = buffer[readIdx];
            readIdx = (readIdx + 1) % capacity;
            count--;
            notFull.signal();
            return msg;
        } finally {
            lock.unlock();
        }
    }

}
