package ui.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import static ui.views.MainStage.getScene;

public class LoginViewController {
    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;


    @FXML
    void btnExitOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        getMainStage().setScene(getScene("UserView.fxml"));
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        getMainStage().setScene(getScene("RegisterView.fxml"));
    }

    private MainStage getMainStage() {
        return MainStage.getInstance();
    }
}