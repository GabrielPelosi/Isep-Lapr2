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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lapr.project.controller.AnalyzeClientTimelineController;
import lapr.project.controller.AnalyzeExecutionOrdersController;
import lapr.project.model.ApplicationServiceProvider;
import lapr.project.model.Company;
import lapr.project.model.ExecutionOrder;
import lapr.project.model.RegisterExecutionOrders;

/**
 *
 * @author Utilizador
 */
public class AnalyzeClientTimelineUI implements Initializable {

    @FXML
    private ListView<String> list;
    private MenuUI aController;
    @FXML
    private TextArea name;
    private List<ExecutionOrder> lis;
    private AnalyzeClientTimelineController analyze;
    @FXML
    private Button back;
    private RegisterExecutionOrders r;
    private Company c;
    private AnalyzeExecutionOrdersController e;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lis=new ArrayList<>();
        analyze=new AnalyzeClientTimelineController();
        c=ApplicationServiceProvider.getInstance().getEmpresa();
        r=c.getRegisterExecutionOrders();
    }
    
    
    @FXML
    private void find(ActionEvent event) {
        try {
            String nameC=name.getText().trim();
//            lis=r.loadBinaryFile();
            e= new AnalyzeExecutionOrdersController();
            lis=e.analizeDate();
            if(verificate(lis,nameC)){
                for(ExecutionOrder a: lis){
                    if(a.getName().equals(nameC)){
                        analyze.AnalyzeOrders(lis, nameC);
                        lis=analyze.analyzeClient();
                        updateList();
                    }
                }
            }else{
                throw new Exception("Name not found");
            }
            

        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }
    
    public boolean verificate(List<ExecutionOrder> lista, String nome){
       for(ExecutionOrder a: lista){
                if(a.getName().equals(nome)){
                    return true;
                }
            } 
    return false;}
    
    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }
    
    public void associateController(MenuUI aController) {
        this.aController = aController;
    }

     public void updateList() {
        list.getItems().removeAll(list.getItems());
        for(ExecutionOrder c: lis){
            list.getItems().add(c.toString());
        }
    }
    

    
    
}
