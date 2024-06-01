package com.otelrezervasyon;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CreateBookingTests extends BaseTest{
    @Test
    public void createBookingTest(){

        // Body kisminin olusturulmasi (Bu kisim BaseTest sinifinda halledildi ve bookingObject() metodu ile bu siniftan erisim saglandi)
        // Cagrı yapilan yerin olusturulmasi

        Response response = createBooking(); // Rezervasyon olusumu en sade hale getirildi ve BaseTest sinifinda tanimlandi

        Assertions.assertEquals("Melih",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Abak",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(100,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));

    }
}
