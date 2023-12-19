package api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

import interfaces.ReferenceRateLoader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;



public class APIReferenceRateLoader implements ReferenceRateLoader {


    private final String key;


    public APIReferenceRateLoader(String key){
        this.key = key;
        load("latest");
    }

    @Override
    public Map<String,Double> load(String date) {
        return loadReferenceValueMap(date);
    }

    private Map<String,Double> loadReferenceValueMap(String date) {
        try {
            String json = loadJson(date
            );
            return toReferenceValueMap(json);
        } catch (IOException e) {
            System.out.println("ERROR: Expired API change it from the code");
            System.exit(1);
            return Collections.emptyMap();
        }
    }

    private String loadJson(String date) throws IOException {
        URL url = new URL(String.format("http://api.exchangeratesapi.io/v1/%s?access_key=%s&base=EUR",date,key));
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private Map<String,Double> toReferenceValueMap(String json) {
        HashMap<String, Double> result = new HashMap<>();
        Map<String, JsonElement> rates = new Gson().fromJson(json, JsonObject.class).get("rates").getAsJsonObject().asMap();
        for (String rate : rates.keySet())
            result.put(rate,  rates.get(rate).getAsDouble());
        return result;
    }

}
