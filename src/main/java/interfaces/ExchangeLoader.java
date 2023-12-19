package interfaces;

import models.ExchangeRate;

public interface ExchangeLoader {
    ExchangeRate load(String from, String to, String date);

}
