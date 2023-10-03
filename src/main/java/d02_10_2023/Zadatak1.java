package d02_10_2023;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

//Napisati program koji:
//Podesava:
//implicitno cekanje za trazenje elemenata od 10s
//implicitno cekanje za ucitavanje stranice od 10s
//eksplicitno cekanje podeseno na 10s
//Podaci:
//Potrebno je u projektu ukljuciti 4 slike:
//front.jpg
//left.jpg
//right.jpg
//back.jpg
//Koraci:
//Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//Maksimizuje prozor
//Klik na edit ikonicu
//Klik na delete iz iskacuceg dijaloga
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte front.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 1.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte right.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 2.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte back.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 3.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Klik na Add Image dugme
//Sacekajte da se pojavi desni meni
//Uploadujte back.jpg sliku
//Sacekajte da je ispod uploada slike, broj slika 3.
//Klik na sliku
//Klik na Done dugme
//Sacekajte 2s
//Sacekajte da Next dugme bude klikljivo
//Klik na Next dugme
//Unesite tekst
//Klik na Next
//Klik na Preview
//Klik na Add to cart
//Sacekajte 5s
//Quit
public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ArrayList<File> files = new ArrayList<>();
        files.add(new File("test_data/front.jpg"));
        files.add(new File("test_data/left.jpg"));
        files.add((new File("test_data/right.jpg")));
        files.add(new File("test_data/back.jpg"));

        driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");
        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("img.edit-image")).click();
        driver.findElement(By.id("image-option-remove")).click();

        for (int i = 0; i < files.size(); i++) {
            driver.findElement(By.cssSelector("img.edit-image")).click();

            wait
                    .withMessage("Dugme nije vidljivo.")
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label[for='imageUpload']")));

            driver.findElement(By.cssSelector("input#imageUpload")).sendKeys(files.get(i).getAbsolutePath());

            wait
                    .withMessage("Slike nisu vidljive.")
                    .until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div.sc-fsQiph.dxKPsO > div"), i+1));

            driver.findElement(By.id("image-option-0")).click();
            driver.findElement(By.cssSelector("#image-crop-done-button > button")).click();
            Thread.sleep(2000);
        }

        wait
                .withMessage("Dugme nije vidljivo.")
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("next-button"))).click();

        driver.findElement(By.id("textareaID")).sendKeys("Hello!");
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();
        driver.findElement(By.id("next-button")).click();
        Thread.sleep(5000);

        driver.quit();
    }
}
