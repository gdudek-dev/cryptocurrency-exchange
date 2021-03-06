package model.cryptocurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public abstract class SetCryptocurrencyExchangeRates {


    public void roundAndSetExchangeRates(String cryptocurrency, ArrayList<Double> rates,CryptocurrencyExchangeRatesModel exchangeRatesModel)
    {
        exchangeRatesModel.setCryptocurrencyExchangeRates(cryptocurrency,round(rates.get(0)),round(rates.get(1)),round(rates.get(2)));
    }

    private double round(double value) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(2, RoundingMode.HALF_DOWN);
        return bd.doubleValue();
    }

}
