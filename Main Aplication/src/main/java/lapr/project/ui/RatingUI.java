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
import lapr.project.gpsd.controller.RatingController;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.RatingRegister;
import lapr.project.gpsd.model.ServiceProvider;

public class RatingUI implements Initializable {

    /**
     * The button for go back in stages
     */
    @FXML
    private Button btnGoBack;

    /**
     * The text fiels for give a rate to a service provider
     */
    @FXML
    private TextField txtRating;

    /**
     * The controller of Rating
     */
    private RatingController sC_Controller = new RatingController();

    /**
     * The controller associated to stage of client´s orders
     */
    private OrdersClientForRatingUI aThis;

    /**
     * The button to confirm the rate given to the service provider
     */
    @FXML
    private Button btnConfirm;

    /**
     * The button to clean the rate
     */
    @FXML
    private Button btnClean;

    /**
     * The text that presents to the client the service provider to rate
     */
    @FXML
    private Text txtServiceProvider;
    private ServiceProvider serviceProvider;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Action that allows client to confirm the rate given to service provider
     *
     * @param event the action event
     */
    @FXML
    private void clickConfirmAction(ActionEvent event) {
        try {
            sC_Controller.newRating(Integer.parseInt(txtRating.getText()));
            if (Integer.parseInt(txtRating.getText()) > 5 || Integer.parseInt(txtRating.getText()) < 0) {
                throw new NumberFormatException();
            }
            sC_Controller.setServiceProvider(getServiceProvider());
            RatingRegister rr = serviceProvider.getServiceProviderScoreList();
            Rating rat = rr.newRating(Integer.parseInt(txtRating.getText()));
            rr.addRating(rat);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText("Rating Service Providers occured with success!");
            alert.showAndWait();
            aThis.setDeleteCurrent(true);
            closeWindow();
            cleanAll();
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The rating has to be an integer and has to be between 0 and 5!");
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
    
    private void closeWindow() {
        Stage stage = (Stage) btnGoBack.getScene().getWindow();
        stage.close();
    }

    /**
     * Action that allows client to clean the text about the rate and the
     * succcess message
     *
     * @param event the action event
     */
    @FXML
    private void clickCleanAction(ActionEvent event) {
        cleanAll();
    }

    /**
     * Clean information about the rate and the succcess message
     */
    private void cleanAll() {
        txtRating.setText("");
    }

    /**
     * Association between controllers
     *
     * @param aThis the OrdersClientForRatingUI´s controller
     * @param serviceProvider the service provider
     */
    public void associateController(OrdersClientForRatingUI aThis, ServiceProvider serviceProvider) {
        this.aThis = aThis;
        this.serviceProvider = serviceProvider;
        txtServiceProvider.setText(serviceProvider.toString());
    }

    /**
     * Return the service provider
     * @return the service provider
     */
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
    
    

}
