/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.controller.ImportExecutionOrdersController;
import lapr.project.model.ExecutionOrder;

/**
 * FXML Controller class
 *
 * @author Pelosi
 */
public class ImportExecutionOrdersUI implements Initializable {

	private MenuUI aController;
	private ImportExecutionOrdersController ieoCntroller;

	@FXML
	private Button buttonImportFile;
	@FXML
	private TextField textNameFile;
	@FXML
	private Button buttonCancel;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ieoCntroller = new ImportExecutionOrdersController();
	}

	/**
	 * Method that will  associate the controller to create new windows 
	 * @param aController controller of the MenuUI class
	 */
	public void associateController(MenuUI aController) {
		this.aController = aController;
	}

	@FXML
	private void clickButtonImportFile(ActionEvent event) {
		try {
			String extensionFile = ieoCntroller.getExtensionFile(textNameFile.getText());
			ieoCntroller.validateFile(extensionFile);
			
			ieoCntroller.readFile(textNameFile.getText(), extensionFile);
			
			ieoCntroller.saveToBinary();
			Alert a = createAlert(Alert.AlertType.INFORMATION, "Success", "the file was successfully uploaded, \n feel free to "
					+ "import another one!");
			a.show();

		} catch (FileNotFoundException fnfe) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", fnfe.getMessage());
			a.show();
		} catch (IllegalArgumentException iae) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Erro", iae.getMessage());
			a.show();
		} catch (Exception e) {
			Alert a = createAlert(Alert.AlertType.ERROR, "Eroo", "Something goes wrong, please check your file");
			a.show();
		}
	}

	@FXML
	private void clickButtonCancel(ActionEvent event) {
		
		Stage stage = (Stage) buttonCancel.getScene().getWindow();
		stage.close();
		
	}

	/**
	 * Method create an Alert with something happens in the system 
	 * @param tipoAlerta Alert type to create
	 * @param cabecalho Header the the Alert
	 * @param mensagem the content that will be showed in the alert  
	 * @return The alert ready to be showed
	 */
	private Alert createAlert(Alert.AlertType tipoAlerta, String cabecalho, String mensagem) {
		Alert alerta = new Alert(tipoAlerta);
		alerta.setTitle("Aplicação");
		alerta.setHeaderText(cabecalho);
		alerta.setContentText(mensagem);
		return alerta;
	}
}
