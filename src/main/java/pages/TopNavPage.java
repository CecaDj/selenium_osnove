package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopNavPage extends BasicPage {

    public TopNavPage(WebDriver driver, WebDriverWait wait) {

        super(driver, wait);
    }

    public WebElement getMenuButton() {

        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public void clickOnMenuButton() {

        getMenuButton().click();
    }

    public WebElement getCartBadge(){
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public String getCartText(){
        return getCartBadge().getText();
    }

}
