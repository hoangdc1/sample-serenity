package api.runner;

import cucumber.api.CucumberOptions;

//import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "api.defs"
        ,plugin = {
                "pretty", "html:target/site/serenity/",
                "json:target/serenity-reports/cucumber_report.json"
        }
//        ,tags = ""
)
public class TestSuite {
}
