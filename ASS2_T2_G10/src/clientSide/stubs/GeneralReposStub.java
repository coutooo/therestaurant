/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.stubs;

/**
 *  Stub to the general repository.
 *
 *    It instantiates a remote reference to the general repository.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class GeneralReposStub
{
  /**
   *  Name of the platform where is located the general repository server.
   */

   private String serverHostName;

  /**
   *  Port number for listening to service requests.
   */

   private int serverPortNumb;

  /**
   *   Instantiation of a stub to the general repository.
   *
   *     @param serverHostName name of the platform where is located the barber shop server
   *     @param serverPortNumb port number for listening to service requests
   */

   public GeneralReposStub (String serverHostName, int serverPortNumb)
   {
      this.serverHostName = serverHostName;
      this.serverPortNumb = serverPortNumb;
   }
}