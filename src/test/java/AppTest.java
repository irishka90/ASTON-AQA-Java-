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
    void applyCookies() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        WebElement applyCookies = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
        applyCookies.click();
    }

    @Test
    @DisplayName("Проверить надписи в незаполненных полях каждого " +
            "варианта оплаты услуг: услуги связи, домашний интернет, рассрочка, задолженность")
    void test1() {
        WebElement selectButton = driver.findElement(By.className("select__header"));

        for (int i = 0; i < 4; i++) {
            selectButton.click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.className("select__list")));

            WebElement selectList = driver.findElement(By.className("select__list"));
            List<WebElement> listItems = selectList.findElements(By.tagName("li"));

            WebElement itemSelected = listItems.get(i);
            itemSelected.click();

            String selectNow = driver.findElement(By.className("select__now")).getText();
            switch (selectNow) {
                case "Услуги связи":
                    Assertions.assertEquals(driver.findElement(By.id("connection-phone")).getAttribute("placeholder"), "Номер телефона");
                    break;
                case "Домашний интернет":
                    Assertions.assertEquals(driver.findElement(By.id("internet-phone")).getAttribute("placeholder"), "Номер абонента");
                    break;
                case "Рассрочка":
                    Assertions.assertEquals(driver.findElement(By.id("score-instalment")).getAttribute("placeholder"), "Номер счета на 44");
                    break;
                case "Задолженность":
                    Assertions.assertEquals(driver.findElement(By.id("score-arrears")).getAttribute("placeholder"), "Номер счета на 2073");
                    break;
            }


            String sum = driver.findElement(By.className("total_rub")).getAttribute("placeholder");
            Assertions.assertEquals("Сумма", sum);

            String email = driver.findElement(By.className("email")).getAttribute("placeholder");
            Assertions.assertEquals(email, "E-mail для отправки чека");

        }
    }


    //Для варианта «Услуги связи» заполнить поля в соответствии с пререквизитами из предыдущей темы,
// нажать кнопку «Продолжить» и в появившемся окне проверить корректность отображения суммы (в том числе на кнопке),
// номера телефона, а также надписей в незаполненных полях для ввода реквизитов карты, наличие иконок платёжных систем.
    @Test
    @DisplayName("Для варианта «Услуги связи» проверить корректность")
    void test2() {
        WebElement phone = driver.findElement(By.id("connection-phone"));
        phone.click();
        String phoneValue = "297777777";
        phone.sendKeys(phoneValue);
        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.click();

        String sumValue = "29.76";
        sum.sendKeys(sumValue);
        WebElement button = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        button.click();


        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bepaid-iframe")));
        WebElement paidFrame = driver.findElement(By.cssSelector(".bepaid-iframe"));

        driver.switchTo().frame(paidFrame);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("header__payment-amount")));

        String headerSum = driver.findElement(By.className("header__payment-amount")).getText();
        Assertions.assertTrue(headerSum.contains(sumValue));


        WebElement cardRoot = driver.findElement(By.className("card-page__card"));

        String btnSum = cardRoot.findElement(By.tagName("button")).getText();
        Assertions.assertTrue(btnSum.contains(sumValue));

        String headerPhone = driver.findElement(By.className("header__payment-info")).getText();
        Assertions.assertTrue(headerPhone.contains(phoneValue));

        List<WebElement> cardLabels = cardRoot.findElements(By.tagName("label"));

        for (WebElement element : cardLabels) {
            switch (element.getAttribute("class")) {
                case "ng-tns-c47-1 ng-star-inserted":
                    Assertions.assertEquals("Номер карты", element.getText());
                    break;
                case "ng-tns-c47-4 ng-star-inserted":
                    Assertions.assertEquals("Срок действия", element.getText());
                    break;
                case "ng-tns-c47-5 ng-star-inserted":
                    Assertions.assertEquals("CVC", element.getText());
                    break;
                case "ng-tns-c47-3 ng-star-inserted":
                    Assertions.assertEquals("Имя держателя (как на карте)", element.getText());
                    break;
            }
        }

        List<WebElement> icons = cardRoot.findElements(By.tagName("img"));

        for (WebElement icon : icons) {
            if (icon.getAttribute("class").equals("ng-tns-c53-0 ng-star-inserted")) {
                Assertions.assertFalse(icon.getAttribute("src").isEmpty());
            }
        }
    }

    @AfterEach
    void close() {
        driver.quit();
    }

}
