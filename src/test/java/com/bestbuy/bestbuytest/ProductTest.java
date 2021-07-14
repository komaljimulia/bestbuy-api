package com.bestbuy.bestbuytest;

import com.bestbuy.ProductsPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductTest  {

    @Test
    //Get
    public void getAllProducts(){
        Response response=
                given()
                .when()
                .get("http://localhost:3030/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void getProductById(){
        Response response=
                given()
                        .pathParam("id", 43900)
                        .when()
                        .get("http://localhost:3030/products/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //post method
    public void createNewProduct(){
        ProductsPojo productspojo = new ProductsPojo();
        productspojo.setName("Milk");
        productspojo.setType("Gold");
        productspojo.setPrice(45);
        productspojo.setShipping(5);
        productspojo.setUpc("Next Day");
        productspojo.setDescription("Milk Powder");
        productspojo.setManufacturer("Amul");
        productspojo.setModel("Gold20");
        productspojo.setUrl("milk@gmail.com");
        productspojo.setImage("Amul Taja");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(productspojo)
                .when()
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }
    @Test
    //Patch
    public void updateProductWithPatch() {
        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setShipping(10);

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(productsPojo)
                        .when()
                        .patch("http://localhost:3030/products/43900");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //Delete
    public void deleteProductInfo() {
        Response response =
                given()
                        .pathParam("id", "150115")
                        .when()
                        .delete("http://localhost:3030/products/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }

}
