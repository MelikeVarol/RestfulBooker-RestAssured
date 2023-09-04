package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTests extends BaseTest{

    @Test
    public void partiallyUpdateBookingTest()
    {
        //Token oluştur
        String token =createToken();

        //Rezervasyon oluştur
        int bookingId =createBookingId();

        //Çağrı yap
//        curl -X PATCH \
//        https://restful-booker.herokuapp.com/booking/1 \
//        -H 'Content-Type: application/json' \
//        -H 'Accept: application/json' \
//        -H 'Cookie: token=abc123' \
//        -d '{
//        "firstname" : "James",
//            "lastname" : "Brown"
//    }'
        JSONObject body =new JSONObject();// obje oluşturdum
        body.put("firstname","Ahmet");//sadece firstname si Ahmet olan bir body oluşturdum.

        Response response =given(spec).contentType(ContentType.JSON)
                           .header("Cookie","token="+token)
                           .body(body.toString())//oluşturduğum bodyi string cinsine çevirdim.
                           .when()
                           .patch("/booking/" + bookingId);


        //Assertion
        String firstName=response.jsonPath().getJsonObject("firstname");
        Assertions.assertEquals("Ahmet",firstName);//testi kontrol ettim

    }
}
