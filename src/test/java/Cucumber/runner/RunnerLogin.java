package Cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Cucumber/features/Login.feature",
        glue = "Cucumber.stepDef",
        plugin = "html:Reports/Cucumber/Login.html"
)

public class RunnerLogin {
}
