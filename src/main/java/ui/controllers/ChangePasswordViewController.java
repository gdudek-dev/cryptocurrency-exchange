package main.java.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import main.java.ui.stage.MainStage;

import static main.java.ui.stage.MainStage.getScene;

public class ChangePasswordViewController {

    //region Controllers
    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfNewPassword;

    @FXML
    private PasswordField pfConfirmedNewPassword;
    //endregion

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        getMainStage().setScene(getScene("AccountView.fxml"));
    }

    private MainStage getMainStage()
    {
        return MainStage.getInstance();
    }

}
