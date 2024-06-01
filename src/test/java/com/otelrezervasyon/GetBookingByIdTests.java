package com.otelrezervasyon;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest{

    @Test
    public void getBookingById(){

        /*
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/1")
                .then()
                .log().all()
                .statusCode(200);
        */

        Response newBooking = createBooking();
        int reservationId = newBooking.jsonPath().getJsonObject("bookingid");

        Response response = given(spec)
                .when()
                .get("/booking/"+reservationId);

        response
                .then()
                .statusCode(200);

        // response.prettyPrint(); //Response kismini daha okunakli halde terminalden gorunur hale getirdik

        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");
        String checkIn = response.jsonPath().getJsonObject("bookingdates.checkin");

        Assertions.assertEquals("Melih",firstName);
        Assertions.assertEquals("Abak",lastName);
        Assertions.assertEquals(100,totalPrice);
        Assertions.assertEquals(true,depositPaid);
        Assertions.assertEquals("2024-01-01",checkIn);

    }
}
