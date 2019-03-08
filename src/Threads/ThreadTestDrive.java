package Threads;

class ThreadTestDrive {
    public static void main(String[] args) {


        Runnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob);
        myThread.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Back to da Choppa!");


    }
}
