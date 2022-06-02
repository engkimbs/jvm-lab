package threadcreation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Example2 {

    private static int MAX_PASSWORD = 999;

    public static void main(String[] args) {
        Random random = new Random();
        int password = random.nextInt(MAX_PASSWORD);
        System.out.println("password : " + password);
        Vault vault = new Vault(password);

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for(Thread thread : threads) {
            thread.start();
        }
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    public static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess = 0; guess < MAX_PASSWORD; guess++) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getThreadGroup() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {
        private int MAX_PASSWORD =9999;;

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for(int guess = MAX_PASSWORD; guess >=0 ; guess--) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.println(this.getThreadGroup() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    public static class PoliceThread extends Thread {
        @Override
        public void run() {
            for(int i=10; i>0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }

            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }

}
