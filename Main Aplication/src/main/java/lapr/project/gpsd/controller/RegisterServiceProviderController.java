/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.List;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Application;
import lapr.project.gpsd.model.Area;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PostalCode;
import lapr.project.gpsd.model.RegisterApplication;
import lapr.project.gpsd.model.RegisterPC;
import lapr.project.gpsd.model.RegisterSP;

/**
 *
 * @author Utilizador
 */
public class RegisterServiceProviderController {

	private ApplicationGPSD m_oApp;
	private Company m_oEmpresa;
	private ServiceProvider m_oPrestadorServico;
	private String m_strPwd;
	private RegisterSP rSP;
	private RegisterApplication rSA;
	private ServiceProvider ps;
	private RegisterPC rPC;

	/**
	 * The constructor of RegisterServiceProviderController
	 */
	public RegisterServiceProviderController() {
		this.m_oApp = ApplicationGPSD.getInstance();
		this.m_oEmpresa = m_oApp.getEmpresa();
		this.ps = null;
	}
	
	/**
	 * get the list with the nif of all applications saved in the system
	 * @return List<String> with the nifs
	 */
	public List<String> getNifOfAllApplications(){
		rSA = m_oEmpresa.getRegisterApplication();
		return rSA.getListNifApplications();
	}
	
	
	/**
	 * get one String list with names of the  areas saved in the system.
	 * @return List<String> with names of all areas in the system
	 */
	public List<String> getGeographicAreasName(){
		return m_oEmpresa.getRegisterArea().getGeographicAreasName();
	}

	/**
	 * get an Area instance by name given by parameter 
	 * @param name String with area name
	 * @return the area with the respective name, or null if the area does not exist
	 */
	public Area getAreaByName(String name){
		return m_oEmpresa.getRegisterArea().getAreaByName(name);
	}
	/**
	 * Method get to the List of Categories in the System
	 *
	 * @return
	 */
	public List<Category> getCategories() {
		return m_oEmpresa.getRegistoCategoria().getCategorias();
	}

	/**
	 * Method the get the List of Geographic Areas in the System
	 *
	 * @return
	 */
	public List<Area> getGeographicAreas() {
		return m_oEmpresa.getRegisterArea().getAreas();
	}

	/**
	 * Method that add one Category to the Service Provider Category List
	 *
	 * @param cat Category object
	 */
	public void addCategory(Category cat) {
		ps.addCategory(cat);

	}

	/**
	 * Method that remove one Category of the Service Provider Category List
	 *
	 * @param cat Category object
	 */
	public void removeCat(Category cat) {
		ps.removeCat(cat);
	}

	/**
	 * Method that add one Geographic Area to the Service Provider Geographic Area List
	 *
	 * @param geo Geographic Area object
	 */
	public void addGeographicArea(Area geo) {
		ps.addGeographicArea(geo);
	}

	/**
	 * Method that remove one Geographic Area of the Service Provider Geographic Area List
	 *
	 * @param area Geographic Area object
	 */
	public void removeArea(Area area) {
		ps.removeArea(area);
	}

	/**
	 * Method that save the Service Provider in the System
	 */
	public void registerServiceProvider() {
		rSP.registerServiceProvider(ps);

	}

	/**
	 * Method that save the Service Provider in the System with the password, to login methods
	 *
	 * @param strPwd String object with the password
	 */
	public void registerServiceProviderFile(String strPwd) {
		rSP.registerServiceProviderFile(ps, strPwd);
	}

	/**
	 * Method that validate one Service Provider
	 */
	public void validateServiceProvider() {
		rSP.validateServiceProvider(ps);
	}

	/**
	 * Method that get the Applications by one Nif passed by parameter
	 *
	 * @param nif String with the NIF code
	 * @return the application corresponding to the NIF code, if the methods does not found the Application, returns null
	 */
	public Application getApplications(String nif) {
		validateNIF(nif);
		rSA = m_oEmpresa.getRegisterApplication();
		return rSA.getApplicationByNIF(nif);
	}

	/**
	 * Method that validate de information of the Service Provider and create the object of ServicePRovider class
	 *
	 * @param tNumber String with the Typing Number
	 * @param fName String with the Full Name
	 * @param aName String with the abbreviated name
	 * @param email String with the email
	 * @param cat Category object
	 * @param geo Geographic Area object
	 */
	public void newServiceProvider(String tNumber, String fName, String aName, String email, Category cat, Area geo, String a, String b, String c) {
		rPC = m_oEmpresa.getRegistoCP();
		PostalCode pc = rPC.getCPById(b);
		validateData(tNumber, fName, aName, email, cat, geo, a, pc, c);
		rSP = m_oEmpresa.getServiceProvidersRegister();
		ps = rSP.newServiceProvider(tNumber, fName, aName, email, a, pc, c);
		ps.addCategory(cat);
		ps.addGeographicArea(geo);
	}

	/**
	 * Method get to the Service Provider attribute
	 *
	 * @return the attribute Service Provider
	 */
	public ServiceProvider getServiceProvider() {
		return ps;
	}

	/**
	 * Method that validate one NIF code
	 *
	 * @param nif String with the NIF code
	 */
	public void validateNIF(String nif) {

		if (!nif.matches("[0-9]+") || nif.length() != 9) {
			throw new IllegalArgumentException("Invalid NIF!");
		}

	}

	/**
	 * Method that validate the Typing Number, Full Name, Abbreviated name, Category object and the Geographic Area object
	 *
	 * @param tNumber String with the Typing Number
	 * @param fName String with the Full Name
	 * @param aName String with the abbreviated name
	 * @param email
	 * @param cat Category object
	 * @param geo Geographic Area object
	 * @param adress
	 * @param pc
	 * @param local
	 */
	public void validateData(String tNumber, String fName, String aName, String email, Category cat, Area geo, String adress, PostalCode pc, String local) {

		if (tNumber != null) {
			if (tNumber.trim().isEmpty()) {
				throw new IllegalArgumentException("The typing number can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The typing number can't be empty!");
		}

		if (fName != null) {
			if (fName.trim().isEmpty()) {
				throw new IllegalArgumentException("The full name can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The full name can't be empty!");
		}

		if (aName != null) {
			if (aName.trim().isEmpty()) {
				throw new IllegalArgumentException("The abbreviated name can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The abbreviated name can't be empty!");
		}

		if (email != null) {
			if (email.trim().isEmpty()) {
				throw new IllegalArgumentException("The email can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The email can't be empty!");
		}

		if (adress != null) {
			if (adress.trim().isEmpty()) {
				throw new IllegalArgumentException("The adress can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The adress can't be empty!");
		}

		if (local != null) {
			if (local.trim().isEmpty()) {
				throw new IllegalArgumentException("the local can't be  empty!");
			}
		} else {
			throw new IllegalArgumentException("the local can't be  empty!");
		}

		if (pc == null) {
			throw new IllegalArgumentException("This postal code doe not exist in the system!");
		}

		//String nameF = fName;
		for (int i = 0; i < fName.length(); i++) {
			char ch = fName.charAt(i);
			if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7'
					|| ch == '8' || ch == '9') {
				throw new IllegalArgumentException("Numbers in full name will not be acepted!");
			}
		}

		//String nameA = aName;
		for (int i = 0; i < aName.length(); i++) {
			char ch = aName.charAt(i);
			if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7'
					|| ch == '8' || ch == '9') {
				throw new IllegalArgumentException("Numbers in abreviated name will not be acepted!");
			}
		}

		if (!tNumber.matches("[0-9]+") || tNumber.trim().length() != 5) {
			throw new IllegalArgumentException("Invalid Typing number!");
		}

		if (cat == null) {
			throw new IllegalArgumentException("You must chose one category!");
		}
		if (geo == null) {
			throw new IllegalArgumentException("You must chose one geographic area");
		}

	}

	/**
	 * Method that change the information of the Service Provider instance using set methods
	 *
	 * @param tNumber String with the Typing Number
	 * @param fName String with the Full Name
	 * @param aName String with the abbreviated name
	 * @param email String with the email
	 */
	public void changeInfo(String tNumber, String fName, String aName, String email, String adress, String postalCode, String local) {
		PostalCode pc = rPC.getCPById(postalCode);
		validateDataToChange(tNumber, fName, aName, email, adress, pc, local);
		ps.changeInfo(tNumber, fName, aName, email, new PostalAddress(adress, pc, local) );
	}
	/**
	 * Method that validate the Typing Number, Full Name, Abbreviated name, Category object and the Geographic Area object
	 *
	 * @param tNumber String with the Typing Number
	 * @param fName String with the Full Name
	 * @param aName String with the abbreviated name
	 */
	public void validateDataToChange(String tNumber, String fName, String aName, String email, String adress, PostalCode pc, String local) {

				if (tNumber != null) {
			if (tNumber.trim().isEmpty()) {
				throw new IllegalArgumentException("The typing number can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The typing number can't be empty!");
		}

		if (fName != null) {
			if (fName.trim().isEmpty()) {
				throw new IllegalArgumentException("The full name can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The full name can't be empty!");
		}

		if (aName != null) {
			if (aName.trim().isEmpty()) {
				throw new IllegalArgumentException("The abbreviated name can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The abbreviated name can't be empty!");
		}

		if (email != null) {
			if (email.trim().isEmpty()) {
				throw new IllegalArgumentException("The email can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The email can't be empty!");
		}

		if (adress != null) {
			if (adress.trim().isEmpty()) {
				throw new IllegalArgumentException("The adress can't be empty!");
			}
		} else {
			throw new IllegalArgumentException("The adress can't be empty!");
		}

		if (local != null) {
			if (local.trim().isEmpty()) {
				throw new IllegalArgumentException("the local can't be  empty!");
			}
		} else {
			throw new IllegalArgumentException("the local can't be  empty!");
		}

		if (pc == null) {
			throw new IllegalArgumentException("This postal code doe not exist in the system!");
		}

		String nameF = fName;

		for (int i = 0; i < nameF.length(); i++) {
			char ch = nameF.charAt(i);
			if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7'
					|| ch == '8' || ch == '9') {
				throw new IllegalArgumentException("Numbers in full name will not be acepted!");
			}
		}

		String nameA = aName;

		for (int i = 0; i < nameA.length(); i++) {
			char ch = nameA.charAt(i);
			if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7'
					|| ch == '8' || ch == '9') {
				throw new IllegalArgumentException("Numbers in abreviated name will not be acepted!");
			}
		}

		if (!tNumber.matches("[0-9]+") || tNumber.length() != 5) {
			throw new IllegalArgumentException("Invalid Typing number!");
		}
	}

	/**
	 * Method that create the instance of the Service Provider
	 *
	 * @param tNumber String with the Typing Number
	 * @param fName String with the Full Name
	 * @param aName String with the abbreviated name
	 * @param email String with the email
	 */
	public void newServiceProvider(String tNumber, String fName, String aName, String email,String address, String local, String postalCode) {
		rPC = m_oEmpresa.getRegistoCP();
		rSP = m_oEmpresa.getServiceProvidersRegister();
		PostalCode pc = rPC.getCPById(postalCode);
		ps = rSP.newServiceProvider(tNumber, fName, aName, email,address,pc,local);
	}

}
