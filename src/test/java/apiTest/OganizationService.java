package apiTest;

import baseURLs.BaseURLs;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojoData.OrganizationServicePojo;
import testData.OrganizationServiceTestData;

import java.util.HashMap;

public class OganizationService extends BaseURLs {
    @Test
    public void getAllOrganization(){
        extentTest = extentReports.createTest("organization","get all organitaions");
        specification.pathParam("organizationPath", "organization");
        extentTest.info("url set edildi");
        Response response = RestAssured.given()
                .spec(specification).when().
                header("Authorization", "Bearer " + access_token)
                .get("/{organizationPath}");
        extentTest.info("get request gönderildi");
        response.then().assertThat().statusCode(404);
        extentTest.info("status cod kontrol edildi");

    }

    @Test
    public void getAOrganization() {
        extentTest=extentReports.createTest("organization", "get a organization");
        specification.pathParams("organizationPath", "organization", "idPath", 182);
        extentTest.info("set url");
        Response response = RestAssured.given().
                spec(specification)
                .header("Authorization", "Bearer " + access_token)
                .when().get("/{organizationPath}/{idPath}");
        extentTest.info("req body gönderildi");
        response.then().assertThat().statusCode(200);


    }

    @Test
    public void postAndDeleteOrganization() {
        extentTest = extentReports.createTest("organization","add new organitaion ");
        specification.pathParam("organizationPath", "organization");
        extentTest.info("url set edildi");
        OrganizationServiceTestData organizationServiceTestData = new OrganizationServiceTestData();
        HashMap <String, Object> enteredReqBody= organizationServiceTestData.setReqBody("RedBull1", 43);
        extentTest.info("request body olusturuldu ve expected data set edildi");

        Response response = RestAssured.given().spec(specification).
                header("Authorization", "Bearer " + access_token)
                .contentType(ContentType.JSON)
                .body(enteredReqBody)
                .when().post("/{organizationPath}");
        extentTest.info("request gönderildi");

        //OrganizationServicePojo actualData = response.as(OrganizationServicePojo.class);
        HashMap actualData = response.as(HashMap.class);
        response.then().assertThat().statusCode(201);
        Assert.assertEquals(enteredReqBody.get("name"), actualData.get("name"));
        Assert.assertEquals(enteredReqBody.get("founder_id"), actualData.get("founder_id"));
        extentTest.info("dogrulama yapildi");

        //Object id = actualData.get("id");
       // extentTest.info("yeni eklenen sirketin id degeri tutuldu");








    }
}
