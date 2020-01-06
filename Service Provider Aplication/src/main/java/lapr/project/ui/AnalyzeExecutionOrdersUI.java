/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lapr.project.controller.AnalyzeExecutionOrdersController;
import lapr.project.model.ExecutionOrder;

/**
 *
 * @author Utilizador
 */
public class AnalyzeExecutionOrdersUI implements Initializable {

    @FXML
    private ListView<String> list;
    private AnalyzeExecutionOrdersController e;
    @FXML
    private Button back;
    @FXML
    private ComboBox<String> combo;
    private MenuUI aController;
    private List<ExecutionOrder> listExecutionOrders;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
           combo.getItems().add("Name");
           combo.getItems().add("Distance");
           combo.getItems().add("Category");
           combo.getItems().add("Date");
           combo.getItems().add("Service");
           combo.getItems().add("Address");
           combo.getItems().add("Ordenate All");
    }
    
    
    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void vomboB(ActionEvent event) {
        try{
            e= new AnalyzeExecutionOrdersController();
            if(combo.getValue()!=null){
                if(combo.getValue().equals("Name")) Name();
                else if (combo.getValue().equals("Distance")) Distance() ;
                else if (combo.getValue().equals("Date")) Date();
                else if (combo.getValue().equals("Category")) Category();
                else if (combo.getValue().equals("Service")) Service();
                else if (combo.getValue().equals("Address")) Address(); 
                else if (combo.getValue().equals("Ordenate All")) All();
            } else System.out.println("gay");
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Firs Import File!");
            alert.showAndWait();
        }    
            
    }
    
    public void associateController(MenuUI aController) {
        this.aController = aController;
    }
    
    
     /**
     * Faz update à lista de disponibilidades do Prestador de Serviços que se encontra com a log in efetuada
     */
    public void updateList() {
        list.getItems().removeAll(list.getItems());
        for(ExecutionOrder c: listExecutionOrders){
            list.getItems().add(c.toString());
        }
    }
    
    public void Name(){
        listExecutionOrders=e.analizeName();
        updateList();
    }
    public void Date(){
        listExecutionOrders=e.analizeDate();
        updateList();
    }
    
    public void Distance(){
        listExecutionOrders=e.analizeDistance();
        updateList();
    }
    
    public void Category(){
        listExecutionOrders=e.analizeCategory();
        updateList();
    }
    
    public void Service(){
        listExecutionOrders=e.analizeService();
        updateList();
    }
    
    public void Address(){
        listExecutionOrders=e.analizeAddress();
        updateList();
    }
    
    public void All(){
        listExecutionOrders=e.analizeAll();
        updateList();
    }
    
}
