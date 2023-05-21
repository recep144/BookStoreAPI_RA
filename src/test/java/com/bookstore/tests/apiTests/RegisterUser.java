package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends TestBase {

    @Test
    public void registerUser(){
        /*{
  "userName": "string",
  "password": "string"
}
         */

        Map<String,Object> map = new HashMap<>();
        map.put("userName", ConfigurationReader.get("userName"));
        map.put("password",ConfigurationReader.get("password"));

       response = RestAssured.given()
               .accept(ContentType.JSON)
               .contentType(ContentType.JSON)
               .body(map)
               .when().log().all()
               .post("/Account/v1/User")
               .prettyPeek();
    }
    @Test
    public void verifyThatRegisterUser(){
        Assert.assertEquals(response.statusCode(),201);
        Assert.assertNotNull(response.path("userID"));
        Assert.assertEquals(response.path("username"),ConfigurationReader.get("userName"));

        ConfigurationReader.set("userID",response.path("userID"));

       // Assert.assertEquals("userID",ConfigurationReader.get("userID"));

    }


}
