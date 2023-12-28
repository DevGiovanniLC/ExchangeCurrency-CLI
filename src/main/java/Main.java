import controller.Controller;
import view.ExchangeLoader;
import view.ReferenceRateLoader;
import view.SymbolLoader;
import view.api.APIExchangeLoader;
import view.api.APIReferenceRateLoader;
import view.api.APISymbolLoader;
import view.cli.CLIMain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        String key = "57e4db41ff0c56098b9af86b6d8bb354";

        SymbolLoader symbolLoader = new APISymbolLoader(key);
        ReferenceRateLoader referenceRateLoader = new APIReferenceRateLoader(key);
        ExchangeLoader exchangeLoader = new APIExchangeLoader(referenceRateLoader,symbolLoader);

        mainLoop(exchangeLoader);
    }

    public static void mainLoop(ExchangeLoader exchangeLoader){
        do {
            checkParameters(exchangeLoader);
        } while (!new Scanner(System.in).nextLine().equals("exit"));
    }

    public static void checkParameters(ExchangeLoader exchangeLoader){
        try{
            CLIMain cliMain = new CLIMain();
            Controller controller = new Controller(cliMain, exchangeLoader);
            controller.execute("exchange");
        }
        catch (Exception e){
            System.out.println("\n ERROR: Bad parameters");
        }
    }

}
