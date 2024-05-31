
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests {

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

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/20");

        response
                .then()
                .statusCode(200);

        response.prettyPrint(); //Response kismini daha okunakli halde terminalden gorunur hale getirdik

        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");
        String checkIn = response.jsonPath().getJsonObject("bookingdates.checkin");


        Assertions.assertEquals("Jane",firstName);
        Assertions.assertEquals("Doe",lastName);
        Assertions.assertEquals(111,totalPrice);
        Assertions.assertEquals(true,depositPaid);
        Assertions.assertEquals("2018-01-01",checkIn);

    }
}
