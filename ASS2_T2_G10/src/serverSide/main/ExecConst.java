/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.main;

/**
 *    Definition of the simulation parameters.
 */

public final class ExecConst{
    /**
     * Number of Students
     */
    public static final int Nstudents = 7;

    /**
     * Number of Courses  (three courses per participant in the
     * dinner: a starter, a main course and a dessert.)
     */
    public static final int Ncourses = 3;

    /**
     * Number of entities that request shutdown in kitchen, bar or table
     */
    public static final int NentitiesToShutKBT = 1;

    /**
     * Number of entities that request shutdown on general repository
     */
    public static final int NentitiesToShutG = 3;
    
    /**
     *   It can not be instantiated.
     */  
    private ExecConst ()
    { }
}