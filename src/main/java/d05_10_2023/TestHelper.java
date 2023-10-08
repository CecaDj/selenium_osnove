package d05_10_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



import java.time.Duration;


public class TestHelper {
    private WebDriver driver;

    public TestHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean elementExists(By by){
        boolean exists = true;
        try {
            driver.findElement(by);

        } catch (Exception e) {
            exists = false;
        }
        return exists;

    }

    public boolean elementExistsByList(By by){

        return !driver.findElements(by).isEmpty();
    }

    public void setDefaultImplicitWait(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void setImplicitWait(int time){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }
}

