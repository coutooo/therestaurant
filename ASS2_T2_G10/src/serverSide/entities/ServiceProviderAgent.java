/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.entities;

import commInfra.Message;
import commInfra.ServerCom;

/**
 *    Service provider agent.
 */
public class ServiceProviderAgent extends Thread implements Chef, Waiter, Student
{
    /**
    *  Communication channel.
    */

    private ServerCom com;
    
    /**
     *  Reference to the provided service.
     */
    
    private SharedRegionInterface shi;

   /**
    *  Service to be provided.
    */

   /**
    *  Instantiation.
    *
    *     @param com communication channel
    *     @param shi reference to provided service
    */

    public ServiceProviderAgent (ServerCom com, SharedRegionInterface shi)
    {
       this.com = com;
       this.shi = shi;
    }
    
    /**
    *  Life cycle of the service provider agent.
    */

    @Override
    public void run ()
    {

       /* service providing */
       Message message = (Message) com.readObject();
       message = shi.processAndReply(message);
       if (message != null) {
    	   com.writeObject(message);
       }
       
    }
    
    /**
    *  Chef State.
    */
    private ChefState chefState;
    
    /**
    *   Set Chef state.
    *
    *     @param state Chef state
    */
    @Override
    public void setChefState (ChefState state)
    {
        chefState = state;
    }

    /**
     *   Get Chef state.
     *
     *     @return Chef state.
     */
    @Override
    public ChefState getChefState ()
    {
        return chefState;
    }
    
    /**
    *  Waiter State.
    */
    private WaiterState waiterState;
    
    /**
    *   Set Waiter state.
    *
    *     @param state Waiter state
    */
    @Override
    public void setWaiterState (WaiterState state)
    {
        waiterState = state;
    }

    /**
     *   Get Waiter state.
     *
     *     @return Waiter state.
     */
    @Override
    public WaiterState getWaiterState ()
    {
        return waiterState;
    }
    
    /**
     *   Student identification
     */
    private int student_id;
    
    /**
     * 	Student state
    */
    private StudentState studentState;
    
    /**
     *   Set Student id.
     *
     *     @param id Student id
     */
    @Override
    public void setStudentID(int id){
        student_id = id;
    }
    
    /**
     *   Get Student id.
     *
     *     @return Student id
     */
    @Override
    public int getStudentID(){
        return student_id;
    }
    
    /**
     *   Set Student state.
     *
     *     @param state Student state
     */
    @Override
    public void setStudentState (StudentState state){
        studentState = state;
    }

    /**
     *   Get Student state.
     *
     *     @return Student state.
     */
    @Override
    public StudentState getStudentState (){
        return studentState;
    }
    
}
