/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.entities;

import serverSide.sharedRegions.*;
import clientSide.entities.*;
import commInfra.*;
import genclass.GenericIO;

/**
 *  Service provider agent for access to the Table.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class TableClientProxy extends Thread implements StudentCloning, WaiterCloning{
    
    /**
   *  Number of instantiayed threads.
   */

   private static int nProxy = 0;

  /**
   *  Communication channel.
   */

   private ServerCom sconi;

  /**
   *  Interface to the Table.
   */

   private TableInterface tableInter;

  /**
   *  Student identification.
   */

   private int studentId;

  /**
   *  Student state.
   */

   private int studentState;

   /**
   *  Waiter state.
   */

   private int waiterState;
   
    /**
     * Id of the student whose request the waiter is taking care of
     */
    private int studentBeingAnswered;
    

    /**
    *  Instantiation of a client proxy.
    *
    *     @param sconi communication channel
    *     @param tableInter interface to the Table
    */

    public TableClientProxy (ServerCom sconi, TableInterface tableInter)
    {
       super ("TableProxy_" + TableClientProxy.getProxyId ());
       this.sconi = sconi;
       this.tableInter = tableInter;
    }

    /**
    *  Generation of the instantiation identifier.
    *
    *     @return instantiation identifier
    */

    private static int getProxyId ()
    {
       Class<?> cl = null;                                            // representation of the TableProxy object in JVM
       int proxyId;                                                   // instantiation identifier

       try
       { cl = Class.forName ("serverSide.entities.TableClientProxy");
       }
       catch (ClassNotFoundException e)
       { GenericIO.writelnString ("Data type TableClientProxy was not found!");
         e.printStackTrace ();
         System.exit (1);
       }
       synchronized (cl)
       { proxyId = nProxy;
         nProxy += 1;
       }
       return proxyId;
    }

    /**
   *  Life cycle of the service provider agent.
   */

    @Override
    public void run ()
    {
       Message inMessage = null,                                      // service request
               outMessage = null;                                     // service reply

      /* service providing */

       inMessage = (Message) sconi.readObject ();                     // get service request
       try
       { outMessage = tableInter.processAndReply (inMessage);         // process it
       }
       catch (MessageException e)
       { GenericIO.writelnString ("Thread " + getName () + ": " + e.getMessage () + "!");
         GenericIO.writelnString (e.getMessageVal ().toString ());
         System.exit (1);
       }
       sconi.writeObject (outMessage);                                // send service reply
       sconi.close ();                                                // close the communication channel
    }
    
    /**
    *   Set student id.
    *
    *     @param id student id
    */

    public void setStudentId (int id)
    {
       studentId = id;
    }
    
    /**
    *   Get student id.
    *
    *     @return student id
    */

    public int getStudentId ()
    {
       return studentId;
    }
    
    /**
    *   Set student state.
    *
    *     @param state new student state
    */

    public void setStudentState (int state)
    {
       studentState = state;
    }
    
    /**
    *   Get student state.
    *
    *     @return student state
    */

    public int getStudentState ()
    {
       return studentState;
    }
    
    /**
    *   Set waiter state.
    *
    *     @param state new waiter state
    */
    
    public void setWaiterState (int state)
    {
       waiterState = state;
    }
    
    /**
    *   Get waiter state.
    *
    *     @return waiter state
    */

    public int getWaiterState ()
    {
       return waiterState;
    }
    
    /**
     * Set studentBeingAnswered Id
     *
     * @param id studentBeingAnswered ID
     */
    public void setStudentBeingAnswered(int id) {
        studentBeingAnswered = id;
    }

    /**
     * Get studentBeingAnswered Id
     *
     * @return id studentBeingAnswered
     */
    public int getStudentBeingAnswered() {
        return studentBeingAnswered;
    }

    
}