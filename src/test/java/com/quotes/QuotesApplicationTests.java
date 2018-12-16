package com.quotes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuotesApplicationTests {
	

	private PreemptiveBasicAuthScheme mockAuthentication() {
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("user");
        authScheme.setPassword("1234");
        return authScheme;
	}

	@Test
	public void contextLoads() {
	}
	
	@Test
    public void test01AddQuote200() {
        RestAssured.baseURI = "http://localhost:8080/quotes-api";
        RestAssured.authentication = mockAuthentication();
        given().urlEncodingEnabled(true).body("{\"name\":\"new name\" , \"price\":15}").contentType(ContentType.JSON)
            .post("/quote")
            .then().statusCode(200);
    }
	
	@Test
    public void test02AddNoNameQuote208() {
        RestAssured.baseURI = "http://localhost:8080/quotes-api";
        RestAssured.authentication = mockAuthentication();
        given().urlEncodingEnabled(true).body("{ \"price\":20}").contentType(ContentType.JSON)
            .post("/quote")
            .then().statusCode(406);  
    }
    
	@Test
    public void test03AddNegativePriceQuote403() {
        RestAssured.baseURI = "http://localhost:8080/quotes-api";
        RestAssured.authentication = mockAuthentication();
        given().urlEncodingEnabled(true).body("{\"name\":\"new name\" , \"price\":-15}").contentType(ContentType.JSON)
            .post("/quote")
            .then().statusCode(406);        
    }
	
	

	
	
	

}

