import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BookingCreateTest {
  private static final String BOOKING = "https://restful-booker.herokuapp.com/booking";
  private static final String BODY = """
    {
      "firstname": "Nitin",
      "lastname": "Sharma",
      "totalprice": 150,
      "depositpaid": true,
      "bookingdates": {
        "checkin": "2026-12-01",
        "checkout": "2026-12-05"
      },
      "additionalneeds": "Breakfast"
    }
    """;

  @Test
  void createReturnsAmountAndNewIdOnRepeat() {
    Response first = RestAssured
      .given()
        .contentType("application/json")
        .body(BODY)
      .when()
        .post(BOOKING)
      .then()
        .statusCode(200)
        .body("bookingid", notNullValue())
        .body("booking.totalprice", equalTo(150))
        .extract()
        .response();

    int firstId = first.path("bookingid");

    Response second = RestAssured
      .given()
        .contentType("application/json")
        .body(BODY)
      .when()
        .post(BOOKING)
      .then()
        .statusCode(200)
        .body("booking.totalprice", equalTo(150))
        .extract()
        .response();

    int secondId = second.path("bookingid");

    // Public API creates a second booking. A payment endpoint must not.
    assertNotEquals(firstId, secondId);
  }
}