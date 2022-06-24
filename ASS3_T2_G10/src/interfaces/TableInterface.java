/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.rmi.*;

/**
 * Operational interface of a remote object of type Table.
 *
 * It provides the functionality to access the Table.
 */
public interface TableInterface extends Remote {

    /**
     * Obtain id of the first student to arrive
     *
     * @return id of the first student to arrive at the restaurant
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int getFirstToArrive() throws RemoteException;

    /**
     * Obtain id of the last student to arrive
     *
     * @return id of the last student to finish eating a meal
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int getLastToEat() throws RemoteException;

    /**
     * Set id of the first student to arrive
     *
     * @param firstToArrive id of the first student to arrive
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void setFirstToArrive(int firstToArrive) throws RemoteException;

    /**
     * Set id of the last student to arrive
     *
     * @param lastToArrive if of the last student to arrive to the restaurant
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void setLastToArrive(int lastToArrive) throws RemoteException;

    /**
     * Operation salute the client
     *
     * @param studentIdBeingAnswered id of the student being answered
     * @return state of the waiter
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int saluteClient(int studentIdBeingAnswered) throws RemoteException;

    /**
     * Operation return to the bar
     *
     * @return state of the waiter
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int returnBar() throws RemoteException;

    /**
     * Operation get the pad
     *
     * @return state of the waiter
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int getThePad() throws RemoteException;

    /**
     * Operation have all clients been served
     *
     * @return true if all clients have been served, false otherwise
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean haveAllClientsBeenServed() throws RemoteException;

    /**
     * Operation deliver portion
     *
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void deliverPortion() throws RemoteException;

    /**
     * Operation present the bill
     * 
     * @return waiter state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int presentBill() throws RemoteException;

    /**
     * Operation siting at the table
     *
     * @param studentId id of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void seatAtTable(int studentId) throws RemoteException;

    /**
     * Operation read the menu
     *
     * @param studentId id of the student
     * @return student state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int readMenu(int studentId) throws RemoteException;

    /**
     * Operation prepare the order
     *
     * @return student state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int prepareOrder() throws RemoteException;

    /**
     * Operation everybody has chosen
     *
     * @return true if everybody choose their course choice, false otherwise
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean everybodyHasChosen() throws RemoteException;

    /**
     * Operation add up ones choices
     *
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void addUpOnesChoices() throws RemoteException;

    /**
     * Operation describe the order
     *
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void describeOrder() throws RemoteException;

    /**
     * Operation join the talk
     * 
     * @return student state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int joinTalk() throws RemoteException;

    /**
     * Operation inform companion
     * 
     * @param studentId id of the student
     * @return state of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int informCompanion(int studentId) throws RemoteException;

    /**
     * Operation start eating
     * 
     * @param studentId id of the student
     * @return state of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int startEating(int studentId) throws RemoteException;

    /**
     * Operation end eating
     * 
     * @param studentId id of the student
     * @return state of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int endEating(int studentId) throws RemoteException;

    /**
     * Operation has everybody finished eating
     * 
     * @param studentId id of the student
     * @return true if everybody has finished false otherwise
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean hasEverybodyFinishedEating(int studentId) throws RemoteException;

    /**
     * Operation honour the bill
     * 
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void honourBill() throws RemoteException;

    /**
     * Operation have all courses been eaten
     * 
     * @return true if all courses have been eaten, false otherwise
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean haveAllCoursesBeenEaten() throws RemoteException;

    /**
     * Operation should have arrived earlier
     * 
     * @param studentId id of the student
     * @return True if current student was the last to arrive, false otherwise
     * and student state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public ReturnBoolean shouldHaveArrivedEarlier(int studentId) throws RemoteException;

    /**
     * Operation Table server shutdown
     *
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void shutdown() throws RemoteException;
}
