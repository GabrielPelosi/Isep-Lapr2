/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.EvaluateServiceProvidersController;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.RatingRegister;
import lapr.project.gpsd.model.ServiceProvider;

/**
 * FXML Controller class
 *
 * @author Francisco Ferreira
 */
public class EvaluateServiceProvidersUI implements Initializable {

    private EvaluateServiceProvidersController eSPController = new EvaluateServiceProvidersController();
    
    @FXML
    private TextField txtMechNumber;
    @FXML
    private TextArea txtRate;
    @FXML
    private Button buttonAccept;
    @FXML
    private Button buttonChange;
    @FXML
    private Button buttonGoBack;
    @FXML
    private Button buttonClean;
    @FXML
    private TextArea txtAreaAbsDeviation;
    @FXML
    private Button buttonOK;
    @FXML
    private TextArea txtAreaAverageRate;
    @FXML
    private TextArea txtAreaStandardDev;
    @FXML
    private BarChart<?, ?> histogramGraph;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    
    private Stage newStageChangeEvaluation;
    
    HumanResourcesOfficerUI aController;
//    private static Evaluation evat;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         buttonGoBack.setDisable(false);
    }    

    @FXML
    private void clickAccpetAction(ActionEvent event) {
        Accept();
    }

    @FXML
    private void clickChangeAction(ActionEvent event) {
        createChangeEvaluationWindow();
        newStageChangeEvaluation.show();
    }

    /**
     * Performs actions at the click of the Accept button
     */
    public void Accept(){
        eSPController.evaluationRegister();
        Alert alert1 = new Alert (Alert.AlertType.INFORMATION);
        alert1.setTitle("Successful");
        alert1.setHeaderText(null);
        alert1.setContentText("Service Provider successfully evaluated");
        alert1.showAndWait();
        goBack();
    }
    
    @FXML
    private void clickGoBackAction(ActionEvent event) {
        goBack();
    }

    @FXML
    private void clickCleanAction(ActionEvent event) {
        clean();
    }
    
    @FXML
    private void clickOKAction(ActionEvent event) {
        String MechNumber = this.txtMechNumber.getText();
        eSPController.initiateEvaluation();
        ServiceProvider sProvider = eSPController.startStatisticsServiceProvider(MechNumber);
        if (sProvider == null){
        Alert alert1 = new Alert (Alert.AlertType.ERROR);
        alert1.setTitle("Error");
        alert1.setHeaderText(null);
        alert1.setContentText("Invalid service provider inserted");
        alert1.showAndWait();
        }
        RatingRegister lServiceProvidersScore = eSPController.getServiceProvidersScoreList();
        double serviceProviderStandardDeviation = eSPController.getProviderStandardDeviation();
        double providerMeanRate = eSPController.getProviderMeanRate();
        String evaluation = eSPController.getProviderEvaluation();
        double serviceProviderAbsDeviation = eSPController.getProviderAbsDeviation();
        String providerMeanRateString = String.format("%.2f",(providerMeanRate));
        txtRate.setText(evaluation.trim());
        String providerAbsDeviationString = String.format("%.2f",(serviceProviderAbsDeviation));
        txtAreaAverageRate.setText(providerMeanRateString);
        String StandardDevString = String.format("%.2f",(serviceProviderStandardDeviation));
        txtAreaStandardDev.setText(StandardDevString);
       txtAreaAbsDeviation.setText(providerAbsDeviationString);
        
        List <Rating> ratingList = lServiceProvidersScore.getScoreList();
        
        XYChart.Series set1  = new XYChart.Series<>();
         
        for (Rating rate : ratingList){ 
            int num = getNumRate(rate,ratingList);
            System.out.println (num);
            
        set1.getData().add (new XYChart.Data((Integer.toString(rate.getValue()).trim()),num));
        
        }

       histogramGraph.getData().clear();
       histogramGraph.getData().addAll(set1);
    }
    
     private void goBack(){
        Stage stage = (Stage) buttonGoBack.getScene().getWindow();
        stage.close();
    }
     
     /**
      * Creates the SP change scenario.
      */
      private void createChangeEvaluationWindow(){
        try {
            newStageChangeEvaluation = new Stage();
            newStageChangeEvaluation.initModality(Modality.APPLICATION_MODAL);

            newStageChangeEvaluation.setTitle("Change Evaluation");
            newStageChangeEvaluation.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnChangeEvaluation.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            ChangeEvaluationUI ncController = loader.getController();
            ncController.associateController(this);
            newStageChangeEvaluation.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
      /**
       * Clears all scenes TextAreas
       */
      private void clean(){
        txtMechNumber.setText ("");
        txtRate.setText ("");
        txtAreaStandardDev.setText("");
        txtAreaAverageRate.setText("");
      }
     
      /**
       * Associates the controllers
       * @param aController controller to associate
       */
       public void associateController(HumanResourcesOfficerUI aController) {
        this.aController = aController;
    }
       
     /**
      * Return number of rate (paramter)
      * @param Rate Service Provider rate
      * @param RatingList List os Service Provider rate
      * @return number of rates received per parameter
      */  
       public int getNumRate (Rating Rate, List<Rating> RatingList){
            int num = 0;
            for (Rating rate : RatingList){
                if (rate.getValue() == Rate.getValue() ){
                    num++;
                }
            }
            return num;
        }
       
       
    
    
    
    
}
