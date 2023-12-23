import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

// WebElement для одной ячейки товара с главного экрана
public class WebProduct {
    private WebElement _webProduct;
    private final String idAttr = "data-nm-id";
    private final By byJSize = new By.ByClassName("j-size");
    private final By byName = new By.ByClassName("product-card__name");
    private final By byPrice = new By.ByClassName("price__lower-price");
    private final By byAddBtn = new By.ByClassName("product-card__order-wrap");
    private final By byAddBtnA = new By.ByTagName("a");

    public WebProduct(WebElement _webProduct) {
        this._webProduct = _webProduct;
    }

    // для фильтрации пустых элементов, там пагинация и не понятно сколько добавляется пустых элементов, а мы случайные добавляем
    public Boolean isNotCorrect() {
        return _webProduct.findElement(byName).getText().isEmpty();
    }

    // получить ид товара
    public String getId() {
        return _webProduct.getAttribute(idAttr);
    }

    // получить имя товара
    public String getName() {
        return _webProduct.findElement(byName).getText().replace("/ ", "");
    }

    // получить цену товара
    public String getPrice() {
        String price = _webProduct.findElement(byPrice).getText();
        return price.substring(0, price.length() - 1).replace(" ", "");
    }

    // получить элемент цены чтобы к нему проскролить, установить на нем фокус и тем самым чтобы отобразились скрытые поля
    public WebElement getPriceForScroll() {
        return _webProduct.findElement(byPrice);
    }

    // получить количество размеров товара (если 0..1 то достаточно кликнуть на "в корзину" иначе выбирать размер на выпадающем попапе)
    public int getSizes() {
        return (int) _webProduct.findElements(byJSize)
                .stream().filter(it -> it.getAttribute("href").contains(getId()))
                .count();
    }

    public WebElement getAddCard() {
        return _webProduct.findElement(byAddBtn).findElement(byAddBtnA);
    }

    // добавить в корзину
    public void clickAddToCart() {
        getAddCard().click();
    }

    @Override
    public String toString() {
        return "Id: " + getId() + " name: " + getName() + " price:" + getPrice();
    }
}
