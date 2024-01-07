package api.core;

//import com.mb.api.core.logger.RestAssuredRequestFilter;
//import com.mb.api.core.logger.RestAssuredResponseFilter;
import api.constant.SCRestData;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class BaseSteps {

    protected RestAssuredConfig createHttpClientConfig(){
        return RestAssuredConfig.config().httpClient(
                HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 30000)
                        .setParam("http.socket.timeout", 30000)
        );
    }
//    protected RequestSpecification getSCDefaultRequestBuilder(String apiPath){
//        String token = Serenity.sessionVariableCalled("SCToken");
//        return SerenityRest.rest()
//                .contentType(ContentType.JSON).baseUri(SCRestData.SC_BASE_URI).basePath(apiPath)
//                .header("Authorization", token).config(createHttpClientConfig())
////                .filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
//                .relaxedHTTPSValidation();
//    }
    protected RequestSpecification getDummyDefaultRequestBuilder(String apiPath){
        return SerenityRest.rest()
                .contentType(ContentType.JSON).baseUri(SCRestData.DUMMY_BASE_URI).basePath(apiPath)
                .config(createHttpClientConfig())
//                .filters(new RestAssuredRequestFilter(), new RestAssuredResponseFilter())
                .relaxedHTTPSValidation();
    }
}
