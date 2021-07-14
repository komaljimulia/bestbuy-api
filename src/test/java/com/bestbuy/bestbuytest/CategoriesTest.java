package com.bestbuy.bestbuytest;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.ProductsPojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesTest {
    @Test
    //Get
    public void getAllCategories(){
        Response response=
                given()
                        .when()
                        .get("http://localhost:3030/categories");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //Post
    public void createNewCategories(){
        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("Raa");
        categoriesPojo.setId("2");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(categoriesPojo)
                .when()
                .post("http://localhost:3030/categories");
        response.then().statusCode(201);
        response.prettyPrint();

    }
    @Test
    //Patch
    public void updateCategorytWithPatch() {
        CategoriesPojo categoriesPojo=new CategoriesPojo();
        categoriesPojo.setName("hhh");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(categoriesPojo)
                        .when()
                        .patch("http://localhost:3030/categories/2");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //Delete
    public void deleteCategoryInfo() {
        Response response =
                given()
                        .pathParam("id", "2")
                        .when()
                        .delete("http://localhost:3030/categories/{id}");
        response.then().statusCode(404);
        response.prettyPrint();
    }
}
