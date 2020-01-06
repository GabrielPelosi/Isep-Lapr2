/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.QueryServiceExecutionOrdersController;
import lapr.project.gpsd.model.Data;
import lapr.project.gpsd.model.RegisterRequest;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.ServiceOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author Francisco Ferreira
 */
public class QueryServiceExecutionOrdersUI implements Initializable {

    private QueryServiceExecutionOrdersController qseoController;
    @FXML
    private DatePicker txtDate;
    @FXML
    private TextField initialHour;
    @FXML
    private TextField initialMinutes;
    @FXML
    private TextField FinalHour;
    @FXML
    private TextField FinalMinutes;
    @FXML
    private Button buttonExportFile;
    @FXML
    private Button buttonGoBack;
    @FXML
    private Button buttonClean;
    @FXML
    private ListView<String> executionOrdersListView;
    @FXML
    private Button buttonOK;

    private ServiceProviderUI aController;

    private Stage newStageExport;
    private ServiceProvider prestador;
    private List<String[]> requestsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonExportFile.setDisable(false);
    }

    @FXML
    private void clickExportFileAction(ActionEvent event) {
        createExportFileWindow();
        newStageExport.show();
    }

    @FXML
    private void clickOKAction(ActionEvent event) {

        String dateS = txtDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Data d = new Data(dateS.trim());

        int hourI = Integer.parseInt(initialHour.getText().trim());
        int minI = Integer.parseInt(initialMinutes.getText().trim());

        Time tI = new Time(hourI, minI);

        int hourF = Integer.parseInt(FinalHour.getText().trim());
        int minF = Integer.parseInt(FinalMinutes.getText().trim());

        Time tF = new Time(hourF, minF);

        requestsList = qseoController.sendPeriod(d, tI, tF, prestador);
        for (String[] ped : requestsList) {
            executionOrdersListView.getItems().add(String.format("%s; %s; %s; %s; %s; %s; %s; %s; %s; %s", ped[0], ped[1], ped[2], ped[3], ped[4], ped[5], ped[6], ped[7], ped[8], ped[9]));
        }
    }

    @FXML
    private void clickGoBackAction(ActionEvent event) {
        goBack();
    }

    @FXML
    private void clickCleanAction(ActionEvent event) {
        executionOrdersListView.getItems().clear();
    }

    /**
     * Go back to the previous scenario.
     */
    private void goBack() {
        Stage stage = (Stage) buttonGoBack.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates the export file scenario.
     */
    private void createExportFileWindow() {
        try {
            newStageExport = new Stage();
            newStageExport.initModality(Modality.APPLICATION_MODAL);

            newStageExport.setTitle("Export File");
            newStageExport.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnExportFile.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            ExportFileUI ncController = loader.getController();
            ncController.associateController(this, requestsList);
            newStageExport.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Associates the controllers
     *
     * @param aThis the controller to associate
     */
    void associateController(ServiceProviderUI aThis, ServiceProvider prestador) {
        this.aController = aThis;
        this.prestador = prestador;
        qseoController = new QueryServiceExecutionOrdersController();
    }

}
