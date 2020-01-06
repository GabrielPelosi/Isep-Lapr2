package lapr.project.ui;

import java.io.IOException;
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
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.RegisterClient;
import lapr.project.gpsd.ui.console.utils.Utils;

public class ClientUI implements Initializable {

    @FXML
    private Button voltarAtras;
    @FXML
    private Text clienteTexto;
    Client cliente;
    ApplicationGPSD app;
    @FXML
    private Button butaoPedido;
    private Stage associatePostalAddressStage;
    private Stage associateRatingStage;
    private Stage associateDecideStage;
    private Stage novoPedidoStage;
    @FXML
    private Text textSucesso;
    @FXML
    private Button btnAssociatePostalAddress;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textSucesso.setText("");
        app = ApplicationGPSD.getInstance();
        cliente = loginInfo();
        clienteTexto.setText(String.format("%s, %s", cliente.getNome(), cliente.getEmail()));

    }

    /**
     * Returns the client who made the log in
     *
     * @return client who made the log in
     */
    public Client getCliente() {
        return cliente;
    }

    /**
     * Returns ApplicationGPSD
     *
     * @return ApplicationGPSD
     */
    public ApplicationGPSD getApp() {
        return app;
    }

    /**
     * If worked=true then the request was successful, if worked=false there
     * were mistakes in the request
     *
     * @param worked
     */
    public void setSucesso(boolean worked) {
        if (worked) {
            textSucesso.setText("The request was registered with sucess");
        } else {
            textSucesso.setText("A problem occurred, please try again.");
        }
    }

    /**
     * Creates a new scene
     */
    private void criarJanelaPedido() {
        try {
            novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("Novo Pedido");
            novoPedidoStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnPedido.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            RequestUI ncController = loader.getController();
            ncController.associarController(this);
            novoPedidoStage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Creates a new scene
     */
    private void createAssociatePostalAddressWindow() {
        try {
            associatePostalAddressStage = new Stage();
            associatePostalAddressStage.initModality(Modality.APPLICATION_MODAL);

            associatePostalAddressStage.setTitle("Associate Postal Address with Customer");
            associatePostalAddressStage.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnAssociatePostalAddress.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            AssociatePostalAddressUI ncController = loader.getController();
            ncController.associateController(this);
            associatePostalAddressStage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Creates a new scene
     */
    private void createRatingWindow() {
        try {
            associateRatingStage = new Stage();
            associateRatingStage.initModality(Modality.APPLICATION_MODAL);
            associateRatingStage.setTitle("Orders associated with the customer");
            associateRatingStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnRequestsClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            OrdersClientForRatingUI ncController = loader.getController();
            ncController.associateController(this);
            associateRatingStage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Returns to the previous scene
     *
     * @param event
     */
    @FXML
    private void clicarVoltarAtras(ActionEvent event) {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Shows the window created in criarJanelaPedido()
     *
     * @param event
     */
    @FXML
    private void clicarPedido(ActionEvent event) {
        criarJanelaPedido();
        novoPedidoStage.show();
    }

    /**
     * Returns the client who made the log in
     *
     * @return client who made the log in
     */
    private Client loginInfo() {
        UserSession sessao = app.getSessaoAtual();
        String email = sessao.getEmailUtilizador();
        Company e = Utils.getEmpresa();
        RegisterClient c = e.getRegistoCliente();
        return c.getClienteByEmail(email);
    }

    /**
     * Shows the window created in createAssociatePostalAddressWindow()
     *
     * @param event
     */
    @FXML
    private void clickAssociatePostalAddressAction(ActionEvent event) {
        createAssociatePostalAddressWindow();
        associatePostalAddressStage.show();
    }

    /**
     * Shows the window created in createRatingWindow()
     *
     * @param event
     */
    @FXML
    private void clickRatingAction(ActionEvent event) {
        createRatingWindow();
        associateRatingStage.show();
    }
    
    /**
     * Creates a new scene
     */
    private void createDecideWindow() {
        try {
            associateDecideStage = new Stage();
            associateDecideStage.initModality(Modality.APPLICATION_MODAL);
            associateDecideStage.setTitle("Decide on proposed period of service");
            associateDecideStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnClientRequest.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            OrdersClientUI ncController = loader.getController();
            ncController.associateController(this);
            associateDecideStage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Shows the window created in createDecideWindow()
     *
     * @param event
     */
    @FXML
    private void clickDecideAction(ActionEvent event) {
        createDecideWindow();
        associateDecideStage.show();
    }

}
