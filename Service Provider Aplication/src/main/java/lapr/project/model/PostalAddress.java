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
class PostalAddress implements Serializable{
	
	private String address;
	private String locality;
	private String postalCode;

	public PostalAddress(String address, String locality, String postalCode) {
		this.address = address;
		this.locality = locality;
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public String getLocality() {
		return locality;
	}

	public String getPostalCode() {
		return postalCode;
	}
        
	public String toStringValidate() {
		return  address+locality+postalCode;
	}
        
	@Override
	public String toString() {
		return  "Address: " + address + ", Locality: " + locality + ", Postal Code: " + postalCode;
	}
	
	
	
	
}
