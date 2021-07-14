package com.bestbuy.bestbuytest;

import com.bestbuy.StoresPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresTest {
    @Test
    //Get
    public void getAllStoresInfo() {
        Response response =
                given()
                        .when()
                        .get("http://localhost:3030/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //Post
    public void createStores(){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Bank of India");
        storesPojo.setType("Ahmedabad");
        storesPojo.setAddress("Sabarmati");
        storesPojo.setAddress2("Keshavnagar");
        storesPojo.setCity("Gujarat");
        storesPojo.setState("Sabarkatha");
        storesPojo.setZip("4520236");
        storesPojo.setLat(4);

        Response response =
                given()
                        .header("Content-Type","application/json")
                        .body(storesPojo)
                        .when()
                        .post("http://localhost:3030/stores");
        response.then().statusCode(201);
        response.prettyPrint();

    }
    @Test
    //Patch
    public void updateStoreWithPatch(){
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("Gandhi Mahal");


        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(storesPojo)
                        .when()
                        .patch("http://localhost:3030/stores/8921");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void deleteProductInfo() {
        Response response =
                given()
                        .pathParam("id", "8921")
                        .when()
                        .delete("http://localhost:3030/stores/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
