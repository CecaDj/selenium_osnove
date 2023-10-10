package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage{
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }



    public WebElement getUsernameInput() {
        return driver.findElement(By.id("user-name"));
    }

    public void clearAndTypeUsername(String input){
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys(input);
    }

    public WebElement getPasswordInput() {
        return driver.findElement(By.id("password"));
    }

    public void clearAndTypePassword(String input){

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(input);
    }

    public WebElement getLoginButton(){
        return driver.findElement(By.id("login-button"));
    }

    public void clickOnLoginButton(){
        getLoginButton().click();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector("[data-test='error']"));
    }

    public String getErrorMessageText() {
        return getErrorMessage().getText();
    }

    public boolean doesUsernameInputExist() {
        return elementExists(By.id("user-name"));
    }




}
