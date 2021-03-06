package model.validation;

import model.database.entity.UserWallet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.session.LoggedUser;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeViewValidationModel {

    public String checkIfDouble(String number)
    {
        try{
            Double.valueOf(number);
        }
        catch (Exception ex){
            return ("Not a valid double value");
        }
        return Valid.VALID;
    }

    public String checkIfSufficientFundsToExchange(String usedCurrency,String currencyAmountToExchange)
    {
        double userFunds = getUserFunds(usedCurrency);
        if(userFunds<Double.parseDouble(currencyAmountToExchange))
        {
            return "You don't have enough funds";
        }
        return Valid.VALID;

    }

    public double getUserFunds(String usedCurrency)
    {
        UserWallet userWallet = LoggedUser.getInstance().getLoggedUser().getUserWallet();
        switch (usedCurrency)
        {
            case "PLN": return userWallet.getPln();
            case "EUR": return userWallet.getEur();
            case "USD": return userWallet.getUsd();
        }
        return 0;
    }


    public void validateComboBox( ComboBox<String> cbxCurrencyToObtain,String selectedCurrency)
    {
        ObservableList<String> currencyToObtain;
        cbxCurrencyToObtain.getItems().removeAll();
        if(selectedCurrency.equals("PLN"))
        {
            currencyToObtain = FXCollections.observableArrayList( "EUR","USD");
        }
        else
        {
            currencyToObtain = FXCollections.observableArrayList( "PLN");
        }
        cbxCurrencyToObtain.setItems(currencyToObtain);
        cbxCurrencyToObtain.getSelectionModel().selectFirst();
    }

    public double getRoundedCurrency(double currencyAmount)
    {
        return round(currencyAmount);
    }

    private double round(double currencyAmount) {
        BigDecimal bd = new BigDecimal(Double.toString(currencyAmount));
        bd = bd.setScale(2, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}
