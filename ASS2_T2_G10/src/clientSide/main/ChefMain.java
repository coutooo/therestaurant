/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.main;

import clientSide.entities.Chef;
import clientSide.stubs.*;
import genclass.GenericIO;

/**
 *    Client side of the Assignment 2 - Chef.
 *    Static solution Attempt (number of threads controlled by global constants - ExecConst)
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 *  @author Rafael Dias
 *  @author Manuel Couto
 */

public class ChefMain {
	
    /**
	 *  Main method.
	 *
	 *    @param args runtime arguments
	 *        args[0] - name of the platform where is located the Kitchen server
	 *        args[1] - port number for listening to service requests
         *        args[2] - name of the platform where is located the Bar server
	 *        args[3] - port number for listening to service requests
	 *        args[4] - name of the platform where is located the General Repository server
	 *        args[5] - port number for listening to service requests
	 */
    public static void main(String[] args) {
        String barServerHostName;                              // name of the platform where is located the barber shop server
        int barServerPortNum = -1;                             // port number for listening to service requests
        String kitchenServerHostName;                          // name of the platform where is located the kitchen server
        int kitchenServerPortNum = -1;                         // port number for listening to service requests
        Chef chef;                                             // remote reference to the chef                                     
        BarStub bar;                                           // remote reference to the bar
        KitchenStub kitchen;                                   // remote reference to the kitchen
        GeneralReposStub genReposStub;
        int genRepoServerPortNum = -1;
        String genRepoServerHostName;
        
        
        	/* Getting problem runtime parameters */
		if(args.length != 6) {
			GenericIO.writelnString ("Wrong number of parameters!");
			System.exit(1);
		}
		//Get kitchen parameters
		kitchenServerHostName = args[0];
		try {
			kitchenServerPortNum = Integer.parseInt (args[1]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[1] is not a number!");
			System.exit(1);
		}
		if( (kitchenServerPortNum < 22110) || (kitchenServerPortNum > 22119) ) {
			GenericIO.writelnString ("args[1] is not a valid port number!");
			System.exit(1);			
		}
		
		//Get bar parameters
		barServerHostName = args[2];
		try {
			barServerPortNum = Integer.parseInt (args[3]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[3] is not a number!");
			System.exit(1);
		}
		if( (barServerPortNum < 22110) || (barServerPortNum > 22119) ) {
			GenericIO.writelnString ("args[3] is not a valid port number!");
			System.exit(1);			
		}
		
		//Get general repo parameters
		genRepoServerHostName = args[4];
		try {
			genRepoServerPortNum = Integer.parseInt (args[5]);
		} catch (NumberFormatException e) {
			GenericIO.writelnString ("args[5] is not a number!");
			System.exit(1);
		}
		if( (genRepoServerPortNum < 22110) || (genRepoServerPortNum > 22119) ) {
			GenericIO.writelnString ("args[5] is not a valid port number!");
			System.exit(1);			
		}
		
		
		/* problem initialisation */
		kitchen = new KitchenStub(kitchenServerHostName, kitchenServerPortNum);
		bar = new BarStub(barServerHostName, barServerPortNum);
		genReposStub = new GeneralReposStub(genRepoServerHostName, genRepoServerPortNum);
		chef = new Chef("chef", kitchen, bar);
		
		/* start simulation */
		GenericIO.writelnString ("Launching Chef Thread ");
		chef.start();
		
		/* waiting for the end of the simulation */
		try {
                    chef.join();
		}catch(InterruptedException e) {}
		GenericIO.writelnString ("The chef thread has terminated.");
		kitchen.shutdown();
		genReposStub.shutdown();
		
	}
}