package lapr.project.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AssociatePostalAddressController;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.ui.console.utils.Utils;

public class AssociatePostalAddressUI implements Initializable {

    private final AssociatePostalAddressController aPAController = new AssociatePostalAddressController();
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnGoBack;
    @FXML
    private Button btnClean;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtLocality;
    ClientUI aController;
    @FXML
    private TextField txtPostalCode1;
    @FXML
    private TextField txtPostalCode2;
    private RegisterPC rpc;
    @FXML
    private Text txtSuccessMessage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Company c;
        c = Utils.getEmpresa();
        rpc = c.getRegistoCP();
    }

    /**
     * Action that allows client to confirm the association of the postal
     * address
     *
     * @param event the action event
     */
    @FXML
    private void clickConfirmAction(ActionEvent event) {
        try {
            PostalCode pc = rpc.getCPById(String.format("%s-%s", txtPostalCode1.getText().trim(), txtPostalCode2.getText().trim()));
            if (pc == null) {
                throw new Exception("Postal Code does not exist!");
            }
            aPAController.newAddress(txtAddress.getText(), pc, txtLocality.getText());
            aPAController.addressRegister();
            txtSuccessMessage.setText("Association Postal Address with Customer successfully");
            cleanAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid data entered");
            alert.showAndWait();
        }
    }

    /**
     * Action that allows the client to go back to the previous window
     *
     * @param event the action event
     */
    @FXML
    private void clickGoBackAction(ActionEvent event) {
        Stage stage = (Stage) btnGoBack.getScene().getWindow();
        stage.close();
    }

    /**
     * Action that allows client to clean the text about the address, the postal
     * code and the locality
     *
     * @param event the action event
     */
    @FXML
    private void clickCleanAction(ActionEvent event) {
        cleanAll();
    }

    /**
     * Clean information about the address, the postal code and the locality
     */
    private void cleanAll() {
        txtAddress.setText("");
        txtPostalCode1.setText("");
        txtPostalCode2.setText("");
        txtLocality.setText("");
    }

    /**
     * Associates the controllers
     *
     * @param aController the ClientUI´s controller
     */
    public void associateController(ClientUI aController) {
        this.aController = aController;
        txtSuccessMessage.setText("");
    }

}
