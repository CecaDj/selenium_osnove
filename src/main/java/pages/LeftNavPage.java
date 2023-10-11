package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LeftNavPage extends BasicPage{
    public LeftNavPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void waitForMenuToBeVisible() {
        wait
                .withMessage("Left navigation menu should be visible.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-menu-wrap")));

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

    public List<WebElement> getMenuOptions(){
        List<WebElement> options = driver.findElements(By.cssSelector(".bm-item-list > a"));
        return options;
    }
    public int getNumberOfMenuOptions(){
       return getMenuOptions().size();
    }

    public void clickOnMenuOption(int index){
        getMenuOptions().get(index).click();
    }

    public WebElement getEkisButton(){
        return driver.findElement(By.id("react-burger-cross-btn"));
    }

    public void clickOnEkisButton(){
        getEkisButton().click();
    }



}
