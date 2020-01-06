/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

/**
 *
 * @author rickropes
 */
public class RequestTime {

    private final Data date;
    private final Time time;

    public RequestTime(Data date, Time time) {
        this.date = date;
        this.time = time;
    }

    public Data getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return date + " at " + time;
    }
    
    
    
    

}
