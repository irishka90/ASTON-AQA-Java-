import org.aston.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainPage {
    private By byShop = new By.ByClassName("main-page__content");
    private By byCartCountMobile = new By.ByClassName("navbar-mobile__notify");
    private By byCartCountPc = new By.ByClassName("navbar-pc__notify");
    private By byProduct = new By.ByTagName("article");
    private By bySizeItem = new By.ByClassName("sizes-list__item");
    private final WebDriver driver;

    //  массив продуктов верстки в виде WebElement
    private List<WebProduct> products = new ArrayList<>();
    //  массив добавленных продуктов
    private ArrayList<Product> productsInCart = new ArrayList<>();

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void initProducts() {

        // дожидаемся загрузки всей страницы
        new WebDriverWait(driver, Duration.ofSeconds(7))
                .until(ExpectedConditions.attributeContains(byProduct, "data-index", "0"));

        // получаем общий контейнер в верстке в котором будет список всех продуктов
        WebElement shopElement = driver.findElement(byShop);

        // получаем все элементы верстки каждого товара
        List<WebElement> productElements = shopElement.findElements(byProduct);

        // преобразуем в веб элементы
        products = productElements
                .stream()
                .map(element -> new WebProduct(element))
                .collect(Collectors.toList());
    }

    // добавить один товар в корзину
    public void addRandomProductInCart() {
        Random rnd = new Random();
        int position = rnd.nextInt(products.size() - 1);
        // берем случайный товар из списка веб элементов
        WebProduct wProduct = products.get(position);

        // удаляем его из списка чтобы не срабатывал переход к корзине раньше времени с учетом кейс из тз
        products.remove(position);

        // если это элемент пустой из-за пагинации, то пропускаем
        if (wProduct.isNotCorrect()) return;

        /// скролимся к элементу
        new Actions(driver).moveToElement(wProduct.getPriceForScroll()).perform();
        // наверняка ждем чтобы фокус установился и появилась кнопка "в корзину"
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfAllElements(wProduct.getAddCard()));

        // делаем скрин для состояния до нажатия
        TestUtil.shot(driver, "beforeClick");
        // преобразуем в класс продукт для передачи на экран корзины для сравнения
        Product product = new Product(wProduct.getId(), wProduct.getName(), wProduct.getPrice());
        // кликаем на "добавить в корзину"
        wProduct.clickAddToCart();

        // делаем скрин для состояния после нажатия
        TestUtil.shot(driver, "afterClick");

        /// получаем размеры товара
        int countSizes = wProduct.getSizes();
        if (countSizes > 1) {
            //наверняка ждем чтобы успел открыть попап
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bySizeItem));

            // если они есть и больше одного, то обрабатываем выскакивающий попап с выбором размера товара
            List<WebElement> elements = driver.findElements(bySizeItem);

            int positionSize = rnd.nextInt(elements.size() - 1) + 1;
            // выбираем случайный размер
            WebElement sizeEl = elements.get(positionSize);
            // скликаем на размер и вызываем тем самым добавление в корзину
            sizeEl.click();
        }
        // добавляем в массив добавленных продуктов
        productsInCart.add(product);
    }


    // получаем счетчик на экране главном кол-ва товаров в корзине
    public int getCountInCart() {
        return Integer.parseInt(driver.findElement(byCartCountPc).getText());
    }

    // вернуть список веб элементов полученных при загрузке страницы
    public List<WebProduct> getProducts() {
        return products;
    }

    // вернуть добавленные в корзину товары
    public ArrayList<Product> getProductsInCart() {
        return productsInCart;
    }
}
