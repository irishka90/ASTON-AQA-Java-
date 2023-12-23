import io.github.bonigarcia.wdm.WebDriverManager;
import org.aston.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class AppTest {

    static WebDriver driver;

    @BeforeAll
    static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.wildberries.ru/");

    }

/*Написать автотесты для сайта wildberries.ru, в ходе которых на главной странице несколько
товаров кладется в корзину, затем осуществляется переход в корзину и происходят проверки на
соответствие названия товаров, их количество, цен каждого товара в корзине и общей суммы товаров.
 При написании автотестов необходимо использовать подходящие паттерны, изученные в данной теме.*/

    @Test
    void test() {
        MainPage mainPage = new MainPage(driver);
        mainPage.initProducts();
        Assertions.assertFalse(mainPage.getProducts().isEmpty(), "Не нашел позиций в магазине");

        addRandomProductsToCart(mainPage);

        ArrayList<Product> selectedProduct = mainPage.getProductsInCart();

        driver.get("https://www.wildberries.ru/lk/basket");
        CartPage cartPage = new CartPage(driver, selectedProduct);
        cartPage.initProducts();
        Assertions.assertFalse(cartPage.getProducts().isEmpty(), "Не нашел позиций в корзине");

        Assertions.assertEquals(selectedProduct.size(), cartPage.getCartCount(), "В корзине не верно отображается счетчик позиций");

        Assertions.assertEquals(selectedProduct.size(), cartPage.getProducts().size(), "В корзине не верное число продуктов");

        int compareSize = cartPage.compareItems();
        Assertions.assertNotEquals(-1, compareSize, "В корзине лишний продукт");
        Assertions.assertEquals(selectedProduct.size(), compareSize, "В корзине не верное число продуктов");

        Assertions.assertEquals(cartPage.getFinalSumExpect(), cartPage.getFinalSumActual(), "Не верная общая сумма покупок для оплаты");
    }

    private void addRandomProductToCart(MainPage mainPage) {
        mainPage.addRandomProductInCart();
        List<Product> productsAdded = mainPage.getProductsInCart();
        Assertions.assertFalse(productsAdded.isEmpty(), "Никого не добавили в корзину");

        TestUtil.sleep(300L);

        int cartContain = mainPage.getCountInCart();
        Assertions.assertEquals(productsAdded.size(), cartContain, "Количество добавленных не совпадает " + productsAdded.size() + "!=" + cartContain + "\n" + productsAdded);
    }

    private void addRandomProductsToCart(MainPage mainPage) {
        for (int i = 0; i < 5; i++) {
            addRandomProductToCart(mainPage);
        }
    }

    @AfterAll
    static void close() {
        driver.quit();
    }

}
