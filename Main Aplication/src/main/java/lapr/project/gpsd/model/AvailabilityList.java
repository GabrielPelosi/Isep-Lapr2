/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.controller.ApplicationGPSD;

/**
 *
 * @author Utilizador
 */
public class AvailabilityList {
    private final ServiceProvider pres;
    private final Company empresa;
    private AvailabilityList list;
    List<DailyAvailability> availability = new ArrayList<>();

    /**
     * Initializes Company empresa and ServiceProvider pres
     */
    public AvailabilityList() {
        this.empresa = ApplicationGPSD.getInstance().getEmpresa();
        this.pres=this.empresa.getServiceProvidersRegister().getPrestadorServicoByEmail(this.empresa.getAutorizacaoFacade().getSessaoAtual().getEmailUtilizador());
    }

    /**
     * 
     * @return AvailabilityList list
     */
    public AvailabilityList ListaDisponibilidadeDiaria() {
    return this.list;
    }
    
    /**
     * Verify that all data is correct and is they are creates and returns a DailyAvailability
     * @param dataI, date of the Dailyavailability
     * @param horaI, inicial hour of the DailyAvailability
     * @param horaF, final hour of the DailyAvailability
     * @return DailyAvailability new DailyAvailability(dataI.horaI,horaF)
     */
    public DailyAvailability newSchedule(Data dataI, String horaI, String horaF){
        if((dataI==null)||(horaI==null)||(horaF==null)) throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        return new DailyAvailability(dataI,horaI,horaF);
    }

    /**
     * Check if DailyAvailability disp is valid
     * @param disp
     * @return true, if disp is valid, false if disp is invalid
     */
    public boolean registerSchedule(DailyAvailability disp){
        if( validateSchedule(disp)&& verificarDisponibilidade(disp)){
            addSchedule(disp);
            return true;
        }
    return false;
    }

    private boolean addSchedule(DailyAvailability disp){
    return this.pres.addDisponibilidades(disp);}
    
    private boolean verificarDisponibilidade(DailyAvailability disp) {
        availability = pres.getListaDisponibilidades();
        for (DailyAvailability a : availability) {
            if (a.getDataI().toString().equalsIgnoreCase(disp.getDataI().toString())) {
                return false;
            }
        }
        return true;
    }
     
    /**
     * Check if DailyAvailability disp is valid
     * @param disp
     * @return true if disp is invalid, false if disp is invalid
     */
    public boolean validateSchedule(DailyAvailability disp) {
        boolean bRet = true;
        if(disp==null) return false;
        return bRet;
    }
}
