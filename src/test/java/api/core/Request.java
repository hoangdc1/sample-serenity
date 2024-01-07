//package com.mb.api.core;
//
//
//import com.mb.api.utils.Utilities;
//import io.restassured.config.HttpClientConfig;
//import io.restassured.config.RestAssuredConfig;
//import io.restassured.http.ContentType;
//import io.restassured.http.Cookie;
//import io.restassured.http.Header;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import net.serenitybdd.core.Serenity;
//import net.serenitybdd.rest.SerenityRest;
//import org.assertj.core.util.Strings;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;
//import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT;
//
//public class Request {
//    private String baseUri;
//    private Map<String, String> pathParams = new HashMap<>();
//    private Map<String, String> params = new HashMap<>();
//    private Map<String, String> queryParams = new HashMap<>();
//    private String url;
//    private String body;
//    private List<Header> header = new ArrayList<>();
//    private String contentType;
//    private Map<String, File> multiPartFile = new HashMap<>();
//    private Map<String, String> multiPartText = new HashMap<>();
//    private RequestSpecification request;
//    private static String flag = "rest";
//    private boolean flagEncoding = true;
//    private boolean isMultiPartJsonFile = false;
//    private int timeout;
//
//    private String basicAuthUsername = Utilities.getValueOfDataConf("internal.common.userName");
//
//    private String basicAuthPassword = Utilities.getValueOfDataConf("internal.common.password");
//
//    public static Request newBuilder() {
//        return new Request();
//    }
//
//    private Request() {
//    }
//
//    public Request params(Map<String, String> data) {
//        params.putAll(data);
//        return this;
//    }
//
//    public Request queryParams(Map<String, String> data) {
//        queryParams.putAll(data);
//        return this;
//    }
//
//    public Request isMultipartJsonFile(boolean data) {
//        isMultiPartJsonFile = data;
//        return this;
//    }
//
//    public Request pathParams(Map<String, String> data) {
//        pathParams.putAll(data);
//        return this;
//    }
//
//    public Request baseUri(String data) {
//        baseUri = data;
//        return this;
//    }
//
//    public Request contentType(String data) {
//        contentType = data;
//        return this;
//    }
//
//    public Request basicAuth(String username, String password) {
//        basicAuthUsername = username;
//        basicAuthPassword = password;
//        return this;
//    }
//
//    public Request body(String data) {
//        body = data;
//        return this;
//    }
//
//    public Request url(String data) {
//        url = data;
//        return this;
//    }
//
//    public Request header(Header data) {
//        header.add(data);
//        return this;
//    }
//
//    public Request multiPartFile(Map<String, File> data) {
//        multiPartFile.putAll(data);
//        return this;
//    }
//
//    public Request multiPartText(Map<String, String> data) {
//        multiPartText.putAll(data);
//        return this;
//    }
//
//    public Request encoding(boolean flag) {
//        flagEncoding = flag;
//        return this;
//    }
//
//    public Request setTimeout(int timeout) {
//        timeout = timeout;
//        return this;
//    }
////    request.config(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout", 1)));
//
//    public Request buildRestRequest() {
//        flag = "rest";
//        request = SerenityRest.rest()
//                .relaxedHTTPSValidation()
//                .baseUri(baseUri);
//
//        if (Strings.isNullOrEmpty(contentType)) {
//            request.contentType(ContentType.JSON);
//        } else {
//            request.contentType(contentType);
//        }
//
//        if (pathParams.size() > 0) {
//            request.pathParams(pathParams);
//        }
//
//        if (params.size() > 0) {
//            request.params(params);
//        }
//
//        if (queryParams.size() > 0) {
//            request.queryParams(queryParams);
//        }
//
//        if (header.size() > 0) {
//            header.forEach(item -> request.header(item));
//        }
//
//        if (!Strings.isNullOrEmpty(body)) {
//            request.body(body);
//        }
//
//        if (multiPartFile.size() > 0) {
//            multiPartFile.forEach((key, value) -> {
//                if (isMultiPartJsonFile) {
//                    request.multiPart(key, value, "application/json");
//                } else {
//                    request.multiPart(key, value);
//                }
//            });
//        }
//
//        if (multiPartText.size() > 0) {
//            multiPartText.forEach((key, value) -> {
//                request.multiPart(key, value);
//            });
//        }
//
//        if (!flagEncoding) request.urlEncodingEnabled(false);
//
//        if (timeout > 0) {
//            request.config(RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
//                    .setParam(CONNECTION_TIMEOUT, timeout)
//                    .setParam(SO_TIMEOUT, timeout)));
////            request = RestAssured.config()
////                    .httpClient(HttpClientConfig.httpClientConfig()
////                            .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000)
////                            .setParam(CoreConnectionPNames.SO_TIMEOUT, 1000));
//        }
//
//        return this;
//    }
//
//    public Request buildSoapRequest() {
//        flag = "soap";
//        request = SerenityRest.rest()
//                .relaxedHTTPSValidation()
//                .auth().preemptive()
//                .basic(basicAuthUsername, basicAuthPassword)
//                .baseUri(baseUri);
//        if (contentType == null) {
//            request.contentType(ContentType.XML);
//        } else {
//            request.contentType(contentType);
//        }
//
//        if (params.size() > 0) {
//            request.params(params);
//        }
//
//        if (queryParams.size() > 0) {
//            request.queryParams(queryParams);
//        }
//
//        if (header.size() > 0) {
//            header.forEach(item -> request.header(item));
//        }
//
//        if (!Strings.isNullOrEmpty(body)) {
//            request.body(body);
//        }
//
//        if (!Strings.isNullOrEmpty(body)) {
//            request.body(body);
//        }
//
//        return this;
//    }
//
////    public Response callGet() {
////        Response response;
////        try {
////            response = request.relaxedHTTPSValidation().log().all().get(url);
////        } catch (Exception ex) {
////            throw new BBException("Cannot send GET request to " + url, ex);
////        }
////        saveResponseToSession(response);
////        return response;
////    }
//
//    public Response callPost() {
//        Response response;
////        try {
//        response = request.relaxedHTTPSValidation().log().all().post(url);
////        } catch (Exception ex) {
////            throw new BBException("Cannot send POST request to " + url, ex);
////        }
//        saveResponseToSession(response);
//        return response;
//    }
//
////    public Response callPatch() {
////        Response response;
////        try {
////            response = request.log().all().patch(url);
////        } catch (Exception ex) {
////            throw new BBException("Cannot send PATCH request to " + url, ex);
////        }
////        saveResponseToSession(response);
////        return response;
////    }
//
////    public Response callPut() {
////        Response response;
////        try {
////            response = request.log().all().put(url);
////        } catch (Exception ex) {
////            throw new BBException("Cannot send PUT request to " + url, ex);
////        }
////        saveResponseToSession(response);
////        return response;
////    }
////
////    public Response callDelete() {
////        Response response;
////        try {
////            response = request.log().all().delete(url);
////        } catch (Exception ex) {
////            throw new BBException("Cannot send DELETE request to " + url, ex);
////        }
////        saveResponseToSession(response);
////        return response;
////    }
//
//    private void saveResponseToSession(Response response) {
////        Logging.info(response.asString());
////        Objects.requireNonNull(response);
//        if (flag.equals("rest")) {
//            Serenity.setSessionVariable("rest").to(response);
//            saveResponseCookieToSession(response);
//        } else {
//            Serenity.setSessionVariable("soap").to(response);
//        }
//    }
//
//    private void saveResponseCookieToSession(Response response) {
//        List<Cookie> list = response.getDetailedCookies().asList();
//        StringBuilder value = new StringBuilder();
//        for (int i = 0; i < list.size(); i++) {
//            value.append(list.get(i).getName())
//                    .append("=")
//                    .append(list.get(i).getValue())
//                    .append(i != list.size() - 1 ? ";" : "");
//        }
//        Serenity.setSessionVariable("cookie").to(value.toString());
//    }
//
//    public Request buildRestRequestWithAuthen(String username, String password) {
//        flag = "rest";
//        request = SerenityRest.rest()
//                .relaxedHTTPSValidation()
//                .baseUri(baseUri)
//                .auth().preemptive().basic(username,password);
//        if (multiPartText.size() > 0) {
//            multiPartText.forEach((key, value) -> {
//                request.multiPart(key, value);
//            });
//        }
//        return this;
//    }
//}
