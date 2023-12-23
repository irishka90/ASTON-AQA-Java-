import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


// Сделать скриншот экрана, нужно для отладки
public final class TestUtil {
    public static void shot(WebDriver driver, String name) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(name + ".png"));
        } catch (IOException e) {
            //
        }
    }

    public static void sleep(long value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException ex) {
            //
        }
    }
}
