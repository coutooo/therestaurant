package clientSide.entities;

import interfaces.BarInterface;
import interfaces.KitchenInterface;


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
		
		kitchenStub.watchTheNews();
		kitchenStub.startPreparation();
		do
		{
			if(!firstCourse)
				kitchenStub.continuePreparation();
			else
				firstCourse = false;
			
			kitchenStub.proceedPreparation();
			barStub.alertWaiter();
			
			while(!kitchenStub.haveAllPortionsBeenDelivered())
				kitchenStub.haveNextPortionReady();
		}
		while(!kitchenStub.hasOrderBeenCompleted());
		
		kitchenStub.cleanUp();
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

}
