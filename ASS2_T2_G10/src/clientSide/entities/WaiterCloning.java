/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

/**
 *    Waiter cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */

public interface WaiterCloning
{

  /**
   *   Set waiter state.
   *
   *     @param state new waiter state
   */

   public void setWaiterState (int state);

  /**
   *   Get chef state.
   *
   *     @return chef state
   */
   public int getWaiterState ();
}