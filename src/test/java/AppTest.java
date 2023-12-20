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


    //Для варианта «Услуги связи» заполнить поля в соответствии с пререквизитами из предыдущей темы,
// нажать кнопку «Продолжить» и в появившемся окне проверить корректность отображения суммы (в том числе на кнопке),
// номера телефона, а также надписей в незаполненных полях для ввода реквизитов карты, наличие иконок платёжных систем.
    @Test
    @DisplayName("Для варианта «Услуги связи» проверить корректность")
    void test2() {
        WebElement phone = driver.findElement(new By.ById("connection-phone"));
        phone.click();
        String phoneValue = "297777777";
        phone.sendKeys(phoneValue);
        WebElement sum = driver.findElement(new By.ById("connection-sum"));
        sum.click();

        String sumValue = "29.76";
        sum.sendKeys(sumValue);
        WebElement button = driver.findElement(new By.ByXPath("//*[@id=\"pay-connection\"]/button"));
        button.click();


        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(new By.ByCssSelector(".bepaid-iframe")));
        WebElement paidFrame = driver.findElement(new By.ByCssSelector(".bepaid-iframe"));

        driver.switchTo().frame(paidFrame);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("header__payment-amount")));

        String headerSum = driver.findElement(new By.ByClassName("header__payment-amount")).getText();
        Assertions.assertTrue(headerSum.contains(sumValue));

        String btnSum = driver.findElement(new By.ByXPath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/button")).getText();
        Assertions.assertTrue(btnSum.contains(sumValue));

        String headerPhone = driver.findElement(new By.ByClassName("header__payment-info")).getText();
        Assertions.assertTrue(headerPhone.contains(phoneValue));

        String cardNumber = driver.findElement(new By.ByXPath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[1]/label")).getText();
        Assertions.assertEquals("Номер карты", cardNumber);

        String dateEnd = driver.findElement(new By.ByXPath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[2]/div[1]/app-input/div/div/div[1]/label")).getText();
        Assertions.assertEquals("Срок действия", dateEnd);

        String cvc = driver.findElement(new By.ByXPath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[2]/div[3]/app-input/div/div/div[1]/label")).getText();
        Assertions.assertEquals("CVC", cvc);

        String nameOwner = driver.findElement(new By.ByXPath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[3]/app-input/div/div/div[1]/label")).getText();
        Assertions.assertEquals("Имя держателя (как на карте)", nameOwner);

        WebElement iconBlock = driver.findElement(new By.ByXPath("/html/body/app-root/div/div/app-payment-container/section/app-card-page/div/div[1]/app-card-input/form/div[1]/div[1]/app-input/div/div/div[2]/div/div"));
        List<WebElement> icons = iconBlock.findElements(new By.ByTagName("img"));
        for (WebElement icon : icons) {
            Assertions.assertFalse(icon.getAttribute("src").isEmpty());
        }
    }



    @AfterAll
    static void close() {
        driver.quit();
    }

}
