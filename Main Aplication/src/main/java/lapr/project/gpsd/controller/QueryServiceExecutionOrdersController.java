/*
z * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.Data;
import lapr.project.gpsd.model.Export;
import lapr.project.gpsd.model.RegisterRequest;
import lapr.project.gpsd.model.Request;
import lapr.project.gpsd.model.ServiceOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.Time;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Francisco Ferreira
 */
public class QueryServiceExecutionOrdersController {
    
    private final Company company;
    private RegisterRequest rPed;
    
    private static List<Request> peds;
    
    /**
     * Constructs an instance of QueryServiceExecutionOrdersController
     */
     public QueryServiceExecutionOrdersController() {
        if (!ApplicationGPSD.getInstance().getSessaoAtual().isLoggedInComPapel(Constantes.PAPEL_PRESTADOR_SERVICO)) {
            throw new IllegalStateException("Unauthorized user");
        }
        this.company = ApplicationGPSD.getInstance().getEmpresa();
     }
     
    /**
     * Returns the register request 
     * @return the register request
     */ 
    public RegisterRequest getRegisterRequest(){
        rPed = company.getRegistoPedido();
        return rPed;
    } 
   
    /**
     * Returns the list of service execution orders
     * @param period the period of service execution orders
     * @return the list of service execution orders
     */
    public List<String[]> sendPeriod (Data d, Time tI, Time tF, ServiceProvider sp){
        List<String[]> list = new ArrayList<String[]>();
        RegisterRequest rr = Utils.getEmpresa().getRegistoPedido();
        
        for(int i = 0; i <rr.getList().size();i++){
            Request r = rr.getList().get(i);
            for(ServiceOrder so: r.getServicoCompleto()){
                System.out.println(sp);
                if(so.getaSP() == sp){
                    System.out.println("PRESTADOR IGUAL");
                    if(so.getaAvailability().getDate().same(d)){
                        System.out.println("DATA IGUAL");
                        Time tSO = so.getaAvailability().getTime();
                        System.out.println(tSO.equals(tI) + " // " + (tSO == tI));
                        System.out.println(tSO.toString());
                        System.out.println(tI.toString());
                        if((tSO.isMaior(tI) || tSO.toString().equals(tI.toString())) && !tSO.isMaior(tF)){
                            System.out.println("TEMPO VALIDO");
                            String[] info = new String[11];
                            info[0] = String.format("%d", i+1);
                            info[1] = r.getCl().getNome();
                            info[2] = String.format("%.2f", Utils.distancia(sp.getEndereco().getPostalCode().getLat(),sp.getEndereco().getPostalCode().getLon(), r.getCP().getLat(), r.getCP().getLon()));
                            info[3] = so.getService().getCat().getId();
                            info[4] = so.getService().getType();
                            info[5] = so.getTempAvailability().getDate().toDiaMesAnoString();
                            info[6] = so.getTempAvailability().getTime().toString();
                            info[7] = r.getMorada().getAddress();
                            info[8] = r.getMorada().getLocality();
                            info[9] = r.getCP().toString();
                            info[10] = sp.getAbvName();
                            list.add(info);
                            
                        }
                    }
                }
            }
        }
        return list;
    }
    
    /**
     * Returns true or false if the new file is exported or not
     * @param fileType file type to export
     * @return true or false if the new file is exported or not
     */
    public boolean exportFile (String fileType, List<String[]> data){
        return Export.exportFile(fileType, data);
        
    }
    
}
