package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageLogin {
    WebDriver driver;
    public PageLogin(WebDriver driver) {
        this.driver = driver;
    }
    By input_username = By.id("user-name");
    By input_password = By.id("password");
    By btn_login = By.id("login-button");
    By errormsg_lockedout = By.xpath("//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]");
    By errormsg_required_username = By.xpath("//*[contains(text(), 'Epic sadface: Username is required')]");
    By errormsg_required_pass = By.xpath("//*[contains(text(), 'Epic sadface: Password is required')]");
    By errormsg_incorrect = By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]");
    By homepage_logo = By.className("app_logo");
    By title_Products = By.xpath("//*[@id='header_container']/div[2]/span");
    //@FindBy(how = How.ID, using = "user-name") WebElement inputUsername;

    public void setUsername(String username) {
        driver.findElement(input_username).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(input_password).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(btn_login).click();
    }

    public void verifyLoginStatus(String status) {
        if (status.equals("success")) {
            driver.findElement(homepage_logo);
            String title_page = driver.findElement(title_Products).getText();
            Assert.assertEquals(title_page, "Products");
        } else if (status.equals("incorrect account")) {
            String errormsg_1 = driver.findElement(errormsg_incorrect).getText();
            Assert.assertEquals(errormsg_1, "Epic sadface: Username and password do not match any user in this service");
        } else if (status.equals("required username")) {
            String errormsg_2 = driver.findElement(errormsg_required_username).getText();
            Assert.assertEquals(errormsg_2, "Epic sadface: Username is required");
        } else if (status.equals("required password")) {
            String errormsg_3 = driver.findElement(errormsg_required_pass).getText();
            Assert.assertEquals(errormsg_3, "Epic sadface: Password is required");
        } else if (status.equals("locked out account")) {
            String errormsg_4 = driver.findElement(errormsg_lockedout).getText();
            Assert.assertEquals(errormsg_4, "Epic sadface: Sorry, this user has been locked out.");
        }
    }
    public void login_saucedemo_success(String username, String password, String status) {
        this.setUsername(username);
        this.setPassword(password);
        this.clickLoginButton();
        this.verifyLoginStatus(status);
    }
}
