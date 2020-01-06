/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.QueryServiceExecutionOrdersController;

/**
 * FXML Controller class
 *
 * @author Francisco Ferreira
 */
public class ExportFileUI implements Initializable {

    private QueryServiceExecutionOrdersController qseoController = new QueryServiceExecutionOrdersController();
    @FXML
    private ComboBox<String> comboFileType;
    @FXML
    private Button buttonExport;
    @FXML
    private Button buttonGoBack;

    private QueryServiceExecutionOrdersUI aController;
    private List<String[]> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> fileTypes = new ArrayList<>();
        fileTypes.add("Excel");
        fileTypes.add("CSV");
        fileTypes.add("XML");
        comboFileType.setItems(FXCollections.observableList(fileTypes));
    }

    /**
     * Go back to the previous scenario.
     */
    private void goBack() {
        Stage stage = (Stage) buttonGoBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickExportFileAction(ActionEvent event) {
        String fileType = comboFileType.getValue();
        boolean value = qseoController.exportFile(fileType, data);
        if (value == true) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Successful");
            alert1.setHeaderText(null);
            alert1.setContentText("File exported successfully");
            alert1.showAndWait();
            goBack();
        } else if (value == false) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error");
            alert1.setHeaderText(null);
            alert1.setContentText("Error exporting file");
            alert1.showAndWait();
            goBack();
        }
    }

    @FXML
    private void clickGoBackAction(ActionEvent event) {
        goBack();
    }

    public void associateController(QueryServiceExecutionOrdersUI aControllerThis, List<String[]> data) {
        aController = aControllerThis;
        this.data = data;
    }

}
