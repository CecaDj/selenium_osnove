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
}
