package org.aston;


public class Fact {

    public static long getFactorial(long f) throws Exception {
        if (f > 20 || f < 0) {
            throw new FactorialException();
        }
        long result = 1;
        for (long i = 1; i <= f; i++) {
            result = result * i;
        }
        return result;
    }
}
