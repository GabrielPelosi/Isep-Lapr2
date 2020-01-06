package lapr.project.gpsd.controller;

import java.text.DecimalFormat;
import java.util.List;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.Constantes;
import lapr.project.gpsd.model.Evaluation;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.RegisterSP;
import lapr.project.gpsd.model.RatingRegister;
import lapr.project.gpsd.model.ServiceProvider;

public class EvaluateServiceProvidersController {

    private final Company company;
    private RegisterSP rSP;
    private ServiceProvider sP;

    private static double totalServiceProvidersMeanScore;
    private static double totalStandardDeviation;
    private static Evaluation evat;
    private static ServiceProvider serviceProvider;
    private static String evaluation;
    private static double providerMeanScore;
    private static RatingRegister lServiceProvidersScore;

    /**
     * Constructs an instance of EvaluateServiceProvidersController
     */
    public EvaluateServiceProvidersController() {
        if (!ApplicationGPSD.getInstance().getSessaoAtual().isLoggedInComPapel(Constantes.PAPEL_FRH)) {
            throw new IllegalStateException("Unauthorized user");
        }
        this.company = ApplicationGPSD.getInstance().getEmpresa();
    }
    
    /**
     * Constructs an instance of EvaluateServiceProvidersController
     * @param registerSp register of service providers
     * @param sp service provider
     */
    public EvaluateServiceProvidersController(RegisterSP registerSp, ServiceProvider sp){
        this.company = ApplicationGPSD.getInstance().getEmpresa();
        this.rSP = registerSp;
        this.sP = sp;
    }

    /**
     * Initiates the evaluation
     */
    public void initiateEvaluation() {
        RatingRegister lServiceProvidersScore = null;
        double totalProvidersScore = 0;
        int totalRating = 0;
        rSP = company.getServiceProvidersRegister();
        List<ServiceProvider> lServiceProviders = rSP.getServiceProvidersList();
        for (ServiceProvider sProvider : lServiceProviders) {
            lServiceProvidersScore = sProvider.getServiceProviderScoreList();
            totalProvidersScore = totalProvidersScore + lServiceProvidersScore.getRateSum();
            totalRating = totalRating + lServiceProvidersScore.getRatingNum();
            
        }
        totalServiceProvidersMeanScore = this.calulateTotalServiceProvidersMeanScore(totalProvidersScore, totalRating);
        totalStandardDeviation = this.calculateStandardDeviation(totalServiceProvidersMeanScore, lServiceProvidersScore);

    }

    /**
     * Calculates the total service providers mean score
     * @param totalProvidersScore total service providers score
     * @param totalRating total number rates of service providers
     * @return total service providers mean score
     */
    private static double calulateTotalServiceProvidersMeanScore(double totalProvidersScore, int totalRating) {
        double totalServiceProvidersMeanScore = totalProvidersScore / totalRating;
//        DecimalFormat fmt = new DecimalFormat("0.00");
//        String string = fmt.format(totalServiceProvidersMeanScore);
//        String[] part = string.split("[.]");
//        String string1 = part[0] + "." + part[1];
//        double totalMeanScore = Double.parseDouble(string1);
    System.out.println ("totalServiceProvidersMeanScore"+totalServiceProvidersMeanScore);
        return totalServiceProvidersMeanScore;
    }

    /**
     * Calculates the total standard deviation (population standard deviation)
     * @param totalServiceProvidersMeanScore total service providers mean score
     * @param lServiceProvidersScor list of service providers rate
     * @return total standard deviation (population standard deviation) 
     */
    private static double calculateStandardDeviation(double totalServiceProvidersMeanScore, RatingRegister lServiceProvidersScore) {
        double total = 0;
        List<Rating> lServiceProvidersScoreList = lServiceProvidersScore.getScoreList();
        for (Rating score : lServiceProvidersScoreList) {
            total = total + (Math.abs(totalServiceProvidersMeanScore - score.getValue()));
        }
        return total;
    }

    /**
     * Starts the statistics of Service Provider
     * @param pMechNum the service provider mechanographic number
     * @return the service provider with pMechNum 
     */
    public ServiceProvider startStatisticsServiceProvider(String pMechNum) {
         serviceProvider = rSP.getServiceProviderByMechNum(pMechNum);

//        RatingRegister lServiceProvidersScore = serviceProvider.getServiceProviderScoreList();
//        double providerMeanScore = lServiceProvidersScore.getMeanScore();
//        double serviceProviderStandardDeviation = calculateProviderStandardDeviation(lServiceProvidersScore, providerMeanScore);
//        String evaluation = displaysEvaluation(totalServiceProvidersMeanScore, providerMeanScore, totalStandardDeviation);
//        double serviceProviderAbsDeviation = calculateProviderAbsDeviation(providerMeanScore,totalServiceProvidersMeanScore);
//        evat = sP.addEvaluation(providerMeanScore, evaluation);
        return serviceProvider;
    }
    
    /**
     * Returns the service providers rate list
     * @return the service providers rate list
     */
    public RatingRegister getServiceProvidersScoreList(){
         lServiceProvidersScore = serviceProvider.getServiceProviderScoreList();
        return lServiceProvidersScore; 
    }
    
    /**
     * Returns the provider mean rate 
     * @return the provider mean rate
     */
    public double getProviderMeanRate(){
        providerMeanScore = lServiceProvidersScore.getMeanScore();
        return providerMeanScore;
    }
    
    /**
     * Returns the provider standard deviation
     * @return the provider standard deviations
     */
    public double getProviderStandardDeviation (){
        double serviceProviderStandardDeviation = calculateProviderStandardDeviation(lServiceProvidersScore, providerMeanScore);
        return serviceProviderStandardDeviation;
    }
    
    /**
     * Returns the provider evaluation
     * @return the provider evaluations
     */
    public String getProviderEvaluation(){
        evaluation = displaysEvaluation(totalServiceProvidersMeanScore, providerMeanScore, totalStandardDeviation);
        return evaluation;
    }
    
    /**
     * Returns the service provider abs deviation
     * @return the service provider abs deviation
     */
    public double getProviderAbsDeviation(){
        double serviceProviderAbsDeviation = calculateProviderAbsDeviation(providerMeanScore,totalServiceProvidersMeanScore);
         System.out.println (serviceProviderAbsDeviation);
         evat = serviceProvider.addEvaluation(providerMeanScore, evaluation);
        return serviceProviderAbsDeviation;
    }
    
    /**
     * Calculates the service provider abs deviation
     * @param providerMeanScore service provider mean score
     * @param totalServiceProvidersMeanScore the total service providers mean score 
     * @return the service provider abs deviation
     */
    private static double calculateProviderAbsDeviation (double providerMeanScore, double totalServiceProvidersMeanScore){
        return (Math.abs(providerMeanScore-totalServiceProvidersMeanScore));
    }
    
    /**
     * Calculates the service provider standard deviation
     * @param scoreList rate list of service provider
     * @param providerMeanScore service provider mean score
     * @return the service provider standard deviation
     */
    private double calculateProviderStandardDeviation(RatingRegister scoreList, double providerMeanScore){
        double variance = 0;
        double total = 0;
        List<Rating> scoreListConverted = scoreList.getScoreList();
        for (Rating score: scoreListConverted){
            double quadrado = score.getValue() - providerMeanScore;
            total = total + (quadrado*quadrado);
        }
        return Math.sqrt (total/scoreListConverted.size());
    }
    
    /**
     * Calculates the service provider evaluation
     * @param totalServiceProvidersMeanScore total service providers mean score
     * @param providerMeanScore provider mean score
     * @param totalStandardDeviation total service providers standard deviation
     * @return the service provider evaluation
     */
    public String displaysEvaluation(double totalServiceProvidersMeanScore, double providerMeanScore, double totalStandardDeviation){
        String worstClassification = "Worst Provider";
        String normalClassification = "Regular Provider";
        String outstandingClassification = "Outstanding Provider";
        double inferiorLimit = totalServiceProvidersMeanScore - totalStandardDeviation;
        double upperLimit = totalServiceProvidersMeanScore + totalStandardDeviation;
        if(providerMeanScore < inferiorLimit){
            return worstClassification;
        }else if (providerMeanScore > upperLimit){
            return outstandingClassification;
        }
        return normalClassification;
    }

    /**
     * Change the service provider evaluation
     * @param newServiceProviderEvaluation the new service provider evaluation
     * @return true or false if the evaluation is changed or not 
     */
    public boolean setEvaluation( String newServiceProviderEvaluation) {
        evat.setClassification(newServiceProviderEvaluation);
        if ((evat.getClassification().equals(newServiceProviderEvaluation))) {
            return true;
        }
        return false;
    }

    /**
     * Registers the service provider evaluation
     * @return true or false if the evaluation is registered or not
     */
    public boolean evaluationRegister() {
        return serviceProvider.evaluationValidate(evat);
    }

}
