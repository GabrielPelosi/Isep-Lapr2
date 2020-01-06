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
public class AnalyzeExecutionOrders {

    public AnalyzeExecutionOrders() {
    }
    
    public List<ExecutionOrder>  ordenateAll(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1 = new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.toStringOrdenade();
                String d2=o2.toStringOrdenade();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;}
    
    public List<ExecutionOrder>  ordenateName(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1 = new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.getName();
                String d2=o2.getName();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;} 
    
    public List<ExecutionOrder>  ordenateDistance(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1 = new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.getDistance();
                String d2=o2.getDistance();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;} 
    
    public List<ExecutionOrder>  ordenateCategory(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1 = new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.getCategory();
                String d2=o2.getCategory();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;} 
    
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
    
    public List<ExecutionOrder>  ordenateService(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1 = new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.getServiceType();
                String d2=o2.getServiceType();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;} 
    
    public List<ExecutionOrder>  ordenateAddress(List<ExecutionOrder> listExecutionOrders){
    
        Comparator<ExecutionOrder> criterio1 = new Comparator<ExecutionOrder>() {

            @Override
            public int compare(ExecutionOrder o1, ExecutionOrder o2) {
                String d1=o1.getpAddress().toStringValidate();
                String d2=o2.getpAddress().toStringValidate();
                if(d1.compareTo(d2)>0) return 1;
                else return -1;  
            }
        };
        Collections.sort(listExecutionOrders, criterio1);    
    return listExecutionOrders;} 
    
}
