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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.PostalAddress;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class AddressUI implements Initializable {

    @FXML
    private Button butaoConfirmar;
    @FXML
    private TextField fieldCP;
    @FXML
    private TextField fieldMorada;
    
    private RequestUI pController;
    @FXML
    private TextField fieldLocal;
    
    Client c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Recebe a classe que criou a nova janela
     * @param cController classe que criou a nova janela
     */
    public void associarController(RequestUI pController) {
        this.pController = pController;
        c = pController.getCliente();
    }
    
    /**
     * Cria e adiciona um novo Endereço Postal ao Cliente que tem sessão iniciada na Aplicação
     * @param event
     */
    @FXML
    private void clicarConfirmar(ActionEvent event) {
        if(fieldCP.getText().trim() != null && fieldMorada.getText().trim() != null && fieldLocal.getText().trim() != null)
        {
            PostalAddress ep = new PostalAddress(fieldMorada.getText().trim(),fieldCP.getText().trim(),fieldLocal.getText().trim());
            c.addPostalAddress(ep);
            pController.setChanged(true);
            Stage stage = (Stage) butaoConfirmar.getScene().getWindow();
            stage.close();
            
        }else{
            System.out.println("NO");
        }
    }
    
}
