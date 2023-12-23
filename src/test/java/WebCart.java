import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

// WebElement для одной ячейки товара с экрана корзины
public class WebCart {

    private WebElement _webCart;
    private final By byName = new By.ByClassName("good-info__good-name");
    private final By byPrice = new By.ByClassName("list-item__price-new");

    public WebCart(WebElement _webCart) {
        this._webCart = _webCart;
    }

    // получить имя товара
    public String getName() {
        return _webCart.findElement(byName).getText();
    }

    // Получить цену товара
    public String getPrice() {
        String price = _webCart.findElement(byPrice).getText();
        return price.substring(0, price.length() - 1).replace(" ", "");
    }

    // Получит ид товара
    public String getId() {

        return _webCart.findElement(new By.ByClassName("list-item__good")) // находим родительский элемент
                .findElements(new By.ByTagName("div")) // находим все див в одном и будет ид товара в корзине
                .stream()
                .filter(it -> it.getAttribute("data-nm") != null) // фильтруем с ид элементы
                .findFirst()
                .get() // достаем первого
                .getAttribute("data-nm"); // достаем ид из атрибута
    }

}
