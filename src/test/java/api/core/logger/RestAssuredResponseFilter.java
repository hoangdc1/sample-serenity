package api.core.logger;


import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.print.ResponsePrinter;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.HashSet;

public class RestAssuredResponseFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RestAssuredResponseFilter.class);

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx) {
        try {
            Response response = ctx.next(requestSpec, responseSpec);
            ResponsePrinter.print(
                    response,
                    response,
                    generateStream(),
                    LogDetail.ALL,
                    true
                    );
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    private PrintStream generateStream() {
        return new PrintStream(System.out) {
            @Override
            public void print(final String string) {
                System.out.println(string);
                Serenity.recordReportData().asEvidence().withTitle("response").andContents(string);
            }
        };
    }
}
