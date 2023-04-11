package apiTest;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RoleService extends BaseURLs {
    @Test
    public void TC01allRole(){


        extentTest = extentReports.createTest("Get All Roles");

        extentTest.info("URL set edildi ");

        specification.pathParam("rolePath","role");

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{rolePath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);
    }
    @Test
    public  void TC02appIdRolle() {


        extentTest = extentReports.createTest("Get All Application Roles by App Id");

        extentTest.info("URL set edildi ");

        specification.pathParams("applicationPath","application","appIdPath",1,"rolepath","role");

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{applicationPath}/{appIdPath}/{rolepath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

    }
    @Test
    public  void TC03ıdRolle(){


        extentTest = extentReports.createTest("Get Role by Id");

        extentTest.info("URL set edildi ");

        specification.pathParams("rolepath","role","idPath",3);

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{rolepath}/{idPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

    }
    @Test
    public  void TC04newAddRolle(){


        extentTest = extentReports.createTest("Add New Role");

        extentTest.info("URL set edildi ");

        specification.pathParam("rolepath","role");

        extentTest.info("POST methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                post("/{rolepath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(415)
                .contentType(ContentType.JSON);

    }

}