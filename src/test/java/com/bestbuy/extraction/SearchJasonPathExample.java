package com.bestbuy.extraction;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;

public class SearchJasonPathExample {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response=given()
                .when()
                .get("/stores")
                .then();
    }
    //1)  Extract the limit
    @Test
    public void test001(){
        int limit=response.extract().path("limit");
        System.out.println("The total number of limit is:"+limit);
    }
    //2)  Extract the total
    @Test
    public void test002(){
        int total= response.extract().path("total");
        System.out.println("The total is:"+total);
    }
    //3)  Extract the name of 5th store
    @Test
    public void test003(){
        String name=response.extract().path("data[4].name");
        System.out.println("Print the name of 5th store:"+name);
    }
    //4)  Extract the names of all the store
    @Test
    public void test004() {
        List<String> name=response.extract().path("data.name");
        System.out.println("Name of all the store"+name);
    }
    //5)  Extract the storeId of all the store
    @Test
    public void test005(){
        List<String> storeId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("All store storeid:"+storeId);
    }
    //6) Print the size of the data list
    @Test
    public void test006(){
        List<HashMap<String,Object>> list=response.extract().path("data");
        int size= list.size();
        System.out.println("The size of the items is: "+size);
    }
    //7) Get all the value of the store where store name = Roseville
    @Test
    public void test007(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='Roseville'}");
        System.out.println("Print the name of Roseville"+values);
    }
    //8)  Get the address of the store where store name = Northtown
    @Test
    public void test008(){
       String storeName = response.extract().path("data[5].address");
        System.out.println("Print the store name"+storeName);
    }
    //9) Get all the services of 8th store
    @Test
    public void test009(){
        List<String> service = response.extract().path("data[7].services");
        System.out.println("Print the services of 8th store:"+service);
    }
    //10) Get storeservices of the store where service name = Geek Squad Services
    @Test
    public void test010(){
        HashMap<String,Integer> storeServices = new HashMap<>();//we use hashmap bcoz it takes string and int value
        storeServices = response.extract().path( "data[0].services[1].storeservices" );
        System.out.println("Print the servicename:"+storeServices);
    }
    //11)  Get id of all the store
    @Test
    public void test011(){
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("All store storeid:"+storeId);
    }
    //12)  Find the store names Where state = MN
    @Test
    public void test012(){
       List<String> name=response.extract().path("data.findAll{it.state=='MN'}.name");
       System.out.println("Print the state name:"+name);
    }
    //13) Find the Total number of services for the store where store name = Inver grove
    @Test
    public void test013(){
        List<HashMap<String,Integer>> services=response.extract().path("data[2].services");
        int size=services.size();
        System.out.println("Print the services"+services);
    }
    //14) Find the createdAt for all services whose name = “Best buy mobile”
    @Test
    public void test014(){
        List<String> createdAt=response.extract().path("data.findAll{it.services=='Best Buy Mobile'}.createdAt");
        System.out.println("The search query is:"+createdAt);

    }
    //15) FInd the name of all services where name='Inver Grove Heights'
    @Test
    public void test015(){
        List<HashMap<String,?>> name= response.extract().path("data[1].services.name");
        System.out.println("The search query is:"+name);
    }
    //16)  Find the zip of all the store
    @Test
    public void test016(){
        List<String> zip=response.extract().path("data.zip");
        System.out.println("The search query is:"+zip);
    }
    //17)  Find the zip of store name = Minnetonka
    @Test
    public void test017(){
        String zip=response.extract().path("data[0].zip");
        System.out.println("The search query is:"+zip);
    }
    //18)  Find the storeservices details of the service name = Samsung Experience
    @Test
    public void test018(){
        List<HashMap<String,?>> storeServices=response.extract().path("data[0].services.storeServices");
        System.out.println("The search query is:"+storeServices);
    }
    //19) Find the lat of all the stores
    @Test
    public void test019(){
        List<Integer> lat=response.extract().path("data.lat");
        System.out.println("The search query is:"+lat);
    }











}
