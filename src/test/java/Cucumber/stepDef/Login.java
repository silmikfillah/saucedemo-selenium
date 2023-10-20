package Cucumber.stepDef;

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

public class Login {
    WebDriver driver;
    PageLogin pageLogin;
    String baseurl = "https://www.saucedemo.com/";

    @Given("I access saucedemo website and go to Login page")
    public void i_access_saucedemo_website() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        driver = new ChromeDriver(opt);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseurl);
    }

    @When("I input username with (.*)$")
    public void i_input_username(String username) {
        pageLogin = new PageLogin(driver);
        if (username.equals("empty")) {
            pageLogin.setUsername("");
        } else {
            pageLogin.setUsername(username);
        }
    }

    @And("I input password with (.*)$")
    public void i_input_password(String password) {
        if (password.equals("empty")) {
            pageLogin.setPassword("");
        } else {
            pageLogin.setPassword(password);
        }
    }

    @And("I click the Login button")
    public void i_click_loginbutton() {
        pageLogin.clickLoginButton();
    }

    @Then("I should see the next step for login: (.*)$")
    public void i_should_seenextstep_login(String status) {
        pageLogin.verifyLoginStatus(status);
        driver.close();
    }
}
