package Cucumber.stepDef;

import Pages.Homepage;
import Pages.PageLogin;
import io.cucumber.java.Before;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class AddToCart {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://saucedemo.com/");
    }
    @Given("I already login to the Saucedemo Website")
    public void i_already_login_tothewebsite() {
        pageLogin = new PageLogin(driver);
        pageLogin.login_saucedemo_success("standard_user", "secret_sauce", "success");
    }

    @And("I on the homepage")
    public void i_on_the_homepage() {
        homepage = new Homepage(driver);
        homepage.display_Homepage();
    }

    @When("I select one of products")
    public void i_select_one_of_products() {
        homepage.getRandomProducts();
    }

    @A("I click the Add To Cart button")
    public void i_click_the_add_to_cart_button() {
        homepage.clickAddToCart();
    }

    @Then("The product will be added to cart")
    public void the_product_will_be_added_to_cart() {
        homepage.verifySuccessAddToCart();
        homepage.getCart();
        homepage.verifyProduct_inCart();
        driver.quit();
    }

}
