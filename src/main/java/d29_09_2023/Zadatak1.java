package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Napisati program koji testira infinity scroll.
//Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//Selektujte delay od 2000ms, koristeci Select klasu.
//Skrol do Show more dugmeta koje se nalazi na dnu stranice
//Sacekajte da dugme bude klikljivo
//Klik na Show more dugme
//Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//Sacekajte da dugme vise ne bude klikljivo
public class Zadatak1 {
    public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");
        driver.manage().window().maximize();


        WebElement selectElement = driver.findElement(By.id("delay-select"));
        Select select = new Select(selectElement);
        select.selectByValue("2000");

        Actions actions = new Actions(driver);
        WebElement btn = driver.findElement(By.cssSelector("button#infinite-scroll-button"));
        actions.scrollToElement(btn);
        actions.perform();


        wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.item"), 11));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("span.disabled-text"), "Loading more items..."));



        driver.quit();
    }
}
