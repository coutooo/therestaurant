package clientSide.entities;

import interfaces.*;
import java.rmi.RemoteException;
import genclass.GenericIO;


/**
 *    Chef thread.
 *
 *      It simulates the barber life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on remote calls under Java RMI. 
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Chef extends Thread{
	
    /**
     *	Chef state 
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
     * Instantiation of a Chef thread
     * 	@param name thread name
     * 	@param kitchenStub reference to the kitchen stub
     * 	@param barStub reference to the bar stub
     */
    public Chef(String name, KitchenInterface kitchenStub, BarInterface barStub) {
            super(name);
            this.currentState = ChefState.WAITING_FOR_AN_ORDER;
            this.kitchenStub = kitchenStub;
            this.barStub = barStub;
    }

    /**
     * 	Life cycle of the chef
     */
    @Override
    public void run ()
    {
            boolean firstCourse = true;

            watchTheNews();
            startPreparation();
            do
            {
                    if(!firstCourse)
                            continuePreparation();
                    else
                            firstCourse = false;

                    proceedPreparation();
                    alertWaiter();

                    while(!haveAllPortionsBeenDelivered())
                            haveNextPortionReady();
            }
            while(!hasOrderBeenCompleted());

            cleanUp();
    }

    /**
    * Set Chef state.
    *
    * @param state new state to be set
    */
    public void setChefState(int state) {
        this.currentState = state;
    }

   /**
    * Get Chef state.
    *
    * @return chef state
    */
    public int getChefState() {
        return currentState;
    }
        
    /**
     * Operation watch the news Remote operation.
     */
    private void watchTheNews() {
        try {
            currentState = kitchenStub.watchTheNews();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on watchTheNews: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation start presentation Remote operation.
     */
    private void startPreparation() {
        try {
            currentState = kitchenStub.startPreparation();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on startPreparation: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation continue preparation Remote operation. 
     */
    private void continuePreparation() {
        try {
            currentState = kitchenStub.continuePreparation();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on continuePreparation: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation proceed presentation Remote operation. 
     */
    private void proceedPreparation() {
        try {
            currentState = kitchenStub.proceedPreparation();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on proceedPreparation: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation alert the waiter Remote operation.
     */
    private void alertWaiter() {
        try {
            currentState = barStub.alertWaiter();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on alertWaiter: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation have all portions been delivered Remote operation.
     * @return true if all portions have been delivered, false otherwise
     */
    private boolean haveAllPortionsBeenDelivered() {
        boolean bValue = false;
        try {
            bValue = kitchenStub.haveAllPortionsBeenDelivered();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on haveAllPortionsBeenDelivered: " + e.getMessage());
            System.exit(1);
        }
        return bValue;
    }

    /**
     * Operation have next portion ready Remote operation. 
     */
    private void haveNextPortionReady() {
        try {
            currentState = kitchenStub.haveNextPortionReady();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on haveNextPortionReady: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation has order been completed Remote operation. 
     * @return true if all courses have been completed, false or not
     */
    private boolean hasOrderBeenCompleted() {
        boolean bValue = false;
        try {
            bValue = kitchenStub.hasOrderBeenCompleted();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception onhasOrderBeenCompleted: " + e.getMessage());
            System.exit(1);
        }
        return bValue;
    }

    /**
     * Operation clean up Remote operation.
     */
    private void cleanUp() {
        try {
            currentState = kitchenStub.cleanUp();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef remote exception on cleanUp: " + e.getMessage());
            System.exit(1);
        }
    }
}
