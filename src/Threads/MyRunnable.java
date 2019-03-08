package Threads;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        go();
    }

    public void go() {
        doMore();
    }

    public void doMore() {
        System.out.println("вершина стека");
    }
}


class ThreadTestDrive {
    public static void main(String[] args) {



            Runnable threadJob = new MyRunnable();
            Thread myThread = new Thread(threadJob);
            myThread.start();


            System.out.println("Back to da Choppa!");


    }
}
