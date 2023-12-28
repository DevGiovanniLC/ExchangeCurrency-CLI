package controller;

import view.ExchangeLoader;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    Map<String, Command> commandMap;

    public Controller(CommandInterface window, ExchangeLoader exchangeLoader) {
        this.commandMap = new HashMap<>();
        this.commandMap.put("exchange", new ExchangeCommand(window, exchangeLoader));
    }

    public void execute(String command){
        this.commandMap.get(command).execute();
    }
}
