package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Napisati program koji ucitava stranicu https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort=
//Klik na Type drawdown
//Klik na Public iz drowdowna
//Ceka da se Clear dugme u desnom uglu prikaze koristeci explicit wait
//Kilk na Clear filter u desnom uglu

public class Zadatak1 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://github.com/orgs/embedly/repositories?q=&type=all&language=&sort= ");

        driver.findElement(By.cssSelector("summary.btn")).click();
        driver.findElement(By.cssSelector("div.SelectMenu-list > label:nth-child(2)")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.TableObject-item.text-right.v-align-top > a")));
        driver.findElement(By.cssSelector("div.TableObject-item.text-right.v-align-top > a")).click();

        driver.quit();

    }
}

