package api.defs;

import api.steps.DummySteps;
import cucumber.api.java.en.When;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class DummyStepDefs {
    @Steps
    DummySteps dummySteps;

    @When("user call dummy post api with name \"([^\"]*)\", salary \"([^\"]*)\", age \"([^\"]*)\"")
    public void userCallDummyPostApiWithData(String name, String salary, String age) throws Exception {
        String[] jsonKey = {"$.name", "$.salary", "$.age"};
        String[] jsonValue = {name, salary, age};
        dummySteps.callDummyPostApi(jsonKey, jsonValue);
    }
}
