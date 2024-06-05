package com.develhope.spring.services;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class OkHttpClientService_v1 {

    private static final String API_KEY = "6cba6eee747f4f4496edf3a7cbfb2617";


    @GetMapping("/Coordinates")
    public static void getAddress() {
        OkHttpClient client = new OkHttpClient();
        try {
            // housenumber=11&street=Rue%20Grenette&postcode=69002&city=Lyon&country=France&apiKey=YOUR_API_KEY
            HttpUrl url = HttpUrl.parse("https://api.geoapify.com/v1/geocode/search").newBuilder()
                    .addQueryParameter("country", "Italy")
                    .addQueryParameter("postcode", "64028")
                    .addQueryParameter("city", "Silvi")
                    .addQueryParameter("street", "Contrada Santo Stefano")
                    .addQueryParameter("housenumber", "39")
                    .addQueryParameter("apiKey", API_KEY)
                    .build();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                JSONObject json = new JSONObject(response.body().string());

                JSONArray results = json.getJSONArray("features");
                JSONObject firstResult = results.getJSONObject(0);
                JSONObject firstResultGeometry = firstResult.getJSONObject("geometry");
                JSONArray firstResultGeometryCoordinates = firstResultGeometry.getJSONArray("coordinates");
                BigDecimal firstResultLon = firstResultGeometryCoordinates.getBigDecimal(0);
                BigDecimal firstResultLat = firstResultGeometryCoordinates.getBigDecimal(1);

                System.out.println(firstResultLon.toString() + firstResultLat.toString()); // prints something like "10117 Berlin, Germany"
            } else {
                System.err.println("Request error " + response.code());
                System.err.println(response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}