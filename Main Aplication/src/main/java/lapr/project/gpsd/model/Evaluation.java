/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

/**
 *
 * @author Francisco Ferreira
 */
public class Evaluation {
    
    /**
     * The mean rate of SP
     */
    private double meanScore;
    
    /**
     * The Evaluation classification
     */
    private String strClassification;
    
    /**
     * Constructs a Evaluation instance by getting the meanScore and classification for that evaluation
     * @param meanScore the meanScore evaluation
     * @param classification the classification evaluation
     */
    public Evaluation (double meanScore, String classification){
         if ((classification == null) || (classification.isEmpty()) || (meanScore <= 0)) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }
        // if ((!classification.trim().equalsIgnoreCase ("Regular Provider")) || (!classification.trim().equalsIgnoreCase ("Worst Provider")) || (!classification.trim().equalsIgnoreCase ("Outstanding Provider"))){
             //throw new IllegalArgumentException ("Classfication error");
        // }
        this.meanScore = meanScore;
        this.strClassification = classification;
    }
   
    /**
     * Returns the evaluation mean score
     * @return the evaluation mean score
     */
    public double getMeanScore(){
        return meanScore;
    }

    /**
     * Returns the evaluation classification
     * @return 
     */
    public String getClassification(){
        return strClassification;
    } 
    
    /**
     * Change the evaluation mean score
     * @param newMeanScore new evaluation mean score 
     */
    public void setMeanScore (double newMeanScore){
       this.meanScore = newMeanScore;
    }
  
    /**
     * Change the evaluation classification
     * @param newEvaluation new evaluation classification
     */
    public void setClassification (String newEvaluation){
        this.strClassification = newEvaluation;
    }
    
    
    /**
     * Returns true or false if evaluation is equals evaluation(parameter)
     * @param evaluation evaluation to compare
     * @return true or false if evaluation is equals evaluation(parameter)
     */
    public boolean equals (Evaluation evaluation){

        if (this == evaluation) {
            return true;
        }

        if (evaluation == null) {
            return false;
        }
        
        if (meanScore == evaluation.getMeanScore() || strClassification.equals (evaluation.getClassification())){
            return true;
        }
        return false;
    }
    
}
