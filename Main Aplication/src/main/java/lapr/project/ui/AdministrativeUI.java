/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class AdministrativeUI implements Initializable {

    @FXML
    private Button butaoPedido;
    @FXML
    private Button voltarAtras;

    private Stage newStageRequest;
    @FXML
    private Text clienteTexto;
    @FXML
    private Button buttonSpecifyCategory;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clienteTexto.setText(loginInfo());
    }

    /**
     * Mostra a cena criada em criarJanelaPedido()
     */
    @FXML
    private void clicarPedido(ActionEvent event) {
        Utils.getEmpresa().getAffectspt().run();
    }

    /**
     * Volta à cena anterior
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

    private void createSpecifyCategoryWindow() {
        try {
            newStageRequest = new Stage();
            newStageRequest.initModality(Modality.APPLICATION_MODAL);

            newStageRequest.setTitle("Specify Category");
            newStageRequest.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnSpecifyCategory.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            SpecifyCategoryUI ncController = loader.getController();
            ncController.associateController(this);
            newStageRequest.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Devolve uma string com o nome e email do utilizador
     *
     * @param String nome,email
     */
    private String loginInfo() {
        ApplicationGPSD app = ApplicationGPSD.getInstance();
        return String.format("%s", app.getSessaoAtual().getEmailUtilizador());
    }

    @FXML
    private void clicarSpecifyCategory(ActionEvent event) {
        createSpecifyCategoryWindow();
        newStageRequest.show();
    }

    @FXML
    private void clicarNewArea(ActionEvent event) {
        try {
            Stage areaWindow = new Stage();
            areaWindow.initModality(Modality.APPLICATION_MODAL);

            areaWindow.setTitle("Add new Area");
            areaWindow.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnRegisterArea.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            RegisterAreaUI ncController = loader.getController();
            ncController.associateController(this);
            areaWindow.setScene(scene);
            areaWindow.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void clickSpecifyService(ActionEvent event) {
        try {
            Stage areaWindow = new Stage();
            areaWindow.initModality(Modality.APPLICATION_MODAL);

            areaWindow.setTitle("Add new service");
            areaWindow.setResizable(false);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnSpecifyService.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            areaWindow.setScene(scene);
            areaWindow.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
