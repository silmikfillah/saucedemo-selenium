package Cucumber.stepDef;

import Pages.Homepage;
import Pages.PageCart;
import Pages.PageLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class RemoveProduct {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;
    PageCart pageCart;

    @Given("I already login to the Website")
    public void i_already_login_to_the_website() {
        String baseurl = "https://www.saucedemo.com/";
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseurl);

        pageLogin = new PageLogin(driver);
        pageLogin.login_saucedemo_success("standard_user", "secret_sauce", "success");
    }

    @And("I already added a product to cart")
    public void i_already_added_a_product_to_cart() {
        homepage = new Homepage(driver);
        homepage.display_Homepage();
        homepage.addToCart_success();
    }

    @When("I go to Cart page")
    public void i_go_to_cart_page() {
        homepage.getCart();
        homepage.verifySuccessAddToCart();
    }

    @And("I click Remove button")
    public void i_click_remove_button() {
        pageCart = new PageCart(driver);
        pageCart.clickRemove();
    }

    @Then("The product should be removed from cart")
    public void the_product_should_be_removed_from_cart() {
        pageCart.verifySuccessRemoved();
        driver.close();
    }
}
