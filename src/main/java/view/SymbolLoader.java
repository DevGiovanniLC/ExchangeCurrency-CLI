package view;


import java.util.List;
import java.util.Map;

public interface SymbolLoader
{
     Map<String, String> load();

     List<String> loadCurrencyNames();
}
