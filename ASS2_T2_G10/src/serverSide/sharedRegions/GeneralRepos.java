/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.sharedRegions;

import clientSide.entities.*;
import genclass.GenericIO;
import genclass.TextFile;
import java.util.Objects;
import serverSide.main.ExecConst;
import serverSide.main.ServerRestaurantGeneralRepos;


/**
 *  General Repository.
 *
 *    It is responsible to keep the visible internal state of the problem and to provide means for it
 *    to be printed in the logging file.
 *    It is implemented using semaphores for synchronization.
 *    All public methods are executed in mutual exclusion.
 *    There are no internal synchronization points.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class GeneralRepos {
     /**
   *  Name of the logging file.
   */

   private String logFileName;

  /**
   *  Number of portions served.
   */
   private int nPortions;
  /**
   *  Number of Courses served.
   */
   private int nCourses;

  /**
   *  State of the Students.
   */
   private final int [] studentState;

  /**
   *  State of the Chef.
   */
   private int chefState;
   
  /**
   *  State of the Waiter.
   */
   private int waiterState;
   
  /**
   *  seat where each student is.
   */
   private int[] seatAtTable;
   
   /**
    *   Number of entity groups requesting the shutdown.
    */
    private int nEntities;
   
    /**
   *   Instantiation of a general repository object.
   *
   */
    public GeneralRepos ()
    {
	this("");
    }
  /**
   *   Instantiation of a general repository object.
   *
   *	@param logFileName name of the logging file
   */
    public GeneralRepos (String logFileName)
    {
        
        this.nEntities =  0;
        if ((logFileName == null) || Objects.equals (logFileName, ""))
            this.logFileName = "logger";
        else this.logFileName = logFileName; 

        // iniciar chef
        chefState = ChefState.WAITING_FOR_AN_ORDER;
        // iniciar waiter
        waiterState = WaiterState.APPRAISING_SITUATION;

        // inicializar students
        studentState = new int[serverSide.main.ExecConst.Nstudents];
        for (int i = 0; i < serverSide.main.ExecConst.Nstudents; i++)
          studentState[i] = StudentState.GOING_TO_THE_RESTAURANT;

        nCourses = 0;
        nPortions = 0;
        seatAtTable = new int[ExecConst.Nstudents];
        // iniciar todos os seats a -1 para indicar que nao tem ninguem sentado
        for(int i = 0; i<serverSide.main.ExecConst.Nstudents;i++)
        {
            this.seatAtTable[i] = -1;
        }

        reportInitialStatus();
    }
      
  /**
   *  Print header.
   */
   private void reportInitialStatus ()
   {
      TextFile log = new TextFile ();                      // instantiation of a text file handler

      if (!log.openForWriting (".", logFileName))
         { GenericIO.writelnString ("The operation of creating the file " + logFileName + " failed!");
           System.exit (1);
         }
      log.writelnString ("\t\t\t\t\t\t  The Restaurant - Description of the internal state");
      //log.writelnString ("\nNumber of iterations = " + nIter + "\n");
      log.writelnString ("\n\tChef\tWaiter\tStu0\tStu1\tStu2\tStu3\tStu4\tStu5\tStu6\tNCourse\tNPortion\t\t\tTable\n");
      log.writelnString ("\n\tState\tState\tState\tState\tState\tState\tState\tState\tState\t\t\tSeat0\tSeat1\tSeat2\tSeat3\tSeat4\tSeat5\tSeat6\n");
      if (!log.close ())
         { GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
           System.exit (1);
         }
      reportStatus ();
   }
  /**
   *  Write the body of the logging file.
   *
   */
   private void reportStatus ()
   {
        TextFile log = new TextFile ();                      // instantiation of a text file handler

        String lineStatus = "";                              // state line to be printed

        if (!log.openForAppending (".", logFileName))
           { GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
             System.exit (1);
           }
        switch (chefState){ 
            case ChefState.WAITING_FOR_AN_ORDER:  lineStatus += "\tWAFOR";
                                               break;
            case ChefState.PREPARING_THE_COURSE: lineStatus += "\tPRPCS";
                                               break;
            case ChefState.DISHING_THE_PORTIONS:      lineStatus += "\tDSHPT";
                                               break;
            case ChefState.DELIVERING_THE_PORTIONS:    lineStatus += "\tDLVPT";
                                               break;
            case ChefState.CLOSING_SERVICE:    lineStatus += "\tCLSSV";
                                               break;
          }
        switch (waiterState){ 
            case WaiterState.APPRAISING_SITUATION:  lineStatus += "\tAPPST";
                                               break;
            case WaiterState.PRESENTING_THE_MENU: lineStatus += "\tPRSMN";  
                                               break;
            case WaiterState.TAKING_THE_ORDER:      lineStatus += "\tTKODR";
                                               break;
            case WaiterState.PLACING_THE_ORDER:    lineStatus += "\tPCODR";
                                               break;
            case WaiterState.WAITING_FOR_PORTION:    lineStatus += "\tWTFPT";
                                               break;
            case WaiterState.PROCESSING_THE_BILL:    lineStatus += "\tPRCBL";
                                               break;                                              
            case WaiterState.RECEIVING_PAYMENT:    lineStatus += "\tRECPM";
                                               break;                                            
          }
        for (int i = 0; i < serverSide.main.ExecConst.Nstudents; i++)
            switch (studentState[i]){ 
                case StudentState.GOING_TO_THE_RESTAURANT:   lineStatus += "\tGGTRT";
                                            break;
                case StudentState.TAKING_A_SEAT_AT_THE_TABLE: lineStatus += "\tTKSTT";
                                              break;    
                case StudentState.SELECTING_THE_COURSES: lineStatus += "\tSELCS";
                                              break; 
                case StudentState.ORGANIZING_THE_ORDER: lineStatus += "\tOGODR";
                                              break; 
                case StudentState.CHATTING_WITH_COMPANIONS: lineStatus += "\tCHTWC";
                                              break; 
                case StudentState.PAYING_THE_MEAL: lineStatus += "\tPYTML";
                                              break; 
                case StudentState.ENJOYING_THE_MEAL: lineStatus += "\tEJTML";
                                              break; 
                case StudentState.GOING_HOME: lineStatus += "\tGGHOM";
                                              break; 
                
            }
        lineStatus += "\t"+nCourses+"\t"+nPortions;
        
        for(int i = 0;i< serverSide.main.ExecConst.Nstudents;i++)
        {
            lineStatus += "\t";
            if(seatAtTable[i] == -1){
                lineStatus += "-";
            }
            else
            {
                lineStatus += seatAtTable[i];
            }
        }
     
      log.writelnString (lineStatus);
      if (!log.close ())
         { GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
           System.exit (1);
         }
   }
  /**
   *   Set Chef state.
   *
   *     @param state chef state
   */
    public synchronized void setChefState(int state) {
        this.chefState = state;
        reportStatus ();
    }
  /**
   *   Set Waiter state.
   *
   *     @param state waiter state
   */
    public synchronized void setWaiterState(int state) {
        waiterState = state;
        reportStatus ();
    }
  /**
   *   Update student state.
   *
   *     @param studentId student id
   *     @param state state
   */
    public synchronized void updateStudentState(int studentId, int state) {
        this.studentState[studentId] = state;
        reportStatus ();
    }
    
    /**
   *   Set student state.
   *
   *     @param studentId student id
   *     @param state state
   *     @param hold specifies if prints line of report status
   */
    public synchronized void setStudentState(int studentId, int state, boolean hold) {
        this.studentState[studentId] = state;
    }
    
  /**
   *   Set number of portions.
   *
   *     @param nPortionsDelivered number of portions delivered
   */
    void setnPortions(int nPortionsDelivered) {
        nPortions = nPortionsDelivered;
    }
  /**
   *   Set number of Courses.
   *
   *     @param setnCourses number of courses
   */
    void setnCourses(int i) {
        nCourses = i;
    }
  /**
   *   update who is seated at the table.
   *
   *     @param nStudentAtRes number of students at the restaurant
   *     @param studentId student id
   */
    void updateSeatsAtTable(int nStudentsAtRes, int studentId) {
        seatAtTable[nStudentsAtRes] = studentId;
        reportStatus();
    }
    
    /**
    * Update the leaving of a student in the seats of the table
    * 
    * @param id student id to leave table
    */
    public synchronized void updateSeatsAtLeaving(int id) {
           int seat = 0;

           for(int i=0; i < this.seatAtTable.length; i++) {
                   if(this.seatAtTable[i] == id)
                           seat = i;
           }

           this.seatAtTable[seat] = -1;
    }	
        
    /**
    *   Operation server shutdown.
    *
    *   New operation.
    */
    public synchronized void shutdown() {
        nEntities += 1;
        if (nEntities >= ExecConst.NentitiesToShutG)
           ServerRestaurantGeneralRepos.waitConnection = false;
        notifyAll();
    }      
}
