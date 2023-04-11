package apiTest;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojoData.UserGroupServicePojo;
import testData.UserGroupServiceData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UserGroupService extends BaseURLs {
    static Response response;
    public static Integer id;

    @Test
    public void TC_01() {
        extentTest = extentReports.createTest("AllUserGroups", "Get All User Groups");

        extentTest.info("URL set edildi ");
        specification.pathParam("user-groupPath", "user-group");

        extentTest.info("GET methodu ile request gönderildi");

        response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{user-groupPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);
    }
    @Test

    public void TC_02() {

        extentTest = extentReports.createTest("Kullanici Post methodu ile request gonderirir");

        specification.pathParam("user-groupPath", "user-group");

        UserGroupServiceData expectedData = new UserGroupServiceData();
        HashMap<String, Object> enteredReqBody = expectedData.setReqBody("Sümeyra3");


        response = given().
                spec(specification).contentType(ContentType.JSON).
                header("Authorization", "Bearer " + access_token).
                when().
                body(enteredReqBody).
                post("/{user-groupPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap <String,Object>actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);

        extentTest.info("Status code 201 olduğu doğrulandı");
        response.then().assertThat().statusCode(201);

        extentTest.info("Assertion işlemleri yapıldı");
        assertEquals(enteredReqBody.get("name"), actualData.get("name"));
        JsonPath jsonPath =response.jsonPath();
        id=jsonPath.get("id");
    }
    @Test

    public void TC_03() {

        extentTest = extentReports.createTest("Kullanici Put methodu ile request gonderirir");

        specification.pathParam("user-groupPath", "user-group");
        UserGroupServiceData expectedData = new UserGroupServiceData();
        HashMap<String, Object> enteredReqBody1 = expectedData.setUpForPutReq(id,"Sümeyra3",2,182);
        response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(enteredReqBody1).
                when().
                put("/{user-groupPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap <String,Object>actualData = response.as(HashMap.class);
        System.out.println("Actual Data: " + actualData);

       extentTest.info("Status code 200 olduğu doğrulandı");
        response.then().assertThat().statusCode(200);

       extentTest.info("Assertion işlemleri yapıldı");
        assertEquals(enteredReqBody1.get("name"), actualData.get("name"));


    }
    @Test

    public void TC_04() {

        extentTest = extentReports.createTest("Kullanici delete methodu ile request gonderirir");

        specification.pathParams("user-groupPath", "user-group", "idPath", id);

        response = given().
                spec(specification).
                header("Authorization", "Bearer " + access_token).
                when().
                delete("{user-groupPath}/{idPath}");

        extentTest.info("Response yazdırıldı");
        response.prettyPrint();

        extentTest.info("Status code 200 olduğu doğrulandı");
        response.then().assertThat().statusCode(200);


    }


}



