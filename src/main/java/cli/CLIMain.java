package cli;

import interfaces.CommandInterface;

import java.time.LocalDate;
import java.util.Scanner;

public class CLIMain implements CommandInterface {


    private final LocalDate selectedDate;
    private final Double amountToExchange;
    private final String currencyFrom;
    private final String currencyTo;

    public CLIMain() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Type the amount to exchange: (#.##) ");
        this.amountToExchange = scanner.nextDouble();

        System.out.println("Type the ISO code of the current currency: (xxx) ");
        this.currencyFrom = scanner.next();

        System.out.println("Type the ISO code of the currency to exchange: (xxx) ");
        this.currencyTo = scanner.next();

        System.out.println("type the selected date to exchange: (yyyy-mm-dd) or latest");
        String date = scanner.next();
        this.selectedDate = date.equals("latest") ? LocalDate.now(): LocalDate.parse(date);
    }

    @Override
    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    @Override
    public double getAmountToExchange() {
        return amountToExchange;
    }

    @Override
    public String getCurrencyFrom() {
        return currencyFrom;
    }

    @Override
    public String getCurrencyTo() {
        return currencyTo;
    }

    @Override
    public void setAmountExchanged(Double amount) {
        System.out.printf("\nAmount exchanged: %f \n",amount);
    }
}
