package d26_09_2023;
//Napisati program koji:
//Ucitava stranicu https://demoqa.com/automation-practice-form
//Popunjava formu sta stranice. Korisnik unosi podatke sa tastature za popunu forme.
//(za vezbanje) Probajte da unese i datum. Sa datumom se radi isto kao i sa obicnim inputom sa sendKeys.
//Klik na submit

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://demoqa.com/automation-practice-form");

        Scanner s = new Scanner(System.in);

        System.out.println("First Name:");
        driver.findElement(By.cssSelector("#firstName")).sendKeys(s.next());
        System.out.println("Last Name:");
        driver.findElement(By.cssSelector("#lastName")).sendKeys(s.next());
        System.out.println("Email:");
        driver.findElement(By.cssSelector("#userEmail")).sendKeys(s.next());

        System.out.println("Gender (Male/Female/Other):");
        String gender = s.next();
        if (gender.equals("Male")) {
            driver.findElement(By.cssSelector("#genterWrapper label")).click();
        } else if (gender.equals("Female")) {
            driver.findElement(By.cssSelector("[for='gender-radio-2']")).click();
        } else {
            driver.findElement(By.cssSelector("[for='gender-radio-3']")).click();
        }

        System.out.println("Mobile:");
        driver.findElement(By.cssSelector("#userNumber")).sendKeys(s.next());
        System.out.println("Hobbies (Sports, Reading, Music):");
        String hobbies = s.next();
        if(hobbies.contains("Sports")) {
            driver.findElement(By.cssSelector("[for='hobbies-checkbox-1']")).click();
        }
        if (hobbies.contains("Reading")){
            driver.findElement(By.cssSelector("[for='hobbies-checkbox-2']")).click();
        }
        if (hobbies.contains("Music")) {
            driver.findElement(By.cssSelector("[for='hobbies-checkbox-3']")).click();
        }

        System.out.println("Current Addres:");
        driver.findElement(By.cssSelector("#currentAddress")).sendKeys(s.next());

        driver.findElement(By.cssSelector("#submit")).click();


        driver.quit();

    }
}
