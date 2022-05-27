package commInfra;

import java.io.*;

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
   
   /**
   *  Boolean check.
   */
   private Boolean check = null;
   /**
   *  Request.
   */    
   private Request request = null;
   
   /**
   *  Name of the logging file.
   */

   private String logFileName;
  
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
    *  Message instantiation.
    *
    *     @param type message type
    *     @param check bolean 
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
    *  Message instantiation.
    *
    *     @param type message type
    *     @param request request 
    *     @param state state
    */
    public Message(int type, Request request, int state) {
        this.msgType = type;
        this.request = request;
        if (type >= 40 && type <= 59)
            this.waiterState = state;
    }
    /**
    *  Message instantiation.
    *
    *     @param type message type
    *     @param studentID student id 
    *     @param state state
    */
    public Message(int type, int studentID, int state) {
        this.msgType = type;
        this.studentId = studentID;
        if (type >= 0 && type <= 39)
            this.studentState = state;
        else if (type >= 40 && type <= 59)
            this.waiterState = state;
    }
    /**
    *  Message instantiation.
    *
    *     @param type message type
    *     @param check bolean 
    *     @param studentID student id 
    *     @param state state
    */
    public Message(int type, Boolean check, int studentID, int state) {
        this.msgType = type;
        this.studentId = studentID;
        if (type >= 0 && type <= 39)
            this.studentState = state;
        else if (type >= 40 && type <= 59)
            this.waiterState = state;
        this.check = check;
    }
    
/**
   *  Message instantiation.
   *
   *     @param type message type
   *     @param name name of the logging file
   */

    public Message (int type, String name)
    {
       msgType = type;
       this.logFileName = name;
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
   *  Getting name of logging file.
   *
   *     @return name of the logging file
   */

   public String getLogFName ()
   {
      return (logFileName);
   }
   /**
   *  Getting chef state.
   *
   *     @return chef state
   */
    public int getChefState() {
        return (chefState);
    }
    /**
    *  Getting waiter state.
    *
    *     @return waiter state
    */
    public int getWaiterState() {
        return (waiterState);
    }
    /**
    *  Getting student state.
    *
    *     @return student state
    */
    public int getStudentState() {
        return (studentState);
    }
    /**
    *  Getting student id.
    *
    *     @return student id
    */
    public int getStudentID() {
        return (studentId);
    }
    /**
    *  Getting request.
    *
    *     @return request
    */
    public char getRequest() {
        return request.type;
    }
    /**
    *  Getting boolean to check.
    *
    *     @return boolean check
    */
    public Boolean getCheck() {
        return check;
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
      return ("Message type: " + msgType
                + "\nCheck: " + check
                + "\nRequest: " + request
                + "\nStudent ID: " + studentId
                + "\nStudent State: " + studentState
                + "\nWaiter State: " + waiterState
                + "\nChef State: " + chefState);
   }
}
