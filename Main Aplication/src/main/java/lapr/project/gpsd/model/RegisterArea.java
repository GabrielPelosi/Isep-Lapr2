/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author rickropes
 */
public class RegisterArea {

    private final Set<Area> listAreas;
    
    public RegisterArea(){
        this.listAreas = new HashSet<>();
    }

    public RegisterArea(Set<Area> listAreas) {
        this.listAreas = listAreas;
    }

    /*public Area novaArea(String nome, String cp, String radius, double valor) {
        return new Area(nome, cp, valor);
    }*/
    
    public Area novaArea(String nome, PostalCode pc, int radius, double valor, String es) {
        return new Area(nome, pc, radius, valor, es);
    }

    public boolean validaArea(Area oArea) {
        boolean bRet = true;

        // Escrever aqui o código de validação
        //
        return bRet;
    }

	/**
	 * Save the area in the system
	 * @param oArea geographic area
	 * @return true if the geographic area was saved or false if isn't
	 */
    public boolean registaArea(Area oArea) {
        if (this.validaArea(oArea)) {
            return addArea(oArea);
        }
        return false;
    }

	/**
	 * add the area to the list of areas in the system
	 * @param oArea geographic area
	 * @return true if save, false if not
	 */
    private boolean addArea(Area oArea) {
        return listAreas.add(oArea);
    }

	/**
	 * get a List with all areas saved in the system
	 * @return List<Area> with all the areas in the system
	 */
    public List<Area> getAreas() {
        List<Area> lc = new ArrayList<>();
        lc.addAll(this.listAreas);
        return lc;
    }
    /**
	 * check if an area exists, searching by the name
	 * @param name String with area name
	 * @return true if exists, false if isn't
	 */
    public boolean nameExists(String name){
        for(Area a: listAreas){
            if(a.getNome().equals(name.trim())) return true;
        }
        return false;
    }
    
	/**
	 * get an area by the name passed by parameter
	 * @param name String with area name
	 * @return the area with the name passed by parameter, or null if the area doesn't exist in the system
	 */
    public Area getAreaByName(String name){
        for(Area a: listAreas){
            if(a.getNome().equals(name.trim())) return a;
        }
        return null;
    }
	
	/**
	 * get a list with the name of all areas saved in the system
	 * @return List<String> with the names of the areas in the system
	 */
	public List<String> getGeographicAreasName(){
		List<String> lis = new ArrayList<>();
		for (Area a : listAreas) {
			lis.add(a.getNome());
		}
		return lis;
	}
}
