package pageobjects;
import helpers.Browser;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    //---products---
    public final String product01WindSurf = "/fuerteventura-sotavento/";
    public final String product02WindEgipt = "/egipt-el-gouna/";
    public final String product03WindGrecja = "/grecja-limnos/";
    public final String product04WindKarpathos = "/windsurfing-w-karpathos/";
    public final String product05WindLanzarote = "/windsurfing-w-lanzarote-costa-teguise/";
    public final String product06WindZiel = "/wyspy-zielonego-przyladka-sal/";
    public final String product07WspinFer = "/wspinaczka-via-ferraty/";
    public final String product08WspinKosc = "/gran-koscielcow/";
    public final String product09WspinPeak = "/wspinaczka-island-peak/";
    public final String product10YogaWis = "/wakacje-z-yoga-w-kraju-kwitnacej-wisni/";
    public final String product11YogaTos = "/wczasy-relaksacyjne-z-yoga-w-toskanii/";
    public final String product12YogaHis = "/yoga-i-pilates-w-hiszpanii/";
    public final String product13YogaPort = "/yoga-i-pilates-w-portugalii/";
    public final String product14YogaMal = "/zmien-swoja-sylwetke-yoga-na-malcie/";
    public final String product15ZeglKurs = "/kurs-zeglarski-na-mazurach/";

    //---elements---
    // button in Card "Dodaj do koszyka"
    private final By addToCart = By.className("single_add_to_cart_button");
    private final By goToCart = By.cssSelector(".woocommerce-message>.button");
    private final By addToWishlist = By.cssSelector(".add_to_wishlist");
    private final By goToWishlistFromHeader = By.cssSelector(".menu-item-248");

    public final StoreHeaderComponent storeHeader;

    public ProductPage(Browser browser){
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
    }
    public ProductPage go(String productSlug) {
        driver.get(baseURL + "/product/" + productSlug);
        return this;
    }
    public ProductPage addToCart() {
        driver.findElement(addToCart).click();
        return this;
    }
    public CartPage goToCart() {
        driver.findElement(goToCart).click();
        return new CartPage(browser);
    }
    public ProductPage closeInfoButton() {
        driver.findElement(By.className("woocommerce-store-notice__dismiss-link")).click();
        return this;
    }

    public ProductPage addToWishlist() {
        driver.findElement(addToWishlist).click();
        waitForLoadingIcons();
        return this;
    }

    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(browser);
    }
}
