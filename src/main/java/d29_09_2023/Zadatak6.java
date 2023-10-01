package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//Napisati program koji:
//Ucitava stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//Vrsi klik na Primary dugme, Secondary, Sucess, Danger
//Ceka da broj toasts-a bude 4
//Ispisuje poruku, toasts su prikazani
//Ceka da broj toasts-a bude 0
//Ispisuje poruku, toasts su se izgubili

public class Zadatak6 {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        List<WebElement> btns = driver.findElements(By.cssSelector(".container.text-center > button"));

        for (int i = 0; i < 4; i++) {
            btns.get(i).click();
        }

        WebElement toast1 = driver.findElement(By.id("basic-primary-example"));
        WebElement toast2 = driver.findElement(By.id("basic-secondary-example"));
        WebElement toast3 = driver.findElement(By.id("basic-success-example"));
        WebElement toast4 = driver.findElement(By.id("basic-success-example"));

        wait
                .withMessage("Toasts nisu prikazani.")
                .until(ExpectedConditions.visibilityOfAllElements(toast1,toast2,toast3,toast4));

        System.out.println("Toasts su prikazani.");

        wait
                .withMessage("Toasts se nisu izgubili.")
                .until(ExpectedConditions.invisibilityOfAllElements(toast1,toast2,toast3,toast4));

        System.out.println("Toasts su se izgubili.");

        driver.quit();
    }
}
