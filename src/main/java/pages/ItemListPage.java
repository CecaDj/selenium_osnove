package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ItemListPage extends BasicPage{
    public ItemListPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public List<WebElement> getItemList(){
        List<WebElement> items = driver.findElements(By.cssSelector(".cart_list .cart_item"));
        return items;
    }

    public WebElement getSingleItem(int index){
        return getItemList().get(index);
    }

    public String getItemsTitle(int index){
        return getSingleItem(index).findElement(By.tagName("a")).getText();
    }

    public String getItemsDescription(int index){
        return getSingleItem(index).findElement(By.className("inventory_item_desc")).getText();
    }

    public String getItemsQuantity(int index){
        return getSingleItem(index).findElement(By.className("cart_quantity")).getText();
    }

    public void clickOnItemTitle(int index){
        getSingleItem(index).findElement(By.tagName("a")).click();
    }

    public WebElement getRemoveButton(int index){
        return getSingleItem(index).findElement(By.tagName("button"));
    }

    public void clickOnRemoveButton(int index){
        getSingleItem(index).findElement(By.tagName("button")).click();
    }

    public WebElement getContinueShoppingButton(){
        return driver.findElement(By.id("continue-shopping"));
    }

    public void clickOnContinueShoppingButton(){
        getContinueShoppingButton().click();
    }

    public WebElement getCheckoutButton(){
        return driver.findElement(By.id("checkout"));
    }

    public void clickOnCheckoutButton(){
        getCheckoutButton().click();
    }
}
