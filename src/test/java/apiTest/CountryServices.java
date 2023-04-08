package apiTest;

import baseURLs.BaseURLs;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojoData.IdCountryWorldPojo;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CountryServices extends BaseURLs {
   

    @Test
    public  void allCountry(){

        extentTest = extentReports.createTest("all country","countries of world");

        extentTest.info("URL set edildi ");




        //https://a3m-qa-gm3.quaspareparts.com/auth/api/country

       specification.pathParam("countryPath","country");



        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{countryPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200)
                .contentType(ContentType.JSON);

    }

    @Test
    public void getIdCountry(){

        extentTest = extentReports.createTest("ID country", "Id country of world");

        extentTest.info("URL set edildi ");




        //https://a3m-qa-gm3.quaspareparts.com/auth/api/country/TR

        specification.pathParams("countryPath","country","TRPath","TR");


        extentTest.info("Excepted Data oluşturuldu");
/*
{
    "iso2": "TR",
    "name": "Turkey",
    "currency": "TRY",
    "currency_name": "Turkish lira",
    "currency_symbol": "���"
}
 */
        IdCountryWorldPojo exceptedData=new IdCountryWorldPojo("TR","Turkey","TRY",
                "Turkish lira","���");

        System.out.println("exceptedData = " + exceptedData);


        extentTest.info("GET methodu ile request gönderildi");

        Response response = given().
                spec(specification).
                when().
                header("Authorization", "Bearer " + access_token).
                get("/{authPath}/{apiPath}/{countryPath}/{TRPath}");

        extentTest.info("Response yazdırıldı");

        response.prettyPrint();

        extentTest.info("actual data oluşturuldu");


        IdCountryWorldPojo actualData = response.as(IdCountryWorldPojo.class);



        System.out.println("Actual Data: " + actualData);

        extentTest.info("Assertion işlemi yapıldı");

        response.then().assertThat().statusCode(200);

        assertEquals(exceptedData.getIso2(),actualData.getIso2());

        assertEquals(exceptedData.getCurrency(),actualData.getCurrency());

        assertEquals(exceptedData.getName(),actualData.getName());

        assertEquals(exceptedData.getCurrency_symbol(),actualData.getCurrency_symbol());
    }





}
