import org.aston.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage {
    private final By allProductsInCart = new By.ByClassName("list-item__wrap");
    private final By byCount = new By.ByCssSelector("data-count");
    private final By sumToPay = new By.ByClassName("b-right");

    private List<WebCart> products = new ArrayList<>();

    private final ArrayList<Product> addedProductsInCart;
    private final WebDriver driver;

    public CartPage(WebDriver driver, ArrayList<Product> addedProductsInCart) {
        this.addedProductsInCart = addedProductsInCart;
        this.driver = driver;
    }

    public void initProducts() {
        // дожидаемся загрузки всей страницы
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allProductsInCart));

        List<WebElement> productElements = driver.findElements(allProductsInCart);
        products = productElements
                .stream()
                .map(element -> new WebCart(element))
                .collect(Collectors.toList());
    }

    // вернуть список веб элементов с позициями добавленных товаров полученных при загрузке страницы
    public List<WebCart> getProducts() {
        return products;
    }


    public int compareItems() {

        int result = 0;
        for (WebCart cartProduct : products) { // идем по списку элементов верстки
            Product productInCart = new Product( // преобразуем в объект Product
                    cartProduct.getId(),
                    cartProduct.getName(),
                    cartProduct.getPrice()
            );

            // Сравниваем на вхождение в список ранее добавленых в корзину на главной странце
            if (addedProductsInCart.contains(productInCart)) {
                result++;  // если входит, то счетчик увеличиваем
            } else {
                return -1; // иначе выкидываем сразу ошибку лишнего элемента
            }
        }
        return result; // выдаем общий счетчик
    }

    public int getCartCount() {
        return driver.findElements(new By.ByTagName("h1"))
                .stream()
                .filter(it -> it.getAttribute("data-count") != null) // фильтруем с кол-во покупок элементы
                .map(it -> Integer.parseInt(it.getAttribute("data-count"))) // преобразуем в инт наш параметр
                .findFirst()
                .get(); // достаем первого
    }

    public double getFinalSumExpect() {
        return addedProductsInCart.stream().mapToDouble(Product::getPrice).sum();
    }

    public Double getFinalSumActual() {
        String finalSum = driver.findElement(sumToPay).getText();
        String totalSumActual = finalSum.substring(0, finalSum.length() - 1).replace(" ", "");
        return Double.parseDouble(totalSumActual);
    }
}
