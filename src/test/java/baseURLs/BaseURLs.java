package baseURLs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import utilities.BaseTestReport;

public class BaseURLs extends BaseTestReport {

    protected RequestSpecification specification;



    @Before

    public void setUpBaseURL(){



        specification = new RequestSpecBuilder().

                setBaseUri("https://a3m-qa-gm3.quaspareparts.com/auth/api").

                build();

    }


}
