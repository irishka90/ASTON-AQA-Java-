

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.aston.Fact;
import org.aston.FactorialException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AstonJUnitTests {
    @Test
    @DisplayName("Нахождение факториала")
    public void factorial1() throws Exception {
        System.out.println("======TEST  fact 4 =======");
        Assertions.assertEquals(24, Fact.getFactorial(4), "Errror");
    }

    @Test()
    public void factorial2() throws Exception {
        System.out.println("======TEST  fact -1 =======");

        Exception exception = Assertions.assertThrows(FactorialException.class, () -> {
            Fact.getFactorial(-1);
        });

        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains("Значение должно быть от 0 до 20"));
    }

    @Test()
    public void factorial3() throws Exception {
        System.out.println("======TEST  fact 21 =======");

        Exception exception = Assertions.assertThrows(FactorialException.class, () -> {
            Fact.getFactorial(21);
        });

        String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains("Значение должно быть от 0 до 20"));
    }


}
