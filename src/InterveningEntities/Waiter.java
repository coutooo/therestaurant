/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

/**
 * Waiter thread:
 * Implements the life-cycle of a waiter and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Waiter extends Thread {
    
    private int id;
    
    private WaiterState state;
    
    public void run(){
        
    }
    
    public int getWaiterID() {
		return id;
	}
    
    public WaiterState getWaiterState(){
        return state;
    }
    
    public void setState(WaiterState s) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		state = s;
	}
    
    
}
