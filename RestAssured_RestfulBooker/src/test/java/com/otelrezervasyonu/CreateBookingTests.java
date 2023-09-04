package com.otelrezervasyonu;


import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CreateBookingTests extends BaseTest{
    @Test
    public void createBookingTest()
    {

        //ARTIK BASE TEST OLUŞTURUDM ORAYA KOPYALADIM BURAYI OKUNURLUK AÇIDAN DAHA İYİ OLMASI İÇİN
//        //body oluştur
//        JSONObject body =new JSONObject();
//        body.put("firstname","Ozan");
//        body.put("lastname","Ilhan");
//        body.put("totalprice",200);
//        body.put("depositpaid",true);
//
//        JSONObject bookingDates =new JSONObject();
//        bookingDates.put("checkin","2018-01-01");
//        bookingDates.put("checkout","2019-01-01");
//
//        body.put("bookingdates",bookingDates);
//        body.put("additionalneeds","Evcil Hayvan kabul edilen oda");
//



        //BASE TESTE YAPIŞTIRDIM

//        //Cağrı gerçekleştir
//
//       String url="https://restful-booker.herokuapp.com/booking";
//        Response response = given().contentType(ContentType.JSON)
//                            .when().body(bookingObject().toString())
//                            .post(url);
//
//        response.prettyPrint();
//
//
//        //Assertion
//         response
//                 .then()
//                 .assertThat()
//                 .statusCode(200);


        Response response=createBooking();//createBookingi çağırarak rezervasyon oluşturcam sonrasında assertionlar devereye giricek.

         String firstname = response.jsonPath().getJsonObject("booking.firstname");//booking altındaki firstname ulaşmış oldum
         Assertions.assertEquals("Ozan",firstname);

         String lastname=response.jsonPath().getJsonObject("booking.lastname");
         Assertions.assertEquals("Ilhan",lastname);

         int totalprice =response.jsonPath().getJsonObject("booking.totalprice");
         Assertions.assertEquals(200,totalprice);



    }
}
