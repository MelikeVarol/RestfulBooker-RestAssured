package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest{

    @Test
    public void updateBookingTest()
    {
//        curl -X PUT \
//        https://restful-booker.herokuapp.com/booking/1 \
//        -H 'Content-Type: application/json' \
//        -H 'Accept: application/json' \
//        -H 'Cookie: token=abc123' \
//        -d '{
//        "firstname" : "James",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    },
//        "additionalneeds" : "Breakfast"
//    }'





        //TOKEN OLUŞTUR
//        curl -X POST \
//        https://restful-booker.herokuapp.com/auth \
//        -H 'Content-Type: application/json' \
//        -d '{
//        "username" : "admin",
//            "password" : "password123"
//    }'
        String token =createToken();


        //Rezervasyon oluştur
        int bookingId=createBookingId();//"bookingid" erişmek istediğim alan


        //Request yap
        Response response=given(spec).contentType(ContentType.JSON)
                .header("Cookie","token="+ token)
                .body(bookingObject("Ayşe","Test",500,false))//ARTIK OLUŞTURDUĞUMUZ REZERVASYONU BURDAKİ VERİLER İLE GÜNCELLER.
                .when()
                .put("/booking/" + bookingId);


        //Assertion
        String firstaName =response.jsonPath().getJsonObject("firstname");// responseden gelen firstname alanını aldım.
        String lastName =response.jsonPath().getJsonObject("lastname");
        int totalPrice =response.jsonPath().getJsonObject("totalprice");
        boolean depositePaid =response.jsonPath().getJsonObject("depositpaid");//response üzerinden depositpaide ulaştım

        Assertions.assertEquals("Ayşe",firstaName);//actual bize responseden gelen değer.
        Assertions.assertEquals("Test",lastName);
        Assertions.assertEquals(500,totalPrice);
        Assertions.assertEquals(false,depositePaid);


    }




}
