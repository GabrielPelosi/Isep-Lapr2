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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.autorizacao.model.UserSession;
import lapr.project.gpsd.controller.ApplicationGPSD;
import lapr.project.gpsd.controller.AssignAvailabilityController;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.RegisterClient;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class ServiceProviderUI implements Initializable {

    private ServiceProviderUI cont;
    private Stage novoPedidoStage;
    ServiceProvider prestador;
    ApplicationGPSD app;
    @FXML
    private Text prestTexto;
    @FXML
    private Button cancel;
    @FXML
    public Text text;

    private Stage newExecutionOrdersStage;
    private Stage completeServiceStage;
    @FXML
    private Button buttonQueryOrdes;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        app = ApplicationGPSD.getInstance();
        prestador = loginInfo();
        prestTexto.setText(String.format("%s, %s", prestador.getNome(), prestador.getEmail()));
    }

    /**
     * Cria uma nova cena
     */
    private void criarJanelaPadrao() {
        try {
            novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("Indicate Standart Availability");
            novoPedidoStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnIndicateAvailability.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            AssignAvailabilityUI ncController = loader.getController();
            ncController.associarController(this);
            novoPedidoStage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Devolve o Prestador de Serv iço que está com sessão iniciada na aplicação
     *
     * @return Prestador de Servicos p.getPrestadorServicoByEmail(email)
     */
    private ServiceProvider loginInfo() {
        UserSession sessao = app.getSessaoAtual();
        String email = sessao.getEmailUtilizador();
        Company e = Utils.getEmpresa();
        RegisterSP p = e.getServiceProvidersRegister();
        return p.getPrestadorServicoByEmail(email);
    }

    @FXML
    private void indicateAvailability(ActionEvent event) {
        criarJanelaPadrao();
        novoPedidoStage.show();
    }

    /**
     * Create query service execution ordes scene
     */
    private void createExecutionOrdersWindow() {
        try {
            newExecutionOrdersStage = new Stage();
            newExecutionOrdersStage.initModality(Modality.APPLICATION_MODAL);

            newExecutionOrdersStage.setTitle("Query Service Execution Orders");
            newExecutionOrdersStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnQueryServiceExecutionOrders.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            QueryServiceExecutionOrdersUI ncController = loader.getController();
            ncController.associateController(this, prestador);
            newExecutionOrdersStage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void createCompleteServiceWindow() {
        try {
            completeServiceStage = new Stage();
            completeServiceStage.initModality(Modality.APPLICATION_MODAL);

            completeServiceStage.setTitle("Complete Services");
            completeServiceStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnCompleteService.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            CompleteServiceUI ncController = loader.getController();
            ncController.associateController(this, prestador);
            completeServiceStage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickQueryOrdersAction(ActionEvent event) {
        createExecutionOrdersWindow();
        newExecutionOrdersStage.show();
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Log in");
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void clickEndService(ActionEvent event) {
        createCompleteServiceWindow();
        completeServiceStage.show();
    }

}
