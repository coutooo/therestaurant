/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

import clientSide.stubs.*;


/**
 *    Waiter thread.
 * 
 *      It simulates the waiter life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Waiter extends Thread {
    
    /**
     * 	Waiter state
    */
    private int currentState;
    
    /**
     *  Reference to the stub of the kitchen.
     */
    private final KitchenStub kitchenStub;
    
    /**
     *  Reference to the stub of the bar.
     */
    private final BarStub barStub;
    
    /**
     *  Reference to the stub of the table.
     */
    private final TableStub tableStub;
    
    
    
    /**
     *   Instantiation of a Waiter thread.
     *
     *     @param name thread name
     *     @param kitchenStub reference to the Kitchen
     *     @param barStub reference to the Bar
     *     @param tableStub reference to the Table
     */
    public Waiter(String name, KitchenStub kitchenStub, BarStub barStub, TableStub tableStub){
        super(name);
        this.currentState = WaiterState.APPRAISING_SITUATION;
        this.kitchenStub = kitchenStub;
        this.barStub = barStub;
        this.tableStub = tableStub;
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
            request = barStub.lookAround();

            switch(request){
                case 'c':	//Client arriving, needs to be presented with the menu
                    tableStub.saluteClient(barStub.getStudentBeingAnswered());
                    tableStub.returnBar();
                    break;
                case 'o':	//Order will be described to the waiter
                    tableStub.getThePad();
                    kitchenStub.handNoteToChef();
                    kitchenStub.returnToBar();
                    break;
                case 'p':	//Portions need to be collected and delivered
                    while(!tableStub.haveAllClientsBeenServed()) {
                        kitchenStub.collectPortion();
                        tableStub.deliverPortion();
                    }
                    tableStub.returnBar();
                    break;
                case 'b':	//Bill needs to be prepared so it can be payed by the student
                    barStub.preprareBill();
                    tableStub.presentBill();
                    tableStub.returnBar();
                    break;
                case 'g':	//Goodbye needs to be said to a student
                    stop = barStub.sayGoodbye();
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
    public void setWaiterState (int state)
    {
        this.currentState = state;
    }

    /**
     *   Get Waiter state.
     *
     *     @return Waiter state.
     */
    public int getWaiterState ()
    {
        return this.currentState;
    }
}