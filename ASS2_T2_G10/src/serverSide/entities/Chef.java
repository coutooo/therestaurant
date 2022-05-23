/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.entities;


/**
 *   Chef thread.
 *
 *   Used to simulate the Pilot life cycle.
 *   Static solution.
 */
public interface Chef{
    
    /**
     *   Set Chef state.
     *
     *     @param state Chef state
     */
    public void setChefState (ChefState state);

    /**
     *   Get Chef state.
     *
     *     @return Chef state.
     */
    public ChefState getChefState ();    
}