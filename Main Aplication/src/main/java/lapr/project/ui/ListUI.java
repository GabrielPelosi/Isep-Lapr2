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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class ListUI implements Initializable {

    @FXML
    private Button butaoVoltar;
    @FXML
    private ListView<String> listaPedidos;

    RegisterSPUI controller;
    @FXML
    private Text txtTitle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clicarVoltar(ActionEvent event) {
        Stage stage = (Stage) butaoVoltar.getScene().getWindow();
        stage.close();
    }

    public void listAll(List l, String txt) {
        txtTitle.setText(txt);
        for (int i = 0; i < l.size(); i++) {
            listaPedidos.getItems().add(String.format("%s", l.get(i), l.get(i)));
        }
    }
    
    public void listAll(List l, List l2, String txt) {
        txtTitle.setText(txt);
        for (int i = 0; i < l.size(); i++) {
            listaPedidos.getItems().add(String.format("%s-%s", l.get(i), l2.get(i)));
        }
    }

}
