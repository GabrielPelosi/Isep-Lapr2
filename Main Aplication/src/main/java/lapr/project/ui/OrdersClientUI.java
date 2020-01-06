package lapr.project.ui;

import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.RequestTime;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

public class OrdersClientUI implements Initializable {

    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnDecide;
    @FXML
    private ListView<Request> requestsClientList;
    private Stage newDecideStage;
    ClientUI sController;
    Client client;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client = Utils.getEmpresa().getRegistoCliente().getClienteByEmail(Utils.getEmpresa().getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
        updateList();
    }

    @FXML
    private void clickGoBackAction(ActionEvent event) {
        Stage stage = (Stage) btnGoBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickDecideAction(ActionEvent event) {
        if (createWindowDecide()) {
            newDecideStage.showAndWait();
            updateList();
        }
    }

    private boolean createWindowDecide() {
        try {
            newDecideStage = new Stage();
            newDecideStage.initModality(Modality.APPLICATION_MODAL);
            newDecideStage.setTitle("Decide on proposed period of service");
            newDecideStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnDecidePeriod.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            DecidePeriodUI ncController = loader.getController();
            Request pedido = requestsClientList.getSelectionModel().getSelectedItem();
            if (pedido == null) {
                throw new Exception("NO REQUEST SELECTED");
            }
            ncController.associateController(this, pedido);
            newDecideStage.setScene(scene);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void associateController(ClientUI aThis) {
        this.sController = aThis;
    }

    public void updateList() {
        try {
            requestsClientList.getItems().clear();
            for (Request i : Utils.getEmpresa().getRegistoPedido().getList()) {
                if (i.getCl().getEmail().equals(client.getEmail()) && i.getTempInfo() && !i.getAssignedInfo()) {
                    requestsClientList.getItems().add(i);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This customer has not requested for services!");
            alert.showAndWait();
        }
    }
}
