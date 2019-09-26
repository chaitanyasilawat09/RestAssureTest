package com.restAssured;


import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class RestAssuredTest {

    @BeforeSuite
    public void beforeMethod(){
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
    }

    @Test
    public void GetWeatherDetails() {
        // Specify the base URL to the RESTful web service
//        Added this Base_URI into @BeforeSuite annotation
//        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step. here we can modify request.
        RequestSpecification httpRequest = given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/Indore");

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();

//        int responseBody = response.getStatusCode();
        System.out.println("Response Body is =>  " + responseBody+" ......."+response.getStatusLine()+
                " ......."+response.headers());

    }


    @Test
    public void statusCodeTest() {
        RequestSpecification httpRequest = given();
        //        ==============for when statusCode matched Ok=================

        /*   Response response = httpRequest.request(Method.GET, "/Indore");
        int statusCode=response.getStatusCode();
        assertEquals(statusCode,200,"statuscode 200 not matched");
        String statusLine1=response.getStatusLine();
        assertThat(statusLine1,is("HTTP/1.1 200 OK"));
        */

//        ==============for when statusCode not matched Bad Request=================
        Response response = httpRequest.request(Method.GET, "123456");
        int statusCode=response.getStatusCode();
        assertEquals(statusCode,400,"statuscode 400 not matched");
        String statusLine1=response.getStatusLine();
//        when statusLine not matched
        assertThat(statusLine1,is("HTTP/1.1 400 Bad Request"));

        System.out.println("Response statuscode is =>  " + statusCode+
                "...   STATUS LINE "+statusLine1);
    }

    @Test
    public void headersTest() {
//        ============for particular Header(Content_type)===============
/*        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/Indore");
        String Content_type=response.header("Content-Type");
        System.out.println("Content_type is :-"+Content_type);
        assertEquals(Content_type,"application/json","Content-Type not matched");
*/

//        =====================for All Headers==========================
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/Indore");
        Headers header=response.headers();
        for(Header allHeaders:header)
        {
            System.out.println("KEY:-"+allHeaders.getName()+"  VALUE:- "+allHeaders.getValue());
        }

    }

    @Test
    public  void responseBodyTest(){
//        ================================= for hole body of particular City=====================
       /* Response response=given().request(Method.GET,"/Indore");
        ResponseBody body=response.getBody();
        System.out.println(body.asString());
        */

//        ==========================for some value from particular body==========================
        Response response=given().request(Method.GET,"/Indore");
        ResponseBody body=response.getBody();
        String bodyStr = body.asString();
        System.out.println(bodyStr);
//        assertEquals(bodyStr.contains("Bhopal"),true,"Body not contain expected String");
        assertThat(bodyStr.toUpperCase().contains("INDORE"),is(true));

    }



    @Test
    public void verifyValueIn_JosonResponse(){
//    ==========================get value from JSON root and verify it================
        given();
        Response response=given().request(Method.GET,"/Indore");
        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPath=response.jsonPath();
        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String j_City=jsonPath.get("City");
        String temp=jsonPath.get("Temperature");
        System.out.println("Value from JSOM path is "+j_City+".....temp."+temp);
        // Validate the response
        assertThat(j_City,is("Indore"));

    }
}
