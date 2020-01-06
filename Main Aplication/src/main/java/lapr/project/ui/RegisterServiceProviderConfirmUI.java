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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterServiceProviderController;
import lapr.project.gpsd.model.Area;
import lapr.project.gpsd.model.ServiceProvider;

/**
 * FXML Controller class
 *
 * @author Pelosi
 */
public class RegisterServiceProviderConfirmUI implements Initializable {

	private RegisterServiceProviderUI aController;
	private RegisterServiceProviderController rsc;
	ServiceProvider ps;

	@FXML
	private Button bConfirm;
	@FXML
	private Button bCancel;
	@FXML
	private Button bReturn;
	@FXML
	private TextArea infoSP;

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
	 * validate and register the service provider in the system
	 * @param event 
	 */
	@FXML
	private void clickConfirm(ActionEvent event) {
		rsc.registerServiceProvider();

		Alert a = createAlert(Alert.AlertType.INFORMATION, "Success", "The Service Provider has been registrated in the system");
		a.show();
		Stage stage = (Stage) bCancel.getScene().getWindow();
		stage.close();
		aController.exit();
	}

	/**
	 * cancel the operation and open the HRO window
	 * @param event 
	 */
	@FXML
	private void clickCancel(ActionEvent event) {
		Stage stage = (Stage) bCancel.getScene().getWindow();
		stage.close();
		aController.exit();
	}

	/**
	 * take the HRO one window back 
	 * @param event 
	 */
	@FXML
	private void clickReturn(ActionEvent event) {
		Stage stage = (Stage) bCancel.getScene().getWindow();
		stage.close();
	}

	/**
	 *Associate the controllers to this class
	 * @param aController RegisterServiceProviderUI instance
	 * @param rsc RegisterServiceProviderController instance
	 */
	public void associateController(RegisterServiceProviderUI aController, RegisterServiceProviderController rsc) {
		this.aController = aController;
		this.rsc = rsc;
	}

	/**
	 * create an Alert to be shown
	 * @param tipoAlerta alert type
	 * @param cabecalho header of the alert
	 * @param mensagem content of the alert
	 * @return 
	 */
	private Alert createAlert(Alert.AlertType tipoAlerta, String cabecalho, String mensagem) {
		Alert alerta = new Alert(tipoAlerta);
		alerta.setTitle("Aplicação");
		alerta.setHeaderText(cabecalho);
		alerta.setContentText(mensagem);
		return alerta;
	}

	/**
	 * Get the information of the Service Provider at the Controller class and set in the interface
	 *
	 * @param a Service Provider with his information
	 */
	public void sendInfo(ServiceProvider a) {
		this.ps = a;
		setInfoSP();
	}

	/**
	 * Method that show the Service Provider information in the interface
	 *
	 */
	public void setInfoSP() {
		List<String> list = new ArrayList<>();
		for (Area a : ps.getListGeographicAreas()) {
			list.add(" [ " + a.getStringArea() + " ] ");
		}
		infoSP.setText("Typing Numer: " + ps.getTypingNumber() + "\n" + "Name: " + ps.getNome() + "\n" + "Abreviated Name: " + ps.getAbvName() + "\n" + "Email: " + ps.getEmail()
				+ "\n" + "Postal Address: " + ps.getPostalAddressString() + "\n" + "Categories: " + ps.getListCategory().toString() + "\n" + "Geographic Areas: " + list.toString());
	}

}
