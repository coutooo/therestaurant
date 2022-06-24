package clientSide.entities;

import java.rmi.RemoteException;

import genclass.GenericIO;
import interfaces.*;

/**
 *    Waiter thread.
 *
 *      It simulates the waiter life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Waiter extends Thread{

    /**
     * 	Waiter state
     */
    private int currentState;

    /**
     * Reference to the stub of the kitchen
     */
    private final KitchenInterface kitchenStub;

    /**
     * Reference to the stub of the bar
     */
    private final BarInterface barStub;

    /**
     * Reference to the stub of the table
     */
    private final TableInterface tableStub;


    /**
     * Instantiation of a Waiter thread
     * 
     * 	@param name thread name
     * 	@param kitchenStub reference to the stub of the kitchen
     * 	@param barStub reference to the stub of the bar
     * 	@param tableStub reference to the stub of the table
     */
    public Waiter(String name, KitchenInterface kitchenStub, BarInterface barStub, TableInterface tableStub) {
            super(name);
            this.currentState = WaiterState.APPRAISING_SITUATION;
            this.kitchenStub = kitchenStub;
            this.barStub = barStub;
            this.tableStub = tableStub;
    }

    /**
     *	Life cycle of the waiter
     */
    @Override
    public void run ()
    {
        //used to store the request that needs to be performed by the waiter
        char request;
        //used to check if simulation may stop or not
        boolean stop = false;

        while(true)
        {
            request = lookAround();
            switch(request)
            {
                case 'c':	//Client arriving, needs to be presented with the menu
                    saluteClient(getStudentBeingAnswered());
                    returnBar();
                    break;
                case 'o':	//Order will be described to the waiter
                    getThePad();
                    handNoteToChef();
                    returnToBar();
                    break;
                case 'p':	//Portions need to be collected and delivered
                    while(!haveAllClientsBeenServed()) 
                    {
                        collectPortion();
                        deliverPortion();
                    }
                    returnBar();
                    break;
                case 'b':	//Bill needs to be prepared so it can be payed by the student
                    prepareBill();
                    presentBill();
                    returnBar();
                    break;
                case 'g':	//Goodbye needs to be said to a student
                    stop = sayGoodbye();
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

    /**
     * Operation look Around Remote operation.
     *
     * @return Character that represents the service to be executed 
     */
    private char lookAround() {
        char c = '\0';
        try {
            c = barStub.lookAround();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on lookAround: " + e.getMessage());
            System.exit(1);
        }
        if (c != 'c' && c != 'o' && c != 'p' && c != 'b' && c != 'g') {
            GenericIO.writelnString("Invalid service type!");
            System.exit(1);
        }
        return c;
    }

    /**
     * Operation salute the client Remote operation. 
     *
     * @param studentIdBeingAnswered id of the student whose request is being
     * answered
     */
    private void saluteClient(int studentIdBeingAnswered) {
        try {
            currentState = tableStub.saluteClient(studentIdBeingAnswered);
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on saluteClient: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Return id of the student whose request is being answered Remote
     * operation.
     *
     * @return Id of the student whose request is being answered
     */
    private int getStudentBeingAnswered() {
        int studentId = -1;
        try {
            studentId = barStub.getStudentBeingAnswered();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on getStudentBeingAnswered: " + e.getMessage());
            System.exit(1);
        }
        if (studentId == -1) {
            GenericIO.writelnString("Invalid student id!");
            System.exit(1);
        }

        return studentId;
    }

    /**
     * Operation return to the bar Remote operation. 
     */
    private void returnBar() {
        try {
            currentState = kitchenStub.returnToBar();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on kitReturnToBar: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation return to the bar Remote operation. 
     */
    private void returnToBar() {
        try {
            currentState = tableStub.returnBar();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on tabReturnToBar: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation get the pad Remote operation.
     */
    private void getThePad() {
        try {
            currentState = tableStub.getThePad();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on getThePad: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation hand note to chef Remote operation. 
     */
    private void handNoteToChef() {
        try {
            currentState = kitchenStub.handNoteToChef();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on handNoteToChef: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation have all clients been served Remote operation.
     *
     * @return true if all clients have been served, false otherwise
     */
    private boolean haveAllClientsBeenServed() {
        boolean value = false;
        try {
            value = tableStub.haveAllClientsBeenServed();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on haveAllClientsBeenServed: " + e.getMessage());
            System.exit(1);
        }
        return value;
    }

    /**
     * Operation collect portion Remote operation. 
     */
    private void collectPortion() {
        try {
            currentState = kitchenStub.collectPortion();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on collectPortion: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation deliver portion Remote operation. 
     */
    private void deliverPortion() {
        try {
            tableStub.deliverPortion();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on deliverPortion: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation prepare the Bill Remote operation. 
     */
    private void prepareBill() {
        try {
            currentState = barStub.prepareBill();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on prepareBill: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation present the bill Remote operation.
     */
    private void presentBill() {
        try {
            currentState = tableStub.presentBill();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on presentBill: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation say Goodbye Remote operation 
     *
     * @return true if there are no more students at the restaurant, false
     * otherwise
     */
    private boolean sayGoodbye() {
        boolean bValue = false;
        try {
            bValue = barStub.sayGoodbye();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter remote exception on sayGoodbye: " + e.getMessage());
            System.exit(1);
        }
        return bValue;
    }
}
