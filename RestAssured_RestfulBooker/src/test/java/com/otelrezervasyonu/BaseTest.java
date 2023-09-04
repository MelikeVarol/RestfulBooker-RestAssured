package com.otelrezervasyonu;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {

    RequestSpecification spec;

    @BeforeEach
    public void setup()
    {
        spec=new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter()))//bu filtreler sayesinde bütün çağrılarda lonsola loglanıcak
                .build();
    }



    //YAZDIĞIM TÜM TESTLERDE BU METODU KULLANARAK REZERVAZYON OLUŞTURULABİLİR.
    //rezervasyon oluşturmayı gerçekleştirir
    protected Response createBooking() //Respons tipinde metot oluşturudm.
    {
        String url="/booking";//buranın başını sildim /booking baseuri a eklenicek.
        Response response = given(spec).contentType(ContentType.JSON)
                .when().body(bookingObject("Ozan","Ilhan",200,true))//createBooking bu şekide oluşturulsun.
                .post(url);

        //response.prettyPrint();gerek yok artık oluşturduğum filtreler sayesinde response konsola otomatik yazılcak

        //Assertion
        response
                .then()
                .assertThat()
                .statusCode(200);

        return response;
    }

//ÖNCE YUKARDA BOOKİNG OLUŞTURDUM YANİ REZERVASYON SONRA REZERVASYON ID SİNE RETURN OLARAK METODA DÖNMEK
//oluşturulan rezervasyonun ID'sini alma
     protected int createBookingId()
    {
        Response response =createBooking();//bookinh oluşturdum
        return response.jsonPath().getJsonObject("bookingid");

    }



    protected String bookingObject(String firstname,String lastname,int totalPrice,boolean depositPaid)
    {
        JSONObject body =new JSONObject();
        body.put("firstname",firstname);
        body.put("lastname",lastname);
        body.put("totalprice",totalPrice);
        body.put("depositpaid",depositPaid);

        JSONObject bookingDates =new JSONObject();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds","Evcil Hayvan kabul edilen oda");

        return body.toString();

    }



    protected String createToken()
    {
        JSONObject body =new JSONObject();//Bu nesne, isteğin gövde kısmına eklenmek üzere JSON verilerini içerecektir.
        body.put("username","admin");// JSON nesnesine "username" anahtarıyla "admin" değeri ekleniyor.
        body.put("password","password123");


        Response response= given(spec).contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .post("/auth");

        return response.jsonPath().getJsonObject("token");//metod token değerini string olarka döndürcek.
    }

}
