package controller.operation_on_wallet;

import view.AvailableViews;
import view.View;
import model.currency.CurrencyExchangeRatesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.operations.ExchangeCurrencyModel;
import model.validation.ExchangeViewValidationModel;
import model.validation.Valid;

public class ExchangeViewViewController extends AbstractOperationOnWalletViewController {


    //region Controls
    @FXML
    private ComboBox<String> cbxCurrencyToObtain;

    @FXML
    private ComboBox<String> cbxCurrentCurrency;

    @FXML
    private TextField tfCurrencyAmount;

    @FXML
    private TextField tfCurrencyToObtainAmount;

    @FXML
    private TextField tfCurrentCurrencyRate;

    @FXML
    private TextField tfCurrencyToObtainRate;

    @FXML
    private Label lbInfo;
    //endregion

    private CurrencyExchangeRatesModel currencyExchangeRatesModel;
    private ExchangeCurrencyModel exchangeCurrencyModel;
    private ExchangeViewValidationModel exchangeViewValidationModel;

    private String selectedCurrency;
    private String selectedCurrencyToObtain;

    @FXML
    public void initialize()
    {
        currencyExchangeRatesModel = new CurrencyExchangeRatesModel();
        exchangeCurrencyModel = new ExchangeCurrencyModel();
        exchangeViewValidationModel = new ExchangeViewValidationModel();

        selectedCurrency = "PLN";
        selectedCurrencyToObtain = "EUR";

        super.establishConnectionWithDatabase();
        setComboBoxItems();
        updateRates();
        setUI();
    }

    private void setComboBoxItems() {
        ObservableList<String> currency = FXCollections.observableArrayList("PLN", "EUR", "USD");
        ObservableList<String> currencyToObtain = FXCollections.observableArrayList( "EUR","USD");
        cbxCurrentCurrency.setItems(currency);
        cbxCurrentCurrency.getSelectionModel().selectFirst();
        cbxCurrencyToObtain.setItems(currencyToObtain);
        cbxCurrencyToObtain.getSelectionModel().selectFirst();
    }

    private void updateRates()
    {
        currencyExchangeRatesModel.updateRates();
    }

    private void setUI()
    {
        tfCurrentCurrencyRate.setText("1 "+selectedCurrency);
        if(selectedCurrency.equals("PLN")) {
            tfCurrencyToObtainRate.setText(exchangeViewValidationModel.getRoundedCurrency(currencyExchangeRatesModel.getExchangeRates().get(selectedCurrency + "To" + selectedCurrencyToObtain).get(1))+" "+selectedCurrencyToObtain);
        }else{
            tfCurrencyToObtainRate.setText(exchangeViewValidationModel.getRoundedCurrency(currencyExchangeRatesModel.getExchangeRates().get(selectedCurrency + "To" + selectedCurrencyToObtain).get(0))+" "+selectedCurrencyToObtain);
        }
    }

    @FXML
    void cbxCurrencyToObtainOnAction(ActionEvent event){
        if(cbxCurrencyToObtain.getItems().size()>1) {
            selectedCurrencyToObtain = cbxCurrencyToObtain.getSelectionModel().getSelectedItem();
            if(selectedCurrencyToObtain==null)
            {
                selectedCurrencyToObtain="EUR";
            }
            setUI();
            showAmountUserCanExchange();
        }
    }
    @FXML
    void cbxCurrentCurrencyOnAction(ActionEvent event) {
        selectedCurrency = cbxCurrentCurrency.getSelectionModel().getSelectedItem();
        exchangeViewValidationModel.validateComboBox(cbxCurrencyToObtain,selectedCurrency);
        selectedCurrencyToObtain = cbxCurrencyToObtain.getSelectionModel().getSelectedItem();
        updateRates();
        setUI();
        showAmountUserCanExchange();
    }

    @FXML
    void btnExchangeOnAction(ActionEvent event) {
        super.resetLabel(lbInfo);
        if(checkIfDouble()){
            if (checkIfSufficientFunds()) {
                exchangeCurrency();
            }
        }
    }

    private boolean checkIfDouble()
    {
        String isDouble = exchangeViewValidationModel.checkIfDouble(tfCurrencyAmount.getText());
        if(!isDouble.equals(Valid.VALID))
        {
            super.showInfo(lbInfo,isDouble);
            return false;
        }
        return true;

    }

    private boolean checkIfSufficientFunds()
    {
        String sufficientFunds = exchangeViewValidationModel.checkIfSufficientFundsToExchange(selectedCurrency,tfCurrencyAmount.getText());
        if(!sufficientFunds.equals(Valid.VALID))
        {
            super.showInfo(lbInfo,sufficientFunds);
            return false;
        }
        return true;
    }

    private void exchangeCurrency()
    {
        exchangeCurrencyModel.exchangeCurrency(selectedCurrency,selectedCurrencyToObtain,tfCurrencyAmount.getText(),tfCurrencyToObtainAmount.getText());
        super.updateLoggedUserData();
        super.showInfo(lbInfo,"Exchanged successfully");
    }

    @FXML
    void tfCurrencyAmountOnKeyTyped(KeyEvent event) {
        showAmountUserCanExchange();
    }

    private void showAmountUserCanExchange()
    {
        if(exchangeViewValidationModel.checkIfDouble(tfCurrencyAmount.getText()).equals(Valid.VALID)) {
            if(selectedCurrency.equals("PLN")) {
                tfCurrencyToObtainAmount.setText(String.valueOf(exchangeViewValidationModel
                        .getRoundedCurrency(exchangeCurrencyModel
                                .getHowMuchUserCanExchange(selectedCurrency,tfCurrencyAmount.getText(),currencyExchangeRatesModel
                                        .getExchangeRates().get(selectedCurrencyToObtain + "To" + selectedCurrency)
                                        .get(1)))));
            }else
            {
                tfCurrencyToObtainAmount.setText(String.valueOf(exchangeViewValidationModel
                        .getRoundedCurrency(exchangeCurrencyModel
                                .getHowMuchUserCanExchange(selectedCurrency,tfCurrencyAmount.getText(),currencyExchangeRatesModel
                                        .getExchangeRates().get(selectedCurrency + "To" + selectedCurrencyToObtain)
                                        .get(0)))));
            }
        }
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        super.closeConnection();
        View.getInstance().setView(View.getView(AvailableViews.MAIN_MENU_VIEW));
    }
}
