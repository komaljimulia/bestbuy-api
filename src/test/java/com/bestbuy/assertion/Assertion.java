package com.bestbuy.assertion;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.core.IsCollectionContaining;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Assertion {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response=given()
                .when()
                .get("/stores")
                .then();
    }
    //1) Verify the if the total is equal to 1561
    @Test
    public void test001(){
        response.body("total",equalTo(1561));
    }
    //2)  Verify the if the stores of limit is equal to 10
    @Test
    public void test002(){
        response.body("limit",equalTo(10));
    }
    //3)  Check the single ‘Name’ in the Array list
    @Test
    public void test003(){
        response.body("data.name",hasItem("Roseville"));//bcoz it is in list(Arraylist)
    }
    //4) Check the multiple ‘Names’ in the ArrayList
    @Test
    public void test00004(){
        response.body("data.name", hasItems("Inver Grove Heights","Burnsville", "Roseville"));
    }
    //5)  Verify the storeId inside storeServices of the third store of second services
    @Test
    public void test005(){
        response.body("data[2].services[1].storeservices",hasKey("storeId"));
    }
    //6) Check hash map values ‘createdAt’ inside storeServices map
    @Test
    public void test006(){
        response.body("data[7].services[2]",hasKey("createdAt"));//haskey bcoz we want to know key(in blue colour)
    }
    //7) Verify the state = MN of third store
    @Test
    public void test007(){
        response.body("data[6].state",equalTo("MN"));
    }
    //8)  Verify the name =Rochester
    @Test
    public void test008(){
        response.body("data[8].name",equalTo("Rochester"));
    }
    //9)  Verify the storeId = 11 for the 6th store
    @Test
    public void test009(){
        response.body("data[5].id",equalTo(11));
    }
    //10)  Verify the serviceId = 15 for the 7th store
    @Test
    public void test10(){
        response.body("data[6].services[9].storeservices.serviceId",equalTo(15));
    }

}
