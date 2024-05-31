import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class CreateBookingTests {
    @Test
    public void createBookingTest(){

        // Body kisminin olusturulmasi

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


        // Cagrı yapilan yerin olusturulmasi

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();


        // Assertionlarin Yazilmasi

        response
                .then()
                .statusCode(200);

        Assertions.assertEquals("Melih",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Abak",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(821,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true,response.jsonPath().getJsonObject("booking.depositpaid"));

    }
}
