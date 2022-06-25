
package clientSide.main;

import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;
import clientSide.entities.*;
import serverSide.main.*;
import interfaces.*;
import genclass.GenericIO;

/**
 *    Client side of the Restaurant (waiter).
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on Java RMI.
 */

public class ClientRestaurantWaiter {
    /**
     * Main method.
     *
     * @param args runtime arguments 
     * args[0] - name of the platform where is located the RMI registering service 
     * args[1] - port number where the registering service is listening to service requests
     */

    public static void main(String args[]) {
        /* get location of the generic registry service */

        String rmiRegHostName;
        int rmiRegPortNumb = -1;

        /* getting problem runtime parameters */
        if (args.length != 2) {
            GenericIO.writelnString("Wrong number of parameters!");
            System.exit(1);
        }
        rmiRegHostName = args[0];
        try {
            rmiRegPortNumb = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            GenericIO.writelnString("args[1] is not a number!");
            System.exit(1);
        }
        if ((rmiRegPortNumb < 4000) || (rmiRegPortNumb >= 65536)) {
            GenericIO.writelnString("args[1] is not a valid port number!");
            System.exit(1);
        }

        /* look for the remote object by name in the remote host registry */
        String nameEntryGeneralRepos = "GeneralRepository";
        String nameEntryTable = "Table";
        String nameEntryBar = "Bar";
        String nameEntryKitchen = "Kitchen";

        GeneralReposInterface reposStub = null;	
        TableInterface tableStub = null;
        BarInterface barStub = null;
        KitchenInterface kitchenStub = null;

        Registry registry = null;

        Waiter waiter;

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            GenericIO.writelnString("RMI registry creation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        //Lookup all the stubs
        try {
            reposStub = (GeneralReposInterface) registry.lookup(nameEntryGeneralRepos);
        } catch (RemoteException e) {
            GenericIO.writelnString("General Repository lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("General Repository not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            kitchenStub = (KitchenInterface) registry.lookup(nameEntryKitchen);
        } catch (RemoteException e) {
            GenericIO.writelnString("Kitchen lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("Kitchen not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            barStub = (BarInterface) registry.lookup(nameEntryBar);
        } catch (RemoteException e) {
            GenericIO.writelnString("Bar lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("Bar not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        try {
            tableStub = (TableInterface) registry.lookup(nameEntryTable);
        } catch (RemoteException e) {
            GenericIO.writelnString("Table lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("Table not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        waiter = new Waiter("waiter", kitchenStub, barStub, tableStub);

        /* start of the simulation */
        waiter.start();

        /* wait for the end */
        try {
            waiter.join();
        } catch (InterruptedException e) {
        }
        GenericIO.writelnString("The waiter thread has terminated.");
        
        try {
            kitchenStub.shutdown();
        } catch (RemoteException e) {
            GenericIO.writelnString("Chef generator remote exception on Bar shutdown: " + e.getMessage());
            System.exit(1);
        }
        try {
            tableStub.shutdown();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter generator remote exception on Table shutdown: " + e.getMessage());
            System.exit(1);
        }
        try {
            barStub.shutdown();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter generator remote exception on Kitchen shutdown: " + e.getMessage());
            System.exit(1);
        }
        try {
            reposStub.shutdown();
        } catch (RemoteException e) {
            GenericIO.writelnString("Waiter generator remote exception on GeneralRepos shutdown: " + e.getMessage());
            System.exit(1);
        }
        GenericIO.writelnString("End of the Simulation");

    }

}
