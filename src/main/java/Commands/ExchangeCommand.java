package Commands;

import interfaces.*;
import models.*;

import java.time.LocalDate;

public class ExchangeCommand implements Command {
    CommandInterface command;
    ExchangeLoader exchangeLoader;

    public ExchangeCommand(CommandInterface window, ExchangeLoader exchangeLoader) {
       this.command = window;
       this.exchangeLoader = exchangeLoader;
    }


    @Override
    public void execute() {
        LocalDate date = this.command.getSelectedDate();
        String from = this.command.getCurrencyFrom();
        String to = this.command.getCurrencyTo();
        double amountToExchange = this.command.getAmountToExchange();
        ExchangeRate exchangeRate = this.exchangeLoader.load(from,to,date.toString());
        this.command.setAmountExchanged(amountToExchange/exchangeRate.getRate());
    }
}
