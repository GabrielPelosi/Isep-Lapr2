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
 * @author Francisco Ferreira
 */
public class RegisterCategory {

    private final Set<Category> m_lstCategories;

    public RegisterCategory() {
        this.m_lstCategories = new HashSet<>();
    }
    
    public RegisterCategory(Set<Category> categoriesSet){
        this.m_lstCategories = categoriesSet;
    }

    public Category getCategoriaById(String strId) {
        for (Category cat : this.m_lstCategories) {
            if (cat.hasId(strId)) {
                return cat;
            }
        }

        return null;
    }
    
    public Category getCategoryByName(String name) {
        for (Category cat : this.m_lstCategories) {
            if (cat.getM_strDescricao().equals(name)) {
                return cat;
            }
        }

        return null;
    }

    /**
     * returns the new category 
     * @param strCodigo category id
     * @param strDescricao category description
     * @return the new category
     */
     public Category newCategory(String strCodigo, String strDescricao) {
        return new Category(strCodigo, strDescricao);
    }
    
    public Category newCategory() {
        return new Category();
    }

    public boolean registerCategory(Category cat) {
            if (this.validateCategory(cat)) {
            return addCategory(cat);
        }
        return false;
    }

    private boolean addCategory(Category cat) {
        return m_lstCategories.add(cat);
    }

    public boolean validateCategory(Category cat) {
         return cat.validate();
    }

    public List<Category> getCategorias() {
        List<Category> lc = new ArrayList<>();
        lc.addAll(this.m_lstCategories);
        return lc;
    }
}
