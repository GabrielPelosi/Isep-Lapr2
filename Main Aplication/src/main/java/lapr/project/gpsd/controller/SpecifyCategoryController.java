/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.RegisterCategory;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Francisco Ferreira
 */
public class SpecifyCategoryController
{
    private final Company moCompany;
    private Category moCategory;
    private RegisterCategory rC;
    
    /**
     * Constructs an instance of SpecifyCategoryController
     */
    public SpecifyCategoryController()
    {
        if(!ApplicationGPSD.getInstance().getSessaoAtual().isLoggedInComPapel(Constantes.PAPEL_ADMINISTRATIVO))
            throw new IllegalStateException("Unauthorized user");
        this.moCompany = ApplicationGPSD.getInstance().getEmpresa();
    }
    
    /**
     * Constructs an instance of SpecifyCategoryController
     * @param c category
     * @param rc register category
     */
    public SpecifyCategoryController(Category c, RegisterCategory rc){
        this.moCompany = ApplicationGPSD.getInstance().getEmpresa();
        this.rC = rc;
        this.moCategory = c;
    }
    
    /**
     * Returns true or false if the new category is created or not
     * @return true or false if the new category is created ornot
     */
    public boolean newCategory()
    {
        try
        {
            rC = this.moCompany.getRegistoCategoria();
           this.moCategory = rC.newCategory();
            return true;
        }
        catch(RuntimeException ex)
        {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.moCategory = null;
            return false;
        }
       
    }
   
    /**
     * Change category id and description, validating the new category cretaed
     * @param cod new category id
     * @param desc new category description
     */
    public void setData (String cod, String desc){
        this.moCategory.setId(cod);
        this.moCategory.setDescription(desc);
        this.rC.validateCategory(moCategory);
    }
    
    /**
     * Register the category
     * @return true or false if the category is registered or not
     */
    public boolean registerCategory()
    {
        return rC.registerCategory(this.moCategory);
    }
    

    /**
     * Returns the textual description of category
     * @return the textual description of category
     */
    public String getCategoriaString()
    {
        return this.moCategory.toString();
    }
}
