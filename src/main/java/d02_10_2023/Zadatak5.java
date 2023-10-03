package d02_10_2023;

// Napisati program koji:
//Ucitava stranicu https://demoqa.com/broken
//Hvata oba linka sa stranice i
//Za svaki link proverava status da je veci ili jednak od 200 i manji od 400
//Koristan link za citanje status koda nekog url-a

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Zadatak5 {
    public static void main(String[] args) throws IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/broken");
        List<WebElement> links = driver.findElements(By.cssSelector(".col-md-6 a"));

        for (int i = 0; i < links.size(); i++) {
            String url = links.get(i).getAttribute("href");

            if (getHTTPResponseStatusCode(url) >= 200 && getHTTPResponseStatusCode(url)< 400) {
                System.out.println("Status je izmedju 200 i 400.");
            } else {
                System.out.println("Status nije izmedju 200 i 400.");
            }
        }

        driver.quit();
    }

    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        return http.getResponseCode();
    }
}

