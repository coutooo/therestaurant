/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

import sharedRegions.*;

/**
 * Chef thread:
 * Implements the life-cycle of a Chef and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Chef extends Thread {
    
    /**
     *   Chef identification
     */
    private int Chef_id;
    
    /**
     *   Chef state
     */
    private ChefState currentState;
    
    /**
     *   Reference to Bar
     */
    private final Bar b;
    
    /**
     *   Reference to Kitchen
     */
    private final Kitchen k;
    
    /**
     *   Instantiation of a Chef thread.
     *
     *     @param name thread name
     *     @param Chef_id Chef id
     *     @param k reference to the Kitchen     
     *     @param b reference to the Bar
     */
    public Chef(String name, int Chef_id, Kitchen k, Bar b){
        super(name);
        this.Chef_id = Chef_id;
        this.currentState = ChefState.WAITING_FOR_AN_ORDER;
        this.k = k;
        this.b = b;
    }
    
    /**
     *   Life cycle of the Chef.
     */
    @Override
    public void run (){
        boolean firstCourse = true;

        k.watchTheNews();
        k.startPreparation();
        do
        {
            if(!firstCourse)
                k.continuePreparation();
            else
                firstCourse = false;

            k.proceedPreparation();
            b.alertWaiter();

            while(!k.haveAllPortionsBeenDelivered())
                k.haveNextPortionReady();
        }
        while(!k.hasOrderBeenCompleted());

        k.cleanUp();
    }
    
    /**
     *   Set Chef id.
     *
     *     @param id Chef id
     */
    public void setChefID(int id){
        Chef_id = id;
    }
    
    /**
     *   Get Chef id.
     *
     *     @return Chef id
     */
    public int getChefID() {
        return Chef_id;
    }
    
    /**
     *   Set Chef state.
     *
     *     @param state Chef state
     */
    public void setChefState (ChefState state){
        currentState = state;
    }

    /**
     *   Get Chef state.
     *
     *     @return Chef state.
     */
    public ChefState getChefState (){
        return currentState;
    }    
}