package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ItemAPI {
    private String token;

    public ItemAPI (String token){
        this.token = token;
    }

    public static Response getItems(String token){
        Response response = RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri("https://api.inv.bg")
                .basePath("/v3")
                .header("User-Agent", "Firefox")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/items")
                .prettyPeek();
        return  response;
    }

    public static Response getItem(String token, int id){
        Response response = RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri("https://api.inv.bg")
                .basePath("/v3")
                .header("User-Agent", "Firefox")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("/items/" + id)
                .prettyPeek();
        return  response;
    }
}
