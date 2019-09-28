package com.restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class RestAssured_AuthenticationBasics_Test {


    @Test
    public void AuthenticationBasics(){
//========== Authentication without userName and Password==============
        RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
        RequestSpecification requestSpecification= RestAssured.given();

        /*JSONObject jsonObject=new JSONObject();
        jsonObject.put("Username:Password","ToolsQA:TestPassword");
        jsonObject.put("Authentication Type","Basic");
        requestSpecification.body(jsonObject);*/

        Response response=requestSpecification.get();
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

    }
}