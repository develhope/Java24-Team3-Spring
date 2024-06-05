package com.develhope.spring.services;

import com.develhope.spring.models.dtos.AddressDto;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class OkHttpClientService_v2 {

    private static final String API_KEY = "6cba6eee747f4f4496edf3a7cbfb2617";


    @GetMapping("/Coordinates")
    public static AddressDto getAddressDtoWithCoordinates(AddressDto addressDto) throws Exception {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse("https://api.geoapify.com/v1/geocode/search").newBuilder()
                .addQueryParameter("country", addressDto.getCountry())
                .addQueryParameter("postcode", addressDto.getPostcode())
                .addQueryParameter("city", addressDto.getCity())
                .addQueryParameter("street", addressDto.getStreet().replace(" ", "%20"))
                .addQueryParameter("housenumber", addressDto.getNumber().toString())
                .addQueryParameter("apiKey", API_KEY)
                .build();
        Request request = new Request.Builder().url(url).build();
        Response response;
        try {
             response = client.newCall(request).execute();
        } catch (Exception e){
            throw new IOException("Error in call to GeoApify.");
        }

        if (response.code() == 200) {
            JSONObject json;
            try{
            json = new JSONObject(response.body().string());}
            catch (Exception e){
                throw new IOException("Error in converting body GeoApyfy's response to json.");
            }

            JSONArray results = json.getJSONArray("features");
            JSONObject firstResult = results.getJSONObject(0);
            JSONObject firstResultGeometry = firstResult.getJSONObject("geometry");
            JSONArray firstResultGeometryCoordinates = firstResultGeometry.getJSONArray("coordinates");
            BigDecimal firstResultLon = firstResultGeometryCoordinates.getBigDecimal(0);
            BigDecimal firstResultLat = firstResultGeometryCoordinates.getBigDecimal(1);

            addressDto.setLon(firstResultLon);
            addressDto.setLat(firstResultLat);
            return addressDto;
        } else {
            throw new Exception("GeoApify's response code is not 200.");
        }
    }
}