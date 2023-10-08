package d05_10_2023;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class SwagLabsTests {
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        baseUrl = "https://www.saucedemo.com/";
    }

    @BeforeMethod
    public void homePage(){
        driver.get(baseUrl);
    }

    @Test (retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUsernameIsMissing(){

        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Missing username error message should be displayed.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".error-message-container > h3"),
                        "Epic sadface: Username is required"));


    }

    @Test (retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenPasswordIsMissing(){
        String username = "standard_user";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Missing password error message should be displayed.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".error-message-container > h3"),
                        "Epic sadface: Password is required"));

    }

    @Test (retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenCredentialsAreWrong(){
        String username = "standard_user";
        String password = "invalidpassword";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Wrong credentials error message should be displayed.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".error-message-container > h3"),
                        "Epic sadface: Username and password do not match any user in this service"));

    }

    @Test (retryAnalyzer = SwagLabsRetry.class)
    public void verifyErrorIsDisplayedWhenUserIsLocked(){
        String username = "locked_out_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        wait
                .withMessage("Locked user error message should be displayed.")
                .until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".error-message-container > h3"),
                        "Epic sadface: Sorry, this user has been locked out."));


    }

    @Test (retryAnalyzer = SwagLabsRetry.class)
    public void verifySuccessfulLogin(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to inventory page after login.");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        wait
                .withMessage("Menu should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-item-list")));

        TestHelper testHelper = new TestHelper(driver);
        boolean elementExists = testHelper.elementExists(By.id("logout_sidebar_link"));
        Assert.assertTrue(elementExists, "User should be able to log out");


        driver.findElement(By.id("logout_sidebar_link")).click();


        boolean loginExists = !driver.findElements(By.className("login-box")).isEmpty();
        Assert.assertTrue(loginExists, "User should be redirected to Login page.");
    }


    @Test
    public void addingProductsToCart(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        wait
                .withMessage("Remove button should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1",
                "Product should be added to cart.");

    }

    @Test
    public void viewingProductDetails(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");
        driver.findElement(By.id("item_4_title_link")).click();

        wait
                .withMessage("Product details should be visible.")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                        (By.cssSelector(".inventory_details_desc_container > div")));

        wait
                .withMessage("Add to cart button should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack")));
    }

    @Test
    public void removingProductsFromCart(){
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1",
                "Product should be added to cart.");

        driver.findElement(By.className("shopping_cart_link")).click();

        wait
                .withMessage("Product should be added to cart.")
                .until(ExpectedConditions.presenceOfElementLocated(By.id("item_4_title_link")));

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        TestHelper testHelper = new TestHelper(driver);
        boolean elementExists = testHelper.elementExists(By.id("remove-sauce-labs-backpack"));
        Assert.assertFalse(elementExists, "Product should be removed from cart.");

    }

    @Test
    public void productCheckout(){
        String username = "standard_user";
        String password = "secret_sauce";
        String checkoutName = "Pera";
        String checkoutLastName = "Peric";
        String checkoutZip = "18000";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "1",
                "Product should be added to cart.");

        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys(checkoutName);
        driver.findElement(By.id("last-name")).sendKeys(checkoutLastName);
        driver.findElement(By.id("postal-code")).sendKeys(checkoutZip);
        driver.findElement(By.id("continue")).click();

        Assert.assertEquals(driver.findElement(By.className("inventory_item_name")).getText(),
                "Sauce Labs Backpack",
                "Item name should match added product name");

        Assert.assertEquals(driver.findElement(By.className("inventory_item_price")).getText(), "$29.99",
                "Item price should match added product price");

        driver.findElement(By.id("finish")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("#checkout_complete_container h2")).getText(),
                "Thank you for your order!",
                "Checkout should be complete.");

    }

    @Test
    public void validateSocialLinksInFooter() {
        String username = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "User should be redirected to Inventory page.");

        new Actions(driver)
                .scrollToElement(driver.findElement(By.className("social")))
                .perform();

        List<WebElement> links = driver.findElements(By.cssSelector(".social a"));

        for (int i = 0; i < links.size(); i++) {
            String url = links.get(i).getAttribute("href");

            try {
                Assert.assertEquals(getHTTPResponseStatusCode(url), 200,
                        "Response status code should be 200");
            } catch (Exception e) {

            }
        }
    }

    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);

        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        return http.getResponseCode();
    }

    @AfterMethod
    public void clear(){
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }


    @AfterClass
    public void quit(){
        driver.quit();;
    }
}

