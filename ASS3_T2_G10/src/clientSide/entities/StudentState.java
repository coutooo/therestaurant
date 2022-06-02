/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

/**
 *    Definition of the internal states of the student during his life cycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
    
public final class StudentState { 
    
    public static final int GOING_TO_THE_RESTAURANT = 0;
    public static final int TAKING_A_SEAT_AT_THE_TABLE = 1;
    public static final int SELECTING_THE_COURSES = 2;
    public static final int ORGANIZING_THE_ORDER = 3;
    public static final int CHATTING_WITH_COMPANIONS = 4;
    public static final int ENJOYING_THE_MEAL = 5;
    public static final int PAYING_THE_MEAL = 6;
    public static final int GOING_HOME = 7;
    
    private StudentState ()
   { }
}
