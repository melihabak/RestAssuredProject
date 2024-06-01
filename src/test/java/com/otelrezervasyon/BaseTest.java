package com.otelrezervasyon;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    // BaseTest test otomasyonunda kullanilan temel bir siniftir.
    // Bu sinifin olusturulma amaci diger test siniflarda kullanilan ortak metot ve nesnelerin burada toplanmasi,
    // test kosumu gerceklesmeden once ve sonrasında yapilan mukerrer islemlerin (before ve after anotasyonları) burada gerceklesmesi
    // ve bu sayede diger test siniflarinin okunabilirliginin ve duzenlenebilirliginin kolaylastirilmasi

    RequestSpecification spec;
    @BeforeEach
    public void setup(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter()))
                .build();
    }

    // Token olusturma metodu
    protected String createToken(){

        JSONObject tokenBody = new JSONObject();
        tokenBody.put("username","admin");
        tokenBody.put("password","password123");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .when()
                .body(tokenBody.toString())
                .post("/auth");
        return response.jsonPath().getJsonObject("token");
    }

    protected int createBookingId(){
        Response response = createBooking();
        return response.jsonPath().getJsonObject("bookingid");
    }

    // Rezervasyon Olusturma
    protected Response createBooking(){
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Melih","Abak",100,true))
                .post("/booking");

        // Assertionlarin Yazilmasi

        response
                .then()
                .statusCode(200);
        return response;
    }
    protected String bookingObject(String firstName,String lastName,int totalPrice,boolean depositPaid){
        JSONObject body = new JSONObject();
        body.put("firstname",firstName);
        body.put("lastname",lastName);
        body.put("totalprice",totalPrice);
        body.put("depositpaid",depositPaid);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2024-01-01");
        bookingDates.put("checkout","2024-01-05");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Nothing");

        return body.toString();
    }
}
