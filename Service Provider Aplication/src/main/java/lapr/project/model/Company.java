/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

/**
 *
 * @author Pelosi
 */
public class Company {
	
	private String strDesignacao;
        private String strNIF;
	private RegisterExecutionOrders resEO;
	
	/**
	 * Constructor method of Company
	 * @param strDesignacao the designation of the company String
	 * @param strNIF the NIF oh the company String
	 */
	public Company(String strDesignacao, String strNIF) {
        if ((strDesignacao == null) || (strNIF == null)
                || (strDesignacao.isEmpty()) || (strNIF.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
		this.strDesignacao = strDesignacao;
        this.strNIF = strNIF;
		resEO = new RegisterExecutionOrders();
	}
	
	
	/**
	 * method get to register Execution order attribute, to enter in this class and use their methods
	 * @return RegisterExecutionOrders instance
	 */
	public RegisterExecutionOrders getRegisterExecutionOrders(){
		return resEO;
	}
	
}
