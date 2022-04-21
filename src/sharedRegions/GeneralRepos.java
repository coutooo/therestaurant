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
   *  Number of iterations of the customer life cycle.
   */

   private final int nIter;
   /**
   *  Semaphore to ensure mutual exclusion on the execution of public methods.
   */

   private final Semaphore access;
  /**
   *  State of the Students.
   */

   private final StudentState [] studentState;
   
   
  /**
   *  Write the header to the logging file.
   *
   *  The barbers are sleeping and the customers are carrying out normal duties.
   *  Internal operation.
   */

   private void reportInitialStatus ()
   {
      TextFile log = new TextFile ();                      // instantiation of a text file handler

      if (!log.openForWriting (".", logFileName))
         { GenericIO.writelnString ("The operation of creating the file " + logFileName + " failed!");
           System.exit (1);
         }
      log.writelnString ("                The Restaurant - Description of the internal state");
      //log.writelnString ("\nNumber of iterations = " + nIter + "\n");
      log.writelnString ("\nChef Waiter Stu0 Stu1 Stu2 Stu3 Stu4 Stu5 Stu6 NCourse NPortion                 Table\n");
      log.writelnString ("\nState State State State State State State State State Seat0 Seat1 Seat2 Seat3 Seat4 Seat5 Seat6\n");
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
        switch (studentState[i])
        { case GOING_TO_THE_RESTAURANT:   lineStatus += " GGTRT ";
                                        break;
          case TAKING_A_SEAT_AT_THE_TABLE: lineStatus += " TKSTT ";
                                        break;    
          case SELECTING_THE_COURSES: lineStatus += " SELCS ";
                                        break; 
          case ORGANIZING_THE_ORDER: lineStatus += " OGODR ";
                                        break; 
          case CHATTING_WITH_COMPANIONS: lineStatus += " CHTWC ";
                                        break; 
          case PAYING_THE_MEAL: lineStatus += " PYTBL ";
                                        break; 
          case GOING_HOME: lineStatus += " GGHOM ";
                                        break; 
        }
      for (int i = 0; i < SimulPar.N; i++)
        switch (customerState[i])
        { case CustomerStates.DAYBYDAYLIFE:  lineStatus += " DAYBYDAY ";
                                             break;
          case CustomerStates.WANTTOCUTHAIR: lineStatus += " WANTCUTH ";
                                             break;
          case CustomerStates.WAITTURN:      lineStatus += " WAITTURN ";
                                             break;
          case CustomerStates.CUTTHEHAIR:    lineStatus += " CUTTHAIR ";
                                             break;
        }
      
      log.writelnString (lineStatus);
      if (!log.close ())
         { GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
           System.exit (1);
         }
   }
}
