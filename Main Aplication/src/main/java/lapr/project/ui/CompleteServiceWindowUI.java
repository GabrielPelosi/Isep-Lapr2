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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lapr.project.gpsd.model.RegisterRequest;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.RequestCompleted;
import lapr.project.gpsd.model.ServiceOrder;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class CompleteServiceWindowUI implements Initializable {

    @FXML
    private TextArea areaDescription;
    @FXML
    private CheckBox checkYes;
    private Request request;
    private RegisterRequest rp;
    @FXML
    private VBox vboxText;
    @FXML
    private TextArea areaDescription1;
    private ServiceOrder so;
    @FXML
    private HBox hboxExpansiv;
    @FXML
    private TextField aditionalTIme;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vboxText.setVisible(false);
        vboxText.setManaged(false);
        hboxExpansiv.setVisible(false);
    }

    @FXML
    private void clickYes(ActionEvent event) {
        if (checkYes.isSelected()) {
            vboxText.setVisible(true);
            vboxText.setManaged(true);
            if (so.getService().getType().equals("Expandible")) {
                hboxExpansiv.setVisible(true);
            }
        } else {
            vboxText.setVisible(false);
            vboxText.setManaged(false);
        }

    }

    @FXML
    private void clickBack(ActionEvent event) {
        Stage stage = (Stage) checkYes.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickConfirm(ActionEvent event) {
        try {
            if (checkYes.isSelected()) {
                request.setDone(so, areaDescription.getText().trim(), areaDescription1.getText().trim());
                if (so.getService().getType().equals("Expandible")) {
                    so.addValue(Double.parseDouble(aditionalTIme.getText()));
                }

            } else {
                request.setDone(so);
            }
            Stage stage = (Stage) checkYes.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    void getInformation(Request request, ServiceOrder so) {
        this.request = request;
        this.so = so;
        rp = Utils.getEmpresa().getRegistoPedido();
    }

}
