/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import lapr.project.gpsd.model.DailyAvailability;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class AvailabilitiesUI implements Initializable {

    @FXML
    private Button apagar;
    @FXML
    private Button voltar;
    private AssignAvailabilityUI cController;
    ServiceProvider prest;
    List<DailyAvailability> disponibilidades;
    @FXML
    private ListView<String> lista;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prest=Utils.getEmpresa().getServiceProvidersRegister().getPrestadorServicoByEmail(Utils.getEmpresa().getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
        disponibilidades=prest.getListaDisponibilidades();
        updateList();
    }    
    
    /**
     * Remove a disponibilidade do Prestador de Serviço que se encontra com a log in efetuada selecionada
     * @param event
     */
    @FXML
    private void apagarDisponibilidade(ActionEvent event) {
        String apagar = lista.getSelectionModel().getSelectedItem();
        for(DailyAvailability d: disponibilidades){
            if(d.toString().equals(apagar)){
                disponibilidades.remove(d);
                updateList();  
                break;
                }
                    
           }
       }
        
    /**
     * Faz update à lista de disponibilidades do Prestador de Serviços que se encontra com a log in efetuada
     */
    public void updateList() {
        disponibilidades=prest.getListaDisponibilidades();
        ordenateAvailabilities();
        lista.getItems().removeAll(lista.getItems());
        for(DailyAvailability c: disponibilidades){
            lista.getItems().add(c.toString());
            }
    }
   
    /**
     * Recebe a classe que criou a nova janela
     * @param cController classe que criou a nova janela
     */
    public void associarController(AssignAvailabilityUI cController) {
        this.cController = cController;
    }
    
    /**
     * Volta para a cena anterior
     * @param event
     */
    @FXML
    private void voltar(ActionEvent event) {
        Stage stage = (Stage) voltar.getScene().getWindow();
        stage.close();
    }

    public List<DailyAvailability> ordenateAvailabilities() {

        Comparator<DailyAvailability> criterio1 = new Comparator<DailyAvailability>() {
     
            @Override
            public int compare(DailyAvailability o1, DailyAvailability o2) {
                String d1=o1.toStringValidate();
                String d2=o2.toStringValidate();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;
            }
        };
        Collections.sort(disponibilidades, criterio1);
        return disponibilidades;
    }

    @FXML
    private void clean(ActionEvent event) {
        disponibilidades.removeAll(disponibilidades);
        updateList();  
    }
    
}
