package apiTest;

import baseURLs.BaseURLs;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import testData.MembershipTypeServiceData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MembershipTypeService extends BaseURLs {
    public static int  id;
    @Test
    public void TC_01() {
        extentTest = extentReports.createTest("Get All Membership Types", "Get All Membership Types.");

        extentTest.info("URL set edildi ");

        specification.pathParam("membership-typePath", "membership-type");

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{membership-typePath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

    }
    @Test
    public  void TC_02(){

        extentTest = extentReports.createTest("Add New Membership Type"," 'name', and 'app_id' are required to create new Membership Type");

        extentTest.info("URL set edildi ");

        specification.pathParam("membership-typePath","membership-type");

        extentTest.info("ExceptedAndRequestBody Data oluşturuldu");

        MembershipTypeServiceData membershipTypeServiceData=new MembershipTypeServiceData();

        Map<Object,Object> reqBodyAndExpectedData=membershipTypeServiceData.reqTestDataBody01();

        extentTest.info("Post methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                post("/{membership-typePath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);


        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().contentType(ContentType.JSON);

        Assert.assertEquals(reqBodyAndExpectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(reqBodyAndExpectedData.get("app_id"),actualData.get("app_id"));

        JsonPath jsonPath = response.jsonPath();
        id=jsonPath.get("id");
        System.out.println("subscription_id = " + id);

    }
    @Test()
    public  void TC_03(){

        extentTest = extentReports.createTest("Update Existing Membership Type","At least, 'id' is required to update the existing Membership Type.");

        extentTest.info("URL set edildi ");

        specification.pathParam("membership-typePath","membership-type");

        extentTest.info("ExceptedAndRequestBody Data oluşturuldu");

        MembershipTypeServiceData membershipTypeServiceData=new MembershipTypeServiceData();

        Map<Object,Object>reqBodyAndExpectedData=membershipTypeServiceData.reqTestDataBody02();

        System.out.println("requestAndExceptedData = " + reqBodyAndExpectedData);

        extentTest.info("Put methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                put("/{membership-typePath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

        Assert.assertEquals(reqBodyAndExpectedData.get("app_id"),actualData.get("app_id"));
        Assert.assertEquals(reqBodyAndExpectedData.get("name"),actualData.get("name"));
        Assert.assertEquals(reqBodyAndExpectedData .get("is_enabled"),actualData.get("is_enabled"));


    }
    @Test
    public  void  TC_04(){

        extentTest = extentReports.createTest("MembershipSubscriptionUUID", "Membership Subscription UUID should be inserted as path parameter.");

        extentTest.info("URL set edildi ");

        specification.pathParams("membership-typePath", "membership-type","IdPath",id);


        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{membership-typePath}/{IdPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();


        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);


        extentTest.info("update kontrol edildi");
    }
    @Test
    public void TC_05(){

        extentTest = extentReports.createTest("Delete Existing Membership Type by Id", "ID should be inserted as path parameter");

        extentTest.info("URL set edildi ");

        specification.pathParams("membership-typePath", "membership-type","idPath",id);

        extentTest.info("Delete methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                delete("/{membership-typePath}/{idPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200);

        extentTest.info("delete kontrol edildi");

    }



}
