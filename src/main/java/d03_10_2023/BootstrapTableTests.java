package d03_10_2023;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BootstrapTableTests {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void baseUrl(){
        driver.get("https://s.bootsnipp.com/iframe/K5yrx");
    }

    @Test
    public  void editRow() {
        String firstName = "Michael";
        String lastName = "Johnson";
        String middleName = "John";

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "User should be on Bootsnipp page");

        driver.findElement(By.className("btn-warning")).click();


        wait
                .withMessage("Edit dialog should be visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-content")));

        driver.findElement(By.id("fn")).clear();
        driver.findElement(By.id("fn")).sendKeys(firstName);

        driver.findElement(By.id("ln")).clear();
        driver.findElement(By.id("ln")).sendKeys(lastName);

        driver.findElement(By.id("mn")).clear();
        driver.findElement(By.id("mn")).sendKeys(middleName);

        driver.findElement(By.id("up")).click();

        wait
                .withMessage("Edit dialog should not be visible")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.className("modal-content")));

        Assert.assertEquals(driver.findElement(By.id("f1")).getText(), firstName,
                "First name should match user input");
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(), lastName,
                "Last name should match user input");
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(), middleName,
                "Middle name should match user input");

    }

    @Test
    public void deleteRow(){
        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));

        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "User should be on Bootsnipp page");

        driver.findElement(By.cssSelector(".delete.btn-danger")).click();

        wait
                .withMessage("Delete dialog should be visible")
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#delete .modal-content")));


        driver.findElement(By.id("del")).click();

        wait
                .withMessage("Delete dialog should not be visible")
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#delete .modal-content")));


        wait
                .withMessage("Row should be deleted.")
                .until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("tr > td:first-child"), rows.size()));

    }

    @Test
    public void takeAScreenshot() throws IOException {
        Assert.assertEquals(driver.getTitle(), "Table with Edit and Update Data - Bootsnipp.com",
                "User should be on Bootsnipp page");

        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f,new File("screenshots/slike.png"));
    }


    @AfterClass
    public void close(){
        driver.quit();
    }
}

