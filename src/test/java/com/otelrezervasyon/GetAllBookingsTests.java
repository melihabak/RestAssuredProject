package com.otelrezervasyon;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GetAllBookingsTests {
    @Test
    public void getAllBookingTest(){

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().all() // Yaptigimiz cagrinin sonucunu gorebilecegiz
                .statusCode(200);

    }
}
