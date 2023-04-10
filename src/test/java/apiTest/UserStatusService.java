package apiTest;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.UserStatusServiceData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserStatusService extends BaseURLs {

    public static int  id;
    @Test
    public void TC_01() {

        extentTest = extentReports.createTest("Get All User Statuses", "Get All User Statuses");

        extentTest.info("URL set edildi ");

        specification.pathParam("user-statusPath", "user-status");

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{user-statusPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public  void  TC_02(){

        extentTest = extentReports.createTest("addNewUserStatus","Membership details should be supplied in the body");

        extentTest.info("URL set edildi ");

        specification.pathParam("user-statusPath","user-status");

        extentTest.info("ExceptedAndRequestBody Data oluşturuldu");

        UserStatusServiceData userStatusServiceData=new UserStatusServiceData();

        Map<Object,Object>reqBodyAndExpectedData=userStatusServiceData.reqTestDataBody01();

        System.out.println("reqBodyAndExpectedData = " + reqBodyAndExpectedData);


        extentTest.info("Post methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                post("/{user-statusPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(201)
                .contentType(ContentType.JSON);

        Assert.assertEquals(reqBodyAndExpectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(reqBodyAndExpectedData.get("description"),actualData.get("description"));

        JsonPath jsonPath = response.jsonPath();
        id=jsonPath.get("id");
        System.out.println("id = " + id);


    }
    @Test
    public  void  TC_03(){

        extentTest = extentReports.createTest("UpdateExistingMembership","Membership details should be supplied for update in the body");

        extentTest.info("URL set edildi ");

        specification.pathParam("user-statusPath","user-status");
        extentTest.info("ExceptedAndRequestBody Data oluşturuldu");

        UserStatusServiceData userStatusServiceData=new UserStatusServiceData();

        Map<Object,Object>reqBodyAndExpectedData=userStatusServiceData.reqTestDataBody02();

        extentTest.info("Put methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                put("/{user-statusPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

        Assert.assertEquals(reqBodyAndExpectedData.get("id"),actualData.get("id"));
        Assert.assertEquals(reqBodyAndExpectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(reqBodyAndExpectedData.get("description"),actualData.get("description"));

    }

    @Test
    public  void  TC_04(){

        extentTest = extentReports.createTest("MembershipSubscriptionUUID", "Membership Subscription UUID should be inserted as path parameter.");

        extentTest.info("URL set edildi ");

        specification.pathParams("user-statusPath", "user-status","IdPath",id);


        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{user-statusPath}/{IdPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();


        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);

        Assert.assertEquals("turgut",actualData.get("name"));

        extentTest.info("update kontrol edildi");
    }

    @Test
    public void TC_05(){

        extentTest = extentReports.createTest("deleteExistingMembership", " Membership Subscription ID should be inserted as path parameter.");

        extentTest.info("URL set edildi ");

        specification.pathParams("user-statusPath", "user-status","idPath",id);

        extentTest.info("Delete methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                delete("/{user-statusPath}/{idPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200);

        extentTest.info("delete kobtrol edildi");
        extentTest.info("turgut");

    }

}
