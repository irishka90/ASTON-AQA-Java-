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
    }

    @BeforeEach
    void open() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        WebElement applyCookies = driver.findElement(new By.ByXPath("//*[@id=\"cookie-agree\"]"));
        applyCookies.click();
    }

    @Test
    @DisplayName("Проверить название указанного блока")
    void test1() {
        WebElement titleName = driver.findElement(By.className("pay__wrapper")).findElement(By.tagName("h2"));
        Assertions.assertEquals("Онлайн пополнение\nбез комиссии", titleName.getText());
    }

    @Test
    @DisplayName("Проверить наличие логотипов платёжных систем")
    void test2() {
        WebElement logos = driver.findElement(By.className("pay__partners")).findElement(By.tagName("ul"));
        List<WebElement> elements = logos.findElements(By.tagName("li"));
        Assertions.assertNotEquals(elements.size(), 0);
    }

    @Test
    @DisplayName("Проверить работу ссылки «Подробнее о сервисе»")
    void test3() {
        WebElement link = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a"));
        String linkHref = link.getAttribute("href");
        link.click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains(linkHref));
        Assertions.assertEquals(linkHref, driver.getCurrentUrl());

        WebElement container = driver.findElement(By.className("main"));
        WebElement header = container.findElement(By.className("container-fluid"));

        Assertions.assertFalse(header.getText().isEmpty());
    }

    //Заполнить поля и проверить работу кнопки «Продолжить»
    // (проверяем только вариант «Услуги связи», номер для теста 297777777)

    @Test
    @DisplayName("Заполнить поля и проверить работу кнопки «Продолжить»")
    void test4() {

        WebElement phone = driver.findElement(By.id("connection-phone"));
        phone.click();
        phone.sendKeys("297777777");
        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.click();
        sum.sendKeys("29");
        WebElement button = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        button.click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bepaid-iframe")));
        WebElement paidFrame = driver.findElement(By.cssSelector(".bepaid-iframe"));

        driver.switchTo().frame(paidFrame);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("card-page__card")));

        WebElement buttonPay = driver.findElement(By.className("card-page__card"))
                .findElement(By.tagName("button"));


        Assertions.assertTrue(buttonPay.isDisplayed());
    }

    @AfterEach
    void close() {
        driver.quit();
    }

}
