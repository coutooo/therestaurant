/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.rmi.*;

/**
 * Operational interface of a remote object of type Kitchen.
 *
 * It provides the functionality to access the Kitchen.
 */
public interface KitchenInterface extends Remote {

    /**
     * Operation watch the news
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int watchTheNews() throws RemoteException;

    /**
     * Operation start presentation
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int startPreparation() throws RemoteException;

    /**
     * Operation proceed presentation
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int proceedPreparation() throws RemoteException;

    /**
     * Operation have all portions been delivered
     * 
     * @return true if all portions have been delivered, false otherwise
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean haveAllPortionsBeenDelivered() throws RemoteException;

    /**
     * Operation has order been completed
     * 
     * @return true if all courses have been completed, false or not
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean hasOrderBeenCompleted() throws RemoteException;

    /**
     * Operation continue preparation
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int continuePreparation() throws RemoteException;

    /**
     * Operation have next portion ready
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int haveNextPortionReady() throws RemoteException;

    /**
     * Operation clean up
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int cleanUp() throws RemoteException;

    /**
     * Operation hand note to chef
     * 
     * @return waiter state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int handNoteToChef() throws RemoteException;

    /**
     * Operation return to the bar
     * 
     * @return waiter state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int returnToBar() throws RemoteException;

    /**
     * Operation collect portion
     * 
     * @return state of the waiter
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int collectPortion() throws RemoteException;

    /**
     * Operation kitchen server shutdown
     *
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void shutdown() throws RemoteException;

}
