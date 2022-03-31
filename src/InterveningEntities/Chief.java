/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

/**
 * Chief thread:
 * Implements the life-cycle of a chief and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Chief extends Thread {
    
     private int id;
    
    private ChiefState state;
    
    public void run(){
        
    }
    
    public void watchTheNews(){
        
    }
    
    public int getChiefID() {
		return id;
	}
    
    public ChiefState getChiefState(){
        return state;
    }
    
    public void setState(ChiefState s) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		state = s;
	}
    
    
    
    
}