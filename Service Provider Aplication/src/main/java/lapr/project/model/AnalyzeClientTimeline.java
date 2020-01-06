/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class AnalyzeClientTimeline {
   
     public List<ExecutionOrder>  ordenateDate(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1= new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.getDate().toAnoMesDiaString()+o1.getStartTime();
                String d2=o2.getDate().toAnoMesDiaString()+o1.getStartTime();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;}
     
}
