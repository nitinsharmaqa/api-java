import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.nullValue;

public class AuthFailTest {
  @Test
  void wrongPasswordReturnsNoToken() {
    RestAssured
      .given()
        .contentType("application/json")
        .body("{\"username\":\"admin\",\"password\":\"wrong-password\"}")
      .when()
        .post("https://restful-booker.herokuapp.com/auth")
      .then()
        .statusCode(200)
        .body("token", nullValue())
        .body("reason", org.hamcrest.Matchers.equalTo("Bad credentials"));
  }
}
