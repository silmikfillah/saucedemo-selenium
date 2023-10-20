package Test;

import Pages.PageLogin;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login {
    //PageLogin pageLogin = new PageLogin();

    WebDriver driver;
    PageLogin pageLogin;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        driver = new ChromeDriver(opt);
    }

    @Test
    public void test_Login() {
        pageLogin = new PageLogin(driver);
        String fileDir = System.getProperty("user.dir") + "/src/test/data/login-data.csv";

        try (CSVReader reader = new CSVReader(new FileReader(fileDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String username = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://www.saucedemo.com/");

                pageLogin.setUsername(username);
                pageLogin.setPassword(password);
                pageLogin.clickLoginButton();
                pageLogin.verifyLoginStatus(status);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @After
    public void afterTest() {
        driver.close();
    }
}
