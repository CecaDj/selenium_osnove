package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.time.Duration;

public abstract class BasicTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected LoginPage login;
    protected String username;
    protected String password;

    protected LeftNavPage leftNav;
    protected InventoryPage inventory;

    protected TopNavPage topNav;
    protected SubHeaderPage subHeader;
    protected ItemListPage itemList;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        baseUrl = "https://www.saucedemo.com/";
        username = "standard_user";
        password = "secret_sauce";

        login = new LoginPage(driver,wait);
        leftNav = new LeftNavPage(driver,wait);
        inventory = new InventoryPage(driver,wait);
        topNav = new TopNavPage(driver,wait);
        subHeader = new SubHeaderPage(driver,wait);
        itemList = new ItemListPage(driver,wait);
    }

    @BeforeMethod
    public void homePage(){
        driver.get(baseUrl);
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
