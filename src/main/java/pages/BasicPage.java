package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasicPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public boolean elementExists(By by) {
        return !driver.findElements(by).isEmpty();
    }
}
