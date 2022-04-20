/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

/**
 * Student thread:
 * Implements the life-cycle of a student and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Student extends Thread {
    
     private int id;
    
    private StudentState state;
    
    
    
    public void run(){
        walkABit();
    }
    
    public void walkABit(){
        
    }
    
    public int geStudentID() {
		return id;
	}
    
    public StudentState getStudentState(){
        return state;
    }
    
    public void setState(StudentState s) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		state = s;
	}
    
    
}