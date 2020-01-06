package lapr.project.gpsd.controller;

import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.model.ActsOn;
import lapr.project.gpsd.model.Area;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.RegisterArea;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.ui.console.utils.Utils;
import lapr.project.ui.AddressUI;

public class RequestController {

    Company empresa = Utils.getEmpresa();

    /**
     * Calcula o preco adicional do pedido
     */
    public double calculoPrecoAdicional(String mcps) {
        try {
            RegisterArea ra = empresa.getRegisterArea();
            RegisterPC rcp = empresa.getRegistoCP();
            Area areaPequena = null;
            PostalCode mcp = rcp.getCPById(mcps);
            for (Area a : ra.getAreas()) {
                for(ActsOn ao: a.getAo())
                if(ao.getPc() == mcp){
                    areaPequena = a;
                    break;
                }
            }
            if(areaPequena == null) return -1;
            else return areaPequena.getValor();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("O nosso serviço ainda não abranje a sua área");
            alert.setContentText("Pedimos desculpas pelo incodomo");

            alert.showAndWait();
            return -1;
        }
    }

    public boolean checkListaServico(List<Service> listaServico, Service serv) {
        for (Service s : listaServico) {
            if (s == serv) {
                return false;
            }
        }
        return true;
    }
}
