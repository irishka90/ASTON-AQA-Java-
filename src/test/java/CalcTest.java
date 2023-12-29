
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.options.BaseOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class CalcTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() {
        var options = new BaseOptions()
                .amend("appium:deviceName", "might")
                .amend("platformName", "Android")
                .amend("appium:ensureWebviewsHavePages", true)
                .amend("appium:nativeWebScreenshot", true)
                .amend("appium:newCommandTimeout", 3600)
                .amend("appium:connectHardwareKeyboard", true);

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void sampleTest() {

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

