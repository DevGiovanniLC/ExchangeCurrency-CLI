package view.api;

import view.ExchangeLoader;
import view.ReferenceRateLoader;
import view.SymbolLoader;
import model.Currency;
import model.ExchangeRate;

import java.util.Map;

public class APIExchangeLoader implements ExchangeLoader {

    private final ReferenceRateLoader referenceValuesLoader;
    private final Map<String, String> symbols;

    public APIExchangeLoader(ReferenceRateLoader referenceRateLoader, SymbolLoader symbols) {
        this.referenceValuesLoader = referenceRateLoader;
        this.symbols =  symbols.load();
    }

    @Override
    public ExchangeRate load(String from, String to, String date) {
        return new ExchangeRate(
                symbolToCurrency(from, date),
                symbolToCurrency(to, date),
                date
        );
    }


    private Currency symbolToCurrency(String symbol, String date){
        Map<String, Double> values = referenceValuesLoader.load(date);

        return new Currency(symbol, symbols.get(symbol), values.get(symbol));
    }
}
