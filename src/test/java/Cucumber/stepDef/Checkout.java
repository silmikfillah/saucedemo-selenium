package Cucumber.stepDef;

import Pages.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Checkout {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;
    PageCart pageCart;
    PageCheckoutInformation pageCheckoutInfo;
    PageCheckoutOverview pageCheckoutOverview;

    @Given("I already logged in to the Saucedemo Website to make an order")
    public void i_already_loggedin_to_make_an_order() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://saucedemo.com/");

        pageLogin = new PageLogin(driver);
        pageLogin.login_saucedemo_success("standard_user", "secret_sauce", "success");
    }

    @And("I already added products to cart and go to Cart page")
    public void i_already_added_products_to_cart_and_go_to_cart_page() {
        homepage = new Homepage(driver);
        homepage.addToCart_success();
    }

    @When("I click Checkout button")
    public void i_click_checkout_button() {
        pageCart = new PageCart(driver);
        pageCart.clickCheckout();
    }

    @And("I fill in address by input First Name with (.*)$")
    public void i_fill_in_address_by_input_first_name(String first_name) {
        pageCheckoutInfo = new PageCheckoutInformation(driver);
        if (first_name.equals("empty")) {
            pageCheckoutInfo.setFirstName("");
        } else {
            pageCheckoutInfo.setFirstName(first_name);
        }
    }

    @And("I fill in address by input Last Name with (.*)$")
    public void i_fill_in_address_by_input_last_name(String last_name) {
        if (last_name.equals("empty")) {
            pageCheckoutInfo.setLastName("");
        } else {
            pageCheckoutInfo.setLastName(last_name);
        }
    }

    @And("I fill in address by input Zip Postal Code with (.*)$")
    public void i_fill_in_address_by_input_zip_postal_code(String postal_code) {
        if (postal_code.equals("empty")) {
            pageCheckoutInfo.setPostalCode("");
        } else {
            pageCheckoutInfo.setPostalCode(postal_code);
        }
    }

    @And("I click Continue button")
    public void i_click_continue_button() {
        pageCheckoutInfo.clickContinue();
    }

    @Then("I should see the next step for set address: (.*)$")
    public void i_see_thenextstep_setAddress(String status) {
        pageCheckoutInfo.verifyAddress(status);
        if (status.equals("success")) {
            System.out.println("Address created successfully");
        } else {
            driver.close();
        }
    }

    @And("I click Finish button")
    public void i_click_finish_button() {
        pageCheckoutOverview = new PageCheckoutOverview(driver);

        pageCheckoutOverview.displayOverviewPage();
        pageCheckoutOverview.verifyAddedProductsVisible();
        pageCheckoutOverview.verifyTotalPriceVisible();
        pageCheckoutOverview.clickFinish();
    }

    @And("Order successfully created and a success message appear")
    public void a_success_message_appear() {
        pageCheckoutOverview.verifySuccessOrder();
        driver.close();
    }
}
