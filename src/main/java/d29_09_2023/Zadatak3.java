package d29_09_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Zadatak
//Napisati program koji:
//Pre nego sto krenete u automatizaciju prvo sve korake uradite rucno
//Implicitno cekanje za trazenje elemenata je maksimalno 10s
//Implicitno cekanje za ucitavanje stranice je 5s
//Ucitava stranicu https://docs.katalon.com/
//Maksimizuje prozor
//Od html elementa cita data-theme atribut.
//Proverava da li je sadrzaj u tom atributu light i ispisuje odgovarajuce poruke
//Klikce na dugme za zamenu tema
//Ponovo cita data-theme atribut html elementa i validira da u atributu stoji vrednost dark
//Izvrsava kombinaciju tastera CTRL + K. Koristan link  za keyboard actions…kako izvrsavati precice
// preko Actions objekta
//Ceka da se dijalog za pretragu pojavi
//Zatim od inputa za pretragu cita atribut type i proverava da je vrednost tog atributa search
//Zatvara pretrazivac
public class Zadatak3 {
    public static void main(String[] args){

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("https://docs.katalon.com/");
        driver.manage().window().maximize();

       String theme = driver.findElement(By.tagName("html")).getAttribute("data-theme");

       if (theme.equals("light")){
           System.out.println("Izabrana je light tema.");
       } else {
           System.out.println("Nije izabrana light tema.");
       }

       driver.findElement(By.cssSelector(".clean-btn.toggleButton_rCf9")).click();
       String theme2 = driver.findElement(By.tagName("html")).getAttribute("data-theme");

        if (theme2.equals("dark")){
            System.out.println("Izabrana je dark tema.");
        } else {
            System.out.println("Nije izabrana dark tema.");
        }

        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("k")
                .perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("docsearch-input")));
        String search = driver.findElement(By.id("docsearch-input")).getAttribute("type");

        if (search.equals("search")){
            System.out.println("Vrednost atributa type = search.");
        } else {
            System.out.println("Vrednost atributa type nije search.");
        }


        driver.quit();


    }
}
