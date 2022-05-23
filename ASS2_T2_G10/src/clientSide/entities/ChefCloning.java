/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

/**
 *    Chef cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */

public interface ChefCloning
{

  /**
   *   Set chef state.
   *
   *     @param state new chef state
   */

   public void setChefState (int state);

  /**
   *   Get chef state.
   *
   *     @return chef state
   */
   public int getChefState ();
}

