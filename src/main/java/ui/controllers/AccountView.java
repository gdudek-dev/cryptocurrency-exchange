package main.java.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.java.ui.stage.MainStage;

import static main.java.ui.stage.MainStage.getScene;

public class AccountView {

    //region Controllers
    @FXML
    private Label lbBtcAmount;

    @FXML
    private Label lbDogeAmount;

    @FXML
    private Label lbEurAmount;

    @FXML
    private Label lbEthAmount;

    @FXML
    private Label lbPlnAmount;

    @FXML
    private Label lbUsdAmount;

    @FXML
    private Label lbUsername;

    @FXML
    private Label lbFirstName;

    @FXML
    private Label lbLastName;

    @FXML
    private Label lbEmail;

    @FXML
    private Label lbNumber;

    @FXML
    private Label lbCountry;
    //endregion

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        getMainStage().setScene(getScene("ChangePasswordView.fxml"));
    }

    @FXML
    void btnChangePersonalDataOnAction(ActionEvent event) {
        getMainStage().setScene(getScene("ChangePersonalDataView.fxml"));
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        getMainStage().setScene(getScene("UserView.fxml"));
    }

    private MainStage getMainStage()
    {
        return MainStage.getInstance();
    }

}
