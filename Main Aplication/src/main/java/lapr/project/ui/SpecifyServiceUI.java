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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.SpecifyServiceController;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.RegisterCategory;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author Pelosi
 */
public class SpecifyServiceUI implements Initializable {

    @FXML
    private ComboBox<Category> comboCat;
    @FXML
    private ComboBox<ServiceType> comboType;
    @FXML
    private TextField textID;
    @FXML
    private TextField textShort;
    @FXML
    private TextArea textComplete;
    @FXML
    private TextField textCost;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonConfirm;
    @FXML
    private HBox hboxTime;
    @FXML
    private Button butaoUp;
    @FXML
    private TextField fieldHoras;
    @FXML
    private Button butaoDown;

    private SpecifyServiceController controller = new SpecifyServiceController();
    private RegisterCategory rc = Utils.getEmpresa().getRegistoCategoria();
    int Fminuto;
    int Fhora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<ServiceType> list = controller.getServiceTypes();
        for (ServiceType i : list) {
            comboType.getItems().add(i);
        }
        comboType.getSelectionModel().selectFirst();

        List<Category> listCat = rc.getCategorias();
        for (Category i : listCat) {
            comboCat.getItems().add(i);
        }
        comboCat.getSelectionModel().selectFirst();
    }

    @FXML
    private void clickConfirm(ActionEvent event) {
        try {
            
            if (textID.getText().equals("") || textShort.getText().equals("") || textComplete.getText().equals("") || textCost.getText().equals("") || comboCat.getSelectionModel().getSelectedItem() == null) {
                throw new Exception("All field must be filled.");
            }
            
            String id = textID.getText();
            ServiceType type = comboType.getValue();
            String descShort = textShort.getText();
            String descLong = textComplete.getText();
            double price = Double.parseDouble(textCost.getText().trim().replace(',', '.'));
            Category cat = comboCat.getSelectionModel().getSelectedItem();
            Time time;
            if (hboxTime.isVisible()) {
                time = new Time(fieldHoras.getText());
            } else {
                time = null;
            }
            
            controller.newService(id, descShort, descLong, price, cat, time, type);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void clickExit(ActionEvent event) {
        Stage stage = (Stage) buttonExit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickType(ActionEvent event) {
        if (comboType.getValue().toString().equals("Fixed")) {
            hboxTime.setVisible(true);
        } else {
            hboxTime.setVisible(false);
        }
    }

    @FXML
    private void clicarUp(ActionEvent event) {
        Fminuto += 30;
        if (Fminuto >= 60) {
            Fminuto = 0;
            Fhora += 1;
        }

        fieldHoras.setText(String.format("%02d:%02d", Fhora, Fminuto));
    }

    @FXML
    private void clicarDown(ActionEvent event) {
        if (Fhora != 0 || Fminuto == 30) {
            Fminuto -= 30;
            if (Fminuto < 0) {
                Fminuto = 30;
                Fhora -= 1;
            }
        }

        fieldHoras.setText(String.format("%02d:%02d", Fhora, Fminuto));
    }
}
