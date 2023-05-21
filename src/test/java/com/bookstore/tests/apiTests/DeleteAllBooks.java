package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteAllBooks extends TestBase {

    @Test
    public void deleteAllBooks() {
        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .queryParam("UserId",ConfigurationReader.get("userID"))
                .header("Authorization", "Bearer "+ ConfigurationReader.get("token"))
                .when().log().all()
                .delete("/BookStore/v1/Books")
                .prettyPeek();
    }
    @Test
    public void verifyDeleteAllBooks(){
        Assert.assertEquals(response.statusCode(),204);
    }

}
