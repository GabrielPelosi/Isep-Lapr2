/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterServiceProviderController;
import lapr.project.gpsd.model.Application;
import lapr.project.gpsd.model.Area;
import lapr.project.gpsd.model.Category;

/**
 * FXML Controller class
 *
 * @author Pelosi
 */
public class RegisterServiceProviderUI implements Initializable {

	private HumanResourcesOfficerUI aController;
	private RegisterServiceProviderController rspC;
	private Stage newRegisterServiceProviderConfirm;
	private RegisterServiceProviderConfirmUI registerConfirm;
	private Stage newSceneMoreCatAreas;

	@FXML
	private TextField textNif;
	@FXML
	private TextField textTyping;
	@FXML
	private TextField textFullName;
	@FXML
	private TextField textAbvName;
	@FXML
	private TextField textInstEmail;
	@FXML
	private Button loadAll;
	@FXML
	private ComboBox<Category> comboCat;
	@FXML
	private Button buttNIF;
	@FXML
	private Button buttonArea;
	@FXML
	private Button ButtonCate;
	@FXML
	private Button bConfirm;
	private ComboBox<Area> comboArea;
	@FXML
	private Button buttonExit;
	@FXML
	private ListView<Category> listViewCat;
	@FXML
	private ListView<Area> listViewArea;
	@FXML
	private Button bDeletCat;
	@FXML
	private Button bDeletArea;
	@FXML
	private Text textWarningMoreCatArea;
	@FXML
	private Button bChangeInfo;
	@FXML
	private TextField textAdress;
	@FXML
	private TextField textLocality;
	@FXML
	private TextField textPostal;
	@FXML
	private TextField textCode;
	@FXML
	private ComboBox<String> cArea;
	private Text textHabAcad;
	private Text textHabProf;
	@FXML
	private Text textInfoHabi;
	@FXML
	private ListView<String> listViewNifApp;
	@FXML
	private Text textHab;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		rspC = new RegisterServiceProviderController();
	}

	/**
	 * associate the controller to this class
	 * @param aController HumanResourcesOfficerUI instance
	 */
	public void associateController(HumanResourcesOfficerUI aController) {
		this.aController = aController;
	}

	/**
	 * set in a list view the nifs of all applications saved in the system
	 */
	public void setListNif(){
		List<String> appNif = new ArrayList<>();
		appNif = rspC.getNifOfAllApplications();
		listViewNifApp.getItems().addAll(appNif);
	}
	/**
	 * get the nif in the text box, validate and get an application with this nif
	 * @param event 
	 */
	@FXML
	private void loadNIF(ActionEvent event) {

		try {
			String nif = textNif.getText();
			Application appli = rspC.getApplications(nif);

			setElementsEnable();
			setInformationApplication(appli);

			textNif.setDisable(true);
			buttNIF.setDisable(true);
			bConfirm.setDisable(false);
			feedCategories();
			feedGeographicAreas();
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		}

	}

	/**
	 * set the information in the text box with the application information
	 * @param appli application that has the information to set
	 */
	public void setInformationApplication(Application appli) {

		if (appli == null) {
			textNif.setText("");

		} else {
			textNif.setText("");
			textInfoHabi.setVisible(true);
			textHab.setText(appli.getAh() + "\n " + appli.getPh());
			textFullName.setText(appli.getName());
			String abvName[] = appli.getName().split(" ");
			textAbvName.setText(abvName[0]);
			textInstEmail.setText(appli.getEmail());
			textAdress.setText(appli.getAdd().getAddress());
			textLocality.setText(appli.getAdd().getLocality());
			String [] postal = appli.getAdd().getPostalCode().toString().split("-");
			textPostal.setText(postal[0]);
			textCode.setText(postal[1]);
		}

	}

	/**
	 * set the text box and buttons enables to be used
	 */
	public void setElementsEnable() {
		textInstEmail.setDisable(false);
		textAbvName.setDisable(false);
		textFullName.setDisable(false);
		textTyping.setDisable(false);
		textAdress.setDisable(false);
		textCode.setDisable(false);
		textLocality.setDisable(false);
		textPostal.setDisable(false);
	}

	/**
	 * set the elements text box and buttons disabled
	 */
	public void setElementsDisable() {
		textInstEmail.setDisable(true);
		textAbvName.setDisable(true);
		textFullName.setDisable(true);
		textTyping.setDisable(true);
		textAdress.setDisable(true);
		textCode.setDisable(true);
		textLocality.setDisable(true);
		textPostal.setDisable(true);

	}

	/**
	 * feed the combo box with categories instances
	 */
	public void feedCategories() {

		comboCat.setItems(FXCollections.observableList(rspC.getCategories()));
		comboCat.setDisable(false);
	}

	/**
	 * feed the geographic areas with String name of the areas saved in the system
	 */
	public void feedGeographicAreas() {
		cArea.setItems(FXCollections.observableList(rspC.getGeographicAreasName()));
		cArea.setDisable(false);
	}

	/**
	 * exit to the HumanResourcesOfficer Window
	 * @param event 
	 */
	@FXML
	private void exitRegister(ActionEvent event) {
		Stage stage = (Stage) buttonExit.getScene().getWindow();
		stage.close();
	}

	/**
	 * method to be used together with exit at RegisterServiceProviderConfirmUI class, to take the user to the HRO window
	 */
	public void exit() {
		Stage stage = (Stage) buttonExit.getScene().getWindow();
		stage.close();
	}

	/**
	 * method that add one area to the ServiceProvider
	 * @param event 
	 */
	@FXML
	private void loadArea(ActionEvent event) {
		try {
			Area area = rspC.getAreaByName(cArea.getValue());
			rspC.addGeographicArea(area);
			listViewArea.getItems().add(area);
			cArea.getItems().remove(cArea.getValue());
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		}
	}

	/**
	 * method that add one category to the service provider
	 * @param event 
	 */
	@FXML
	private void loadCat(ActionEvent event) {
		try {
			rspC.addCategory(comboCat.getValue());
			listViewCat.getItems().add(comboCat.getValue());
			comboCat.getItems().remove(comboCat.getValue());
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		}

	}	

	/**
	 * create an alert to be shown
	 * @param tipoAlerta
	 * @param cabecalho
	 * @param mensagem
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
	 * delete one category from the service provider 
	 * @param event 
	 */
	@FXML
	private void clickDeletCat(ActionEvent event) {
		comboCat.getItems().add( listViewCat.getSelectionModel().getSelectedItem());
		rspC.removeCat(listViewCat.getSelectionModel().getSelectedItem());
		listViewCat.getItems().remove(listViewCat.getSelectionModel().getSelectedItem());
		
	}

	/**
	 * delete one area from the service provider
	 * @param event 
	 */
	@FXML
	private void clickDeletArea(ActionEvent event) {		
		cArea.getItems().add(listViewArea.getSelectionModel().getSelectedItem().getNome());
		
		rspC.removeArea(listViewArea.getSelectionModel().getSelectedItem());
		listViewArea.getItems().remove(listViewArea.getSelectionModel().getSelectedItem());
	}

	/**
	 * update the information of the service provider
	 * @param event 
	 */
	@FXML
	private void clickChangeInfo(ActionEvent event) {
		try {
			String postalCode = textPostal.getText() + "-" + textCode.getText();
			rspC.changeInfo(textTyping.getText(), textFullName.getText(), textAbvName.getText(), textInstEmail.getText(), textAdress.getText(), postalCode,textLocality.getText());
			setElementsDisable();
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		}
	}

	/**
	 * validate the service provider and take the HRO to the confirmation window
	 * @param event 
	 */
	@FXML
	private void clickConfirmButton(ActionEvent event) {
		try {
			rspC.validateServiceProvider();
			newRegisterServiceProviderConfirm = new Stage();
			newRegisterServiceProviderConfirm.initModality(Modality.APPLICATION_MODAL);
			newRegisterServiceProviderConfirm.setTitle("Confirm Service Provider");
			newRegisterServiceProviderConfirm.setResizable(false);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnRegisterServiceProviderConfirm.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			RegisterServiceProviderConfirmUI adtController = loader.getController();
			adtController.associateController(this, rspC);

			newRegisterServiceProviderConfirm.setScene(scene);
			newRegisterServiceProviderConfirm.show();
			adtController.sendInfo(rspC.getServiceProvider());
			bConfirm.setDisable(false);
			setElementsEnable();
			bChangeInfo.setVisible(true);
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		} catch (IOException ex) {
		}
	}

	/**
	 * validate the information in the interface and create the instance of Service provider
	 * @param event 
	 */
	@FXML
	private void clickLoadInformationButton(ActionEvent event) {
		//try catch para validacoes
		try {
			String postalCode = textPostal.getText() + "-" + textCode.getText();
			Area area = rspC.getAreaByName(cArea.getValue());
			rspC.newServiceProvider(textTyping.getText(), textFullName.getText(), textAbvName.getText(), textInstEmail.getText(), comboCat.getValue(), area, textAdress.getText(), postalCode, textLocality.getText());
			listViewArea.getItems().add(area);
			listViewCat.getItems().add(comboCat.getValue());
			comboCat.getItems().remove(comboCat.getValue());
			cArea.getItems().remove(cArea.getValue());
			ButtonCate.setVisible(true);
			buttonArea.setVisible(true);
			loadAll.setDisable(false);
			bConfirm.setDisable(true);
			bDeletArea.setVisible(true);
			bDeletCat.setVisible(true);
			bConfirm.setVisible(false);
			setElementsDisable();
			textWarningMoreCatArea.setText("If you want, \n you can chose more categories or geographic areas.");
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		}
	}


}
