package Test;

import Pages.Homepage;
import Pages.PageCart;
import Pages.PageLogin;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class RemoveProduct {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;
    PageCart pageCart;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        driver = new ChromeDriver(opt);
    }

    @Test
    public void test_RemoveProduct() {
        pageLogin = new PageLogin(driver);
        homepage = new Homepage(driver);
        pageCart = new PageCart(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        pageLogin.login_saucedemo_success("standard_user", "secret_sauce", "success");
        homepage.addToCart_success();
        pageCart.clickRemove_fromCart();
        pageCart.verifySuccessRemoved();
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
