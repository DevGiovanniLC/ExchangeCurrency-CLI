package view.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import view.SymbolLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class APISymbolLoader implements SymbolLoader {

    private final String key;
    Map<String,String> symbolsMap;

    public APISymbolLoader(String key) {
        this.key = key;
        this.symbolsMap = preloadSymbols();
    }

    @Override
    public Map<String,String> load() {
         return this.symbolsMap;
    }

    @Override
    public List<String> loadCurrencyNames() {
        return load()
                .entrySet()
                .stream()
                .map(entry-> entry.getKey() + ", " + entry.getValue())
                .sorted()
                .collect(Collectors.toList());
    }

    private Map<String,String>  preloadSymbols(){
        try {
            String json = loadJson();
            return toCurrencyMap(json);
        } catch (IOException e) {
            System.out.println("ERROR: Expired API change it from the code");
            System.exit(1);
            return Collections.emptyMap();
        }
    }


    private String loadJson() throws IOException {
        URL url = new URL(String.format("http://api.exchangeratesapi.io/v1/symbols?access_key=%s",key));
        try (InputStream is = url.openStream()) {
            return new String(is.readAllBytes());
        }
    }

    private Map<String,String> toCurrencyMap(String json) {
        HashMap<String, String> result = new HashMap<>();
        Map<String, JsonElement> rates = new Gson().fromJson(json, JsonObject.class).get("symbols").getAsJsonObject().asMap();
        for (String rate : rates.keySet())
            result.put(rate,  rates.get(rate).getAsString());
        return result;
    }

}
