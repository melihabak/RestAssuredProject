package com.otelrezervasyon;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTests extends BaseTest{

    @Test
    public void partiallyUpdateBookingTest(){

        // Cagriyi yap
        JSONObject body = new JSONObject();
        body.put("firstname","Ahmet");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie","token="+createToken())
                .body(body.toString())
                .patch("https://restful-booker.herokuapp.com/booking/"+createBookingId());
        response.prettyPrint();

        // Assertion/Test yaz
        Assertions.assertEquals("Ahmet",response.jsonPath().getJsonObject("firstname"));


    }

}
