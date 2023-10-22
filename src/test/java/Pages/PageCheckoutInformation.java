package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCheckoutInformation {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;
    PageCart pageCart;
    public PageCheckoutInformation(WebDriver driver) {
        this.driver = driver;
    }
    By input_firstname = By.id("first-name");
    By input_lastname = By.id("last-name");
    By input_postcode = By.id("postal-code");
    By continue_btn = By.id("continue");
    By cancel_btn = By.id("cancel");
    By errormsg_fname = By.xpath("//*[contains(text(), 'Error: First Name is required')]");
    By erromsg_lname = By.xpath("//*[contains(text(), 'Error: Last Name is required')]");
    By errormsg_postcode = By.xpath("//*[contains(text(), 'Error: Postal Code is required')]");
    By title_Information = By.xpath("//*[contains(text(), 'Checkout: Your Information')]");
    By title_Overview = By.xpath("//*[contains(text(), 'Checkout: Overview')]");

    public void displayCheckoutInfo() {
        driver.findElement(title_Information);
        System.out.println("Please set an address");
    }
    public void setFirstName(String first_name) {
        driver.findElement(input_firstname).sendKeys(first_name);
    }
    public void setLastName(String last_name) {
        driver.findElement(input_lastname).sendKeys(last_name);
    }
    public void setPostalCode(String postal_code) {
        driver.findElement(input_postcode).sendKeys(postal_code);
    }
    public void clickContinue() {
        driver.findElement(continue_btn).click();
    }
    public void clickCancel() {
        driver.findElement(cancel_btn).click();
    }
    public void verifyAddress(String status) {
        pageCart = new PageCart(driver);

        if (status.equals("success")) {
            Assert.assertTrue(pageCart.isElementVisible(title_Overview));
        } else if (status.equals("required fname")) {
            String errormsg1 = driver.findElement(errormsg_fname).getText();
            Assert.assertEquals(errormsg1, "Error: First Name is required");
            System.out.println(errormsg1);
        } else if (status.equals("required lname")) {
            String errormsg2 = driver.findElement(erromsg_lname).getText();
            Assert.assertEquals(errormsg2, "Error: Last Name is required");
            System.out.println(errormsg2);
        } else if (status.equals("required postcode")) {
            String errormsg3 = driver.findElement(errormsg_postcode).getText();
            Assert.assertEquals(errormsg3, "Error: Postal Code is required");
            System.out.println(errormsg3);
        }
    }
    public void setAddress(String first_name, String last_name, String postal_code, String status) {
        this.setFirstName(first_name);
        this.setLastName(last_name);
        this.setPostalCode(postal_code);
        this.clickContinue();
        this.verifyAddress(status);
    }
}
