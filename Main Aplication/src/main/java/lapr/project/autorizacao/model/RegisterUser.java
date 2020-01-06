/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.autorizacao.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author paulomaio
 */
public class RegisterUser
{
    private Set<User> m_lstUtilizadores = new HashSet<User>();
    
    
    public User novoUtilizador(String strNome, String strEmail, String strPassword)
    {
        return new User(strNome,strEmail,strPassword);
    }
    
    public boolean addUtilizador(User utlz)
    {
        if (utlz != null)
            return this.m_lstUtilizadores.add(utlz);
        return false;
    }
    
    public boolean removeUtilizador(User utlz)
    {
        if (utlz != null)
            return this.m_lstUtilizadores.remove(utlz);
        return false;
    }
    
    public User procuraUtilizador(String strId)
    {
        for(User utlz: this.m_lstUtilizadores)
        {
            if(utlz.hasId(strId))
                return utlz;
        }
        return null;
    }
    
    public boolean hasUtilizador(String strId)
    {
        User utlz = procuraUtilizador(strId);
        if (utlz != null)
            return this.m_lstUtilizadores.contains(utlz);
        return false;
    }
    
    public boolean hasUtilizador(User utlz)
    {
        return this.m_lstUtilizadores.contains(utlz);
    }
}
