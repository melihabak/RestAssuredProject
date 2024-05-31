package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    // BaseTest test otomasyonunda kullanilan temel bir siniftir.
    // Bu sinifin olusturulma amaci diger test siniflarda kullanilan ortak metot ve nesnelerin burada toplanmasi,
    // test kosumu gerceklesmeden once ve sonrasında yapilan mukerrer islemlerin (before ve after anotasyonları) burada gerceklesmesi
    // ve bu sayede diger test siniflarinin okunabilirliginin ve duzenlenebilirliginin kolaylastirilmasi

    protected Response createBooking(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject())
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();

        // Assertionlarin Yazilmasi

        response
                .then()
                .statusCode(200);
        return response;
    }
    protected String bookingObject(){
        JSONObject body = new JSONObject();
        body.put("firstname","Melih");
        body.put("lastname","Abak");
        body.put("totalprice",821);
        body.put("depositpaid",true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2024-01-01");
        bookingDates.put("checkout","2024-01-05");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Nothing");

        return body.toString();
    }
}
