package commInfra;

import java.io.*;
import genclass.GenericIO;

/**
 *   Internal structure of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class Message implements Serializable
{
  /**
   *  Serialization key.
   */

   private static final long serialVersionUID = 2022L;

  /**
   *  Message type.
   */

   private int msgType = -1;

  /**
   *  Chef state.
   */

   private int chefState = -1;

  /**
   *  Student identification.
   */

   private int studentId = -1;

  /**
   *  Student state.
   */

   private int studentState = -1;

  /**
   *  Waiter state.
   */

   private int waiterState = -1;

   private int var1 = -1;

    private int var2 = -1;

    private Boolean check = null;
   
   
  /**
   *  Message instantiation .
   *
   *     @param type message type
   */

   public Message (int type)
   {
      msgType = type;
   }

  /**
   *  Message instantiation.
   *
   *     @param type message type
   *     @param state state
   */

   public Message (int type, int state)
   {
      this.msgType = type;
        if (type >= 40 && type <= 59)
            this.waiterState = state;
        else if (type >= 60 && type <= 75)
            this.chefState = state;
        else if (type > 86) {
            this.waiterState = state;
            this.chefState = state;
        }
   }

  /**
   *  Message instantiation (form 4).
   *
   *     @param type message type
   *     @param check boolean
   *     @param state state
   */
   public Message(int type, Boolean check, int state) {
        this.msgType = type;
        this.check = check;
        if (type >= 40 && type <= 59)
            this.waiterState = state;
        else if (type >= 60 && type <= 75)
            this.chefState = state;
        else if (type > 86) {
            this.waiterState = state;
            this.chefState = state;
        }
    }
 

  

  /**
   *  Getting message type.
   *
   *     @return message type
   */

   public int getMsgType ()
   {
      return (msgType);
   }

  /**
   *  Printing the values of the internal fields.
   *
   *  It is used for debugging purposes.
   *
   *     @return string containing, in separate lines, the pair field name - field value
   */

   @Override
   public String toString ()
   {
      return ("Message type = " + msgType +
              "\nChef State = " + chefState +
              "\nStudent Id = " + studentId +
              "\nStudent State = " + studentState +
              "\nWaiter State = " + waiterState);
   }

    public int getChefState() {
        return (chefState);
    }

    public int getWaiterState() {
        return (waiterState);
    }

    public int getStudentState() {
        return (studentState);
    }

    public int getStudentID() {
        return (studentId);
    }
}
