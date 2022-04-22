/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

import sharedRegions.*;

/**
 * Waiter thread:
 * Implements the life-cycle of a waiter and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Waiter extends Thread {
    
    /**
     * 	Waiter state
    */
    private WaiterState currentState;
    
    /**
     *   Reference to Kitchen
     */
    private final Kitchen k;
    
    /**
     *   Reference to Bar
     */
    private final Bar b;
    
    /**
     *   Reference to Table
     */
    private final Table t;
    
    /**
     *   Instantiation of a Waiter thread.
     *
     *     @param name thread name
     *     @param k reference to the Kitchen
     *     @param b reference to the Bar
     *     @param t reference to the Table
     */
    public Waiter(String name, Kitchen k, Bar b, Table t){
        super(name);
        this.currentState = WaiterState.APPRAISING_SITUATION;
        this.k = k;
        this.b = b;
        this.t = t;
    }
    
    /**
     *   Life cycle of the Waiter.
     */
    @Override
    public void run (){
        //used to store the request that needs to be performed by the waiter
        char request;
        //used to check if simulation may stop or not
        boolean stop = false;

        while(true){
            request = b.lookAround();

            switch(request){
                case 'c':	//Client arriving, needs to be presented with the menu
                    t.saluteClient(b.getStudentBeingAnswered());
                    t.returnBar();
                    break;
                case 'o':	//Order will be described to the waiter
                    t.getThePad();
                    k.handNoteToChef();
                    k.returnToBar();
                    break;
                case 'p':	//Portions need to be collected and delivered
                    while(!t.haveAllClientsBeenServed()) {
                        k.collectPortion();
                        t.deliverPortion();
                    }
                    t.returnBar();
                    break;
                case 'b':	//Bill needs to be prepared so it can be payed by the student
                    b.preprareBill();
                    t.presentBill();
                    t.returnBar();
                    break;
                case 'g':	//Goodbye needs to be said to a student
                    stop = b.sayGoodbye();
                    break;
            }
            //If the last student has left the restaurant, life cycle may terminate
            if (stop)
                break;
        }
    }
     
    /**
     *   Set Waiter state.
     *
     *     @param state Waiter state
     */
    public void setWaiterState (WaiterState state)
    {
        currentState = state;
    }

    /**
     *   Get Waiter state.
     *
     *     @return Waiter state.
     */
    public WaiterState getWaiterState ()
    {
        return currentState;
    }
}