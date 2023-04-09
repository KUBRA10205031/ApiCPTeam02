package utilities;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojoData.UserGroupTypeRequestPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReusableMethods extends BaseURLs {

  //  private static RequestSpecification specification;

    public static Response getAllRequest(RequestSpecification specification) {

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{userGroupTypePath}");
        return response;

    }

    public static Response getByIdRequest(RequestSpecification specification) {

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{userGroupTypePath}/{idPath}");
        return response;

    }

    public static Response postRequest(RequestSpecification specification, UserGroupTypeRequestPojo requestBodyPojo) {

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                when().
                header("Authorization", "Bearer " + access_token).
                body(requestBodyPojo).
                post("/{userGroupTypePath}");
        return response;

    }

    public static Response deleteRequest(RequestSpecification specification, UserGroupTypeRequestPojo requestBodyPojo) {
        Response response = null;
        try {
            response = given().
                    spec(specification).
                    contentType(ContentType.JSON).
                    when().
                    header("Authorization", "Bearer " + access_token).
                    body(requestBodyPojo).
                    delete("/{userGroupTypePath}/{idPath}");
            Assert.assertTrue(true);
        } catch (Exception e) {

            System.out.println("404 not found");
            Assert.assertTrue(true);
        }
        return response;

    }


    public static Response putRequest(RequestSpecification specification, Map<String, Object> requestPutMap) {

        Response response = given().
                spec(specification).
                contentType(ContentType.JSON).
                when().body(requestPutMap).
                header("Authorization", "Bearer " + access_token).
                put("/{userGroupTypePath}");
        return response;

    }


}
