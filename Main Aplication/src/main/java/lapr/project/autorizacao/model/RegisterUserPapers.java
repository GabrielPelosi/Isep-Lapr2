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
public class RegisterUserPapers
{
    private Set<UserPapers> m_lstPapeis = new HashSet<UserPapers>();
    
    public UserPapers novoPapelUtilizador(String strPapel)
    {
        return new UserPapers(strPapel);
    }
    
    public UserPapers novoPapelUtilizador(String strPapel, String strDescricao)
    {
        return new UserPapers(strPapel,strDescricao);
    }
    
    public boolean addPapel(UserPapers papel)
    {
        if (papel != null)
            return this.m_lstPapeis.add(papel);
        return false;
    }
    
    public boolean removePapel(UserPapers papel)
    {
        if (papel != null)
            return this.m_lstPapeis.remove(papel);
        return false;
    }
    
    public UserPapers procuraPapel(String strPapel)
    {
        for(UserPapers p: this.m_lstPapeis)
        {
            if(p.hasId(strPapel))
                return p;
        }
        return null;
    }
    
    public boolean hasPapel(String strPapel)
    {
        UserPapers papel = procuraPapel(strPapel);
        if (papel != null)
            return this.m_lstPapeis.contains(papel);
        return false;
    }
    
    public boolean hasPapel(UserPapers papel)
    {
        return this.m_lstPapeis.contains(papel);
    }
}
