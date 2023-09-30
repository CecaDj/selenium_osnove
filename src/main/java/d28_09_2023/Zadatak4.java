package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Ucitati stranicu http://seleniumdemo.com/?post_type=product
//Klik na search dugme u gornjem desnom uglu
//Cekati da forma za pretragu bude vidljiva
//Uneti sledeci tekst za pretragu BDD Cucumber i ENTER
//Dohvatiti prvi rezultat pretrage i proveriti
// da li u nazivu sadrzi tekst koji je unet za pretragu. Ispisati odgovarajuce poruke u terminalu
public class Zadatak4 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://seleniumdemo.com/?post_type=product");

        driver.findElement(By.cssSelector("a.czr-overlay-toggle_btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lsearch-651536d633c09")));

        driver.findElement(By.id("s-651536d633c09")).sendKeys("BDD Cucumber", Keys.ENTER);
        String text = driver.findElement(By.cssSelector("a.czr-title")).getText();

        if (text.contains("BDD Cucumber")) {
            System.out.println("Sadrzi unet tekst.");
        } else {
            System.out.println("Ne sadrzi unet tekst.");
        }

        driver.quit();


    }
}
