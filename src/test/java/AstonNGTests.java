

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.aston.Fact;
import org.aston.FactorialException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AstonNGTests {
    @Test
    public void factorial1() throws Exception {
        System.out.println("======TEST  fact 4 =======");
        Assert.assertEquals(24, Fact.getFactorial(4), "Errror");
    }

    @Test(expectedExceptions = FactorialException.class)
    public void factorial2() throws Exception {
        System.out.println("======TEST  fact -1 =======");
        Fact.getFactorial(-1);
    }

    @Test(expectedExceptions = FactorialException.class)
    public void factorial3() throws Exception {
        System.out.println("======TEST  fact 21 =======");
        Fact.getFactorial(21);
    }
}
