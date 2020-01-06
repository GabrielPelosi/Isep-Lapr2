/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.ApplicationGPSD;

/**
 * FXML Controller class
 *
 * @author Pelosi
 */
public class HumanResourcesOfficerUI implements Initializable {

	private Stage newRegisterServiceProvider;
	@FXML
	private Label HrcInfo;
	@FXML
	private Button bLogOut;
	@FXML
	private Button buttonEvaluate;

	private Stage newEvaluateServiceProvider;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		HrcInfo.setText(HumanResourcesOfficerInfo());
	}

	/**
	 * create the window to register a service provider and set the application nifs in the new window
	 */
	public void createRegisterServiceProviderWindow() {
		try {
			newRegisterServiceProvider = new Stage();
			newRegisterServiceProvider.initModality(Modality.APPLICATION_MODAL);
			newRegisterServiceProvider.setTitle(" ");
			newRegisterServiceProvider.setResizable(false);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnRegisterServiceProvider.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			RegisterServiceProviderUI adtController = loader.getController();
			adtController.associateController(this);

			newRegisterServiceProvider.setScene(scene);
			adtController.setListNif();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

	/**
	 *show the window to register a service provider
	 * @param event
	 */
	@FXML
	private void clickRegisterServiceProvider(ActionEvent event) {
		createRegisterServiceProviderWindow();
		newRegisterServiceProvider.show();
	}

	/**
	 *get the information of the HRO in the current session
	 * @return String with the HRO information
	 */
	public String HumanResourcesOfficerInfo() {
		ApplicationGPSD app = ApplicationGPSD.getInstance();
		return String.format("%s", app.getSessaoAtual().getEmailUtilizador());
	}

	/**
	 *back to the first window of the program
	 * @param event
	 */
	@FXML
	private void clickLogOut(ActionEvent event) {
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

	/**
	 *Show the window to evaluate service providers
	 * @param event
	 */
	@FXML
	private void clickEvaluateAction(ActionEvent event) {
		createEvaluateServiceProviderWindow();
		newEvaluateServiceProvider.show();
	}

	/**
	 * create the window to evaluate a service provider
	 */
	public void createEvaluateServiceProviderWindow() {
		try {
			newEvaluateServiceProvider = new Stage();
			newEvaluateServiceProvider.initModality(Modality.APPLICATION_MODAL);
			newEvaluateServiceProvider.setTitle("Evaluate Service Provider");
			newEvaluateServiceProvider.setResizable(false);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnEvaluateServiceProviders.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			EvaluateServiceProvidersUI adtController = loader.getController();
			adtController.associateController(this);

			newEvaluateServiceProvider.setScene(scene);
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}

	}

}
