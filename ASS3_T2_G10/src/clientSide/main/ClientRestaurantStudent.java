package clientSide.main;

import java.rmi.registry.*;
import java.rmi.*;
import java.rmi.server.*;
import clientSide.entities.*;
import serverSide.main.*;
import interfaces.*;
import genclass.GenericIO;

/**
 *    Client side of the Restaurant (students).
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on Java RMI.
 */

public class ClientRestaurantStudent {
    /**
     * Main method.
     *
     * @param args runtime arguments 
     * args[0] - name of the platform where is located the RMI registering service 
     * args[1] - port number where the registering service is listening to service requests
     * 
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
        String nameEntryTable = "Table";
        String nameEntryBar = "Bar";

        TableInterface tableStub = null;
        BarInterface barStub = null;

        Registry registry = null;

        Student[] pass = new Student[ExecConst.Nstudents];

        try {
            registry = LocateRegistry.getRegistry(rmiRegHostName, rmiRegPortNumb);
        } catch (RemoteException e) {
            GenericIO.writelnString("RMI registry creation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        //Lookup all the stubs
        try {
            tableStub = (TableInterface) registry.lookup(nameEntryTable);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            barStub = (BarInterface) registry.lookup(nameEntryBar);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        for (int i = 0; i < ExecConst.Nstudents; i++) {
            pass[i] = new Student("student_" + (i + 1), i,barStub, tableStub);
        }

        /* start of the simulation */
        for (int i = 0; i < ExecConst.Nstudents; i++) {
            pass[i].start();
        }

        /* wait for the end */
        for (int i = 0; i < ExecConst.Nstudents; i++) {
            try {
                pass[i].join();
            } catch (InterruptedException e) {
            }
            System.out.println("The Student " + (i + 1) + " just terminated");
        }

        try {
            barStub.shutdown();
        } catch (RemoteException e) {
            GenericIO.writelnString("Customer generator remote exception on BarberShop shutdown: " + e.getMessage());
            System.exit(1);
        }
        try {
            tableStub.shutdown();
        } catch (RemoteException e) {
            GenericIO.writelnString("Customer generator remote exception on BarberShop shutdown: " + e.getMessage());
            System.exit(1);
        }

        System.out.println("End of the Simulation");

    }
}
