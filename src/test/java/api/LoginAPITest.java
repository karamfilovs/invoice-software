package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginAPITest {
    private static final String LOGIN_PAGE = "https://api.inv.bg/v3/login/token";
    private static final String EMAIL = System.getProperty("email", "dummy@gmail.com");
    private static final String PASSWORD = System.getProperty("password", "1212121");
    private static final String DOMAIN = System.getProperty("domain", "companyx");

    @Test
    @Tag("api")
    @DisplayName("Can obtain token")
    public void canObtainToken(){
        //Send POST request to obtain token
        Response response = LoginAPI.obtainToken(EMAIL, PASSWORD, DOMAIN);
        //Check the status code
        Assertions.assertEquals(200, response.statusCode());
        //Check that the token field is not empty
        String bearerToken = response.then().extract().path("token");
        String expiresString = response.then().extract().path("expires_string");
        Assertions.assertFalse(bearerToken.isEmpty(), "The token is empty but it should not be");
        Assertions.assertFalse(expiresString.isEmpty(), "The expires string is empty but it should not be");
    }

    @Test
    @Tag("api")
    @DisplayName("Cant obtain token for not existing company")
    public void cantObtainTokenForNotExistingCompany(){
        //Send POST request to obtain token
        Response response = LoginAPI.obtainToken(EMAIL, PASSWORD, "qaground");
        //Check the status code
        Assertions.assertEquals(401, response.statusCode());
        //Check message
        String error = response.then().extract().path("error");
        Assertions.assertEquals("Firm not found", error);
    }

    @Test
    @Tag("api")
    @Tag("negative")
    @DisplayName("Cant obtain token with invalid credentials")
    public void cantObtainTokenWithInvalidCredentials(){
        //Send POST request to obtain token
        Response response = LoginAPI.obtainToken(EMAIL, "blabla", "st2016");
        //Check the status code
        Assertions.assertEquals(401, response.statusCode());
        //Check message
        String error = response.then().extract().path("error");
        Assertions.assertEquals("Wrong username or password", error);
    }

    @Test
    @Tag("api")
    @Tag("negative")
    @DisplayName("Broken test")
    public void brokenTest(){
        //Send POST request to obtain token
        Response response = LoginAPI.obtainToken(EMAIL, "blabla", "st2016");
        //Check the status code
        Assertions.assertEquals(402, response.statusCode());
        //Check message
        String error = response.then().extract().path("error");
        Assertions.assertEquals("Wrong username or password", error);
    }


}
