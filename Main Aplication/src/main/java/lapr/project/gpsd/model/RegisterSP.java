/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.autorizacao.AuthorizationFacade;
import lapr.project.gpsd.ui.console.utils.Utils;

/**
 *
 * @author Utilizador
 */
public class RegisterSP {

	private final AuthorizationFacade m_oAutorizacao;
	private final Set<ServiceProvider> m_lstPrestadorServico;

	public RegisterSP(AuthorizationFacade m_oAutorizacao) {
		this.m_oAutorizacao = m_oAutorizacao;
		this.m_lstPrestadorServico = new HashSet<>();
	}
        public RegisterSP( Set<ServiceProvider> m_lstPrestadorServico1){
            this.m_oAutorizacao = new AuthorizationFacade();
            this.m_lstPrestadorServico =  m_lstPrestadorServico1;
        }

	public ServiceProvider getPrestadorServicoByEmail(String strEMail) {
		for (ServiceProvider prestador : this.m_lstPrestadorServico) {
			if (prestador.hasEmail(strEMail)) {
				return prestador;
			}
		}

		return null;
	}

	public ServiceProvider novoPrestadorServico(String nMecanografico, String NIF, String nomeC, String nomeA, String email, Category categoria, PostalAddress endereco) {
		return new ServiceProvider(nMecanografico, NIF, nomeC, nomeA, email, categoria, endereco);
	}
        
        public ServiceProvider novoPrestadorServico(String nMecanografico, String NIF, String nomeC, String nomeA, String email, PostalAddress endereco) {
		return new ServiceProvider(nMecanografico, NIF, nomeC, nomeA, email, endereco);
	}

	public boolean validaPrestadorServico(ServiceProvider oPrestadorServico, String strPwd) {
		boolean bRet = true;

		// Escrever aqui o código de validação
		if (this.m_oAutorizacao.existeUtilizador(oPrestadorServico.getEmail())) {
			bRet = false;
		}
		if (strPwd == null) {
			bRet = false;
		}
		if (strPwd.isEmpty()) {
			bRet = false;
		}
		//

		return bRet;
	}

	public boolean registaPrestadorServico(ServiceProvider oPrestadorServico, String strPwd) {
		if (this.validaPrestadorServico(oPrestadorServico, strPwd)) {
			if (this.m_oAutorizacao.registaUtilizadorComPapel(oPrestadorServico.getNome(), oPrestadorServico.getEmail(), strPwd, Constantes.PAPEL_PRESTADOR_SERVICO)) {
				return addPrestadorServico(oPrestadorServico);
			}
		}
		return false;
	}

	private boolean addPrestadorServico(ServiceProvider oCliente) {
		return m_lstPrestadorServico.add(oCliente);
	}

	public List<ServiceProvider> getServiceProvidersList() {
		List<ServiceProvider> lps = new ArrayList<>();

		for (ServiceProvider serv : this.m_lstPrestadorServico) {
			lps.add(serv);
		}
		return lps;
	}

	public ServiceProvider newServiceProvider(String tNumber, String fName, String aName, String email, String address, PostalCode pc, String local) {
		
		return new ServiceProvider(tNumber, fName, aName, email, new PostalAddress(address, pc, local));
	}

	public void registerServiceProvider(ServiceProvider sp) {
		validateServiceProvider(sp);
		m_lstPrestadorServico.add(sp);
		int pwd = generatePwd();
		String password = Integer.toString(pwd);
		m_oAutorizacao.registaUtilizadorComPapel(sp.getNome(), sp.getEmail(), password, Constantes.PAPEL_PRESTADOR_SERVICO);
		
	}
	
	
	public void registerServiceProviderFile(ServiceProvider sp, String strPwd){
		m_lstPrestadorServico.add(sp);
		m_oAutorizacao.registaUtilizadorComPapel(sp.getNome(), sp.getEmail(), strPwd, Constantes.PAPEL_PRESTADOR_SERVICO);
		
	}

	public void validateServiceProvider(ServiceProvider sp) {

		//lancar exceptions para cada tipo de validacao
		if (sp.getListCategory().isEmpty()) {
			throw new IllegalArgumentException("You must chose at leats one Category!");
		}
		if (sp.getListGeographicAreas().isEmpty()) {
			throw new IllegalArgumentException("You must chose at least one Geographic Area!");
		}

	}

	public int getServiceProvidersTotal() {
		return this.m_lstPrestadorServico.size();
	}

	public int generatePwd() {
		Random gen = Utils.getRandom();
		return 100000 + gen.nextInt(900000);

	}

	public ServiceProvider getServiceProviderByMechNum(String mechNum) {
		for (ServiceProvider provider : this.m_lstPrestadorServico) {
			if (provider.getTypingNumber().equals(mechNum)) {
				return provider;
			}
		}
		return null;
	}
        
        public ServiceProvider getServiceProviderByEmail(String mechNum) {
		for (ServiceProvider provider : this.m_lstPrestadorServico) {
			if (provider.getEmail()== mechNum) {
				return provider;
			}
		}
		return null;
	}

}
