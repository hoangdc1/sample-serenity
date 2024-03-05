package api.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = "api.defs"
        ,plugin = {
                "pretty", "html:target/site/serenity/"
        }
//        ,tags = ""
)
public class TestSuite {
}
