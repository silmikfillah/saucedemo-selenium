package Test;

import Pages.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Checkout {
    WebDriver driver;
    PageLogin pageLogin;
    PageCart pageCart;
    PageCheckoutInformation pageCheckoutInformation;
    Homepage homepage;
    PageCheckoutOverview pageCheckoutOverview;
    @Before
    public void setupCheckout() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);
        driver = new ChromeDriver(opt);
    }
    @Test
    public void test_Checkout() {
        pageLogin = new PageLogin(driver);
        homepage = new Homepage(driver);
        pageCart = new PageCart(driver);
        pageCheckoutInformation = new PageCheckoutInformation(driver);
        pageCheckoutOverview = new PageCheckoutOverview(driver);

        String fileDir = System.getProperty("user.dir") + "/src/test/data/address-data.csv";

        try (CSVReader reader = new CSVReader(new FileReader(fileDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String first_name = nextLine[0];
                String last_name = nextLine[1];
                String postal_code = nextLine[2];
                String status = nextLine[3];

                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get("https://saucedemo.com/");

                pageLogin.login_saucedemo_success("standard_user", "secret_sauce", "success");

                if (status.equals("success")) {
                    homepage.addToCart_success();
                    pageCart.clickCheckout();

                    pageCheckoutInformation.displayCheckoutInfo();
                    pageCheckoutInformation.setAddress(first_name, last_name, postal_code, status);

                    pageCheckoutOverview.displayOverviewPage();
                    pageCheckoutOverview.verifyAddedProductsVisible();
                    pageCheckoutOverview.verifyTotalPriceVisible();
                    pageCheckoutOverview.clickFinish();

                    pageCheckoutOverview.verifySuccessOrder();
                } else {
                    try {
                        homepage.addToCart_success();

                        pageCart.clickCheckout();
                        pageCheckoutInformation.displayCheckoutInfo();
                        pageCheckoutInformation.setAddress(first_name, last_name, postal_code, status);
                    } catch (Exception e2) {
                        homepage.addToCart_refresh();

                        pageCart.clickCheckout();
                        pageCheckoutInformation.displayCheckoutInfo();
                        pageCheckoutInformation.setAddress(first_name, last_name, postal_code, status);
                    }
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    @After
    public void afterTest() {
        driver.close();
    }
}
