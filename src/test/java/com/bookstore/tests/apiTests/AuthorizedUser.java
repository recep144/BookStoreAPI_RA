package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AuthorizedUser extends TestBase {

    @Test
    public void authorizedUser(){
        Map<String,Object> map = new HashMap<>();
        map.put("userName", ConfigurationReader.get("userName"));
        map.put("password",ConfigurationReader.get("password"));

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map)
                .when().log().all()
                .post("/Account/v1/Authorized")
                .prettyPeek();
    }
    @Test
    public void verifyAuthorizedUser(){
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.asString(),"true");
        Assert.assertTrue(response.asString().contains("true"));

    }

}
