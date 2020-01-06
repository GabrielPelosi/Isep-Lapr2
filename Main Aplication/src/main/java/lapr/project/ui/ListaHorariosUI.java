/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import lapr.project.gpsd.model.Data;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class ListaHorariosUI implements Initializable {

    private RequestUI pController;

    @FXML
    private DatePicker butaoData;
    @FXML
    private TextField butaoHora;
    @FXML
    private TextField butaoMinuto;
    @FXML
    private Button butaoAdicionar;
    @FXML
    private Button butaoVoltar;
    @FXML
    private Button butaoApagar;
    @FXML
    private ListView<String> listaHorarios;

    private List<String> horarios;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        butaoHora.setText("06");
        butaoMinuto.setText("00");
    }
    
    /**
     * Adiciona um horário válido ao pedido do cliente que está com a log in efetuada
     * @param event
     */
    @FXML
    private void clicarAdicionar(ActionEvent event) {
        try {
            if(butaoData.getValue() == null) throw new Exception("Data inválida");
            
            String data = butaoData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (compararData(data)) {
                throw new Exception("Data inválida");
            }

            int horas = Integer.parseInt(butaoHora.getText().trim());
            int minutos = Integer.parseInt(butaoMinuto.getText().trim());
            if (horas >= 24 || horas < 6 || minutos >= 60 || minutos < 0) {
                throw new Exception("Horas Inválidas");
            }

            String horarioTotal = String.format("%s; %02d:%02d", data, horas, minutos);
            
            boolean nExiste = true;
            for (String h : horarios) {
                if (horarioTotal.equals(h)) {
                    nExiste = false;
                }
            }
            if(nExiste){
                horarios.add(horarioTotal);
                updateList();
            }

        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Horas Inválidas");

            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }
    
    /**
     * Volta à cena anterior
     * @param event
     */
    @FXML
    private void clicarVoltar(ActionEvent event) {
        Stage stage = (Stage) butaoVoltar.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Remove o horário selecionado do pedido do cliente que está com a log in efetuada
     * @param event
     */
    @FXML
    private void clicarApagar(ActionEvent event) {
        String apagar = listaHorarios.getSelectionModel().getSelectedItem();
        for (String h : horarios) {
            if (h.equals(apagar)) {
                horarios.remove(h);
                break;
            }
        }
        updateList();
    }

    /**
     * Recebe a classe que criou a nova janela
     * @param pController classe que criou a nova janela
     */

    public void associarController(RequestUI pController) {
        this.pController = pController;
        init();
    }
    
    /**
     * Instancia os horarios dos pedidos do cliente e atualiza a lista de horarios do pedido
     */
    private void init() {
        horarios = pController.getHorarios();
        updateList();
    }
    
    /**
     * Atualiza a lista de horarios do pedido
     */
    private void updateList() {
        listaHorarios.getItems().removeAll(listaHorarios.getItems());
        listaHorarios.getItems().add("Data; horas");
        for (String h : horarios) {
            listaHorarios.getItems().add(h);
        }
    }
    
    /**
     * 
     * @param pedidoData
     * @param pedidoHoras
     * @param pedidoMinutos
     */
    private void checkInfo(String pedidoData, int pedidoHoras, int pedidoMinutos) throws Exception {
        pedidoData = butaoData.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (compararData(pedidoData)) {
            throw new Exception("Data inválida");
        }

        pedidoHoras = Integer.parseInt(butaoHora.getText().trim());
        pedidoMinutos = Integer.parseInt(butaoMinuto.getText().trim());
        if (pedidoHoras >= 24 || pedidoHoras < 0 || pedidoMinutos >= 60 || pedidoMinutos < 0) {
            throw new Exception("Horas Inválidas");
        }
    }
    
    /**
     * Verifica se a dataCalendario selecionada é ou não uma data anterior à de hoje
     * @param dataCalendario
     * @return true, se a dataCalendario for uma data anterior à de hoje
     * @return false, se a dataCalendario não for uma data anterior à de hoje
     */
    private boolean compararData(String dataCalendario) {
        Date dateNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dataAgora = formatter.format(dateNow);

        String[] dataC = dataCalendario.split("-");
        String[] dataA = dataAgora.split("-");

        Data dc = new Data(Integer.parseInt(dataC[0].trim()), Integer.parseInt(dataC[1].trim()), Integer.parseInt(dataC[2].trim()));
        Data da = new Data(Integer.parseInt(dataA[0].trim()), Integer.parseInt(dataA[1].trim()), Integer.parseInt(dataA[2].trim()));
        
        if(dc.determinarDiaDaSemana().trim().equals("Domingo")) return true;
        
        return da.isMaior(dc);
    }
}
