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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterClientController;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class PostalAddressesUI implements Initializable {

    private RegisterAsCustomerUI cController;
    private RegisterClientController cli;
    @FXML
    private TextField address;
    @FXML
    private TextField locality;
    @FXML
    private TextField postal;
    @FXML
    private TextField code;
    @FXML
    private ListView<String> postalAddresses;
    @FXML
    private Button finish;
    private RegisterPC rpc=new RegisterPC();
    private Company ap;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ap = Utils.getEmpresa();
        rpc = ap.getRegistoCP();
    }
    
    @FXML
    private void delete(ActionEvent event) {
        String apagar = postalAddresses.getSelectionModel().getSelectedItem();
        for(PostalAddress d: cli.getClient().getMoradas()){
            if(d.toString().equals(apagar)){
                if(cli.getClient().getMoradas().size()>1){
                cli.getClient().getMoradas().remove(d);
                updateList();    
                }else{
                 Alert alert1 = new Alert (Alert.AlertType.INFORMATION);
                 alert1.setTitle("Error");
                 alert1.setHeaderText(null);
                 alert1.setContentText("At least one address!");
                 alert1.showAndWait();   
                }
                  
                break;
            }
        }
    }

    
    @FXML
    private void add(ActionEvent event) {
        try {
           
           if((address.getText().trim().equals("")) || (locality.getText().trim().equals("")) || (postal.getText().trim().equals("")) || (code.getText().trim().equals(""))){
               throw new Exception("All data must be completed!");
           }
               
           if (!postal.getText().matches("[0-9]+") || postal.getText().length()!=4 || !code.getText().matches("[0-9]+") || code.getText().length()!=3){
               throw new Exception("Invalid Postal Code!");
           }
           
           PostalCode pc = rpc.getCPById(String.format("%s-%s", postal.getText().trim() , code.getText().trim()));
            if (pc == null) {
                throw new Exception("Postal Code does not exist!");
            }
           
        
           String adr=postal.getText()+"-"+code.getText();
           for(PostalAddress a:cli.getClient().getMoradas()){
              if(a.toString().equalsIgnoreCase(address.getText().trim()+" - "+adr.trim()+" - "+locality.getText().trim())) {
              throw new Exception("Postal Address already exist");   
              }
           }
           
           if (cli.addPostalAddress(address.getText(), adr, locality.getText())){
            updateList();
            Alert alert1 = new Alert (Alert.AlertType.INFORMATION);
            alert1.setTitle("Successful");
            alert1.setHeaderText(null);
            alert1.setContentText("New address added to customer!");
            alert1.showAndWait();
           }else throw new Exception("Error!");
        
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
    private void clean(ActionEvent event) {
        address.setText("");
        locality.setText("");
        postal.setText("");
        code.setText("");
    }

    @FXML
    private void finish(ActionEvent event) {
        try{
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Successful");
            alert1.setHeaderText(null);
            alert1.setContentText("Customer created!");
            alert1.showAndWait();
            Stage stage = (Stage) finish.getScene().getWindow();
            stage.close();
        }catch (Exception exc){
            System.out.println(exc.getMessage());
        }
    }
    
    public void associarController(RegisterAsCustomerUI cController,RegisterClientController rcc) {
        this.cController = cController;
        this.cli=rcc;
    }
    
    public void updateList(){
    postalAddresses.getItems().removeAll(postalAddresses.getItems());
    
        for(PostalAddress d:cli.getClient().getMoradas()){
            postalAddresses.getItems().add(d.toString());
        } 
          
    }
    
        
    

    
    
}
