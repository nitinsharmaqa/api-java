import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class AuthTest {
  @Test
  void authReturnsAToken() {
    RestAssured
      .given()
        .contentType("application/json")
        .body("{\"username\":\"admin\",\"password\":\"password123\"}")
      .when()
        .post("https://restful-booker.herokuapp.com/auth")
      .then()
        .statusCode(200)
        .body("token", notNullValue());
  }
}
