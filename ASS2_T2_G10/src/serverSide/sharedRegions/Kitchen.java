package serverSide.sharedRegions;

import serverSide.main.*;
import clientSide.entities.ChefState;
import clientSide.entities.WaiterState;
import clientSide.stubs.GeneralReposStub;
import serverSide.entities.KitchenClientProxy;

/**
 * 	Kitchen
 * 
 * 	It is responsible for keeping track of portions prepared and delivered.
 *      Is implemented as an implicit monitor.
 *      All public methods are executed in mutual exclusion.
 *	Synchronisation points include:
 *		Chef has to wait for the note that describes the order given by the waiter
 *		Chef has to wait for waiter to collect portions
 *		Waiter has to wait for chef to start preparing the order
 *		Waiter has to wait for portions from the chef
 *
 * @author Rafael Dias
 * @author Manuel Couto
 */

public class Kitchen
{
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
 * Reference to the stub of the General Repository.
 */
private final GeneralReposStub reposStub;

/**
 * Number of entities that requested shutdown
 */
private int nEntities;	

/**
 * Kitchen instantiation
 * 
 * @param reposStub reference to general repository
 */
    public Kitchen(GeneralReposStub reposStub)
    {
            this.numberOfPortionsReady = 0;
            this.numberOfPortionsDelivered = 0;
            this.numberOfCoursesDelivered = 0;
            this.reposStub = reposStub;
            this.nEntities = 0;
    }

    /**
     * 	Operation watch the news
     */
    public synchronized void watchTheNews()
    {
            //Set chef state
            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.WAITING_FOR_AN_ORDER);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

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
     */
    public synchronized void startPreparation()
    {
            //Update new Chef State
            reposStub.setnCourses(numberOfCoursesDelivered+1);
            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.PREPARING_THE_COURSE);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

            //Notify Waiter that the preparation of the order has started
            notifyAll();
    }

    /**
     * 	Operation proceed presentation
     */
    public synchronized void proceedPreparation()
    {
            //Update new Chef state
            numberOfPortionsPrepared++;
            reposStub.setnPortions(numberOfPortionsPrepared);
            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.DISHING_THE_PORTIONS);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

            //Update numberOfPortionsReady
            numberOfPortionsReady++;
    }


    /**
     * 	Operation have all portions been delivered
     * 	@return true if all portions have been delivered, false otherwise
     */
    public synchronized boolean haveAllPortionsBeenDelivered()
    {
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
     *	Operation has order been completed
     * 	@return true if all courses have been completed, false or not
     */
    public synchronized boolean hasOrderBeenCompleted()
    {
            //Check if all courses have been delivered
            if (numberOfCoursesDelivered == ExecConst.NCourses)
                    return true;
            return false;
    }

    /**
     * 	Operation continue preparation
     */
    public synchronized void continuePreparation()
    {
            //Update chefs state
            reposStub.setnCourses(numberOfCoursesDelivered+1);
            numberOfPortionsPrepared = 0;
            reposStub.setnPortions(numberOfPortionsPrepared);

            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.PREPARING_THE_COURSE);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());
    }


    /**
     * Operation have next portion ready
     */
    public synchronized void haveNextPortionReady()
    {	
            //Update chefs state
            numberOfPortionsPrepared++;		
            reposStub.setnPortions(numberOfPortionsPrepared);
            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.DISHING_THE_PORTIONS);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

            //Update numberOfPortionsReady
            numberOfPortionsReady++;

            //Update chefs state
            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.DELIVERING_THE_PORTIONS);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());

            //Notify Waiter that there is a portion waiting to be delivered
            notifyAll();
    }

    /**
     * Operation clean up
     */
    public synchronized void cleanUp()
    {	
            //Update chefs state to terminate life cycle
            ((KitchenClientProxy) Thread.currentThread()).setChefState(ChefState.CLOSING_SERVICE);
            reposStub.setChefState(((KitchenClientProxy) Thread.currentThread()).getChefState());
    }


    /**
     * Operation hand note to chef
     */	
    public synchronized void handNoteToChef()
    {		
            //Update waiter state
            ((KitchenClientProxy) Thread.currentThread()).setWaiterState(WaiterState.PLACING_THE_ORDER);
            reposStub.setWaiterState(((KitchenClientProxy) Thread.currentThread()).getWaiterState());

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
     */
    public synchronized void returnToBar()
    {
            //Update waiter state
            ((KitchenClientProxy) Thread.currentThread()).setWaiterState(WaiterState.APPRAISING_SITUATION);
            reposStub.setWaiterState(((KitchenClientProxy) Thread.currentThread()).getWaiterState());
    }

    /**
     * Operation collect portion
     */
    public synchronized void collectPortion()
    {
            ((KitchenClientProxy) Thread.currentThread()).setWaiterState(WaiterState.WAITING_FOR_PORTION);
            reposStub.setWaiterState(((KitchenClientProxy) Thread.currentThread()).getWaiterState());

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
            if(numberOfPortionsDelivered > ExecConst.Nstudents)
                    numberOfPortionsDelivered = 1;

            //Update portion number and course number in general repository
            reposStub.setnPortions(numberOfPortionsDelivered);
            reposStub.setnCourses(numberOfCoursesDelivered+1);

            //Signal chef that portion was delivered
            notifyAll();

    }

    /**
     * Operation kitchen server shutdown
     */
    public synchronized void shutdown()
    {
            nEntities += 1;
            if(nEntities >= ExecConst.NShutKBT)
                    ServerRestaurantKitchen.waitConnection = false;
            notifyAll ();
    }
}
