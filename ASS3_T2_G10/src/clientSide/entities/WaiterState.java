/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

/**
 *    Definition of the internal states of the waiter during his life cycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
    
public final class WaiterState { 
    public static final int APPRAISING_SITUATION = 0;
    public static final int PRESENTING_THE_MENU = 1;
    public static final int TAKING_THE_ORDER = 2;
    public static final int PLACING_THE_ORDER = 3;
    public static final int WAITING_FOR_PORTION = 4;
    public static final int PROCESSING_THE_BILL = 5;
    public static final int RECEIVING_PAYMENT = 6;
    
    private WaiterState ()
   { }
}
