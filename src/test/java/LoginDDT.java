import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
    @Test
    public void main() {
        WebDriver driver;
        String baseurl = "https://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        String fileDir = System.getProperty("user.dir") + "/src/test/data/login-data.csv";

        try (CSVReader reader = new CSVReader(new FileReader(fileDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String username = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                driver = new ChromeDriver(opt);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(baseurl);

                driver.findElement(By.id("user-name")).sendKeys((username));
                driver.findElement(By.id("password")).sendKeys((password));
                driver.findElement(By.id("login-button")).click();

                if (status.equals("success")) {
                    driver.findElement(By.className("app_logo"));
                    String title_page = driver.findElement(By.xpath("//*[@id='header_container']/div[2]/span")).getText();
                    Assert.assertEquals(title_page, "Products");
                } else if (status.equals("incorrect account")) {
                    String error_msg = driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]")).getText();
                    Assert.assertEquals(error_msg, "Epic sadface: Username and password do not match any user in this service");
                } else if (status.equals("required password")) {
                    String error_msg = driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Password is required')]")).getText();
                    Assert.assertEquals(error_msg, "Epic sadface: Password is required");
                } else if (status.equals("required username")) {
                    String error_msg = driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Username is required')]")).getText();
                    Assert.assertEquals(error_msg, "Epic sadface: Username is required");
                } else if (status.equals("locked out account")) {
                    String error_msg = driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]")).getText();
                    Assert.assertEquals(error_msg, "Epic sadface: Sorry, this user has been locked out.");
                }
                driver.quit();
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        
    }
}
