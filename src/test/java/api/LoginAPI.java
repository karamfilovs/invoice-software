package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginAPI {
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static Response obtainToken(String email, String password, String domain){
        Login qaGround = new Login();
        qaGround.email = email;
        qaGround.password = password;
        qaGround.domain = domain;
        String body = GSON.toJson(qaGround);
        Response response = RestAssured.given()
                .log().all()
                .baseUri("https://api.inv.bg")
                .basePath("/v3")
                .header("User-Agent", "Firefox")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post("/login/token")
                .prettyPeek();
        return  response;
    }

    public static void main(String[] args) {
        Login qaGround = new Login();
        qaGround.email = "dummy@gmail.com";
        qaGround.password = "2323233";
        qaGround.domain = "st2016";
        System.out.println(GSON.toJson(qaGround));
    }
}
