package serverSide.sharedRegions;

import java.util.Objects;
import serverSide.main.*;
import genclass.GenericIO;
import genclass.TextFile;
import clientSide.entities.*;

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

public class GeneralRepos
{
    /**
     *  Name of the logging file.
     */

    private final String logFileName;

    /**
     *  State of the Chef
     */

    private int chefState;

    /**
     *	State of the Waiter
     */

    private int waiterState;

    /**
     *  State of the Chef
     */

    private int[] studentState;

    /**
     *	Number of courses delivered (not sure)
     */

    private int nCourses;

    /**
     * 	Number of Portions delivered (not sure)
     */

    private int nPortions;

    /**
     * 	Seats at the table
     */
    private int[] seatsAtTable;

    /**
     * Number of entities that requested shutdown
     */
    private int nEntities;

    /**
     *	Instantiation of a general repository object.
     */

    public GeneralRepos ()
    {
            this("");
    }


    /**
     *	Instantiation of a general repository object.
     *
     *	@param logFileName name of the logging file
     */

    public GeneralRepos (String logFileName)
    {
            nEntities = 0;
            if ((logFileName == null) || Objects.equals (logFileName, ""))
                    this.logFileName = "logger";
            else this.logFileName = logFileName;  		
            chefState = ChefState.WAITING_FOR_AN_ORDER;
            waiterState = WaiterState.APPRAISING_SITUATION;
            studentState = new int[ExecConst.Nstudents];
            for (int i = 0; i < ExecConst.Nstudents; i++)
                    studentState[i] = StudentState.GOING_TO_THE_RESTAURANT;
            nCourses = 0;
            nPortions = 0;
            seatsAtTable = new int[ExecConst.Nstudents];
            for(int i = 0; i < ExecConst.Nstudents; i++)
            {
                    seatsAtTable[i] = -1;
            }
            reportInitialStatus ();
    }

    /**
     *  Write the header to the logging file.
     */

    private void reportInitialStatus ()
    {
            TextFile log = new TextFile ();                      // instantiation of a text file handler

            if (!log.openForWriting (".", logFileName))
            { 
                    GenericIO.writelnString ("The operation of creating the file " + logFileName + " failed!");
                    System.exit (1);
            }
            log.writelnString ("                                        The Restaurant - Description of the internal state");
            log.writelnString (" Chef Waiter  Stu0  Stu1  Stu2  Stu3  Stu4  Stu5  Stu6  NCourse  NPortion                    Table");
            log.writelnString ("State State  State State State State State State State                     Seat0 Seat1 Seat2 Seat3 Seat4 Seat5 Seat6");
            if (!log.close ())
            { 
                    GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
                    System.exit (1);
            }
    }

    /**
     *  Write a state line at the end of the logging file
     */

    private void reportStatus ()
    {
            TextFile log = new TextFile ();                  	// instantiation of a text file handler
            String line = "";                              		// state line to be printed
            if (!log.openForAppending (".", logFileName))
            { 
                    GenericIO.writelnString ("The operation of opening for appending the file " + logFileName + " failed!");
                    System.exit (1);
            }
            switch(chefState)
            {
            case ChefState.WAITING_FOR_AN_ORDER: line += "WAFOR "; break;
            case ChefState.PREPARING_THE_COURSE: line += "PRPCS "; break;
            case ChefState.DISHING_THE_PORTIONS: line += "DSHPT "; break;
            case ChefState.DELIVERING_THE_PORTIONS: line += "DLVPT "; break;
            case ChefState.CLOSING_SERVICE: line += "CLSSV "; break;
            }

            switch(waiterState)
            {
            case WaiterState.APPRAISING_SITUATION: line += "APPST  "; break;
            case WaiterState.PRESENTING_THE_MENU: line += "PRSMN  "; break;
            case WaiterState.TAKING_THE_ORDER: line += "TKODR  "; break;
            case WaiterState.PLACING_THE_ORDER: line += "PCODR  "; break;
            case WaiterState.WAITING_FOR_PORTION: line += "WTFPT  "; break;
            case WaiterState.PROCESSING_THE_BILL: line += "PRCBL  "; break;
            case WaiterState.RECEIVING_PAYMENT: line += "RECPM  "; break;
            }

            for(int i = 0; i < ExecConst.Nstudents; i++)
            {
                    switch(studentState[i])
                    {
                    case StudentState.GOING_TO_THE_RESTAURANT: line += "GGTRT "; break;
                    case StudentState.TAKING_A_SEAT_AT_THE_TABLE: line += "TKSTT "; break;
                    case StudentState.SELECTING_THE_COURSES: line += "SELCS "; break;
                    case StudentState.ORGANIZING_THE_ORDER: line += "OGODR "; break;
                    case StudentState.CHATTING_WITH_COMPANIONS: line += "CHTWC "; break;
                    case StudentState.ENJOYING_THE_MEAL: line += "EJYML "; break;
                    case StudentState.PAYING_THE_MEAL: line += "PYTBL "; break;
                    case StudentState.GOING_HOME: line += "GGHOM "; break;
                    }
            }

            line += "    " + String.valueOf(nCourses);
            line += "        " + String.valueOf(nPortions);
            line += "        " + (seatsAtTable[0] >= 0 ? String.valueOf(seatsAtTable[0]) : "-");
            for(int i = 1; i < ExecConst.Nstudents; i++)
            {
                    line += "     " + (seatsAtTable[i] >= 0 ? String.valueOf(seatsAtTable[i]) : "-");
            }

            log.writelnString (line);
            if (!log.close ())
            { 
                    GenericIO.writelnString ("The operation of closing the file " + logFileName + " failed!");
                    System.exit (1);
            }
    }

    /**
     * Write in the logging file the new chef state
     * @param value chef state to set
     */
    public synchronized void setChefState(int value) {
            this.chefState = value;
            reportStatus();
    }

    /**
     * Write in the logging file the new waiter state
     * @param value waiter state to set
     */
    public synchronized void setWaiterState(int value) {
            this.waiterState = value;
            reportStatus();
    }

    /**
     * Write in the logging file the updated student state
     * @param id student id
     * @param value student state to set
     */
    public synchronized void updateStudentState(int id, int value) {
            this.studentState[id] = value;
            reportStatus();
    }

    /**
     * Update student state
     * @param id student id
     * @param value student state to set
     * @param hold specifies if prints line of report status
     */
    public synchronized void updateStudentState(int id, int value, boolean hold) {
            this.studentState[id] = value;
    }

    /**
     * Set variable nCourses and report status in the logging file
     * @param value nCourses value to set
     */
    public synchronized void setnCourses(int value) {
            this.nCourses = value;
    }

    /**
     * Write the portion value in the logging file
     * 
     * @param value nPortions value to set
     */
    public synchronized void setnPortions(int value) {
            this.nPortions = value;
    }

    /**
     * Write to the logging file the updated seats values at the table 
     * 
     * @param seat seat at the table
     * @param id student id to sit
     */
    public synchronized void updateSeatsAtTable(int seat, int id) {
            this.seatsAtTable[seat] = id;
            reportStatus();
    }

    /**
     * Update the leaving of a student in the seats of the table
     * 
     * @param id student id to leave table
     */
    public synchronized void updateSeatsAtLeaving(int id) {
            int seat = 0;

            for(int i=0; i < this.seatsAtTable.length; i++) {
                    if(this.seatsAtTable[i] == id)
                            seat = i;
            }

            this.seatsAtTable[seat] = -1;
    }	

    /**
     * Operation Table server shutdown
     */
    public synchronized void shutdown()
    {
            nEntities += 1;
            if(nEntities >= ExecConst.NShutG) {
                    ServerRestaurantGeneralRepos.waitConnection = false;
            }
            notifyAll ();
    }
}









