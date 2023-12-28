package view;

import model.ExchangeRate;

public interface ExchangeLoader {
    ExchangeRate load(String from, String to, String date);

}
