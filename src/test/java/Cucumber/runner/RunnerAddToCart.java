package Cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Cucumber/features/AddToCart.feature",
        glue = "Cucumber.stepDef",
        tags = "@AddToCart",
        plugin = "html:Reports/Cucumber/AddToCart.html"
)
public class RunnerAddToCart {
}
