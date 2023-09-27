package d26_09_2023;
//Napisati program koji:
//Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//POMOC: Brisite elemente odozdo.

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://s.bootsnipp.com/iframe/Dq2X");

        List<WebElement> alerts = driver.findElements(By.cssSelector("div.alert"));

        for (int i = 0; i < alerts.size(); i++) {

            alerts.get(i).findElement(By.className("close")).click();
            List<WebElement> newAlerts = driver.findElements(By.cssSelector("div.alert"));

            if (newAlerts.size() < alerts.size()) {
                System.out.println("Alert is removed.");
                Thread.sleep(2000);
            }
        }

        driver.quit();
    }
}
