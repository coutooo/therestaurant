/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.stubs;

/**
 *  Stub to the Table.
 *
 *    It instantiates a remote reference to the table.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class TableStub {
    
   /**
    *  Name of the computational system where the server is located.
    */
    private String serverHostName;

    /**
    *  Number of the listening port at the computational system where the server is located.
    */
    private int serverPortNumb;

    /**
    *  Instantiation of a remote reference
    *
    *    @param hostName name of the computational system where the server is located
    *    @param port number of the listening port at the computational system where the server is located
    */
    public TableStub (String hostName, int port)
    {
       serverHostName = hostName;
       serverPortNumb = port;
    }
}