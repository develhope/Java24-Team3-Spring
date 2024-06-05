package com.develhope.spring.services;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// https://mvnrepository.com/artifact/org.json/json
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OkHttpClientService_prova {

    private static final String API_KEY = "6cba6eee747f4f4496edf3a7cbfb2617";


    @GetMapping("/Coordinates_prova")
    public static void getAddress() {
        OkHttpClient client = new OkHttpClient();
        try {
            // housenumber=11&street=Rue%20Grenette&postcode=69002&city=Lyon&country=France&apiKey=YOUR_API_KEY
            HttpUrl url = HttpUrl.parse("https://api.geoapify.com/v1/geocode/search").newBuilder()
                    .addQueryParameter("text", "Berlin")
                    .addQueryParameter("apiKey", API_KEY)
                    .build();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                JSONObject json = new JSONObject(response.body().string());

                JSONArray results = json.getJSONArray("features");
                JSONObject firstResult = results.getJSONObject(0);
                JSONObject firstResultProperties = firstResult.getJSONObject("properties");

                String formattedAddress = firstResultProperties.getString("formatted");
                System.out.println(formattedAddress); // prints something like "10117 Berlin, Germany"
            } else {
                System.err.println("Request error " + response.code());
                System.err.println(response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}