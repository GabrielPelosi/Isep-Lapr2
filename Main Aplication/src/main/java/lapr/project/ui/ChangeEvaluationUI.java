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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.EvaluateServiceProvidersController;

/**
 * FXML Controller class
 *
 * @author Francisco Ferreira
 */
public class ChangeEvaluationUI implements Initializable {

    private EvaluateServiceProvidersController eSPController = new EvaluateServiceProvidersController();
    @FXML
    private TextField txtNewEvaluation;
    @FXML
    private Button buttonConfirm;
    @FXML
    private Button buttonGoBack;
    @FXML
    private Button buttonClean;
    
    EvaluateServiceProvidersUI aController;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickConfirmAction(ActionEvent event) {
       String newEvaluation = txtNewEvaluation.getText().trim();
       if (newEvaluation.trim().equalsIgnoreCase ("Worst Provider") || newEvaluation.trim().equalsIgnoreCase("Regular Provider") || (newEvaluation.trim().equalsIgnoreCase ("Outstanding Provider"))){
       boolean value = eSPController.setEvaluation(newEvaluation);
       if (value == true){
           Alert alert = new Alert (Alert.AlertType.INFORMATION);
           alert.setTitle ("Successful");
           alert.setHeaderText(null);
           alert.setContentText("New evaluation changed successfully");
           alert.showAndWait();
           goBack();
           aController.Accept();
       }
       
    }
    }

    @FXML
    private void clickGoBackAction(ActionEvent event) {
        goBack();
    }

    @FXML
    private void clickCleanAction(ActionEvent event) {
        txtNewEvaluation.setText("");
    }
    
    
     private void goBack(){
        Stage stage = (Stage) buttonGoBack.getScene().getWindow();
        stage.close();
    }
     
       public void associateController(EvaluateServiceProvidersUI aController) {
        this.aController = aController;
    }
    
}
