package com.mb.api.core.logger;//package com.mb.api.core.logger;
//
//
//import io.restassured.filter.Filter;
//import io.restassured.filter.FilterContext;
//import io.restassured.filter.log.LogDetail;
//import io.restassured.filter.log.UrlDecoder;
//import io.restassured.internal.print.RequestPrinter;
//import io.restassured.response.Response;
//import io.restassured.specification.FilterableRequestSpecification;
//import io.restassured.specification.FilterableResponseSpecification;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.PrintStream;
//import java.nio.charset.Charset;
//
//public class RestAssuredRequestFilter implements Filter {
//    private static final Logger logger = LoggerFactory.getLogger(RestAssuredRequestFilter.class);
//
//    @Override
//    public Response filter(
//            FilterableRequestSpecification requestSpec,
//            FilterableResponseSpecification responseSpec,
//            FilterContext ctx) {
//        String uri = requestSpec.getURI();
//        uri =
//                UrlDecoder.urlDecode(
//                        uri,
//                        Charset.forName(
//                                requestSpec
//                                        .getConfig()
//                                        .getEncoderConfig()
//                                        .defaultQueryParameterCharset()),
//                        true);
//        RequestPrinter.print(
//                requestSpec,
//                requestSpec.getMethod(),
//                uri,
//                LogDetail.ALL,
//                generateStream(),
//                true);
//        return ctx.next(requestSpec, responseSpec);
//    }
//
//    private PrintStream generateStream() {
//        return new PrintStream(System.out) {
//            @Override
//            public void print(final String string) {
//                System.out.println(string);
//                logger.info(string);
//            }
//        };
//    }
//}
