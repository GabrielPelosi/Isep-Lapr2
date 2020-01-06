package lapr.project.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.ServiceOrder;

public class DecidePeriodUI implements Initializable {

    @FXML
    private Text txtPeriodOfService;
    @FXML
    private Text txtSuccessMessage;
    @FXML
    private Button btnAccept;
    @FXML
    private Button btnDoNotAccept;
    @FXML
    private Button btnGoBack;
    private OrdersClientUI aThis;
    private Request sC_Controller;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickAcceptAction(ActionEvent event) {
        try {
            sC_Controller.confirmSP();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText("Request time has been saved and forwared to the service provider.");
            sC_Controller.setAssignedInfo(true);
            alert.showAndWait();
            closeWindow();
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You need to accept or not accept the proposed time!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickDoNotAcceptAction(ActionEvent event) {
        try {
            sC_Controller.rejectSP();
            txtSuccessMessage.setText("Decide on proposed period of service occured with success!\nIn the future, a new period will be assigned!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NOT ACCEPTED");
            alert.setHeaderText(null);
            alert.setContentText("We will assign a new proposed time, thank you for your patience");
            sC_Controller.setTempInfo(false);
            alert.showAndWait();
            closeWindow();
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You need to accept or not accept the proposed time!");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickGoBackAction(ActionEvent event) {
        closeWindow();
    }

    public void associateController(OrdersClientUI aThis, Request pedido) {
        this.aThis = aThis;
        sC_Controller = pedido;
        txtSuccessMessage.setText("");
        String temp = "";
        for(ServiceOrder i: pedido.getServico()){
            temp += i.toString();
            temp += "\n";
        }
        txtPeriodOfService.setText(temp);
    }

    private void closeWindow() {
        Stage stage = (Stage) btnGoBack.getScene().getWindow();
        stage.close();
    }

}
