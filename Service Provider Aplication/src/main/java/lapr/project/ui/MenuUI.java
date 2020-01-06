/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.model.ApplicationServiceProvider;
import lapr.project.model.Company;
import lapr.project.model.ExecutionOrder;
import lapr.project.model.RegisterExecutionOrders;

/**
 * FXML Controller class
 *
 * @author Pelosi
 */
public class MenuUI implements Initializable {

	private Stage scnImportFiles;
	private Stage scnAnalyse;

	private Stage scnAnalyseTimeline;

	@FXML
	private Button buttonImport;
	@FXML
	private Button buttonTimeline;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadBinaryFile();
	}

	@FXML
	private void clickButtonImport(ActionEvent event) {

		try {

			scnImportFiles = new Stage();
			scnImportFiles.initModality(Modality.APPLICATION_MODAL);
			scnImportFiles.setTitle("Import Execution Orders");
			scnImportFiles.setResizable(false);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnImportExecutionOrders.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			ImportExecutionOrdersUI ncController = loader.getController();
			ncController.associateController(this);
			scnImportFiles.setScene(scene);
			scnImportFiles.show();
		} catch (IOException ioe) {
			System.out.println("aa");
		} catch (IllegalArgumentException iea) {
			System.out.println(iea.getMessage());
		}
	}

	@FXML
	private void analyze(ActionEvent event) {
		create();
		scnAnalyse.show();
	}

	public void create() {
		try {
			scnAnalyse = new Stage();
			scnAnalyse.initModality(Modality.APPLICATION_MODAL);

			scnAnalyse.setTitle("Analyse Execution Orders");
			scnAnalyse.setResizable(true);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnAnalyze.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			AnalyzeExecutionOrdersUI ncController = loader.getController();
			ncController.associateController(this);
			scnAnalyse.setScene(scene);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@FXML
	private void clickAnalyzeTimeline(ActionEvent event) {
            analyseTimeline();
            scnAnalyseTimeline.show();	
	}
        
        public void analyseTimeline(){
            try {
			scnAnalyseTimeline = new Stage();
			scnAnalyseTimeline.initModality(Modality.APPLICATION_MODAL);

			scnAnalyseTimeline.setTitle("Analyse Client Timeline");
			scnAnalyseTimeline.setResizable(true);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnAnalyzeClientTimeline.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root);

			AnalyzeClientTimelineUI ncController = loader.getController();
			ncController.associateController(this);
			scnAnalyseTimeline.setScene(scene);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
        }

	
	public void loadBinaryFile(){
		ApplicationServiceProvider app = ApplicationServiceProvider.getInstance();
		Company com = app.getEmpresa();
		RegisterExecutionOrders rc = com.getRegisterExecutionOrders();
//		rc.loadBinaryFile();
	}
}
