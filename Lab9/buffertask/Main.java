package buffertask;

class Producer implements Runnable {
    private final CircleBuffer outBuffer;
    private final int id;

    public Producer(int id, CircleBuffer outBuffer) {
        this.id = id;
        this.outBuffer = outBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = "Потік № " + id + " згенерував повідомлення";
                outBuffer.put(msg);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Middleman implements Runnable {
    private final CircleBuffer inBuffer;
    private final CircleBuffer outBuffer;
    private final int id;

    public Middleman(int id, CircleBuffer inBuffer, CircleBuffer outBuffer) {
        this.id = id;
        this.inBuffer = inBuffer;
        this.outBuffer = outBuffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = inBuffer.get();
                String transformed = "Потік № " + id + " переклав повідомлення [" + received + "]";
                outBuffer.put(transformed);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CircleBuffer buffer1 = new CircleBuffer(10);
        CircleBuffer buffer2 = new CircleBuffer(10);

        for (int i = 0; i < 5; i++) {
            Thread producer = new Thread(new Producer(i, buffer1));
            producer.setDaemon(true);
            producer.start();
        }

        for (int i = 0; i < 2; i++) {
            Thread middleman = new Thread(new Middleman(i, buffer1, buffer2));
            middleman.setDaemon(true);
            middleman.start();
        }

        try {
            for (int i = 0; i < 100; i++) {
                String result = buffer2.get();
                System.out.println("Main received " + i + " " + result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
