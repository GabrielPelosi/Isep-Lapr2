package lapr.project.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lapr.project.autorizacao.model.UserPapers;
import lapr.project.gpsd.controller.ApplicationGPSD;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.RegisterArea;
import lapr.project.gpsd.ui.console.utils.Bootable;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class LogInUI implements Initializable {

    @FXML
    private Button butaoSair;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldPass;

    private AuthenticationController ac = new AuthenticationController();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Utils.getEmpresa().getRegistoCP().getListCP().size() == 0){
            Bootable b = new Bootable();
            b.readAll();
        }
        ac.doLogout();
    }

    /**
     * Encerra a aplicação
     */
    @FXML
    private void clicarSair(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Cria uma nova cena
     *
     * @param event
     */
    @FXML
    private void clicarFich(ActionEvent event) {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLerFich.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Ler Dados");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Verifica quem está a tentar fazer log in na aplicação e redireciona-o para uma nova cena caso seja um cliente, prestador de serviços ou um administrativo válido
     *
     * @param event
     */
    @FXML
    private void clicarLogIn(ActionEvent event) {
        try {
            String email = fieldEmail.getText().trim();
            String pass = fieldPass.getText().trim();
            AuthenticationController ac = new AuthenticationController();
            ac.doLogin(email, pass);
            UserPapers papel = ac.getPapeisUtilizador().get(0);
            if (papel.hasId("ADMINISTRATIVO")) {
                mudarJanelaAdministrativo();
            }
            if (papel.hasId("CLIENTE")) {
                mudarJanelaCliente();
            }
            if (papel.hasId(Constantes.PAPEL_PRESTADOR_SERVICO)) {
                mudarJanelaPrestador();
            }
	    
            if (papel.hasId(Constantes.PAPEL_FRH)){
	           mudarFRH();
             }
        }catch(NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    /**
     * Carrega e mostra a janela inicial para o Cliente
     */
    private void mudarJanelaCliente() {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnCliente.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) butaoSair.getScene().getWindow();
            stage.setTitle("Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Carrega e mostra a janela inicial para o Prestador
     */
    private void mudarJanelaPrestador() {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnPrestador.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) butaoSair.getScene().getWindow();
            stage.setTitle("Prestador Serviço");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Carrega e mostra a janela inicial para o Administrativo
     */
    private void mudarJanelaAdministrativo() {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnAdministrativo.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) butaoSair.getScene().getWindow();
            stage.setTitle("Administrativo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }
	
	public void mudarFRH(){
		try{
			Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnHumanResourcesOfficer.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) butaoSair.getScene().getWindow();
            stage.setTitle("Human Resources Officer");
            stage.setScene(scene);
            stage.show();
        }catch (Exception exc){
            System.out.println(exc.getMessage());
        }
	}
    
    /**
     * Verifica quem está a tentar fazer log in na aplicação e redireciona-o para uma nova cena caso seja um cliente, prestador de serviços ou um administrativo válido
     *
     * @param event
     */
    @FXML
    private void clicarEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            try {
                String email = fieldEmail.getText().trim();
                String pass = fieldPass.getText().trim();
                AuthenticationController ac = new AuthenticationController();
                ac.doLogin(email, pass);
                UserPapers papel = ac.getPapeisUtilizador().get(0);
                if (papel.hasId("ADMINISTRATIVO")) {
                    mudarJanelaAdministrativo();
                }
                if (papel.hasId("CLIENTE")) {
                    mudarJanelaCliente();
                }
                if (papel.hasId("PRESTADOR SERVIÇO")) {
                    mudarJanelaPrestador();
                }
            } catch (NullPointerException npe) {
                System.out.println(npe.getMessage());
            }
        }
    }

    @FXML
    private void butRegisterSP(ActionEvent event) {
        windowRegisterSP();
    }
    
    private void windowRegisterSP() {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnRegisterSP.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) butaoSair.getScene().getWindow();
            stage.setTitle("Register as Service Provider");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    @FXML
    private void registerAsCustomer(ActionEvent event) {
      try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnRegisterAsCustomer.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) butaoSair.getScene().getWindow();
            stage.setTitle("Register as Customer!");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

}
