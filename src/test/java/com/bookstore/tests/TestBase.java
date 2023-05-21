package com.bookstore.tests;

import static io.restassured.RestAssured.*;

import com.bookstore.utulities.ConfigurationReader;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    public static Response response;
    @BeforeMethod
    public void setUp(){
        baseURI = ConfigurationReader.get("baseURI");

    }

}
