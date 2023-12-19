package interfaces;

import java.time.LocalDate;

public interface CommandInterface {

    LocalDate getSelectedDate();

    double getAmountToExchange();

    String getCurrencyFrom();

    String getCurrencyTo();

    void setAmountExchanged(Double amount);

}
