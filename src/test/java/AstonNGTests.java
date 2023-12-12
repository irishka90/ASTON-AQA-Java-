

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.aston.Fact;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AstonNGTests {
    @Test
    public void factorial() throws Exception {
        System.out.println("======TEST  fact 4 =======");
        Assert.assertEquals(24, Fact.getFactorial(4), "Errror");
    }


}
