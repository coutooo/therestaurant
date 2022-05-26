/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

import clientSide.stubs.*;

/**
 *    Chef thread.
 * 
 *      It simulates the chef life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Chef extends Thread {
    
    /**
     *   Chef state
     */
    private int currentState;
    
    /**
     *  Reference to the stub of the bar.
     */
    private final BarStub barStub;
    
    /**
     *  Reference to the stub of the kitchen.
     */
    private final KitchenStub kitchenStub;
    
    /**
     *   Instantiation of a Chef thread.
     *
     *     @param name thread name
     *     @param kitchenStub reference to the Kitchen     
     *     @param barStub reference to the Bar
     */
    public Chef(String name, KitchenStub kitchenStub, BarStub barStub){
        super(name);
        this.currentState = ChefState.WAITING_FOR_AN_ORDER;
        this.kitchenStub = kitchenStub;
        this.barStub = barStub;
    }
    
    /**
     *   Life cycle of the Chef.
     */
    @Override
    public void run (){
        boolean firstCourse = true;

        kitchenStub.watchTheNews();
        kitchenStub.startPreparation();
        do
        {
            if(!firstCourse)
                kitchenStub.continuePreparation();
            else
                firstCourse = false;

            kitchenStub.proceedToPreparation();   // ou continuePreparation?
            barStub.alertWaiter();

            while(!kitchenStub.haveAllPortionsBeenDelivered())
                kitchenStub.haveNextPortionReady();
        }
        while(!kitchenStub.hasOrderBeenCompleted());

        kitchenStub.cleanUp();
    }
    
    /**
     *   Set Chef state.
     *
     *     @param state Chef state
     */
    public void setChefState (int state){
        currentState = state;
    }

    /**
     *   Get Chef state.
     *
     *     @return Chef state.
     */
    public int getChefState (){
        return currentState;
    }    
}