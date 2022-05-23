/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.main;

import commInfra.ServerCom;
import genclass.GenericIO;
import java.net.SocketTimeoutException;
import serverSide.entities.ServiceProviderAgent;

/**
 *    Server side of the Table Shared Region.
 *
 *    Request serialization variant.
 *    It waits for the start of the message exchange.
 */
public class TableMain {
    
    /**
    *    Main method.
    *
    *    @param args runtime arguments
    */
    public static void main (String[] args)
    {
        /* service is established */

        ServerCom serverCom, sconi;                                        // communication channels
        int portNumb = 22152;                                          // port nunber for listening to service requests

        serverCom = new ServerCom (portNumb);
        serverCom.start ();                             // service is instantiated
        serverCom.setSoTimeout(10000);
        GenericIO.writelnString ("Service is established!");
        GenericIO.writelnString ("Server is listening for service requests.");

        GeneralReposStub generalReposStub = new GeneralReposStub("l040101-ws08.ua.pt", 22153);
        DestinationAirport destinationAirport = new DestinationAirport(generalReposStub);
        SharedRegionInterface sharedRegionInterface = new DestinationAirportInterface(destinationAirport);

       /* service request processing */
                                         // service provider agent
        while (!sharedRegionInterface.hasShutdown())
        { 
          try {
                  sconi = serverCom.accept ();                                     // enter listening procedure
                  ServiceProviderAgent serviceProviderAgent = new ServiceProviderAgent (sconi, sharedRegionInterface);            // start a service provider agent to address
                  serviceProviderAgent.start ();      
          } 
          catch(SocketTimeoutException ste) {}
        }

        GenericIO.writelnString ("Service is closed!");
    }
}
