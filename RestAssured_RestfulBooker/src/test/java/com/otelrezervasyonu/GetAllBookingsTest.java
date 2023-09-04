package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetAllBookingsTest extends BaseTest {
    //Cagriyi olustur
    //Response kontrolleri
    //https://restful-booker.herokuapp.com/booking


    //Bu test senaryosu, tüm rezervasyonların listelendiği durumu kontrol eder.
    @Test
    public void getAllBookingTest()
    {
        String url="/booking";
        Response response =given(spec).when().get(url);

        response

                .then()
                .statusCode(200);
    }


    //Bu metot, adından da anlaşılacağı gibi adı "Ozan" olan kişilere ait rezervasyonları filtreleyerek getiren
    // bir test senaryosunu temsil eder. //Belirli bir filtreye göre rezervasyonları çekmeyi ve filtrenin doğruluğunu doğrulamayı amaçlar.
    @Test
    public  void getBookings_with_firstname_filter_test()
    {
        //Yeni Rezervasyon oluştur.
        int bookingId = createBookingId();


        //Cağrımıza Query Parametresi ekle
        spec.queryParam("firstname","Ozan");
        spec.queryParam("lastname","Ilhan");


        //Çağrıyı Gerçekleştir
        Response response =given(spec)
                .when()
                .get("/booking");


        //Assertion/Test yaz
        response
                .then()
                .statusCode(200);

        //liste oluşturdum sonra liste içerisinde bookingid nin olup olmadığını kontrol ettim.
        List<Integer> filtrelenenRezervazyonListesi=response.jsonPath().getList("bookingid");//getList ile jsonPath deki bütün bookinid'lerine erişim sağlandı.ve listeye kaydedildi
        //System.out.println(list);
        Assertions.assertTrue(filtrelenenRezervazyonListesi.contains(bookingId));//sonrasında listenin ilk oluşturulan bookingid yi içerip içermediğini kontrol ettim.


    }


}
