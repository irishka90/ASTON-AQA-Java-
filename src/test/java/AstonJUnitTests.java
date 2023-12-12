

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.aston.Fact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AstonJUnitTests {
    @Test
    @DisplayName("Нахождение факториала")
    public void Factorial() {
        System.out.println("======TEST  fact 4 =======");
        Assertions.assertEquals(24, Fact.getFactorial(4), "Errror");
    }

}
