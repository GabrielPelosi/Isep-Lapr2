/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.Serializable;

/**
 *
 * @author Utilizador
 */
public class DailyAvailability  implements Serializable{

    private Data dataI;
    private String horaI;
    private String horaF;

    /**
     * If data is valid creates a DailyAvailability
     * @param dataI
     * @param horaI
     * @param horaF
     */
    public DailyAvailability(Data dataI, String horaI, String horaF) {
        if ((horaI == null) || (horaF == null) || (dataI == null)) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }
        this.dataI = dataI;
        this.horaI = horaI.trim();
        this.horaF = horaF.trim();
    }

    /**
     * Returns the date of the DailyAvailability
     * @return dataI, date of the DailyAvailability
     */
    public Data getDataI() {
        return dataI;
    }

    /**
     * Returns the inical hour of the DailyAvailability
     * @return horaI, inicial hour of the Daily Availability
     */
    public String getHoraI() {
        return horaI;
    }

    /**
     * Returns the final hour of the DailyAvailability
     * @return horaF, final hour of the availability
     */
    public String getHoraF() {
        return horaF;
    }
    
    /**
     * Returns the date of the DailyAvailability 
     * @return dataI.toAnoMesDiaString() eg(2019/07/22)
     */
    public String toStringValidate() {
        return dataI.toAnoMesDiaString();
    }
    
    /**
     * Returns the date of the DailyAvailability (2019/07/22)
     * @return dataI: horaI-horaF
     */
    @Override
    public String toString() {
        return dataI+": "+horaI+"-"+horaF;
    }

}
