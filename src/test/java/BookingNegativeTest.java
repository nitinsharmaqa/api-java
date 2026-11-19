import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class BookingNegativeTest {
  @Test
  void negativeTotalPriceIsRejectedOrAcceptedAsIs() {
    String body = """
      {
        "firstname": "Nitin",
        "lastname": "Sharma",
        "totalprice": -50,
        "depositpaid": true,
        "bookingdates": {
          "checkin": "2026-12-01",
          "checkout": "2026-12-05"
        },
        "additionalneeds": "Breakfast"
      }
      """;

    RestAssured
      .given()
        .contentType("application/json")
        .body(body)
      .when()
        .post("https://restful-booker.herokuapp.com/booking")
      .then()
        .statusCode(anyOf(equalTo(200), equalTo(400)));
  }
}