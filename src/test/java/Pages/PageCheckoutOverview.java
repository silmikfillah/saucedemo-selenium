package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCheckoutOverview {
    WebDriver driver;
    PageLogin pageLogin;
    Homepage homepage;
    PageCart pageCart;
    PageCheckoutInformation pageCheckoutInfo;
    public PageCheckoutOverview(WebDriver driver) {
        this.driver = driver;
    }
    By title_Checkout_Overview = By.xpath("//*[contains(text(), 'Checkout: Overview')]");
    public void displayOverviewPage() {
        driver.findElement(title_Checkout_Overview);
        System.out.println("You are on the Checkout Overview Page");
    }
    By first_product_card = By.xpath("(//div[@class = 'cart_item'])[1]");
    public void verifyAddedProductsVisible() {
        driver.findElement(first_product_card).isDisplayed();
        System.out.println("Your Products are here");
    }
    By total_price = By.xpath("//*[@class = 'summary_info_label summary_total_label']");
    public void verifyTotalPriceVisible() {
        driver.findElement(total_price).isDisplayed();
        System.out.println("Total price is visible");
    }
    By finish_btn = By.id("finish");
    By cancel_btn = By.id("cancel");
    public void clickFinish() {
        driver.findElement(finish_btn).click();
    }
    By successmsg_Order_Thx = By.xpath("//div[@id='checkout_complete_container']/h2");
    By successmsg_Order_Dispatched = By.xpath("//div[@id='checkout_complete_container']/div");
    By backtohome_btn = By.id("back-to-products");
    public void verifySuccessOrder() {
        String thankyou = driver.findElement(successmsg_Order_Thx).getText();
        String expected1 = thankyou;
        Assert.assertEquals(expected1, thankyou);
        System.out.println(thankyou);

        String dispatched = driver.findElement(successmsg_Order_Dispatched).getText();
        String expected2 = dispatched;
        Assert.assertEquals(expected2, dispatched);
        System.out.println(dispatched);
    }
}
