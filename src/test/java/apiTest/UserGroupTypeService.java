package apiTest;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.Assert;
import pojoData.IdCountryWorldPojo;
import pojoData.UserGroupTypeRequestPojo;
import utilities.ReusableMethods;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class UserGroupTypeService extends BaseURLs {


    static Response response;

    @Test //GET All User Group Type
    public void allUserGroupType() {
        extentTest = extentReports.createTest("All User Group Type", "Get All User Group Type");

        specification.pathParam("userGroupTypePath", "user-group-type");
        extentTest.info("Get request yapmak için Url set edildi.");


        response = ReusableMethods.getAllRequest(specification);


        response.prettyPrint();

        extentTest.info("GET methodu ile request gönderildi");


        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                // body("id", Matchers.hasItems(1,2,3));
                        body("name", Matchers.hasItems("Department", "Remote Unit", "Team"));

    }

    @Test //GET  User Group Type By Id
    public void userGroupTypeById() {

        extentTest = extentReports.createTest("User Group Type", "Ad New User Group Type");

        specification.pathParam("userGroupTypePath", "user-group-type");
        extentTest.info("POST request yapmak için Url set edildi.");

        UserGroupTypeRequestPojo requestBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");
        response = ReusableMethods.postRequest(specification, requestBodyPojo);

        response.prettyPrint();
        extentTest.info("POST methodu ile request gönderildi");

        JsonPath responseJPath = response.jsonPath();
        int reqid = responseJPath.get("id");


        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);
        extentTest.info("Get request yapmak için Url set edildi.");


        response = ReusableMethods.getByIdRequest(specification);
        response.prettyPrint();

        extentTest.info("GET methodu ile request gönderildi");

        responseJPath = response.jsonPath();


        assertEquals(requestBodyPojo.getName(), responseJPath.get("name"));

        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);

        response = ReusableMethods.deleteRequest(specification, requestBodyPojo);



        response.then().assertThat().statusCode(200);
        extentTest.info(" (DELETE) Status code un 200 olduğu verify edildi");





    }


    @Test // POST New Group TYpe Service
    public void postNewGroupTypeService() {

        extentTest = extentReports.createTest("User Group Type", "Ad New User Group Type");

        specification.pathParam("userGroupTypePath", "user-group-type");
        extentTest.info("POST request yapmak için Url set edildi.");

        UserGroupTypeRequestPojo requestBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");
        System.out.println("requestBodyPojo = " + requestBodyPojo);

        UserGroupTypeRequestPojo expectedResponseBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");

        response = ReusableMethods.postRequest(specification, requestBodyPojo);

        System.out.println("response : ");
        response.prettyPrint();
        extentTest.info("POST methodu ile request gönderildi");

        JsonPath responseJPath = response.jsonPath();
        int reqid = responseJPath.get("id");


        response.then().assertThat().statusCode(201).contentType(ContentType.JSON);
        assertEquals(expectedResponseBodyPojo.getName(), responseJPath.get("name"));
        assertEquals(expectedResponseBodyPojo.getDescription(), responseJPath.get("description"));
        extentTest.info("Yeni oluşturulan UserGroupType için Assertion yapıldı");

        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);
        extentTest.info("Delete request yapmak için Url set edildi.");


        response = ReusableMethods.deleteRequest(specification, requestBodyPojo);

        System.out.println("response : ");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        extentTest.info(" (DELETE) Status code un 200 olduğu verify edildi");

    }

    @Test// PUT Group Type  Service
    public void putGroupTypeService() {

        extentTest = extentReports.createTest("User Group Type", "Ad New User Group Type");

        specification.pathParam("userGroupTypePath", "user-group-type");
        extentTest.info("POST request yapmak için Url set edildi.");

        UserGroupTypeRequestPojo requestBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");


        System.out.println("requestBodyPojo = " + requestBodyPojo);

        UserGroupTypeRequestPojo expectedResponseBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");


        extentTest.info("POST methodu ile request gönderildi");
        response = ReusableMethods.postRequest(specification, requestBodyPojo);

        JsonPath responseJPath = response.jsonPath();
        int reqid = responseJPath.get("id");

        //id eklemek için requestBodyPojo Map e çevrildi
        Map<String, Object> requestPutMap = new HashMap<>();
        requestPutMap.put("id", reqid);
        requestPutMap.put("name", requestBodyPojo.getName());
        requestPutMap.put("description", "Updated Team02_Brs");

        specification.pathParams("userGroupTypePath", "user-group-type");
        extentTest.info("PUT request yapmak için Url set edildi.");


        // Response assert için Map e çevrildi.
        response = ReusableMethods.putRequest(specification, requestPutMap);
        Map<String, Object> responsePutMap = response.as(HashMap.class);

        assertEquals(requestPutMap.get("id"), responsePutMap.get("id"));
        assertEquals(requestPutMap.get("name"), responsePutMap.get("name"));
        assertEquals(requestPutMap.get("description"), responsePutMap.get("description"));
        extentTest.info("PUT ile Update edilen User Group Type için assertion yapıldı.");

        reqid = (int) requestPutMap.get("id");
        System.out.println("reqidput = " + reqid);
        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);


        response = ReusableMethods.deleteRequest(specification, requestBodyPojo);
        extentTest.info("Delete methodu  ile " + reqid + " id li   UserGrpupType silindi");

    }
    @Test()
    public void deleteUserGroupType() {
        extentTest = extentReports.createTest("User Group Type", "Ad New User Group Type");

        specification.pathParam("userGroupTypePath", "user-group-type");
        extentTest.info("POST request yapmak için Url set edildi.");

        UserGroupTypeRequestPojo requestBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");
        System.out.println("requestBodyPojo = " + requestBodyPojo);

        UserGroupTypeRequestPojo expectedResponseBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");

        response = ReusableMethods.postRequest(specification, requestBodyPojo);

        System.out.println("response : ");
        response.prettyPrint();
        extentTest.info("POST methodu ile request gönderildi");

        JsonPath responseJPath = response.jsonPath();
        int reqid = responseJPath.get("id");

        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);
        extentTest.info("Delete request yapmak için Url set edildi.");

        response = ReusableMethods.deleteRequest(specification, requestBodyPojo);

        System.out.println("response : ");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        extentTest.info(" (DELETE) Status code un 200 olduğu verify edildi");


    }


    @Test
    public void postGetDeleteGet() {
        extentTest = extentReports.createTest("User Group Type", "Ad New User Group Type");

        specification.pathParam("userGroupTypePath", "user-group-type");
        extentTest.info("POST request yapmak için Url set edildi.");

        UserGroupTypeRequestPojo requestBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");
        System.out.println("requestBodyPojo = " + requestBodyPojo);

        UserGroupTypeRequestPojo expectedResponseBodyPojo = new UserGroupTypeRequestPojo("Team02_Brs", "Add new user group Type Team02_Brs");


        response = ReusableMethods.postRequest(specification, requestBodyPojo);


        System.out.println("response : ");
        response.prettyPrint();
        extentTest.info("POST methodu ile request gönderildi");

        JsonPath responseJPath = response.jsonPath();
        int reqid = responseJPath.get("id");


        response.then().assertThat().statusCode(201).contentType(ContentType.JSON);
        assertEquals(expectedResponseBodyPojo.getName(), responseJPath.get("name"));
        assertEquals(expectedResponseBodyPojo.getDescription(), responseJPath.get("description"));
        extentTest.info("Yeni oluşturulan UserGroupType için Assertion yapıldı");


        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);
        extentTest.info("GET request için Url set edildi.");

        response = ReusableMethods.getByIdRequest(specification);
        System.out.println("response : ");
        response.prettyPrint();

        extentTest.info("GET methodu  ile yeni oluşturulan UserGrpupType cağrıldı");

        JsonPath responseGetIdJPath = response.jsonPath();

        assertEquals(reqid, (int) responseGetIdJPath.get("id"));
        extentTest.info("Yeni oluşturulan UserGroupType ın id sinin ." + reqid + " olduğu verify edildi");


        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);
        extentTest.info("Delete request yapmak için Url set edildi.");

        response = ReusableMethods.deleteRequest(specification, requestBodyPojo);

        extentTest.info("Delete methodu  ile " + reqid + " id li   UserGrpupType silindi");

        System.out.println("response : ");
        response.prettyPrint();


        response.then().assertThat().statusCode(200);
        extentTest.info(" (DELETE) Status code un 200 olduğu verify edildi");

        System.out.println("reqid0 = " + reqid);

        specification.pathParams("userGroupTypePath", "user-group-type", "idPath", reqid);
        extentTest.info("Deleteden sonra tekrar GET request yapmak için Url set edildi.");
        System.out.println("reqid1 = " + reqid);


        response = ReusableMethods.deleteRequest(specification, requestBodyPojo);
        response.prettyPrint();

        extentTest.info("GET methodu  ilesilinen  UserGrpupType cağrıldı");
        extentTest.info(" Status code un 404 olduğu verify edildi");


    }


}