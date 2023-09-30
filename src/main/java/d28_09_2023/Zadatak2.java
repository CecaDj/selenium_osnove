package d28_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//Ucitati stranicu https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example
//Klik na svako dugme od PRIMARY do DARK
//Sacekati da se toasts u desnom gornjem uglu pojavi
//Pauza izmedju klikova 1s
//Postavite implicitno cekanje za ucitavanje stranice i trazenje elemenata na 10s
public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.get("https://mdbootstrap.com/docs/standard/components/toasts/#section-basic-example");

        List<WebElement> btns = driver.findElement(By.cssSelector("div.container.text-center")).findElements(By.tagName("button"));
        List<WebElement> toasts = driver.findElements(By.cssSelector("div.fade.toast-fixed"));

        for (int i = 0; i < btns.size(); i++) {
            Thread.sleep(1000);
            btns.get(i).click();
            wait.until(ExpectedConditions.visibilityOf(toasts.get(i)));

        }

        driver.quit();

    }
}
