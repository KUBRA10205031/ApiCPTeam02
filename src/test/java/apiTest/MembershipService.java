package apiTest;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.MembershipServiceData;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class MembershipService extends BaseURLs {

    public static Object  subscription_id;

    @Test()
    public void TC_01() {

        extentTest = extentReports.createTest("Get All Memberships", "Enter user id to fetch memberships of specific user. If absent, memberships of Principal user are returned.");

        extentTest.info("URL set edildi ");

        specification.pathParam("membershipPath", "membership");

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{membershipPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public  void TC_02(){

        extentTest = extentReports.createTest("AddNewMembership","Membership details should be supplied in the body");

        extentTest.info("URL set edildi ");

        specification.pathParam("membershipPath", "membership");

        extentTest.info("ExceptedAndRequestBody Data oluşturuldu");

         MembershipServiceData membershipServiceData=new MembershipServiceData();

        Map<Object,Object>reqBodyAndExpectedData=membershipServiceData.reqTestDataBody01(2,342,
                67,67,true,false);

        extentTest.info("Post methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                body(reqBodyAndExpectedData).
                when().
                header("Authorization", "Bearer " + access_token).
                post("/{membershipPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);


        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().contentType(ContentType.JSON);

        Assert.assertEquals(reqBodyAndExpectedData.get("app_id"),actualData.get("app_id"));
        Assert.assertEquals(reqBodyAndExpectedData.get("user_id"),actualData.get("user_id"));
        Assert.assertEquals(reqBodyAndExpectedData .get("membership_type_id"),actualData.get("membership_type_id"));
        Assert.assertEquals(reqBodyAndExpectedData.get("subscription_type_id"),actualData.get("subscription_type_id"));

        JsonPath jsonPath = response.jsonPath();
        subscription_id=jsonPath.get("subscription_type_id");
        System.out.println("subscription_id = " + subscription_id);

    }

    @Test
    public void TC_03() {
        extentTest = extentReports.createTest("MembershipSubscriptionUUID", "Membership Subscription UUID should be inserted as path parameter.");
        extentTest.info("URL set edildi ");


       specification.pathParams("membershipPath", "membership","subscriptionUUIDPath",subscription_id);

        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{membershipPath}/{subscriptionUUIDPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

        extentTest.info("actual data oluşturuldu");

        HashMap<Object, Object> actualData = response.as(HashMap.class);

        System.out.println("actualData = " + actualData);

        Assert.assertEquals(subscription_id,actualData.get("subscription_id"));

    }

    @Test
    public void TC_04() {
        extentTest = extentReports.createTest("deleteExistingMembership", " Membership Subscription ID should be inserted as path parameter.");
        extentTest.info("URL set edildi ");

        //https://a3m-qa-gm3.quaspareparts.com/auth/api/membership/82ee181a-24d0-4f40-ace2-fd5a1f0966c7

        specification.pathParams("membershipPath", "membership","SubscriptionPath",subscription_id);

        extentTest.info("Delete methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                delete("/{membershipPath}/{SubscriptionPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200);

    }















}
