package Cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Cucumber/features/RemoveProduct.feature",
        glue = "Cucumber.stepDef",
        plugin = "html:Reports/Cucumber/RemoveProduct.html"
)

public class RunnerRemove {
}
