package ui.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static ui.views.MainStage.getScene;

public class ChangePersonalDataViewController {

    //region Controllers
    @FXML
    private TextField tfNewUsername;

    @FXML
    private TextField tfNewFirstName;

    @FXML
    private TextField tfNewLastName;

    @FXML
    private TextField tfNewNumber;

    @FXML
    private TextField tfNewEmail;

    @FXML
    private TextField tfNewCountry;
    //endregion

    @FXML
    void btnChangeCountryOnAction(ActionEvent event) {

    }

    @FXML
    void btnChangeEmailOnAction(ActionEvent event) {

    }

    @FXML
    void btnChangeFirstNameOnAction(ActionEvent event) {

    }

    @FXML
    void btnChangeLastNameOnAction(ActionEvent event) {

    }

    @FXML
    void btnChangeNumberOnAction(ActionEvent event) {

    }

    @FXML
    void btnChangeUsernameOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        getMainStage().setScene(getScene("AccountView.fxml"));
    }


    private MainStage getMainStage() {
        return MainStage.getInstance();
    }
}