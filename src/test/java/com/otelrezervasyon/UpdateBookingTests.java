package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest{



    @Test
    public void updateBookingTest(){

        // Token olustur
        String token = createToken();

        // Rezervasyon olustur
        Response createBookingObject = createBooking();
        int bookingId = createBookingObject.jsonPath().getJsonObject("bookingid");

        // Request yap
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+token)
                .body(bookingObject("Test","Test",1500,true))
                .put("https://restful-booker.herokuapp.com/booking/"+bookingId);
        response.prettyPrint();

        // Assertion/Test yaz
        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");

        Assertions.assertEquals("Test",firstName);
        Assertions.assertEquals("Test",lastName);
        Assertions.assertEquals(1500,totalPrice);
        Assertions.assertEquals(true,depositPaid);

    }
}
