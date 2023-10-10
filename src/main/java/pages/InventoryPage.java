package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InventoryPage extends BasicPage{
    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void scrollToItem(){
        new Actions(driver)
                .scrollToElement(driver.findElement(By.id("item_4_title_link")))
                .perform();
    }

    public WebElement getAddtoCartButton(){
         return driver.findElement(By.cssSelector(".inventory_item button"));
    }

    public void clickOnAddToCartButton () {
        getAddtoCartButton().click();
    }

    public WebElement getRemoveButton() {
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }


}
