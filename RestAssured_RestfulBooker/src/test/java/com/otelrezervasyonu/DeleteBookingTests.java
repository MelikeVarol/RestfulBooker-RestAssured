package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class DeleteBookingTests extends BaseTest{

    @Test
    public void deleteBookingTest()
    {
        //Token oluştur.
        String token =createToken();


        //Silmek istediğin Rezervasyonu oluştur.
        int bookingId=createBookingId();//"bookingid" erişmek istediğim alan


        //DELETE çağrısı
//        curl -X DELETE \
//        https://restful-booker.herokuapp.com/booking/1 \
//        -H 'Content-Type: application/json' \
//        -H 'Cookie: token=abc123'

        Response response= given(spec).contentType(ContentType.JSON)
                .header("Cookie","token="+token)
                .when()
                .delete( "/booking/"+bookingId);

        //response.prettyPrint();//responseyi konsola yazdır. artık gerek yok requestspecifation sayesinde konsola yazdırılcak

        //Assertion/Test yaz
        response
                .then()
                .statusCode(201);//responsenin 201 olduğunu kontrol ettim
    }
}
