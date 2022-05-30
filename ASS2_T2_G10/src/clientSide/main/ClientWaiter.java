package clientSide.main;

import clientSide.entities.Waiter;
import clientSide.stubs.*;
import genclass.GenericIO;

/**
 *    Client side of the Assignment 2 - Waiter.
 *    Static solution Attempt (number of threads controlled by global constants - ExecConst)
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 *  @author Rafael Dias
 *  @author Manuel Couto
 */
public class ClientWaiter {
	
	/**
	 *  Main method.
	 *
	 *    @param args runtime arguments
	 *        args[0] - name of the platform where is located the kitchen server
	 *        args[1] - port number for listening to service requests
	 *        args[2] - name of the platform where is located the bar server
	 *        args[3] - port number for listening to service requests
	 *        args[4] - name of the platform where is located the table server
	 *        args[5] - port number for listening to service requests
     *		  args[6] - name of the platform where is located the general repository server
	 *        args[7] - port number for listening to service requests
	 */
	public static void main(String[] args) {

		Waiter waiter;					//Waiter thread
		KitchenStub kitchenStub;			//remote reference to the kitchen stub
		BarStub barStub;				//remote reference to the bar stub
		TableStub tableStub;				//remote reference to the table stub
		GeneralReposStub genReposStub;	//remote reference to the general repository
		
		//Name of the platforms where kitchen and bar servers are located
		String kitchenServerHostName, barServerHostName, tableServerHostName, genRepoServerHostName;
		//Port numbers for listening to service requests
		int kitchenServerPortNumb = -1, barServerPortNumb = -1, tableServerPortNumb = -1, genRepoServerPortNumb = -1;
		
		/* Getting problem runtime parameters */
		if(args.length != 8) {
			GenericIO.writelnString ("Wrong number of parameters!");
			System.exit(1);
		}
		//Get kitchen parameters
		kitchenServerHostName = args[0];
		try {
			kitchenServerPortNumb = Integer.parseInt (args[1]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[1] is not a number!");
			System.exit(1);
		}
		if( (kitchenServerPortNumb < 22110) || (kitchenServerPortNumb > 22119) ) {
			GenericIO.writelnString ("args[1] is not a valid port number!");
			System.exit(1);			
		}
		
		//Get bar parameters
		barServerHostName = args[2];
		try {
			barServerPortNumb = Integer.parseInt (args[3]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[3] is not a number!");
			System.exit(1);
		}
		if( (barServerPortNumb < 22110) || (barServerPortNumb > 22119) ) {
			GenericIO.writelnString ("args[3] is not a valid port number!");
			System.exit(1);			
		}

		//Get table parameters
		tableServerHostName = args[4];
		try {
			tableServerPortNumb = Integer.parseInt (args[5]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[5] is not a number!");
			System.exit(1);
		}
		if( (tableServerPortNumb < 22110) || (tableServerPortNumb > 22119) ) {
			GenericIO.writelnString ("args[5] is not a valid port number!");
			System.exit(1);			
		}
		
		
		//Get general repo parameters
		genRepoServerHostName = args[6];
		try {
			genRepoServerPortNumb = Integer.parseInt (args[7]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[7] is not a number!");
			System.exit(1);
		}
		if( (genRepoServerPortNumb < 22110) || (genRepoServerPortNumb > 22119) ) {
			GenericIO.writelnString ("args[7] is not a valid port number!");
			System.exit(1);			
		}
		
		
		/* problem initialisation */
		kitchenStub = new KitchenStub(kitchenServerHostName, kitchenServerPortNumb);
		barStub = new BarStub(barServerHostName, barServerPortNumb);
		tableStub = new TableStub(tableServerHostName, tableServerPortNumb);
		genReposStub = new GeneralReposStub(genRepoServerHostName, genRepoServerPortNumb);
		waiter = new Waiter("waiter", kitchenStub, barStub, tableStub);
		
		/* start simulation */
		GenericIO.writelnString ("Launching Waiter Thread");
		waiter.start();
		
		/* waiting for the end of the simulation */
		try {
			waiter.join();
		}catch(InterruptedException e) {}
		GenericIO.writelnString ("The waiter thread has terminated.");
		barStub.shutdown();
		tableStub.shutdown();
		genReposStub.shutdown();

	}

}
