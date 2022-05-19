/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

import clientSide.stub.*;

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
     *   Chef state
     */
    private ChefState currentState;
    
    /**
     *   Reference to Bar
     */
    private final BarStub b;
    
    /**
     *   Reference to Kitchen
     */
    private final KitchenStub k;
    
    /**
     *   Instantiation of a Chef thread.
     *
     *     @param name thread name
     *     @param k reference to the Kitchen     
     *     @param b reference to the Bar
     */
    public Chef(String name, KitchenStub k, BarStub b){
        super(name);
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