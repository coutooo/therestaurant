/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

/**
 *    Student cloning.
 *
 *      It specifies his own attributes.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */

public interface StudentCloning
{
   /**
    *   Set student id.
    *
    *     @param id student id
    */
   public void setStudentId (int id);

  /**
   *   Get student id.
   *
   *     @return student id
   */
   public int getStudentId ();

  /**
   *   Set student state.
   *
   *     @param state new student state
   */
   public void setStudentState (int state);

  /**
   *   Get student state.
   *
   *     @return student state
   */
   public int getStudentState ();
}