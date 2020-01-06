/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AssignAvailabilityController;
import lapr.project.gpsd.model.DailyAvailability;
import lapr.project.gpsd.model.Data;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class AssignAvailabilityUI implements Initializable {

    @FXML
    private TextField initialHour;
    @FXML
    private TextField initialMinutes;
    @FXML
    private TextField finalHour;
    @FXML
    private TextField finalMinutes;
    private ServiceProviderUI cController;
    @FXML
    private Button cancel;
    AssignAvailabilityController contr;
    @FXML
    private DatePicker day;
    private DailyAvailability disp;
    private ServiceProvider prest;
    @FXML
    private Button monthly;
    @FXML
    private Text text;
    public boolean bret=true;
    private Stage novoPedidoStage;
    AssignAvailabilityUI cC;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prest=Utils.getEmpresa().getServiceProvidersRegister().getPrestadorServicoByEmail(Utils.getEmpresa().getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
    }
    
        
    @FXML
    private void cancel(ActionEvent event) {
     goBack();  
    }
    
     public void associarController(ServiceProviderUI cController) {
        this.cController = cController;
    }
    
    public void goBack(){
       Stage stage = (Stage) cancel.getScene().getWindow();
       stage.close(); 
    }

    @FXML
    private void add(ActionEvent event) {
        try {
            String data1 = day.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int horas1 = Integer.parseInt(initialHour.getText().trim());
            int minutos1 = Integer.parseInt(initialMinutes.getText().trim());
            int horas2 = Integer.parseInt(finalHour.getText().trim());
            int minutos2 = Integer.parseInt(finalMinutes.getText().trim());
            
            if (day.getValue() == null || initialHour.getText().equals("") || initialMinutes.getText().equals("") || finalHour.getText().equals("") || finalMinutes.getText().equals("")) {
                throw new Exception("All Data Must Be Completed");
            }
            
            if (compararData(data1)) {
                throw new Exception("Invalid Date");
            }
            
            Data d1 = new Data(data1);
            if(d1.determinarDiaDaSemana().equals("Domingo")){
                throw new Exception("ERROR: Service provider only works from Monday to Saturday");
            }
            
            
            if ((horas1 > 24 || horas1 < 6 || minutos1 >= 60 || minutos1 < 0) || (horas1>24 && minutos1>0)) {
                throw new Exception("Invalid Initial Hours(Service Provider can work 06-24)");
            }

            
            if ((horas2 > 24 || horas2 < 6 || minutos2 >= 60 || minutos2 < 0) || (horas2>24 && minutos2>0)) {
                throw new Exception("Invalid Final Hours (Service Provider can work 06-24)");
            }
            
            if ((horas1 > horas2) || ((horas1 == horas2) && (minutos2 - minutos1 < 30))) {
                throw new Exception("Error in data entered");
            }
            
            
            contr=new AssignAvailabilityController();
            String hora1= horas1+":"+minutos1;
            String hora2= horas2+":"+minutos2;  
            disp = contr.newSchedule(d1, hora1, hora2);
            System.out.println(disp);
            if (contr.registerSchedule()) {
                reset();
            } else {
                throw new Exception("Availability already exists!");
            }

        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Hours");

            alert.showAndWait();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    @FXML
    private void weekly(ActionEvent event) {
        validate(7);
        if(bret){
            text.setText("Success!");
        }else   text.setText("Failed!");
        
    }

    @FXML
    private void biweekly(ActionEvent event) {
        validate(14);
        if(bret){
            text.setText("Success!");
        }else   text.setText("Failed!");
    }

    /**
     * Verifica se a dataCalendario selecionada é ou não uma data anterior à de hoje
     * @param dataCalendario
     * @return true, se a dataCalendario for uma data anterior à de hoje
     * @return false, se a dataCalendario não for uma data anterior à de hoje
     */
    private boolean compararData(String dataCalendario) {
        Date dateNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dataAgora = formatter.format(dateNow);

        String[] dataC = dataCalendario.split("-");
        String[] dataA = dataAgora.split("-");

        Data dc = new Data(Integer.parseInt(dataC[0].trim()), Integer.parseInt(dataC[1].trim()), Integer.parseInt(dataC[2].trim()));
        Data da = new Data(Integer.parseInt(dataA[0].trim()), Integer.parseInt(dataA[1].trim()), Integer.parseInt(dataA[2].trim()));

        return da.isMaior(dc);
    }
    
    public void reset(){
        initialHour.setText("06");
        initialMinutes.setText("00");
        finalHour.setText("24");
        finalMinutes.setText("00");
        day.setValue(null); 
    }
    
    
    @FXML
    private void clean(ActionEvent event) {
        reset();
    }
    
    public void validate(int b){
        try{
        boolean bret=true;
        int horas1 = Integer.parseInt(initialHour.getText().trim());
        int minutos1 = Integer.parseInt(initialMinutes.getText().trim());
        int horas2 = Integer.parseInt(finalHour.getText().trim());
        int minutos2 = Integer.parseInt(finalMinutes.getText().trim()); 
        
            if ((horas1 > 24 || horas1 < 6 || minutos1 >= 60 || minutos1 < 0) || (horas1>24 && minutos1>0)) {
                throw new Exception("Invalid Initial Hours(Service Provider can work 06-24)");
            }

            
            if ((horas2 > 24 || horas2 < 6 || minutos2 >= 60 || minutos2 < 0) || (horas2>24 && minutos2>0)) {
                throw new Exception("Invalid Final Hours (Service Provider can work 06-24)");
            }
            
            if ((horas1 > horas2) || ((horas1 == horas2) && (minutos2 - minutos1 < 30))) {
                throw new Exception("Error in data entered");
            }
            
            contr=new AssignAvailabilityController();
            String hora1= horas1+":"+minutos1;
            String hora2= horas2+":"+minutos2; 
            Date dateNow = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dataAgora = formatter.format(dateNow);
            String[] dataA = dataAgora.split("-");
            Data da = new Data(Integer.parseInt(dataA[0].trim()), Integer.parseInt(dataA[1].trim()), Integer.parseInt(dataA[2].trim()));
            int num=da.getDiaMes();
            int a=1;
             
            for(int i=0;i<b;i++){
            Data ba=new Data(da.getAno(), da.getMes(), da.getDia());       
                    if(da.getDia()+i<=num){
                        ba.setData(ba.getAno(), ba.getMes(), (ba.getDia()+i));
                        if(!ba.determinarDiaDaSemana().equals("Domingo")){
                            disp = contr.newSchedule(ba, hora1, hora2);
                            if (contr.registerSchedule()) {
                                reset();
                            }else {
                                bret=false;
                                throw new Exception("Availability already exists!");
                                
                                        
                            }      
                        }
                    }
                
                if(da.getDia()+i>num){
                    ba.setData(ba.getAno(), (ba.getMes()+1),a );
                    a=a+1;
                    if(!ba.determinarDiaDaSemana().equals("Domingo")){
                        disp = contr.newSchedule(ba, hora1, hora2);
                        if (contr.registerSchedule()) {
                            reset();
                           
                        } else {
                            bret=false;
                            throw new Exception("Availability already exists!");
                        }      
                    }
                
                }
                
                if(da.getMes()==12){
                    if(ba.getDia()+i>num){
                        ba.setData(ba.getAno()+1,1,1);
                        a=a+1;
                        if(!ba.determinarDiaDaSemana().equals("Domingo")){
                            disp = contr.newSchedule(ba, hora1, hora2);
                            if (contr.registerSchedule()) {
                                reset();
                                
                            } else {
                                bret=false;
                                throw new Exception("Availability already exists!");
                            }      
                        }
                
                    }
                }
            }
            a=0;
            bret=true;
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
        
    }

    @FXML
    private void monthly(ActionEvent event) {
        validate(30);
        if(bret){
            text.setText("Success!");
        }else   text.setText("Failed!");
    }

    @FXML
    private void availabilities(ActionEvent event) {
        criarJanelaPadrao();
        novoPedidoStage.show();
    }
    
    /**
     * Cria uma nova cena
     */
     private void criarJanelaPadrao() {
        try {
            novoPedidoStage = new Stage();
            novoPedidoStage.initModality(Modality.APPLICATION_MODAL);

            novoPedidoStage.setTitle("Availabilities");
            novoPedidoStage.setResizable(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/scnDisponibilidades.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            AvailabilitiesUI ncController = loader.getController();
            ncController.associarController(this);
            novoPedidoStage.setScene(scene);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
