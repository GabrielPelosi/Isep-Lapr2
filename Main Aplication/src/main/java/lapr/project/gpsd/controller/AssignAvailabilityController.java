/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.DailyAvailability;
import lapr.project.gpsd.model.AvailabilityList;
import lapr.project.gpsd.model.Data;

/**
 *
 * @author Utilizador
 */
public class AssignAvailabilityController {
    private final AvailabilityList lista=new AvailabilityList();
    private DailyAvailability disp;
    
    /**
     * instance the Company Company and ServiceProvider pres

     */
    public AssignAvailabilityController() {
        if(!ApplicationGPSD.getInstance().getSessaoAtual().isLoggedInComPapel(Constantes.PAPEL_PRESTADOR_SERVICO)){
            throw new IllegalStateException("Unauthorized User");
        }
    }
    
    /**
     * Creates and returns a DailyAvailability
     * @param dataI, date of the Dailyavailability
     * @param horaI, inicial hour of the DailyAvailability
     * @param horaF, final hour of the DailyAvailability
     * @return DailyAvailability disp
     */
    public DailyAvailability newSchedule(Data dataI, String horaI, String horaF){
        this.disp=this.lista.newSchedule(dataI, horaI, horaF); 
    return this.disp; }
    
    /**
     * Check if DailyAvailability disp is valid
     * @return true, if DailyAvailability disp is valid, false if DailyAvailability is invalid
     */
    public boolean registerSchedule(){
        return lista.registerSchedule(disp);
    }
}   
    

