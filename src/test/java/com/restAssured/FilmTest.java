package com.restAssured;

import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class FilmTest {

    @BeforeSuite
    public  void beforeTest(){
        baseURI="https://swapi.co/api/films/";
    }

    @Test
    public void test(){
//        given().when().request("GET","/1").then().log().body();
        given().when().request("GET","/2").then().log().all();
    }

    @Test
    public void test2(){
        JsonPath jsonPath=
        given().
                when().request("GET","/1").
                then().extract().body().jsonPath();

//        ==================get title grom Json path and compare it============
//        String title=jsonPath.getString("title");
//        System.out.println("title is......"+title);
//        assertEquals(title,"A New Hope","title not matched");

//        ==================get Openin_crawl  from JsonPath ====================
        String title1=jsonPath.getString("opening_crawl");
        System.out.println("opening_crawl is :- "+title1);
    }

    @Test
    public void test3(){
//
//        given().when().request("GET","/1").then().log().body();
//        given().when().request("GET","/2").then().log().all();

        Film film=given().when().request("GET","/2").then().assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                as(Film.class);
        String name=film.getTitle();
        String eposide_id=film.getEpisode_id();
        System.out.println("title is :-"+name+"eposide_id is :-"+eposide_id);
    }



}