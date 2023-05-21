package com.bookstore.tests.apiTests;

import com.bookstore.tests.TestBase;
import com.bookstore.utulities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBook extends TestBase {
    @Test
    public void deleteBook() {
        String body = "{\n" +
                "  \"isbn\": \""+ ConfigurationReader.get("isbn8") +"\",\n" +
                "  \"userId\": \""+ConfigurationReader.get("userID")+"\"\n" +
                "}";
        response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .header("Authorization", "Bearer "+ConfigurationReader.get("token"))
                .when().log().all()
                .delete("/BookStore/v1/Book")
                .prettyPeek();

    }

    @Test
    public void verifyDeleting() {
        Assert.assertEquals(response.statusCode(),204);
    }
}
