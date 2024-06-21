package com.develhope.spring.services;

import com.develhope.spring.models.dtos.AddressDto;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Service class that interacts with the GeoApify API to obtain geocoding information.
 * This class provides a method to enrich an AddressDto object with geocoordinates
 * and standardized address information.
 *
 * @Service
 * @see <a href="https://www.geoapify.com/geocoding-api?gad_source=1&gclid=CjwKCAjw9IayBhBJEiwAVuc3fguNCm6wsJkEj22aOsvPPBoCRMaxdkS1Ha7ND7xdtkF4uHduR8rVzhoCsRwQAvD_BwE">GeoApify Geocoding API</a>
 * @api_key Associated with the Gmail account of Chiara de Guglielmo (profilo superfalso)
*/
@Service
public class OkHttpClientService {

    private static final String API_KEY = "6cba6eee747f4f4496edf3a7cbfb2617";

    @GetMapping("/Coordinates")
    public AddressDto getAddressDtoWithCoordinates(AddressDto addressDto) throws Exception {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = HttpUrl.parse("https://api.geoapify.com/v1/geocode/search").newBuilder()
                .addQueryParameter("country", addressDto.getCountry())
                .addQueryParameter("postcode", addressDto.getPostcode())
                .addQueryParameter("city", addressDto.getCity())
                .addQueryParameter("street", addressDto.getStreet())
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
            JSONArray firstResultGeometryCoordinates = firstResult.getJSONObject("geometry").getJSONArray("coordinates");
            String firstResultCountry = firstResult.getJSONObject("properties").getString("country");
            String firstResultPostcode = firstResult.getJSONObject("properties").getString("postcode");
            String firstResultCity = firstResult.getJSONObject("properties").getString("city");
            String firstResultStreet = firstResult.getJSONObject("properties").getString("street");
            String firstResultNumber = firstResult.getJSONObject("properties").getString("housenumber");

            addressDto.setCountry(firstResultCountry);
            addressDto.setPostcode(firstResultPostcode);
            addressDto.setCity(firstResultCity);
            addressDto.setStreet(firstResultStreet);
            addressDto.setNumber(firstResultNumber);
            addressDto.setCoordinates(new BigDecimal[]{firstResultGeometryCoordinates.getBigDecimal(1), firstResultGeometryCoordinates.getBigDecimal(0)});
            return addressDto;
        } else {
            throw new Exception("GeoApify's response code is not 200.");
        }
    }

}