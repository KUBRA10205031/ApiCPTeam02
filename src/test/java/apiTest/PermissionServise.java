package apiTest;

import baseURLs.BaseURLs;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import testData.PermissionTestData;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PermissionServise extends BaseURLs {
    public static JsonPath jsonPath;

    public static int permissionId;

    @Test
    public void TC01() {
        extentTest = extentReports.createTest("Get All Permissions", "Description");
        extentTest.info("URL set edildi ");

        extentTest.info("GET methodu ile request gönderildi");

        specification.pathParam("permissionPath", "permission");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{permissionPath}");

        extentTest.info("Response yazdırıldı");
        System.out.println("response = " + response);
        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");
        response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        extentTest.pass("Testimiz gecerli");
    }

    /*
     {

             "resource": "file",
             "action": "read",
             "app_id": 2
     }
 */
    @Test
    public void TC02() {


        extentTest = extentReports.createTest("Permissionpost");

        specification.pathParam("permissionPath", "permission");

        PermissionTestData userStatusServiceData=new PermissionTestData();

        Map<Object,Object> reqBodyAndExpectedData=userStatusServiceData.reqTestDataBody01();

        extentTest = extentReports.createTest("add New Permission");

        extentTest.info("Url'e gidilip access token alınıyor...");

        extentTest.info("Post metodu ile request atıldı");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                post("/{permissionPath}");


        // response.then().assertThat().statusCode(201);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        extentTest.info("Response'la gelen Status Code dogrulandı");

        Map<String, Object> actualDataAndResponseBody = response.as(Map.class);
        System.out.println("actualDataAndResponseBody: " + actualDataAndResponseBody);


        JsonPath jsonPath = response.jsonPath();
        permissionId = jsonPath.get("id");
        System.out.println("postReqId = " + permissionId);

        assertEquals(jsonPath.getString("name"), actualDataAndResponseBody.get("name"));
        assertEquals(jsonPath.getString("description"), actualDataAndResponseBody.get("description"));
        extentTest.info("Expected Data ile Actual Data karşılaştırıldı...");
        extentTest.info("Test Passed Oldu..:)");

    }

    @Test
    public void TC03() {
        extentTest = extentReports.createTest("Permissionpost");

        specification.pathParam("permissionPath", "permission");

        PermissionTestData userStatusServiceData=new PermissionTestData();

        Map<Object,Object> reqBodyAndExpectedData=userStatusServiceData.reqTestDataBody01();

        extentTest = extentReports.createTest("add New Permission");

        extentTest.info("Url'e gidilip access token alınıyor...");

        extentTest.info("Put metodu ile request atıldı");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                put("/{permissionPath}");


         response.then().assertThat().statusCode(200);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        extentTest.info("Response'la gelen Status Code dogrulandı");

        Map<String, Object> actualDataAndResponsePutBody = response.as(Map.class);

        System.out.println("actualDataAndResponseBody: " + actualDataAndResponsePutBody);

        JsonPath jsonPath=response.jsonPath();

        assertEquals(jsonPath.getString("resource"), actualDataAndResponsePutBody.get("resource"));
        extentTest.info("Expected Data ile Actual Data karşılaştırıldı...");
        extentTest.info("Test Passed Oldu..:)");

    }

    @Test
    public void TC04() {

        extentTest = extentReports.createTest("Permission New Get");
        //https://a3m-qa-gm3.quaspareparts.com/auth/api/permission/774

        String url = "https://a3m-qa-gm3.quaspareparts.com/auth/api/";
        String permissionUrl = url + "permission" + "/"+ permissionId;

        extentTest.info("Url'e gidilip access token alınıyor...");

        extentTest.info("Assertion işlemi yapıldı");
        Response response = given().when().
                header("Authorization", "Bearer " + access_token).
                get(permissionUrl);
        response.prettyPrint();
        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);
        extentTest.pass("Testimiz gecerli");



    }
    @Test
    public void TC05 () {
        extentTest = extentReports.createTest("Permissin Delete");

        //https://a3m-qa-gm3.quaspareparts.com/auth/api/permission/751
        String url2 = "https://a3m-qa-gm3.quaspareparts.com/auth/api/permission/";
        String permissionUrl2 = url2 + permissionId;

        extentTest.info("Url'e gidilip access token alınıyor...");
        extentTest.info("Delete metodu ile request atıldı");

        Response response = given().headers("Authorization",
                        "Bearer " + access_token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept", ContentType.JSON)
                .when()
                .delete(permissionUrl2)
                .then()
                .extract()
                .response();
        extentTest.info("Delete metodu ile girilen id ye sahip permission silindi request atıldı");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        extentTest.info("Permission'un silindiği doğrulandı");
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        extentTest.pass("Testimiz gecerli");

    }
}
