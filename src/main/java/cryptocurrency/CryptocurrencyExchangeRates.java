package cryptocurrency;

import cryptocurrency.api.CryptocurrencyApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CryptocurrencyExchangeRates extends CryptocurrencyApi {

    private static CryptocurrencyExchangeRates cryptocurrencyExchangeRates = new CryptocurrencyExchangeRates();
    private Map<String, List<Double>> exchangeRatesMap = new HashMap<>();


    public static CryptocurrencyExchangeRates  getInstance()
    {
        return cryptocurrencyExchangeRates;
    }

    private CryptocurrencyExchangeRates()
    {
        setMapKeys();
    }

    private void setMapKeys()
    {
        List<Double> cryptocurrencyExchangeRatesArrayList = new ArrayList<>();
        cryptocurrencyExchangeRatesArrayList.add(0.0); //pln
        cryptocurrencyExchangeRatesArrayList.add(0.0); //eur
        cryptocurrencyExchangeRatesArrayList.add(0.0); //usd
        exchangeRatesMap.put("BTC",cryptocurrencyExchangeRatesArrayList);
        exchangeRatesMap.put("DOGE",cryptocurrencyExchangeRatesArrayList);
        exchangeRatesMap.put("ETH",cryptocurrencyExchangeRatesArrayList);
    }

    public void setCryptocurrencyExchangeRates(String key,double plnRate, double eurRate, double usdRate)
    {
        List<Double> cryptocurrencyExchangeRatesArrayList =  new ArrayList<>();
        cryptocurrencyExchangeRatesArrayList.add(plnRate);
        cryptocurrencyExchangeRatesArrayList.add(eurRate);
        cryptocurrencyExchangeRatesArrayList.add(usdRate);
        exchangeRatesMap.replace(key,cryptocurrencyExchangeRatesArrayList);
    }

    public void updateRates(String cryptocurrency)
    {
        getData(cryptocurrency);
    }



}
