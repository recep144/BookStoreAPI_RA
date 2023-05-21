package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetUserInfo extends TestBase {

    @Test
    public void userInfo(){
        Map<String, String> tokenBody = new HashMap<>();
        tokenBody.put("Authorization","Bearer "+ConfigurationReader.get("token"));

        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("UUID", ConfigurationReader.get("userID"))
              //  .header("Authorization","Bearer "+ConfigurationReader.get("token"))
               .headers(tokenBody)
                .when().log().all()
                .get("/Account/v1/User/{UUID}")
                .prettyPeek();
    }

    @Test
    public void verifyUserInfo() {
        Assert.assertEquals(response.statusCode(),200);

        List<String> addedBook = response.path("books.isbn");

        Assert.assertTrue(addedBook.contains(ConfigurationReader.get("isbn3")));
        Assert.assertTrue(addedBook.contains(ConfigurationReader.get("isbn5")));

        Assert.assertEquals(response.path("userId"),ConfigurationReader.get("userID"));
        Assert.assertEquals(response.path("username"),ConfigurationReader.get("userName"));


    }
}
