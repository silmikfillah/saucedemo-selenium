package Cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Cucumber/features/Checkout.feature",
        glue = "Cucumber.stepDef",
        tags = "@Checkout",
        plugin = "html:Reports/Cucumber/Checkout.html"
)
public class RunnerCheckout {
}
