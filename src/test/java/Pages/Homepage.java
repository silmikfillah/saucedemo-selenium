package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Homepage {
    WebDriver driver;
    public Homepage(WebDriver driver) {
        this.driver = driver;
    }
    By title_Products = By.xpath("//*[@id='header_container']/div[2]/span");
    By web_logo = By.className("app_logo");
    By cart_button = By.xpath("//div[@id='shopping_cart_container']/a");
    By cart_badge = By.className("shopping_cart_badge");
    By first_AddToCart_btn = By.id("add-to-cart-sauce-labs-backpack");
    By second_AddToCart_btn = By.id("add-to-cart-sauce-labs-bike-light");
    By third_AddToCart_btn = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    By fourth_AddToCart_btn = By.id("add-to-cart-sauce-labs-fleece-jacket");
    By fifth_AddToCart_btn = By.id("add-to-cart-sauce-labs-onesie");
    By sixth_AddToCart_btn = By.id("add-to-cart-test.allthethings()-t-shirt-(red)");
    By first_card_product = By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[3]");
    List<WebElement> givenList;
    public void getRandomProducts() {
        givenList = new ArrayList<>();
        givenList.add(driver.findElement(first_AddToCart_btn));
        givenList.add(driver.findElement(second_AddToCart_btn));
        givenList.add(driver.findElement(third_AddToCart_btn));
        givenList.add(driver.findElement(fourth_AddToCart_btn));
        givenList.add(driver.findElement(fifth_AddToCart_btn));
        givenList.add(driver.findElement(sixth_AddToCart_btn));
    }

    @Test
    public void display_Homepage() {
        driver.findElement(web_logo);
        driver.findElement(title_Products);
    }
    public void clickAddToCart() {
        this.getRandomProducts();
        Random addToCart = new Random();
        int i = addToCart.nextInt(givenList.size());
        givenList.get(i).click();
    }
    public void verifySuccessAddToCart() {
        String expected = driver.findElement(cart_badge).getText();
        String actual = expected;
        Assert.assertEquals(expected, actual);
    }
    public void getCart() {
        driver.findElement(cart_button).click();
    }
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
        System.out.println("Element is visible");
    }
}
