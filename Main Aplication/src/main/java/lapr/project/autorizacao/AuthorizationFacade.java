/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.autorizacao;

import lapr.project.autorizacao.model.UserPapers;
import lapr.project.autorizacao.model.RegisterUserPapers;
import lapr.project.autorizacao.model.RegisterUser;
import lapr.project.autorizacao.model.UserSession;
import lapr.project.autorizacao.model.User;

/**
 *
 * @author paulomaio
 */
public class AuthorizationFacade
{
    private UserSession m_oSessao = null;
    
    private final RegisterUserPapers m_oPapeis = new RegisterUserPapers();
    private final RegisterUser m_oUtilizadores = new RegisterUser();
    
    public boolean registaPapelUtilizador(String strPapel)
    {
        UserPapers papel = this.m_oPapeis.novoPapelUtilizador(strPapel);
        return this.m_oPapeis.addPapel(papel);
    }
    
    public boolean registaPapelUtilizador(String strPapel, String strDescricao)
    {
        UserPapers papel = this.m_oPapeis.novoPapelUtilizador(strPapel,strDescricao);
        return this.m_oPapeis.addPapel(papel);
    }
    
    public boolean registaUtilizador(String strNome, String strEmail, String strPassword)
    {
        User utlz = this.m_oUtilizadores.novoUtilizador(strNome,strEmail,strPassword);
        return this.m_oUtilizadores.addUtilizador(utlz);
    }
    
    public boolean registaUtilizadorComPapel(String strNome, String strEmail, String strPassword, String strPapel)
    {
        UserPapers papel = this.m_oPapeis.procuraPapel(strPapel);
        User utlz = this.m_oUtilizadores.novoUtilizador(strNome,strEmail,strPassword);
        utlz.addPapel(papel);
        return this.m_oUtilizadores.addUtilizador(utlz);
    }
    
    public boolean registaUtilizadorComPapeis(String strNome, String strEmail, String strPassword, String[] papeis)
    {
        User utlz = this.m_oUtilizadores.novoUtilizador(strNome,strEmail,strPassword);
        for (String strPapel: papeis)
        {
            UserPapers papel = this.m_oPapeis.procuraPapel(strPapel);
            utlz.addPapel(papel);
        }
        
        return this.m_oUtilizadores.addUtilizador(utlz);
    }
    
    public boolean existeUtilizador(String strId)
    {
        return this.m_oUtilizadores.hasUtilizador(strId);
    }
    
    
    public UserSession doLogin(String strId, String strPwd)
    {
        User utlz = this.m_oUtilizadores.procuraUtilizador(strId);
        if (utlz != null)
        {
            if (utlz.hasPassword(strPwd)){
                this.m_oSessao = new UserSession(utlz);
            }
        }
        return getSessaoAtual();
    }
    
    public UserSession getSessaoAtual()
    {
        return this.m_oSessao;
    }
    
    public void doLogout()
    {
        if (this.m_oSessao != null)
            this.m_oSessao.doLogout();
        this.m_oSessao = null;
    }
}
