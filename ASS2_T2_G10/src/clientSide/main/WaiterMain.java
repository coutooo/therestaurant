	/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.main;

import clientSide.entities.Waiter;
import clientSide.stubs.BarStub;
import clientSide.stubs.GeneralReposStub;
import clientSide.stubs.KitchenStub;
import clientSide.stubs.TableStub;
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
public class WaiterMain {
	
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
     *	      args[6] - name of the platform where is located the general repository server
     *        args[7] - port number for listening to service requests
     */
    public static void main(String[] args) {


        String barServerHostName;                               // name of the platform where is located the barber shop server
        int barServerPortNum = -1;                             // port number for listening to service requests
        String tableServerHostName;
        int tableServerPortNum = -1;
        String genReposServerHostName;                                 // name of the platform where is located the general repository server
        int genReposServerPortNum = -1;                               // port number for listening to service requests                                    
        String kitchenServerHostName;                                 // name of the platform where is located the kitchen server
        int kitchenServerPortNum = -1;                               // port number for listening to service requests
        BarStub bar;													// remote reference to the bar
        TableStub table;
        KitchenStub kitchen;
        Waiter waiter;
        GeneralReposStub genReposStub;								// remote reference to the general repository


        /* Getting problem runtime parameters */
        if (args.length != 8) {
            GenericIO.writelnString("Wrong number of parameters!");
            System.exit(1);
        }
        //Get kitchen parameters
        kitchenServerHostName = args[0];
        try {
            kitchenServerPortNum = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[1] is not a number!");
            System.exit(1);
        }
        if ((kitchenServerPortNum < 22110) || (kitchenServerPortNum > 22119)) {
            GenericIO.writelnString("args[1] is not a valid port number!");
            System.exit(1);
        }

        //Get bar parameters
        barServerHostName = args[2];
        try {
            barServerPortNum = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[3] is not a number!");
            System.exit(1);
        }
        if ((barServerPortNum < 22110) || (barServerPortNum > 22119)) {
            GenericIO.writelnString("args[3] is not a valid port number!");
            System.exit(1);
        }

        //Get table parameters
        tableServerHostName = args[4];
        try {
            tableServerPortNum = Integer.parseInt(args[5]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[5] is not a number!");
            System.exit(1);
        }
        if ((tableServerPortNum < 22110) || (tableServerPortNum > 22119)) {
            GenericIO.writelnString("args[5] is not a valid port number!");
            System.exit(1);
        }

        //Get general repo parameters
        genReposServerHostName = args[6];
        try {
            genReposServerPortNum = Integer.parseInt(args[7]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[7] is not a number!");
            System.exit(1);
        }
        if ((genReposServerPortNum < 22110) || (genReposServerPortNum > 22119)) {
            GenericIO.writelnString("args[7] is not a valid port number!");
            System.exit(1);
        }


        /* problem initialisation */
        kitchen = new KitchenStub(kitchenServerHostName, kitchenServerPortNum);
        bar = new BarStub(barServerHostName, barServerPortNum);
        table = new TableStub(tableServerHostName, tableServerPortNum);
        genReposStub = new GeneralReposStub(genReposServerHostName, genReposServerPortNum);
        waiter = new Waiter("waiter", kitchen, bar, table);

        /* start simulation */
        GenericIO.writelnString ("Launching Waiter Thread");
        waiter.start();

        /* waiting for the end of the simulation */
        try {
                waiter.join();
        }catch(InterruptedException e) {}
        GenericIO.writelnString ("The waiter thread has terminated.");
        bar.shutdown();
        table.shutdown();
        genReposStub.shutdown();

    }
}
