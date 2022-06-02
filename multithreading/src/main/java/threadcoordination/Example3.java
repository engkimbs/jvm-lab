package threadcoordination;

import java.math.BigInteger;
import java.util.*;

public class Example3 {

    public static final Integer MOD = 1000_000_000 + 7;

    public static void main(String[] args) {
        //Thread t = new Thread(new LongComputationTask(new BigInteger("2"), new BigInteger("12000000")));
//
//        List<Integer> arr = new ArrayList<>(){{
//            add(1);
//            add(5);
//            add(6);
//            add(4);
//        }};
//

        System.out.println(solution(10000, new ArrayList<>(){{
            add(3);
            add(5);
            add(9);
            add(25);
            add(100);
            add(200);
            add(500);
            add(1250);
            add(6000);
        }}));
    }

    public static int solution(int n, List<Integer> infectedHouses) {
        // Write your code here

        // ans
        // (number of houses in day i) => ni
        // ans ( perm(n1) * perm(n2) * perm(n3) ... ) % mod
        // = ((perm(n1)%mod) * perm(n2)%mod * perm(n3)%mod)
        // = (pn11%mod * pn12%mod ... * 1%mod) * (pn21%mod*pn22%mod*pn23%mod ... * 1%mod) * ...

        // number of houses in day i
        //  1. get list of infectedHouse
        //  2. get list of house to be infected (using infectedHouseset)
        //  3. add list of house to infectedHouseSet
        //  4. calc perm % mod of ni
        //  5. result *= (perm % mod) % mod
        //  6. Until infectedHouseSet size is equal to n


        Set<Integer> infectedHouseSet = new HashSet<>(infectedHouses);

        long result = 1L;

        List<Integer> currentInfectedHouseList = new ArrayList<>(infectedHouses);

        while(infectedHouseSet.size() != n) {
            List<Integer> toBeinfectedHouseList = new ArrayList<>();

            for (Integer infectedHouseNumber : currentInfectedHouseList) {
                Integer left = infectedHouseNumber - 1;
                if (!infectedHouseSet.contains(left) && left >= 1) {
                    toBeinfectedHouseList.add(left);
                    infectedHouseSet.add(left);
                }

                Integer right = infectedHouseNumber + 1;
                if (!infectedHouseSet.contains(right) && right <= n) {
                    toBeinfectedHouseList.add(right);
                    infectedHouseSet.add(right);
                }
            }

            result = ((result % MOD) * (getPermMod(toBeinfectedHouseList.size()) % MOD))%MOD;

            currentInfectedHouseList = toBeinfectedHouseList;
        }

        return (int)(result % MOD);
    }

    public static Long getPermMod(int n) {
        long result = 1L;
        for(int i=1; i<=n; ++i)
            result = ((result % MOD)*(i % MOD))%MOD;

        return result % MOD;
    }
//
//    public static int usingBinarySearch(int value, int[] a) {
//        if (value <= a[0]) { return 0; }
//        if (value >= a[a.length - 1]) { return a.length - 1; }
//
//        int result = Arrays.binarySearch(a, value);
//        if (result >= 0) { return result; }
//        System.out.println(result);
//        return -result - 1;
////        int insertionPoint = -result - 1;
////        return (a[insertionPoint] - value) < (value - a[insertionPoint - 1]) ?
////                a[insertionPoint] : a[insertionPoint - 1];
//    }
//
//    private static class LongComputationTask implements Runnable {
//
//        private BigInteger base;
//        private BigInteger power;
//
//        public LongComputationTask(BigInteger base, BigInteger power) {
//            this.base = base;
//            this.power = power;
//        }
//
//        @Override
//        public void run() {
//            System.out.println(base + "^" + power + " = " + pow(base, power));
//        }
//
//        private BigInteger pow(BigInteger base, BigInteger power) {
//            BigInteger result = BigInteger.ONE;
//            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)) {
//                if(Thread.currentThread().isInterrupted()) {
//                    System.out.println("Prematurely interrupted computation");
//                    return BigInteger.ZERO;
//                }
//                result = result.multiply(base);
//            }
//
//            return result;
//        }
//    }
}