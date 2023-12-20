import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AppTest {

    static WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
    }

    @BeforeAll
    static void applyCookies() {
        WebElement applyCookies = driver.findElement(new By.ByXPath("//*[@id=\"cookie-agree\"]"));
        applyCookies.click();
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях каждого " +
            "варианта оплаты услуг: услуги связи, домашний интернет, рассрочка, задолженность")
    void test1() {
         WebElement selectButton = driver.findElement(new By.ByClassName("select__header"));

        for (int i = 0; i < 4; i++) {
            selectButton.click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("select__list")));

            WebElement selectList = driver.findElement(new By.ByClassName("select__list"));
            List<WebElement> listItems = selectList.findElements(new By.ByTagName("li"));

            WebElement itemSelected = listItems.get(i);
            itemSelected.click();

            String selectNow = driver.findElement(new By.ByClassName("select__now")).getText();
            switch (selectNow) {
                case "Услуги связи":
                    Assertions.assertEquals(driver.findElement(new By.ById("connection-phone")).getAttribute("placeholder"), "Номер телефона");
                    break;
                case "Домашний интернет":
                    Assertions.assertEquals(driver.findElement(new By.ById("internet-phone")).getAttribute("placeholder"), "Номер абонента");
                    break;
                case "Рассрочка":
                    Assertions.assertEquals(driver.findElement(new By.ById("score-instalment")).getAttribute("placeholder"), "Номер счета на 44");
                    break;
                case "Задолженность":
                    Assertions.assertEquals(driver.findElement(new By.ById("score-arrears")).getAttribute("placeholder"), "Номер счета на 2073");
                    break;
            }


            String sum = driver.findElement(new By.ByClassName("total_rub")).getAttribute("placeholder");
            Assertions.assertEquals("Сумма", sum);

            String email = driver.findElement(new By.ByClassName("email")).getAttribute("placeholder");
            Assertions.assertEquals(email, "E-mail для отправки чека");

        }







    }



    @AfterAll
    static void close() {
        driver.quit();
    }

}
