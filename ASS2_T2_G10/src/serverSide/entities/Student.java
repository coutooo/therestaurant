/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.entities;


/**
 *   Student thread.
 *
 *   Used to simulate the Student life cycle.
 *   Static solution.
 */
public interface Student{
    
    /**
     *   Set Student id.
     *
     *     @param id Student id
     */
    public void setStudentID(int id);
    
    /**
     *   Get Student id.
     *
     *     @return Student id
     */
    public int getStudentID();
    
    /**
     *   Set Student state.
     *
     *     @param state Student state
     */
    public void setStudentState (StudentState state);

    /**
     *   Get Student state.
     *
     *     @return Student state.
     */
    public StudentState getStudentState ();
}