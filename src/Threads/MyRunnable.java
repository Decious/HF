package Threads;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        go();
    }

    public void go() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doMore();
    }

    public void doMore() {
        System.out.println("вершина стека");
    }
}


