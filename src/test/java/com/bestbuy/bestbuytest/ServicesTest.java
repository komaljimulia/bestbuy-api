package com.bestbuy.bestbuytest;

import com.bestbuy.CategoriesPojo;
import com.bestbuy.ServicePojo;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesTest {
    @Test
    //Get
    public void getAllServices(){
        Response response=
                given()
                        .when()
                        .get("http://localhost:3030/services");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //Post-id 22 is automatically generated and i delete that 22 id
    public void createNewService(){
        ServicePojo servicePojo=new ServicePojo();
        servicePojo.setName("fff");

        Response response = given()
                .header("Content-Type", "application/json")
                .body(servicePojo)
                .when()
                .post("http://localhost:3030/services");
        response.then().statusCode(201);
        response.prettyPrint();
    }
    @Test
    //Patch
    public void updateServiceData(){
        ServicePojo servicePojo=new ServicePojo();
       servicePojo.setName("Rushik");

        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(servicePojo)
                        .when()
                        .patch("http://localhost:3030/services/4");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //Delete
    public void deleteServices(){
        Response response =
                given()
                        .pathParam("id", "22")
                        .when()
                        .delete("http://localhost:3030/categories/{id}");
        response.then().statusCode(404);
        response.prettyPrint();

    }
}
