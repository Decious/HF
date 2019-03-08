package Threads;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        go();
    }

    private void go() {
        doMore();
    }

    private void doMore() {
        System.out.println("**********");
    }



    public static void main(String[] args) {
        Runnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob);
        ;
        System.out.println("Back to da Choppa!");
        myThread.start();

    }
}
