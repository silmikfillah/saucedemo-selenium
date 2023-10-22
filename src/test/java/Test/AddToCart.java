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

public class AddToCart {
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
    public void test_AddToCart() {
        pageLogin = new PageLogin(driver);
        homepage = new Homepage(driver);
        pageCart = new PageCart(driver);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://saucedemo.com/");

        pageLogin.login_saucedemo_success("standard_user", "secret_sauce", "success");
        homepage.display_Homepage();
        homepage.getRandomProducts();
        homepage.clickAddToCart();
        homepage.verifySuccessAddToCart();
        homepage.getCart();
        pageCart.verifyProduct_inCart();
    }

    @After
    public void afterTest() {
        driver.quit();
    }
}
