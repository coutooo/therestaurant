/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.entities;


/**
 *   Waiter thread.
 *
 *   Used to simulate the Pilot life cycle.
 *   Static solution.
 */
public interface Waiter{
    
    /**
     *   Set Waiter state.
     *
     *     @param state Waiter state
     */
    public void setWaiterState (WaiterState state);

    /**
     *   Get Waiter state.
     *
     *     @return Waiter state.
     */
    public WaiterState getWaiterState ();
}