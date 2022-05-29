package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;


/** 
 * Kitchen
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Kitchen{
    
    /**
     *	Number of portions ready
     */
    private int numberOfPortionsReady;

    /**
     *	Number of portions delivered in at each course
     */
    private int numberOfPortionsDelivered;

    /**
     *	Number of courses delivered
     */
    private int numberOfCoursesDelivered;
    
    /**
     * Number of portions prepared by the chef
     */
    private int numberOfPortionsPrepared;

    /**
    * Reference to the General Repository.
    */
    private final GeneralReposStub repos;
    
    /**
    *   Number of entity groups requesting the shutdown.
    */
    private int nEntities;

    /**
     * 	Kitchen instantiation
     * @param repos reference to general repository
     */
    public Kitchen(GeneralReposStub repos){
        this.numberOfPortionsReady = 0;
        this.numberOfPortionsDelivered = 0;
        this.numberOfCoursesDelivered = 0;
        this.repos = repos;
        this.nEntities = 0;

    }

    /**
     * 	Operation watch the news
     * 
     * 	It is called by the chef while waiting to be notified by the waiter to give the order
     */
    public synchronized void watchTheNews(){
        //Set chef state
        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.WAITING_FOR_AN_ORDER);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

        //Block waiting for waiter to notify of the order
        try {
                wait();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }



    /**
     * 	Operation start presentation
     * 
     * 	It is called by the chef after waiter has notified him of the order to be prepared
     */
    public synchronized void startPreparation(){
        repos.setnCourses(numberOfCoursesDelivered + 1);
        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.PREPARING_THE_COURSE);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

        //Notify Waiter that the preparation of the order has started
        notifyAll();
    }

    /**
     * 	Operation proceed presentation
     * 
     * 	It is called by the chef when a portion needs to be prepared
     */
    public synchronized void proceedPreparation(){
        //Update new Chef state
        numberOfPortionsPrepared++;
        repos.setnPortions(numberOfPortionsPrepared);
        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.DISHING_THE_PORTIONS);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

        //Update numberOfPortionsReady
        numberOfPortionsReady++;
    }

    /**
     * 	Operation have all portions been delivered
     * 
     * 	It is called by the chef when he finishes a portion and checks if another one needs to be prepared or not.
     * 	It is also here were the chef blocks waiting for waiter do deliver the current portion
     *  @return true if all portions have been delivered, false otherwise
     */
    public synchronized boolean haveAllPortionsBeenDelivered(){
        //Wait for waiter to collect the portion
        while( numberOfPortionsReady != 0) {
            try {
                    wait();
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
        }

        //Check if all portions of the course have been delivered or not
        if(numberOfPortionsDelivered == ExecConst.Nstudents) 
        {
                //If all portions have been delivered means that a course was completed
                numberOfCoursesDelivered++;
                return true;
        }
        return false;
    }

    /**
     * 	Operation has order been completed
     * 
     * 	It is called by the chef when he finishes preparing all courses
     * 	@return true if all courses have been completed, false or not
     */
    public synchronized boolean hasOrderBeenCompleted(){
        //Check if all courses have been delivered
        if (numberOfCoursesDelivered == ExecConst.Ncourses)
            return true;
        return false;
    }

    /**
     * 	Operation continue preparation
     * 
     * 	It is called by the chef when not all portions have been delivered
     */
    public synchronized void continuePreparation(){
        repos.setnCourses(numberOfCoursesDelivered+1);
        numberOfPortionsPrepared = 0;
        repos.setnPortions(numberOfPortionsPrepared);

        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.PREPARING_THE_COURSE);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());
    }

    /**
     * Operation have next portion ready
     * 
     * It is called by the chef after a portion has been delivered and another one needs to be prepared
     */
    public synchronized void haveNextPortionReady(){	
        numberOfPortionsPrepared++;		
        repos.setnPortions(numberOfPortionsPrepared);
        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.DISHING_THE_PORTIONS);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

        //Update numberOfPortionsReady
        numberOfPortionsReady++;

        //Update chefs state
        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.DELIVERING_THE_PORTIONS);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

        //Notify Waiter that there is a portion waiting to be delivered
        notifyAll();
    }

    /**
     * Operation clean up
     * 
     * It is called by the chef when he finishes the order
     */
    public synchronized void cleanUp(){	
        //Update chefs state to terminate life cycle
        ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.CLOSING_SERVICE);
        repos.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());
    }

    /**
     * Operation hand note to chef
     * 
     * Called by the waiter to Wake chef up chef to give him the description of the order
     */
    public synchronized void handNoteToChef(){		
        //Update waiter state
        ((KitchenClientProxy) Thread.currentThread()).setWaiterState(WaiterState.PLACING_THE_ORDER);
        repos.setWaiterState(((KitchenClientProxy) Thread.currentThread()).getWaiterState());

        //Notify chef that he can start the preparation of the order
        notifyAll();

        //Block waiting for chef to start the preparation of the order
        try {
                wait();
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
    
    /**
     * Operation return to the bar
     * 
     * Called by the waiter when he is the kitchen to return to the bar
     */
    public synchronized void returnToBar(){
        //Update waiter state
        ((KitchenClientProxy) Thread.currentThread()).setWaiterState(WaiterState.APPRAISING_SITUATION);
        repos.setWaiterState(((KitchenClientProxy) Thread.currentThread()).getWaiterState());
    }

    /**
     * Operation collect portion
     * 
     * Called by the waiter when there is a portion to be delivered. Signal chef that the portion was delivered
     */
    public synchronized void collectPortion()
    {
        ((KitchenClientProxy) Thread.currentThread()).setWaiterState(WaiterState.WAITING_FOR_PORTION);
        repos.setWaiterState(((KitchenClientProxy) Thread.currentThread()).getWaiterState());

        //If there are no portions to deliver waiter must block
        while (numberOfPortionsReady == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //Update number of portions ready and delivered
        numberOfPortionsReady--;
        numberOfPortionsDelivered++;

        //If a new course is being delivered then numberOfPortionsDelivered must be "reseted"
        if (numberOfPortionsDelivered > ExecConst.Nstudents) {
            numberOfPortionsDelivered = 1;
        }

        //Update portion number and course number in general repository
        repos.setnPortions(numberOfPortionsDelivered);
        repos.setnCourses(numberOfCoursesDelivered + 1);

        //Signal chef that portion was delivered
        notifyAll();

    }
    /**
    *   Operation server shutdown.
    *
    *   New operation.
    */
    public synchronized void shutdown() {
        nEntities += 1;
        if (nEntities >= ExecConst.NentitiesToShutKBT)
           ServerRestaurantKitchen.waitConnection = false;
        notifyAll ();
    }
}
