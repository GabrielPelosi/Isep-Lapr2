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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.SpecifyCategoryController;

/**
 * FXML Controller class
 *
 * @author franc
 */
public class SpecifyCategoryUI implements Initializable {

    private SpecifyCategoryController sC_Controller = new SpecifyCategoryController();
    @FXML
    private TextField txtUniqueCode;
    @FXML
    private Button buttonConfirm;
    @FXML
    private Button buttonGoBack;
    @FXML
    private Button buttonClean;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Label txtSuccessMessage;
    AdministrativeUI aController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonGoBack.setDisable(false);
    }    

    @FXML
    private void clickConfirmAction(ActionEvent event) {
        
        try{
            sC_Controller.newCategory();
        
        sC_Controller.setData (txtUniqueCode.getText(),txtDescription.getText());
        sC_Controller.registerCategory();
        txtDescription.setText("");
        txtUniqueCode.setText("");
        Alert alert1 = new Alert (Alert.AlertType.INFORMATION);
        alert1.setTitle("Successful");
        alert1.setHeaderText(null);
        alert1.setContentText("New category specified successfully");
        alert1.showAndWait();
        goBack();
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid data entered");

            alert.showAndWait();
        }
    }
    
    /**
    *Go back to the previous scenario. 
    */
     private void goBack(){
        Stage stage = (Stage) buttonGoBack.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void clickGobackAction(ActionEvent event) {
        goBack();
    }
 
    @FXML
    private void clickCleanAction(ActionEvent event) {
        txtUniqueCode.setText ("");
        txtDescription.setText("");
    }
    
 
    /**
     * Associates the controllers
     * @param aController controller to associate
     */
     public void associateController(AdministrativeUI aController) {
        this.aController = aController;
    }
    
}
