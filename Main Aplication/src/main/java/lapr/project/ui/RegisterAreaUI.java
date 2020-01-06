/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterAreaController;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class RegisterAreaUI implements Initializable {

    @FXML
    private TextField fieldDesig;
    @FXML
    private TextField fieldRadius;
    @FXML
    private TextField fieldCost;
    @FXML
    private TextField fieldCP1;
    @FXML
    private TextField fieldCP2;

    AdministrativeUI aController;
    RegisterAreaController controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new RegisterAreaController();
    }

    @FXML
    private void pressCancel(ActionEvent event) {
        Stage stage = (Stage) fieldDesig.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void pressConfirm(ActionEvent event) {
        try {
            controller.newArea(fieldDesig.getText(), fieldCP1.getText(), fieldCP2.getText(), fieldRadius.getText(), fieldCost.getText());
            Stage stage = (Stage) fieldDesig.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Wrong data");

            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    public void associateController(AdministrativeUI aController) {
        this.aController = aController;
    }

}
