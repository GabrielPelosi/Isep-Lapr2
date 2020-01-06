/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterSPController;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.RegisterCategory;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 * FXML Controller class
 *
 * @author rickropes
 */
public class RegisterSPUI implements Initializable {

    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldNIF;
    @FXML
    private TextField fieldPN;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldCity;
    @FXML
    private TextField fieldAddress;
    @FXML
    private TextField fieldPC1;
    @FXML
    private TextField fieldPC2;
    @FXML
    private TextField fieldProfessionalHab;
    @FXML
    private ComboBox<Category> comboCategory;
    @FXML
    private TextField fieldAcademicHab;

    private RegisterSPController controller = new RegisterSPController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegisterCategory cat = Utils.getEmpresa().getRegistoCategoria();
        List<Category> listcat = cat.getCategorias();
        for (Category c : listcat) {
            comboCategory.getItems().add(c);
        }
        comboCategory.getSelectionModel().selectFirst();
    }

    @FXML
    private void pressConfirm(ActionEvent event) {
        String name = fieldName.getText();
        String nif = fieldNIF.getText();
        String pn = fieldPN.getText();
        String email = fieldEmail.getText();
        String ah = fieldAcademicHab.getText();
        String ph = fieldProfessionalHab.getText();

        String city = fieldCity.getText();
        String pc1 = fieldPC1.getText();
        String pc2 = fieldPC2.getText();
        String address = fieldAddress.getText();
        try {
            if(nif.length() != 9) throw new Exception("INVALID NIF");
            controller.addAddress(city, pc1, pc2, address);
            if (controller.getCat().size() > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Information? ");
                alert.setHeaderText("Is all your information ccrrect?");
                alert.setContentText(String.format("Nome: %s%nNIF: %s%nPhone Number: %s%nEmail: %s%nAddresses: %s%nAcademic Hab.: %s%nProfessional Hab.: %s%nCategories: %s%n",
                        name, nif, pn, email, controller.getAdd(), ah, ph, controller.getCat()));

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    controller.newApplication(name, nif, pn, email, ah, ph);
                    goBack();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }

    }

    @FXML
    private void pressCancel(ActionEvent event) {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /*
    private void pressAdd(ActionEvent event) {
        String city = fieldCity.getText();
        String pc1 = fieldPC1.getText();
        String pc2 = fieldPC2.getText();
        String address = fieldAddress.getText();
        try {
            controller.addAddress(city, pc1, pc2, address);
            fieldCity.setText("");
            fieldPC1.setText("");
            fieldPC2.setText("");
            fieldAddress.setText("");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void pressCheck(ActionEvent event) {
        try {
            Stage novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("List");
            novoPedidoStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnListRegisterSP.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            ListUI ncController = loader.getController();
            novoPedidoStage.setScene(scene);
            ncController.listAll(controller.getAdd(), "Selected Addresses");
            novoPedidoStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }*/

    @FXML
    private void pressAddCat(ActionEvent event) {
        try {
            controller.addCategory(comboCategory.getValue());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void pressCheckCat(ActionEvent event) {
        try {
            Stage novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("List");
            novoPedidoStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnList.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            ListUI ncController = loader.getController();
            novoPedidoStage.setScene(scene);
            ncController.listAll(controller.getCat(), "Selected Categories");
            novoPedidoStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void goBack() {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) fieldName.getScene().getWindow();
            stage.setTitle("Log in");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

}
