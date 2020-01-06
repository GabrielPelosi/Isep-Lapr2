/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.ReadFileController;

/**
 *
 * @author rickropes
 */
//(nome; NIF; numeroTelefone; email; password; local; codPostal; localidade)
public class ReadFileUI {

    @FXML
    private TextField fieldCliente;

    @FXML
    private TextField fieldCodPostal;
    @FXML
    private TextField fieldCat;
    @FXML
    private TextField fieldServ;
    @FXML
    private TextField fieldPrest;
    @FXML
    private TextField fieldArea;

    private final ReadFileController lfc = new ReadFileController();

    /**
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("SHIT");
    }

    /**
     * Lê um ficheiro com os dados dos cliente e instancia-os
     *
     * @param event
     */
    @FXML
    private void clicarCliente(ActionEvent event) {
        try {
            String nome = fieldCliente.getText();
            lfc.lerFicheiroCliente(nome);
            fieldCliente.setText("");
            fieldCliente.setPromptText("Sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lê um ficheiro com os dados das categorias e instancia-as
     *
     * @param event
     */
    @FXML
    private void clicarCat(ActionEvent event) {
        try {
            String nome = fieldCat.getText();
            lfc.lerFicheiroCategoria(nome);
            fieldCat.setText("");
            fieldCat.setPromptText("Sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lê um ficheiro com os dados dos códigos postais e instancia-os
     *
     * @param event
     */
    @FXML
    private void clicarCod(ActionEvent event) {
        try {
            String nome = fieldCodPostal.getText();
            lfc.lerFicheiroCP(nome);
            fieldCodPostal.setText("");
            fieldCodPostal.setPromptText("Sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Lê um ficheiro com os dados dos serviços e instancia-os
     *
     * @param event
     */
    @FXML
    private void clicarServ(ActionEvent event) {
        try {
            String nome = fieldServ.getText();
            lfc.lerFicheiroServico(nome);
            fieldServ.setText("");
            fieldServ.setPromptText("Sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lê um ficheiro com os dados dos prestador de serviço e instancia-os
     *
     * @param event
     */
    @FXML
    private void clicarPrest(ActionEvent event) {
        try {
            String nome = fieldPrest.getText();
            lfc.lerFicheiroPrestadorServico(nome);
            fieldPrest.setText("");
            fieldPrest.setPromptText("Sucesso!");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

    }

    /**
     * Lê um ficheiro com os dados das Areas Geográficas e instancia-as
     *
     * @param event
     */
    @FXML
    private void clicarArea(ActionEvent event) {
        try {
            String nome = fieldArea.getText();
            lfc.lerFicheiroAreaGeografica(nome);
            fieldArea.setText("");
            fieldArea.setPromptText("Sucesso!");
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    /**
     * Volta para a cena anterior
     *
     * @param event
     */
    @FXML
    private void clicarVoltar(ActionEvent event) {
        try {
            Parent blah = FXMLLoader.load(getClass().getResource("/fxml/scnLogIn.fxml"));
            Scene scene = new Scene(blah);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Cliente");
            stage.setScene(scene);
            stage.show();
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
    }

    /**
     * Chama todos os métodos de leitura de dados(Clientes, Prestadores, Categorias, Servicos, Códigos e Áreas) e instancia-os
     *
     * @param event
     */
    @FXML
    private void clicarTodos(ActionEvent event) {
        try {

            String nomecp = fieldCodPostal.getText();
            lfc.lerFicheiroCP(nomecp);
            fieldCodPostal.setText("");
            fieldCodPostal.setPromptText("Sucesso!");

            String nomecat = fieldCat.getText();
            lfc.lerFicheiroCategoria(nomecat);
            fieldCat.setText("");
            fieldCat.setPromptText("Sucesso!");

            String nomes = fieldServ.getText();
            lfc.lerFicheiroServico(nomes);
            fieldServ.setText("");
            fieldServ.setPromptText("Sucesso!");

            String nomea = fieldArea.getText();
            lfc.lerFicheiroAreaGeografica(nomea);
            fieldArea.setText("");
            fieldArea.setPromptText("Sucesso!");

            String nomec = fieldCliente.getText();
            lfc.lerFicheiroCliente(nomec);
            fieldCliente.setText("");
            fieldCliente.setPromptText("Sucesso!");

            String nomep = fieldPrest.getText();
            lfc.lerFicheiroPrestadorServico(nomep);
            fieldPrest.setText("");
            fieldPrest.setPromptText("Sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
