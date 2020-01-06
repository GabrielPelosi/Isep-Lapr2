/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class ServiceProvider implements Serializable {

    private String nMecanografico;
    private String nomeC;
    private String nomeA;
    private String email;
    private String NIF;
    private Category categoria;
    private PostalAddress endereco;
    private List<DailyAvailability> disponibilidades = new ArrayList<DailyAvailability>();
    //private List<PostalAddress> enderecos = new ArrayList<PostalAddress>();
    private List<DailyAvailability> timeBusy = new ArrayList<DailyAvailability>();
    private List<Category> listCategory;
    private List<Area> listGeographicAreas;
    private RatingRegister scoreList;
    private List<Evaluation> listEvaluations;

    /**
     *
     * @param nMecanografico
     * @param nomeC
     * @param nomeA
     * @param email
     * @param categoria
     * @param endereco
     */
    public ServiceProvider(String nMecanografico, String NIF, String nomeC, String nomeA, String email, Category categoria, PostalAddress endereco) {
        if ((nMecanografico == null) || (nomeC == null) || (nomeA == null) || (email == null) || (categoria == null) || endereco == null) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        this.nMecanografico = nMecanografico;
        this.nomeC = nomeC;
        this.nomeA = nomeA;
        this.email = email;
        this.categoria = categoria;
        this.endereco = endereco;
        this.scoreList = new RatingRegister();
        this.NIF = NIF;
        this.listEvaluations = new ArrayList<>();
    }

    /*
     * Constructor of ServicePRovider class
     *
     * @param nMecanografico String with the Typing NumberString
     * @param email String with the email
     * @param nomeC String with the Full Name
     * @param nomeA String with the abbreviated name
     */
    public ServiceProvider(String nMecanografico, String nomeC, String nomeA, String email, PostalAddress pa) {
        if ((nMecanografico == null) || (nomeC == null) || (nomeA == null) || (email == null)
                || (nMecanografico.isEmpty()) || (nomeC.isEmpty()) || (nomeA.isEmpty()) || email.isEmpty()) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        this.nMecanografico = nMecanografico;
        this.nomeC = nomeC;
        this.nomeA = nomeA;
        this.email = email;
        this.endereco = pa;
        this.listCategory = new ArrayList<Category>();
        this.listGeographicAreas = new ArrayList<Area>();
        this.scoreList = new RatingRegister();
        this.listEvaluations = new ArrayList<>();
    }

    public ServiceProvider(List<Category> listCategory, List<Area> listAreas, List<DailyAvailability> listDailyAvailability, PostalAddress listPostal) {
        this.listCategory = listCategory;
        this.listGeographicAreas = listAreas;
        this.timeBusy = listDailyAvailability;
        this.endereco = listPostal;
    }

    ServiceProvider(String nMecanografico, String NIF, String nomeC, String nomeA, String email, PostalAddress endereco) {
        if ((nMecanografico == null) || (nomeC == null) || (nomeA == null) || (email == null)
                || (nMecanografico.isEmpty()) || (nomeC.isEmpty()) || (nomeA.isEmpty()) || email.isEmpty()) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        this.nMecanografico = nMecanografico;
        this.nomeC = nomeC;
        this.nomeA = nomeA;
        this.email = email;
        this.endereco = endereco;
        this.scoreList = new RatingRegister();
        this.NIF = NIF;
        this.listCategory = new ArrayList<Category>();
        this.listGeographicAreas = new ArrayList<Area>();
        this.listEvaluations = new ArrayList<>();
    }
    
    public ServiceProvider (List<Category> listCategory,List<DailyAvailability> listDailyAvailability,PostalAddress listPostal){
        this.listCategory = listCategory;
        this.timeBusy = listDailyAvailability;
        this.endereco = listPostal;
    }
    
    public ServiceProvider (){
        this.listEvaluations = new ArrayList<>();
    }

    public List<DailyAvailability> getBusyList() {
        return timeBusy;
    }

    /**
     * Method that set the information to change
     *
     * @param tNumber String with the Typing Number
     * @param fName String with the Full Name
     * @param aName String with the abbreviated name
     * @param email String with the email
     */
    public void changeInfo(String tNumber, String fName, String aName, String email, PostalAddress pa) {
        this.nMecanografico = tNumber;
        this.nomeC = fName;
        this.nomeA = aName;
        this.email = email;
        this.endereco = pa;
    }

    public String getPostalAddressString() {
        return this.endereco.toString();
    }

    /**
     * Method that validate and add one Category to the Service Provider Category List
     *
     * @param cat Category object that will be added
     */
    /**
     * Method that set the information to change
     *
     * @param tNumber String with the Typing Number
     * @param fName String with the Full Name
     * @param aName String with the abbreviated name
     * @param email String with the email
     */
    public void changeInfo(String tNumber, String fName, String aName, String email) {
        this.nMecanografico = tNumber;
        this.nomeC = fName;
        this.nomeA = aName;
        this.email = email;
    }

    /**
     * Method that validate and add one Category to the Service Provider Category List
     *
     * @param cat Category object that will be added
     */
    public void addCategory(Category cat) {
        validadeCategory(cat);
        listCategory.add(cat);

    }

    /**
     * Method that remove one Category of the Category List
     *
     * @param cat Category object that will be removed
     */
    public void removeCat(Category cat) {
        listCategory.remove(cat);
    }

    /**
     * Method that validate the Category object
     *
     * @param cat Category object
     */
    public void validadeCategory(Category cat) {

        /*if (cat == null) {
            throw new IllegalArgumentException("You must select one Category!");
        }*/
    }

    /**
     * Change Rating Register of Service Provider
     * @param scoreList rating register of service provider 
     */
    public void setScoreList(RatingRegister scoreList) {
        this.scoreList = scoreList;
    }
    

    /**
     * Method that validate and add one Geographic Area to the List
     *
     * @param geo Geographic Area instance
     */
    public void addGeographicArea(Area geo) {
        validadeArea(geo);
        listGeographicAreas.add(geo);
    }

    /**
     * Method that remove one geographic area from the list
     *
     * @param area geographic area instance
     */
    public void removeArea(Area area) {
        listGeographicAreas.remove(area);
    }

    /**
     * method that validate one Geographic Area
     *
     * @param geo geographic area instance
     */
    public void validadeArea(Area geo) {
        if (geo == null) {
            throw new IllegalArgumentException("You must select one Geographic Area!");
        }

    }

    /**
     *
     * @return
     */
    public List<DailyAvailability> getListaDisponibilidades() {
        return disponibilidades;
    }

    /**
     * Method that return the full name attribute
     *
     * @return String with the full name
     */
    public String getNome() {
        return nomeC;
    }

    /**
     * Method that return the email attribute
     *
     * @return String with the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that return the abbreviated name attribute
     *
     * @return String with the abbreviated name
     */
    public String getAbvName() {
        return nomeA;
    }

    /**
     * Method that return the typing number attribute
     *
     * @return String with the typing number
     */
    public String getTypingNumber() {
        return nMecanografico;
    }

    /**
     *
     * @return
     */
    public List<Category> getListCategory() {
        List<Category> list = new ArrayList<Category>();
        for (Category a : listCategory) {
            list.add(a);
        }
        return list;
    }

    /**
     *
     * @return
     */
    public List<Area> getListGeographicAreas() {
        List<Area> list = new ArrayList<Area>();
        for (Area a : listGeographicAreas) {
            list.add(a);
        }
        return list;
    }

    /**
     *
     * @param disp
     * @return
     */
    public boolean addDisponibilidades(DailyAvailability disp) {
        disponibilidades.add(disp);
        return true;
    }

    /**
     *
     * @param strCodigo
     * @param strDescricao
     * @return
     */
    public static Category novaCategoria(String strCodigo, String strDescricao) {
        return new Category(strCodigo, strDescricao);
    }
    
    public static Rating newRating(int rat) {
        return new Rating(rat);
    }

    /**
     * Method that return the ScoreList of the Service PRovider
     *
     * @return
     */
    public RatingRegister getServiceProviderScoreList() {
        return this.scoreList;
    }

    /**
     * Method that verify if the Service Provider has the email passed by parameter
     *
     * @param strEmail String with the email
     * @return true if the SP has this email, false if not
     */
    public boolean hasEmail(String strEmail) {
        return this.email.equalsIgnoreCase(strEmail);
    }

    /**
     *
     * @param providerMeanScore
     * @param serviceProviderEvaluation
     * @return
     */
    public Evaluation addEvaluation(double providerMeanScore, String serviceProviderEvaluation) {
        return new Evaluation(providerMeanScore, serviceProviderEvaluation);
    }

    /**
     *
     * @return
     */
    public String disponToString() {
        String a = "";
        for (DailyAvailability e : disponibilidades) {
            a += e.toString() + "\n";
        }
        return a;
    }

    /*public String toString(){
        return nMecanografico+" "+nomeC+" "+ nomeA +" "+email;
    }*/
    public String toString() {
        return nMecanografico + "; " + email;
    }

    /**
     *
     * @param evat
     * @return
     */
    public boolean evaluationValidate(Evaluation evat) {
        if (this.validateEvaluation(evat)) {
            return this.addEvaluation(evat);
        }
        return false;
    }

    /**
     *
     * @param evat
     * @return
     */
    public boolean validateEvaluation(Evaluation evat) {
        if ((evat.getClassification() == null) || (evat.getClassification().isEmpty()) || (evat.getMeanScore() <= 0)) {
            throw new NumberFormatException();
        }
        return true;
    }

    /**
     *
     * @param evat
     * @return
     */
    public boolean addEvaluation(Evaluation evat) {
        return listEvaluations.add(evat);
    }

    public PostalAddress getEndereco() {
        return endereco;
    }

}
