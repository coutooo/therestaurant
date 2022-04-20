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
    
    /**
     *   Chief identification
     */
    private int chief_id;
    
    /**
     *   Chief state
     */
    private ChiefState currentState;
    
    /**
     *   Reference to Bar
     */
    private final Bar b;
    
    /**
     *   Reference to Kitchen
     */
    private final Kitchen k;
    
    /**
     *   Instantiation of a Chief thread.
     *
     *     @param name thread name
     *     @param chief_id chief id
     *     @param k reference to the Kitchen     
     *     @param b reference to the Bar
     */
    public Chief(String name, int chief_id, Kitchen k, Bar b){
        super(name);
        this.chief_id = chief_id;
        this.currentState = ChiefState.WAITING_FOR_AN_ORDER;
        this.k = k;
        this.b = b;
    }
    
    /**
     *   Life cycle of the Chief.
     */
    @Override
    public void run(){
        boolean firstCourse=true;
        k.watchTheNews();
        k.startPreparation();
        do{
            if(!firstCourse) k.continuePreparation();
            else firstCourse = false;
            
            k.proceedToPresentation();
            b.alertWaiter();
            
            while(!k.allPortionsDelivered()){
                k.haveNextPortionReady();
                b.alertWaiter();
            }
        }while(!orderBeenCompleted());
        k.cleanUp();
    }
    
    /**
     *   Set Chief id.
     *
     *     @param id Chief id
     */
    public void setChiefID(int id){
        chief_id = id;
    }
    
    /**
     *   Get Chief id.
     *
     *     @return Chief id
     */
    public int getChiefID() {
        return chief_id;
    }
    
    /**
     *   Set Chief state.
     *
     *     @param state Chief state
     */
    public void setChiefState (ChiefState state)
    {
        currentState = state;
    }

    /**
     *   Get Chief state.
     *
     *     @return Chief state.
     */
    public ChiefState getChiefState ()
    {
        return currentState;
    }

    private boolean orderBeenCompleted() {
        if(Kitchen.getNCourses() == 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    
    
}