package api.steps;

import api.constant.SCRestData;
import api.core.BaseSteps;
import api.utils.Utilities;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

public class DummySteps extends BaseSteps {

    @Step
    public Response callDummyPostApi(String[] jsonKey, String[] jsonValue) throws Exception {
        System.out.println(SCRestData.POST_REQUEST_DUMMY);
        System.out.println(SCRestData.DUMMY_BASE_URI);
        System.out.println(Utilities.getFilePathFromDataConf("apiData.dummyPostApi.requestBody"));
        String jsonBody = Utilities.modifyJson(Utilities.getFilePathFromDataConf("apiData.dummyPostApi.requestBody"), jsonKey, jsonValue);
        Response response = getDummyDefaultRequestBuilder(SCRestData.POST_REQUEST_DUMMY)
                .body(jsonBody).post();
        Serenity.setSessionVariable("response").to(response);
        return response;
    }
}
