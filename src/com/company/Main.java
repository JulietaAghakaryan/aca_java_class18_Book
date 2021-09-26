package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println(Thread.currentThread().getName());

        var thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep");
            }
        });
        thread.start();
        throw new UnsupportedOperationException();
    }
}
