package com.mb.api.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "com.mb.api.defs",
        plugin = {
                "pretty", "html:target/site/serenity/",
                "json:target/serenity-reports/cucumber_report.json"
        }
//        ,tags = ""
)
public class Runner {
}
