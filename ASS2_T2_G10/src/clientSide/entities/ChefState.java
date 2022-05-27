/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

/**
 *    Definition of the internal states of the Chef during his life cycle. 
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */

public final class ChefState { 
    public static final int WAITING_FOR_AN_ORDER = 0;
    public static final int PREPARING_THE_COURSE = 1;
    public static final int DISHING_THE_PORTIONS = 2;
    public static final int DELIVERING_THE_PORTIONS = 3;
    public static final int CLOSING_SERVICE = 4;
    
    private ChefState ()
   { }
}

