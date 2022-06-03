/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.rmi.*;

/**
 *
 * @author rafae
 */
public interface GeneralReposInterface extends Remote {
     public void shutdown () throws RemoteException;

  /**
   *   Set barber state.
   *
   *     @param id barber id
   *     @param state barber state
   *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry
   *                             service fails
   */

   public void setChefState (int id, int state) throws RemoteException;

  /**
   *   Set customer state.
   *
   *     @param id customer id
   *     @param state customer state
   *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry
   *                             service fails
   */

   public void setCustomerState (int id, int state) throws RemoteException;

  /**
   *   Set barber and customer state.
   *
   *     @param barberId barber id
   *     @param barberState barber state
   *     @param customerId customer id
   *     @param customerState customer state
   *     @throws RemoteException if either the invocation of the remote method, or the communication with the registry
   *                             service fails
   */

   public void setBarberCustomerState (int barberId, int barberState, int customerId, int customerState) throws RemoteException;
}

