/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.io.Serializable;

/**
 *
 * @author Pelosi
 */
public class ExecutionOrder implements Serializable{
	private final String number;
	private final String name;
	private final String distance;
	private final String category;
	private final String serviceType;
	private final Data date;
	private final String startTime;
	private final PostalAddress pAddress;

	/**
	 *Constructor of Execution Orders
	 * @param number Sting with the number of the order
	 * @param name Sting with the name of the order
	 * @param distance Sting with the distance of the order
	 * @param category Sting with the category of the order
	 * @param serviceType Sting with the service type of the order
	 * @param date Sting with the date of the order that will be a Date object
	 * @param startTime Sting with the start time of the order
	 * @param address  Sting with the address of the order
	 * @param locality Sting with the locality of the order
	 * @param postalCode Sting with the postal code of the order
	 */
	public ExecutionOrder(String number, String name, String distance, String category, String serviceType, String date, String startTime, String address, String locality, String postalCode) {
		this.number = number;
		this.name = name;
		this.distance = distance;
		this.category = category;
		this.serviceType = serviceType;
		this.date = new Data(date) ;
		this.startTime = startTime;
		this.pAddress = new PostalAddress(address, locality, postalCode);
	}
        
	/**
	 *Method get of the String to Ordenade
	 * @return String to Ordenade in analyze execution order
	 */
	public String toStringOrdenade(){
            return name+distance+category+date.toAnoMesDiaString()+startTime+serviceType+pAddress.toStringValidate();  
        }

	/**
	 *Method get of the number attribute
	 * @return numer
	 */
	public String getNumber() {
            return number;
        }

	/**
	 *Method get of the name attribute
	 * @return name
	 */
	public String getName() {
            return name;
        }

	/**
	 *Method get of the distance attribute
	 * @return distance
	 */
	public String getDistance() {
            return distance;
        }

	/**
	 *Method get of the category attribute
	 * @return category
	 */
	public String getCategory() {
            return category;
        }

	/**
	 *Method get of the service type attribute
	 * @return service type
	 */
	public String getServiceType() {
            return serviceType;
        }

	/**
	 *Method get of the date attribute
	 * @return date
	 */
	public Data getDate() {
        return date;
    }

	/**
	 *Method get of the start time attribute
	 * @return start time
	 */
	public String getStartTime() {
        return startTime;
    }

	/**
	 *Method get of the address attribute
	 * @return address 
	 */
	public PostalAddress getpAddress() {
        return pAddress;
    }
        

	@Override
	public String toString() {
		return "ExecutionOrder: "  + "Number: " + number + ", Name: " + name + ", Distance: " + 
				distance + ", Category: " + category + ", Service Type: " + serviceType + ", Date: " + date.toAnoMesDiaString()
				+ ", Start Time: " + startTime + ", Postal Address: " + pAddress.toString();
	}
	
	
	
	
	
}
