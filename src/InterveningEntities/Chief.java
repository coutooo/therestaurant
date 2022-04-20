/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

import sharedRegions.*;

/**
 * Chief thread:
 * Implements the life-cycle of a chief and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Chief extends Thread {
    
     private int id;
    
    private ChiefState currentState;
    
    private final Bar bar;
    
    private final Kitchen kitchen;
    
    private final Table table;
    
    
    public void run(){
        boolean firstCourse=true;
        Kitchen.watchTheNews();
        Kitchen.startPreparation();
        do{
            if(!firstCourse) Kitchen.continuePreparation();
            else firstCourse = false;
            
            Kitchen.proceedToPresentation();
            Bar.alertWaiter();
            
            while(!Kitchen.allPortionsDelivered()){
                Kitchen.haveNextPortionReady();
                Bar.alertWaiter();
            }
        }while(!orderBeenCompleted());
        Kitchen.cleanUp();
    }
    
    public int getChiefID() {
		return id;
	}
    
    public ChiefState getChiefState(){
        return currentState;
    }
    
    public void setState(ChiefState s) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		currentState = s;
	}

    private boolean orderBeenCompleted() {
        return true;
    }
    
    
    
    
}