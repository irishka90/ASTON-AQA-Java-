
import com.google.common.math.DoubleMath;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.event.KeyValuePair;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CalcTest {

    private static final String packetName = "com.miui.calculator";

    private static AndroidDriver driver;

    private final Map<Integer, By> keysNumber = new HashMap<>() {
        {
            put(0, By.id(packetName + ":id/btn_0_s"));
            put(1, By.id(packetName + ":id/btn_1_s"));
            put(2, By.id(packetName + ":id/btn_2_s"));
            put(3, By.id(packetName + ":id/btn_3_s"));
            put(4, By.id(packetName + ":id/btn_4_s"));
            put(5, By.id(packetName + ":id/btn_5_s"));
            put(6, By.id(packetName + ":id/btn_6_s"));
            put(7, By.id(packetName + ":id/btn_7_s"));
            put(8, By.id(packetName + ":id/btn_8_s"));
            put(9, By.id(packetName + ":id/btn_9_s"));
        }
    };

    private final KeyValuePair plus = new KeyValuePair("+", By.id(packetName + ":id/btn_plus_s"));
    private final KeyValuePair mul = new KeyValuePair("×", By.id(packetName + ":id/btn_mul_s"));
    private final KeyValuePair minus = new KeyValuePair("-", By.id(packetName + ":id/btn_minus_s"));
    private final KeyValuePair div = new KeyValuePair("÷", By.id(packetName + ":id/btn_div_s"));

    private final By equal = By.id(packetName + ":id/btn_equal_s");


    private final Random random = new Random();

    @BeforeAll
    public static void setUp() {
        DesiredCapabilities options = new DesiredCapabilities();
        options.setCapability("platformName", "Android");
        options.setCapability("appium:deviceName", "might");
        options.setCapability("noReset", "true");
        options.setCapability("full-reset", false);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
        driver.activateApp(packetName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @BeforeEach
    private void clearOldResult() {
        driver.findElement(By.id(packetName + ":id/btn_c_s")).click();
        Assertions.assertEquals("0", getContent());
    }

    private String getContent() {
        return driver.findElement(By.id(packetName + ":id/expression")).getAttribute("content-desc");
    }

    private String getResult() {
        return driver.findElement(By.id(packetName + ":id/result")).getText();
    }


    private By getRandomNumber() {
        int position = random.nextInt(keysNumber.size());
        return keysNumber.get(position);
    }

    private By getRandomNumberDiv() {
        int position = random.nextInt(keysNumber.size()) + 1;
        return keysNumber.get(position);
    }

    @Test
    @DisplayName("Сложение")
    public void test1() {
        String result = operation(plus, (first, second) -> String.valueOf(first + second));
        Assertions.assertEquals("= " + result, getResult());
    }

    @Test
    @DisplayName("Вычитание")
    public void test2() {
        String result = operation(minus, (first, second) -> String.valueOf(first - second));
        Assertions.assertEquals("= " + result, getResult());
    }

    @Test
    @DisplayName("Умножение")
    public void test3() {
        String result = operation(mul, (first, second) -> String.valueOf(first * second));
        Assertions.assertEquals("= " + result, getResult());
    }


    @Test
    @DisplayName("Деление")
    public void test4() {
        String result = operation(div, (first, second) -> String.valueOf((double) first / (double) second));

        double resultCalc = Double.parseDouble(result);
        double resultContent = Double.parseDouble(getResult().substring(2).replace(",", "."));

        Assertions.assertTrue(DoubleMath.fuzzyEquals(resultCalc, resultContent, 0.000001d));

        Assertions.assertTrue(getResult().startsWith("= "));

    }


    private String operation(KeyValuePair pair, OperationCalc callback) {
        WebElement first = driver.findElement(getRandomNumber());
        WebElement second = driver.findElement(pair == div ? getRandomNumberDiv() : getRandomNumber());

        first.click();
        Assertions.assertEquals(first.getText(), getContent());

        driver.findElement((By) pair.value).click();

        Assertions.assertEquals(first.getText() + pair.key, getContent());
        second.click();
        Assertions.assertEquals(first.getText() + pair.key + second.getText(), getContent());
        driver.findElement(equal).click();

        return callback.apply(Integer.parseInt(first.getText()), Integer.parseInt(second.getText()));
    }

    @AfterAll
    static void terminateApp() {
        driver.terminateApp(packetName);
    }
}

