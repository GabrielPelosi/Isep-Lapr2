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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.ServiceOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class CompleteServiceUI implements Initializable {

    @FXML
    private ListView<ServiceOrder> listServices;
    private Stage completeServiceStage;
    private List<Request> listRequest = new ArrayList<Request>();
    @FXML
    private Button butFinished;
    private ServiceProvider sp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void clickBack(ActionEvent event) {
        Stage stage = (Stage) butFinished.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickConfirm(ActionEvent event) {
        createCompleteServiceWindow();
        completeServiceStage.showAndWait();
        init(sp);
    }

    private void init(ServiceProvider sp) {
        listServices.getItems().clear();
        for (Request r : Utils.getEmpresa().getRegistoPedido().getList()) {
            for (ServiceOrder i : r.getServico()) {
                if (i.getaSP() == sp) {
                    listServices.getItems().add(i);
                    listRequest.add(r);
                    
                }
                System.out.println("");
            }
        }
    }

    void associateController(ServiceProviderUI aThis, ServiceProvider sp) {
        this.sp = sp;
        init(sp);
    }

    private void createCompleteServiceWindow() {
        try {
            completeServiceStage = new Stage();
            completeServiceStage.initModality(Modality.APPLICATION_MODAL);

            completeServiceStage.setTitle("Complete Services");
            completeServiceStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnCompleteServiceWindow.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            CompleteServiceWindowUI ncController = loader.getController();
            ServiceOrder so = listServices.getSelectionModel().getSelectedItem();
            ncController.getInformation(listRequest.get(listServices.getSelectionModel().getSelectedIndex()), so);
            completeServiceStage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
