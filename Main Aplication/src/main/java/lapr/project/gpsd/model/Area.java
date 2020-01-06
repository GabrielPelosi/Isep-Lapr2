package lapr.project.gpsd.model;

import java.util.List;

/**
 *
 * @author Pelosi
 */
public class Area {

    private String nome;
    private PostalCode pc;
    private int radius;
    private double valor;
    private ExternalService es;
    private List<ActsOn> ao ;
    
	/**
	 *Constructor to create Area instances 
	 * @param nome String with the name
	 * @param pc Postal code instance
	 * @param radius integer with the radius
	 * @param valor double with the valor of displacement 
	 * @param es String with the external service
	 */
	public Area(String nome, PostalCode pc,int radius, double valor, String es)
    {
        this.nome = nome;
        this.pc = pc;
        this.radius = radius;
        this.valor = valor;
        try{
            Class<?> oClass = Class.forName(String.format("lapr.project.gpsd.model.ExternalService%sAdapter", es.trim()));
            this.es = (ExternalService) oClass.newInstance();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        ao = this.es.getActing(pc, radius);
    }

	/**
	 *Constructor to create Area instances without the external service
	 * @param nome String with the name
	 * @param pc Postal code instance
	 * @param radius integer with the radius
	 * @param valor double with the valor of displacement
	 */
	public Area(String nome, PostalCode pc,int radius, double valor)
    {
        this.nome = nome;
        this.pc = pc;
        this.radius = radius;
        this.valor = valor;
        ao = this.es.getActing(pc, radius);
    }
        
	/**
	 *get to the name attribute
	 * @return String with the name
	 */
	public String getNome() {
        return nome;
    }

	/**
	 *get to the postalCode attribute
	 * @return the postal Code instance
	 */
	public PostalCode getCp() {
        return pc;
    }

	/**
	 *get to the value attribute
	 * @return double with the value
	 */
	public double getValor() {
        return valor;
    }

	/**
	 *get to the act on list attribute
	 * @return List<ActsOn> of the Area instance
	 */
	public List<ActsOn> getAo() {
        return ao;
    }
    
	/**
	 * toString with name, postalCode, radius, and value attribute
	 * @return  String with name, postalCode, radius, and value
	 */
	public String getStringArea(){
		return String.format("%s, %s, %d, %.2f",nome, pc, radius, valor);
	}

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %.2f, %s",nome, pc, radius, valor, ao);
    }

    

}
