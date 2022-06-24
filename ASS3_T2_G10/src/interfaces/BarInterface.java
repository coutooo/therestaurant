/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.rmi.*;

/**
 * Operational interface of a remote object of type Bar.
 *
 * It provides the functionality to access the Bar.
 */
public interface BarInterface extends Remote {

    /**
     * Return id of the student whose request is being answered
     *
     * @return Id of the student whose request is being answered
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int getStudentBeingAnswered() throws RemoteException;

    /**
     * Operation alert the waiter
     * 
     * @return chef state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int alertWaiter() throws RemoteException;

    /**
     * Operation look Around
     *
     * @return Character that represents the service to be executed 
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public char lookAround() throws RemoteException;

    /**
     * Operation prepare the Bill
     *
     * @return waiter state
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int prepareBill() throws RemoteException;

    /**
     * Operation say Goodbye
     *
     * @return true if there are no more students at the restaurant, false
     * otherwise
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public boolean sayGoodbye() throws RemoteException;

    /**
     * Operation enter the restaurant
     * 
     * @param studentId id of the student
     * @return state of the student
     * @throws RemoteException if either the invocation of the remote method, or
     * the communication with the registry service fails
     */
    public int enter(int studentId) throws RemoteException;

    /**
     * Operation call the waiter
     * 
     * @param studentId id of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void callWaiter(int studentId) throws RemoteException;

    /**
     * Operation signal the waiter
     * 
     * @param studentId id of the student
     * @param studentState state of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void signalWaiter(int studentId, int studentState) throws RemoteException;

    /**
     * Operation exit the restaurant
     * 
     * @param studentId id of the student
     * @return state of the student
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public int exit(int studentId) throws RemoteException;

    /**
     * Operation bar server shutdown
     *
     * @throws Remote Exception if either the invocation of the remote method,
     * or the communication with the registry service fails
     */
    public void shutdown() throws RemoteException;
}
