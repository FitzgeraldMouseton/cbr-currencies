import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class CurrencyParser {

    public List<Currency> parseCurrencies(String url) {
        JsonObject currencies = getCurrenciesInfo(url);
        return currencies.entrySet().stream().map(e -> {
            JsonObject currencyData = JsonParser.parseString(e.getValue().toString()).getAsJsonObject();
            String currencyName = currencyData.get("CharCode").getAsString();
            double currencyValue = currencyData.get("Value").getAsDouble();
            double currencyPreviousValue = currencyData.get("Previous").getAsDouble();
            return new Currency(currencyName, currencyValue, currencyPreviousValue);
        })
                .sorted(Comparator.comparingDouble(
                        (Currency c) -> abs(c.getValue() - c.getPreviousValue()))
                        .reversed())
                .collect(Collectors.toList());
    }

    private String getDataFromUrl(String url) {
        try (InputStream is = new URL(url).openStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader bufferedReader = new BufferedReader(isr)) {
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    private JsonObject getCurrenciesInfo(String url) {
        String data = getDataFromUrl(url);
        return JsonParser
                .parseString(data)
                .getAsJsonObject()
                .get("Valute")
                .getAsJsonObject();
    }
}
