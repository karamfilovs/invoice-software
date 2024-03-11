package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ItemAPITest {
    private static final String EMAIL = System.getProperty("email", "dummy@gmail.com");
    private static final String PASSWORD = System.getProperty("password", "1212121");
    private static final String DOMAIN = System.getProperty("domain", "companyx");

    @Test
    @Tag("api")
    @DisplayName("Cant get items with invalid token")
    public void cantGetItemsWithInvalidToken(){
        //Send GET request to /items
        Response response = ItemAPI.getItems("blabla");
        //Check the status code
        Assertions.assertEquals(401, response.statusCode());
        //Check the error message
        String message = response.then().extract().path("message");
        Assertions.assertEquals("Wrong number of segments", message);
    }

    @Test
    @Tag("api")
    @DisplayName("Cant get items with valid token")
    public void cantGetItemsWithValidToken(){
        //Obtain token
        Response loginResponse = LoginAPI.obtainToken(EMAIL,PASSWORD, DOMAIN);
        String token = loginResponse.then().extract().path("token");
        //Send GET request to /items
        Response response = ItemAPI.getItems(token);
        //Check the status code
        Assertions.assertEquals(200, response.statusCode());
        //Check that the total is correct
        Integer total = response.then().extract().path("total");
        Assertions.assertEquals(11, total);
    }

    @Test
    @Tag("api")
    @DisplayName("Cant get single item with valid token")
    public void canGetSingleItemWithValidToken(){
        //Obtain token
        Response loginResponse = LoginAPI.obtainToken(EMAIL,PASSWORD, DOMAIN);
        String token = loginResponse.then().extract().path("token");
        //Send GET request to /items
        Response response = ItemAPI.getItem(token, 8067);
        //Check the status code
        Assertions.assertEquals(200, response.statusCode());
        //Check the item id
        Integer id = response.then().extract().path("id");
        Assertions.assertEquals(8067, id);
    }
}
