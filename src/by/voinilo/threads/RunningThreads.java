package threads;

public class RunningThreads implements Runnable{

    public static void main(String[] args) {
        RunningThreads runner = new RunningThreads();
        Thread alpha = new Thread(runner);
        Thread beta = new Thread(runner);

        alpha.setName("alpha");
        beta.setName("beta");
        
        alpha.start();
        beta.start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 125; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
        }

    }
}
