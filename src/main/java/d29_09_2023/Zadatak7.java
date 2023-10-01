package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

//Napisati program koji:
//Ucitava stranicu https://tus.io/demo.html
//Hvata sve linkove sa stranice
//Skrola do svakog h3 elementa
//Od svakog h3 elementa cita text

public class Zadatak7 {

    public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://tus.io/demo.html");

        List<WebElement> links = driver.findElements(By.cssSelector("a[href]"));
        List<WebElement> h3 = driver.findElements(By.tagName("h3"));
        Actions actions = new Actions(driver);

        for (int i = 0; i < h3.size(); i++) {

            try {
                actions.scrollToElement(h3.get(i));
                actions.perform();

            } catch (Exception e) {

            }

            System.out.println(h3.get(i).getText());
        }

        driver.quit();
    }
}
