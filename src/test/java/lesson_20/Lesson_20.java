package lesson_20;

import entities.pojo.reqres.Data;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.testing.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static utils.properties.PropertyReader.getProperties;

@Listeners(Listener.class)
public class Lesson_20 {
    List<Map> usersList = new ArrayList<>();
    Integer expectedTotal = 12;
    Integer expectedDataSize = 6;

    @lombok.Data
    class CreateUser {
        private Integer id;
        private String name;
        private String job;
        private String email;
        private String password;
    }

    @BeforeTest
    public void precondition() {
        baseURI = getProperties().getProperty("url");
    }

    @Test(priority = 1)
    public void listUsers() {
        Response response = given().get("/users?page=2");
        response.then().statusCode(200);
        Assert.assertEquals(response.getStatusCode(), 200);
        usersList = response.jsonPath().getList("data", Map.class);
        System.out.println(usersList);
    }

    @Test(priority = 2)
    public void singleUser() {
        Response response = given().basePath("/users").get(usersList.get(0).get("id").toString());
        response.then().statusCode(200);
        response.prettyPrint();
        Assert.assertEquals(Integer.parseInt(usersList.get(0).get("id").toString()), response.jsonPath().getInt("data.id"));
        Data data = response.jsonPath().getObject("data", Data.class);
        Assert.assertEquals(data.getId(), Integer.parseInt(usersList.get(0).get("id").toString()));
        Assert.assertEquals(data.getEmail(), usersList.get(0).get("email").toString());
        Assert.assertEquals(data.getFirst_name(), usersList.get(0).get("first_name").toString());
        Assert.assertEquals(data.getLast_name(), usersList.get(0).get("last_name").toString());
        Assert.assertTrue(data.getAvatar().contains("https"));
    }

    @Test(priority = 3)
    public void createUser() {
        Response response = given().basePath("/users").body(new CreateUser() {{
            setName("Qa");
            setJob("qa");
        }}).post();
        response.then().statusCode(201);
    }

    @Test(priority = 4)
    public void singleUserNotFound() {
        Response response = given().basePath("/users").get(new CreateUser() {{
            setId(13);
        }}.toString());
        response.then().statusCode(404);
    }

    @Test(priority = 5)
    public void listResource() {
        Response response = given().get("/unknown");
        response.then().statusCode(200);
        List<Map> actualListData = response.jsonPath().getList("data");
        Integer actualTotal = response.jsonPath().getInt("total");
        Assert.assertEquals(actualListData.size(), expectedDataSize);
        Assert.assertEquals(actualTotal, expectedTotal);
    }

    @Test(priority = 6)
    public void updateUser() {
        Response response = given().basePath("/users").body(new CreateUser() {{
            setName("morpheus");
            setJob("zion resident");
        }}).put(usersList.get(0).get("id").toString());
        response.then().statusCode(200);
    }

    @Test(priority = 7)
    public void deleteUser() {
        Response response = given().basePath("/users").delete(new CreateUser() {{
            setId(3);
        }}.toString());
        response.then().statusCode(204);
    }

    @Test(priority = 8)
    public void registerNewUser() {
        Response response = given().basePath("/register").contentType("application/json").body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}").post();
        response.then().statusCode(200);
        Integer actualId = response.jsonPath().getInt("id");
        Assert.assertEquals(actualId, 4);
        Assert.assertNotEquals(response.jsonPath().getString("token"), null);
    }

    @Test(priority = 9)
    public void successfulLogin() {
        Response response = given().basePath("/login").contentType("application/json").body("{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}").post();

        response.then().statusCode(200);
        Assert.assertNotEquals(response.jsonPath().getString("token"), null);
    }

    @Test(priority = 10)
    public void delayResponse() {
        Response response = given().basePath("/users").param("delay", "3").get();
        response.then().statusCode(200);
    }
}
