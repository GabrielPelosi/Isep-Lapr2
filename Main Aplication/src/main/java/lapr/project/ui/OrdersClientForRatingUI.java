package lapr.project.ui;

import java.net.URL;
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
import lapr.project.gpsd.controller.RatingController;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.RequestCompleted;
import lapr.project.gpsd.model.ServiceOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.ui.console.utils.Utils;

public class OrdersClientForRatingUI implements Initializable {

    private RatingController aController = new RatingController();
    @FXML
    private Button btnGoBack;
    @FXML
    private ListView<ServiceOrder> requestsList;
    ClientUI sController;
    private Stage newRatingStage;
    @FXML
    private Button btnRating;

    private boolean deleteCurrent = false;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            updateList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("This customer has not provided services!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickGoBackAction(ActionEvent event) {
        Stage stage = (Stage) btnGoBack.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickRatingAction(ActionEvent event) {
        ServiceOrder pedido = requestsList.getSelectionModel().getSelectedItem();
        int num = requestsList.getSelectionModel().getSelectedIndex();
        if (createWindowRating(pedido)) {
            newRatingStage.showAndWait();
            pedido.setRated(true);
            updateList();
        }
    }

    public void setDeleteCurrent(boolean deleteCurrent) {
        this.deleteCurrent = deleteCurrent;
    }

    /**
     * Associates the controllers
     *
     * @param sController
     */
    public void associateController(ClientUI sController) {
        this.sController = sController;
    }

    /**
     * Creates the window for rate service providers
     */
    private boolean createWindowRating(ServiceOrder pedido) {
        try {
            newRatingStage = new Stage();
            newRatingStage.initModality(Modality.APPLICATION_MODAL);
            newRatingStage.setTitle("Rating Service Providers");
            newRatingStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnRating.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            RatingUI ncController = loader.getController();
            if (pedido == null) {
                throw new Exception("NO REQUEST SELECTED");
            }
            ServiceProvider prestador = pedido.getaSP();
            ncController.associateController(this, prestador);
            newRatingStage.setScene(scene);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private void updateList() {
        Client client = Utils.getEmpresa().getRegistoCliente().getClienteByEmail(Utils.getEmpresa().getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
        requestsList.getItems().clear();
        for (Request i : Utils.getEmpresa().getRegistoPedido().getList()) {
            if (i.getCl().getEmail().equals(client.getEmail()) && i.getAssignedInfo()) {
                System.out.println(i.getServicoCompleto());
                for (ServiceOrder so : i.getServicoCompleto()) {
                    if (!so.getRated()) {
                        requestsList.getItems().add(so);
                    }
                }
            }
        }
    }

}
