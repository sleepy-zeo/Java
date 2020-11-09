package com.sleepy.zeo.shutdownhook;

public class ShutdownHook {

    public static void main(String[] args) {
        System.out.println("main started...");
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("prepare to exit...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exit successfully");
            }
        }));
        int sign = 0;
        while (true) {
            try {
                Thread.sleep(1000);
                if (sign++ > 5) {
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
