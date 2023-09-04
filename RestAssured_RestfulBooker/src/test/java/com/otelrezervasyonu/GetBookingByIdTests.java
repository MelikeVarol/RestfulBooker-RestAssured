package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest{
    @Test
    public void getBookingById()
    {
        //Cağrıyı oluştur

        //Response kontrolleri
// BASE TESTTE CREATEBOOKINGID DİYE METOT OLUŞTURDUM ARTIK UNLARA GEREK KALMADI.16. VİDEODA
//        Response newBooking=createBooking();//Bu şekilde yeni bir rezervasyon oluşturdum.
//        //bir sonraki adım oluşturudğum rezervasyon id sine erişmek
//
//        //booking id sini bir değişkene kaydettim ve dinamik hale getirdim
//        int rezervationId=newBooking.jsonPath().getJsonObject("bookingid");//ulaşmak istediğim alan bookinid

        int rezervationId=createBookingId();


        String url="/booking/" + rezervationId;
        Response response =given(spec).when().get(url);

        response
                .then()
                .statusCode(200);


        String firstname=response.jsonPath().getJsonObject("firstname");
        Assertions.assertEquals("Ozan",firstname);

        String lastname =response.jsonPath().getJsonObject("lastname");
        Assertions.assertEquals("Ilhan",lastname);

        int totalprice =response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals(200,totalprice);









    }
}
