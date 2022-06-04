package threadcoordination.joining;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example1 {

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(1000000L, 3435L, 35435L, 2324L, 4656L);

        List<FactorialThread> threads = new ArrayList<>();

        for(long inputNumber: inputNumbers) {
            threads.add(new FactorialThread(inputNumber));
        }

        for(Thread thread: threads) {
            thread.setDaemon(true);
            thread.start();
        }

        for(Thread thread: threads) {
            thread.join(200);
        }

        for(int i=0; i<inputNumbers.size(); ++i) {
            FactorialThread factorialThread = threads.get(i);
            if(factorialThread.isFinished) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

    }

    private static class FactorialThread extends Thread {
        private long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber) {
            this.inputNumber = inputNumber;
        }

        @Override
        public void run() {
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n) {
            BigInteger result = BigInteger.ONE;

            for(long i = n; i>=0; --i) {
                result = result.multiply(new BigInteger(Long.toString(i)));
            }
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}