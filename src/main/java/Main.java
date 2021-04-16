import java.util.List;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {

        String url = "https://www.cbr-xml-daily.ru/daily_json.js";

        CurrencyParser parser = new CurrencyParser();
        List<Currency> currencies = parser.parseCurrencies(url);

        currencies.stream()
                .limit(5)
                .forEach(c -> System.out.println(c + ": изменение за день - "
                        + abs(c.getValue() - c.getPreviousValue())));
    }
}
