package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class GenerateToken extends TestBase {
    @Test
    public void generateToken(){
        /*{
  "userName": "string",
  "password": "string"
}
         */
        Map<String,Object> map = new HashMap<>();
        map.put("userName", ConfigurationReader.get("userName"));
        map.put("password",ConfigurationReader.get("password"));

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(map)
                .when().log().all()
                .post("/Account/v1/GenerateToken")
                .prettyPeek();
    }
    @Test
    public void verifyToken(){
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertNotNull(response.path("token"));
        Assert.assertEquals(response.path("result"),"User authorized successfully.");
        Assert.assertEquals(response.path("status"),"Success");

        ConfigurationReader.set("token",response.path("token"));

    }

}
