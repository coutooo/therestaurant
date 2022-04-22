/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sharedRegions;

import genclass.GenericIO;
import genclass.TextFile;
import java.util.Objects;

import therestaurant.*;
import InterveningEntities.*;
import commInfra.*;
/**
 * 
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */



/**
 *  General Repository.
 *
 *    It is responsible to keep the visible internal state of the problem and to provide means for it
 *    to be printed in the logging file.
 *    It is implemented using semaphores for synchronization.
 *    All public methods are executed in mutual exclusion.
 *    There are no internal synchronization points.
 */
public class GeneralRepos {
   /**
   *  Name of the logging file.
   */
   private final String logFileName;
  /**
   *  Number of portions served.
   */

   private int nPortions;
  /**
   *  Number of Courses served.
   */

   private int nCourses;
  /**
   *  Number of iterations of the customer life cycle.
   */

   private final Semaphore access;
  /**
   *  State of the Students.
   */

   private final StudentState [] studentState;

  /**
   *  State of the Chef.
   */

   private ChefState chefState;
   
  /**
   *  State of the Waiter.
   */

   private WaiterState waiterState;
   
  /**
   *  seat where each student is.
   */

   private final int [] seatAtTable = new int[TheRestaurant.Nstudents];
   
   
  /**
   *  Write the header to the logging file.
   *
   *  The barbers are sleeping and the customers are carrying out normal duties.
   *  Internal operation.
   */

   
  /**
   *   Instantiation of a general repository object.
   *
   *     @param logFileName name of the logging file
   *     @param nIter number of iterations of the customer life cycle
   */

   public GeneralRepos (String logFileName)
   {
      if ((logFileName == null) || Objects.equals (logFileName, ""))
         this.logFileName = "logger";
         else this.logFileName = logFileName;
      
      // inicializar students
      studentState = new StudentState[TheRestaurant.Nstudents];
      for (int i = 0; i < TheRestaurant.Nstudents; i++)
        studentState[i] = StudentState.GOING_TO_THE_RESTAURANT;
      
      // iniciar chef
      chefState = ChefState.WAITING_FOR_AN_ORDER;
      // iniciar waiter
      waiterState = WaiterState.APPRAISING_SITUATION;
      access = new Semaphore ();
      access.up ();
      reportInitialStatus ();
   }
   private void reportInitialStatus ()
   {
      TextFile log = new TextFile ();                      // instantiation of a text file handler

      if (!log.openForWriting (".", logFileName))
         { GenericIO.writelnString ("The operation of creating the file " + logFileName + " failed!");
           System.exit (1);
         }
      log.writelnString ("                The Restaurant - Description of the internal state");
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
   *  Write a state line at the end of the logging file.
   *
   *  The current state of the barbers and the customers is organized in a line to be printed.
   *  Internal operation.
   */

   private void reportStatus ()
   {
        TextFile log = new TextFile ();                      // instantiation of a text file handler

        String lineStatus = "";                              // state line to be printed

        if (!log.openForAppending (".", logFileName))
           { GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
             System.exit (1);
           }
        for (int i = 0; i < TheRestaurant.Nstudents; i++)
            switch (studentState[i]){ 
                case GOING_TO_THE_RESTAURANT:   lineStatus += "\tGGTRT";
                                            break;
                case TAKING_A_SEAT_AT_THE_TABLE: lineStatus += "\tTKSTT";
                                              break;    
                case SELECTING_THE_COURSES: lineStatus += "\tSELCS";
                                              break; 
                case ORGANIZING_THE_ORDER: lineStatus += "\tOGODR";
                                              break; 
                case CHATTING_WITH_COMPANIONS: lineStatus += "\tCHTWC";
                                              break; 
                case PAYING_THE_MEAL: lineStatus += "\tPYTBL";
                                              break; 
                case GOING_HOME: lineStatus += "\tGGHOM";
                                              break; 
            }
        switch (chefState){ 
            case WAITING_FOR_AN_ORDER:  lineStatus += "\tWAFOR";
                                               break;
            case PREPARING_THE_COURSE: lineStatus += "\tPRPCS";
                                               break;
            case DISHING_THE_PORTIONS:      lineStatus += "\tDSHPT";
                                               break;
            case DELIVERING_THE_PORTIONS:    lineStatus += "\tDLVPT";
                                               break;
            case CLOSING_SERVICE:    lineStatus += "\tCLSSV";
                                               break;
          }
        switch (waiterState){ 
            case APPRAISING_SITUATION:  lineStatus += "\tAPPST";
                                               break;
            case PRESENTING_THE_MENU: lineStatus += "\tPRSMN";
                                               break;
            case TAKING_THE_ORDER:      lineStatus += "\tTKODR";
                                               break;
            case PLACING_THE_ORDER:    lineStatus += "\tPCODR";
                                               break;
            case WAITING_FOR_PORTION:    lineStatus += "\tWTFPT";
                                               break;
            case PROCESSING_THE_BILL:    lineStatus += "\tPRCBL";
                                               break;                                              
            case RECEIVING_PAYMENT:    lineStatus += "\tRECPM";
                                               break;                                            
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
    void setChefState(ChefState state) {
        access.down ();                                      // enter critical region
        this.chefState = state;
        reportStatus ();
        access.up ();                                        // exit critical region
    }
  /**
   *   Set Waiter state.
   *
   *     @param state waiter state
   */
    void setWaiterState(WaiterState state) {
        access.down ();                                      // enter critical region
        waiterState = state;
        reportStatus ();
        access.up ();                                        // exit critical region
    }
  /**
   *   Set student state.
   *
   *     @param studentId student id
   *     @param state state
   */
    void setStudentState(int studentId, StudentState state) {
        access.down ();                                      // enter critical region
        this.studentState[studentId] = state;
        reportStatus ();
        access.up ();                                        // exit critical region
    }

    void setnPortions(int numberOfPortionsDelivered) {
        nPortions = numberOfPortionsDelivered;
    }

    void setnCourses(int i) {
        nCourses = i;
    }

    void updateSeatsAtTable(int nStudentsAtRes, int studentId) {
        seatAtTable[studentId] = nStudentsAtRes;
    }
}
