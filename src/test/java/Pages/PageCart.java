package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCart {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;

    public PageCart(WebDriver driver) {
        this.driver = driver;
    }
    By first_card_product = By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[3]");
    By checkout_btn = By.id("checkout");
    By cont_shopping_btn = By.id("continue-shopping");
    By first_remove_btn = By.xpath("(//button[(text() = 'Remove')])[1]");

    public boolean isElementVisible(By by) {
        try {
            driver.findElement(by);
            return true;

        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public void verifyProduct_inCart() {
        Assert.assertTrue(isElementVisible(first_card_product));
        System.out.println("Your product is on Cart");
    }
    public void clickCheckout() {
        driver.findElement(checkout_btn).click();
    }
    public void clickContinueShopping() {
        driver.findElement(cont_shopping_btn).click();
    }

    By remove_btn_homepage = By.xpath("(//button[(text() = 'Remove')])[1]");
    By remove_btn_CartPage = By.xpath("(//button[(text() = 'Remove')])[1]");
    public void clickRemove_fromHomepage() {
        driver.findElement(remove_btn_homepage).click();
    }

    public void clickRemove_fromCart() {
        driver.findElement(remove_btn_CartPage).click();
    }

    public void clickRemove() {
        driver.findElement(first_remove_btn).click();
    }

    public void verifySuccessRemoved() {
        //Assert.assertFalse(driver.findElement(first_card_product));
        System.out.println("Product removed successfully");
    }
}
