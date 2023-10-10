package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeftNavPage extends BasicPage{
    public LeftNavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void waitForMenuToBeVisible() {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("bm-menu-wrap")));

    }

    public boolean doesLogoutButtonExist() {
        return elementExists(By.id("logout_sidebar_link"));
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public void clickOnLogoutLink() {
        getLogoutLink().click();
    }

}
