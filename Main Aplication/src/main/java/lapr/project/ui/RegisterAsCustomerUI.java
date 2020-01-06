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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterClientController;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterClient;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class RegisterAsCustomerUI implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField nif;
    @FXML
    private TextField telephone;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button RegisterAsCustomer;
    @FXML
    private Button cancel;
    @FXML
    private TextField address;
    @FXML
    private TextField locality;
    @FXML
    private TextField postal;
    @FXML
    private TextField code;
    private Company ap;
    private RegisterClientController rcc = new RegisterClientController();
    private PostalAddressesUI apa;
    private Stage novoPedidoStage;
    private RegisterClient customers;
    private RegisterPC rpc=new RegisterPC();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap = Utils.getEmpresa();
        rpc = ap.getRegistoCP();
    }

    @FXML
    private void registerAsCustomer(ActionEvent event) {
        try {

            if ((name.getText().trim().equals("")) || (nif.getText().trim().equals("")) || (telephone.getText().trim().equals("")) || (email.getText().trim().equals("")) || (password.getText().trim().equals("")) || (address.getText().trim().equals("")) || (locality.getText().trim().equals("")) || (postal.getText().trim().equals("")) || (code.getText().trim().equals(""))) {
                throw new Exception("All data must be completed!");
            }

            if (!nif.getText().matches("[0-9]+") || nif.getText().length() != 9) {
                throw new Exception("Invalid NIF!");
            }

            if (!telephone.getText().matches("[0-9]+") || telephone.getText().length() != 9) {
                throw new Exception("Invalid Telephone number!");
            }

            if (!postal.getText().matches("[0-9]+") || postal.getText().length() != 4 || !code.getText().matches("[0-9]+") || code.getText().length() != 3) {
                throw new Exception("Invalid Postal Code!");
            }
            
            customers=ap.getRegistoCliente();
            for(Client c: customers.getClients() ){
                if((c.getNIF().equals(nif.getText())) || (c.getEmail().equals(email.getText()))){
                throw new Exception("Client already exists!");    
                }
            }            
            
            PostalCode pc = rpc.getCPById(String.format("%s-%s", postal.getText().trim() , code.getText().trim()));
            if (pc == null) {
                throw new Exception("Postal Code does not exist!");
            }
            
            String adr = postal.getText() + "-" + code.getText();
            if (rcc.newClient(name.getText(), nif.getText(), telephone.getText(), email.getText(), password.getText(), address.getText(), adr, locality.getText())) {
                if (rcc.registerClient()) {
                    criarJanelaPedido();
                    novoPedidoStage.showAndWait();
                    novoPedidoStage.close();
                    goBack();
                }
            } else {
                throw new Exception("Error!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            Stage stage = (Stage) cancel.getScene().getWindow();
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void clean(ActionEvent event) {
        name.setText("");
        nif.setText("");
        telephone.setText("");
        password.setText("");
        email.setText("");
        name.setText("");
        address.setText("");
        locality.setText("");
        postal.setText("");
        code.setText("");
    }

    private void criarJanelaPedido() {
        try {
            novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("Add More Postals Adresses");
            novoPedidoStage.setResizable(false);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnPostalAddresses.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            PostalAddressesUI ncController = loader.getController();
            ncController.associarController(this, rcc);
            novoPedidoStage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void goBack(){
      try {
            Stage stage = (Stage) cancel.getScene().getWindow();
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }  
    }

}
