package commInfra;

import genclass.GenericIO;
import java.io.Serializable;
import serverSide.main.ExecConst;

public class Message implements Serializable {

    private static final long serialVersionUID = 2022L;

    private int msgType = -1;

    private int chefState = -1;

    private int waiterState = -1;

    private int studentID = -1;

    private int studentState = -1;
    /**
     * student Id Being Answered
     */	
    private int studentIdBeingAnswered = -1;

    /**
     * Boolean value to be transported that holds true if all portions have been delivered, false otherwise
     */
    private boolean allPortionsDelivered;

    /**
     * Boolean value to be transported that holds true if order has been completed, false otherwise
     */
    private boolean orderCompleted;

    /**
     * Holds true if there are no students at the restaurant, false otherwise
     */
    private boolean studentsAtRestaurant;

    /**
     * Holds the value of the type of request that must be answered by the waiter
     */
    private char requestType;

    /**
     * Holds the id of the student whose request is being answered by the waiter
     */
    private int studentBeingAnswered;

    /**
     * Holds true if all clients have been served, false otherwise
     */
    private boolean allClientsBeenServed;

    /**
     * Holds true if everybody has choose their preference, false otherwise
     */
    private boolean everybodyHasChosen;

    /**
     * Holds true if everybody has finished eating, false otherwise
     */
    private boolean everybodyHasEaten;

    /**
     * Holds true if all courses have been eaten, false otherwise
     */
    private boolean haveAllCoursesBeenEaten;

    /**
     * Used to check which student was the last to arrive in the Table
     */
    private boolean shouldArrivedEarlier;

    /**
     * Holds the id of the first student to arrive
     */
    private int firstToArrive;

    /**
     * Holds the id of the last student to eat
     */
    private int lastToEat;

    /**
     * Holds the id of the last student to arrive
     */
    private int lastToArrive;

    /**
     * Holds the number of courses served (to be used in general repo)
     */
    private int nCourses;

    /**
     * Holds the number of portions served (to be used in general repo)
     */
    private int nPortions;

    /**
     * Holds the value of a specific seat at the table
     */
    private int seatAtTable;

    /**
     * Holds a value true or false depending if is necessary to print a reportStatus line or not
     */
    private boolean hold;

    /**
     *  Message constructor
     *
     *     @param type message type
     */
    public Message(int type) {
        msgType = type;
    }

    public Message(int type, int state) {
        msgType = type;
        int entitie = getEntitieFromMessageType(type);

        if(entitie == 1) //Chef message
                chefState = state;
        else if (entitie == 2) //Waiter message
                waiterState = state;
        else if (entitie == 3) { //Student message
                if(msgType == MessageType.CWREQ || msgType == MessageType.CWDONE || msgType == MessageType.HEFEREQ)
                        studentID = state;
                else if(msgType == MessageType.POREQ || msgType == MessageType.PODONE || 
                            msgType == MessageType.JTREQ || msgType == MessageType.JTDONE)
                        studentState = state;
        }
        else if (entitie == 4) {  //Additional message
                if (msgType == MessageType.GFTADONE)
                        firstToArrive = state;
                else if (msgType == MessageType.GLTEDONE)
                        lastToEat = state;
                else if (msgType == MessageType.SFTAREQ)
                        firstToArrive = state;
                else if (msgType == MessageType.SLTAREQ)
                        lastToArrive = state;
                else if (msgType == MessageType.GSBADONE)
                        studentBeingAnswered = state;
        }
        else if (entitie == 5) {	//General repository messages
                if (msgType == MessageType.STCSTREQ)
                        chefState = state;
                else if (msgType == MessageType.STWSTREQ)
                        waiterState = state;
                else if (msgType == MessageType.SETNCREQ) {
                        if ( state < 0 || state  > ExecConst.Ncourses) {	// Not a valid number of courses
                                GenericIO.writelnString ("Invalid number of courses");
                                System.exit (1);
                        } 
                        nCourses = state;
                }
                else if (msgType == MessageType.SETNPREQ) {
                        if ( state < 0 || state  > ExecConst.Nstudents) {	// Not a valid number of portions
                                GenericIO.writelnString ("Invalid number of portions");
                                System.exit (1);
                        }
                        nPortions = state;
                }
                else if (msgType == MessageType.USSEATREQ){
                        if ( state < 0 || state  >= ExecConst.Nstudents) {	// Not a valid Student id
                                GenericIO.writelnString ("Invalid student id");
                                System.exit (1);
                        }
                        studentID = state;
                }
        }
        else { 
                GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation!");
                System.exit (1);
        }
    }

	/**
	 * Message instantiation (form 3).
	 * 
	 * 	@param type message type
	 * 	@param bValue boolean that can have haveAllPortionsBeenDelivered, hasOrderBeenCompleted, number of studentsAtRestaurant 
	 * 		or allBeenClientsServed value.
	 */
    public Message(int type, boolean bValue)
    {
        msgType = type;
        if (msgType == MessageType.HAPBDDONE)
                allPortionsDelivered = bValue;
        else if (msgType == MessageType.HOBCDONE)
                orderCompleted = bValue;   
        else if (msgType == MessageType.SGDONE)
                studentsAtRestaurant = bValue;
        else if (msgType == MessageType.HACBSDONE)
                allClientsBeenServed = bValue;
        else if (msgType == MessageType.HACBEDONE)
                everybodyHasChosen = bValue;
        else if (msgType == MessageType.EHCREQ)
                haveAllCoursesBeenEaten = bValue;
        else if (msgType == MessageType.HEFEDONE)
                everybodyHasEaten = bValue;


    }

    /**
     *  Message instantiation (form 4).
     *
     *     @param type message type
     *     @param id student identification or student being answered by the waiter identification
     *     @param stateOrSeat student state, waiter state or seat at the table (when used in the general repos functions)
     */

    public Message (int type, int id, int stateOrSeat)
    {
            msgType = type;
            int entity = getEntitieFromMessageType(type);

            //Update seats at the table (general repos)
            if (msgType == MessageType.USSEATREQ)
                    seatAtTable = stateOrSeat;
            //salute a client (waiter in the table)
            else if (msgType == MessageType.SCREQ || msgType == MessageType.SCDONE){
                    studentBeingAnswered = id;
                    waiterState = stateOrSeat;
                    return;
            }
            else 
            {
                    if ((entity != 3) && msgType != MessageType.USSTDONE1) {	// Not a Student entity Type Message
                            GenericIO.writelnString ("Message type = " + msgType + ": non-implemented instantiation on Student!");
                            System.exit (1);
                    }
                    studentState = stateOrSeat;
            }

            //Update studentId
            if ( id < 0 || id  >= ExecConst.Nstudents) {	// Not a valid Student id
                    GenericIO.writelnString ("Invalid student id");
                    System.exit (1);
            }
            studentID = id;
    }


    /**
     * Message instantiation (form 5).
     * 
     * 		@param type message type
     * 		@param id id of the student
     * 		@param everybodyEaten holds the value of everybody has eaten (true if yes, false otherwise)
     */
    public Message (int type, int id, boolean everybodyEaten)
    {
            msgType = type;
            studentID = id;
            everybodyHasEaten = everybodyEaten;	

    }


    /**
     *  Message instantiation (form 6).
     *
     *     @param type message type
     *     @param id of the student
     *     @param state student state
     *     @param bValue that can hold shouldHaveArrivedEarlier value or hold value (used in general repos)
     */

    public Message (int type, int id, int state, boolean bValue)
    {
            //Check if student id is valid
            if ( id < 0 || id  >= ExecConst.Nstudents) {	// Not a valid Student id
                    GenericIO.writelnString ("Invalid student id");
                    System.exit (1);
            }

            msgType = type;
            studentID = id;
            studentState = state;
            if(msgType == MessageType.SHAEDONE)
                    shouldArrivedEarlier = bValue;
            else if (msgType == MessageType.USSTREQ2)
                    hold = bValue;
    }


    /**
     * 	Message instantiation (form 7).
     * 		@param type message type
     * 		@param c character that identifies which request should the waiter attend
     */
    public Message(int type, char c)
    {
            msgType = type;
            requestType = c;		
    }
    
    public int getMsgType() {
        return msgType;
    }


    public int getStudentID() { 
        return studentID;
    }

    public int getStudentState() {
        return studentState;
    }

    public int getWaiterState() {
        return waiterState;
    }

    public int getChefState() {
        return chefState;
    }
    /**
     * @return studentIdBeingAnswered
     */
    public int getStudentIdBeingAnswered() {return (studentIdBeingAnswered); }
    
    /**
     * @return true if all portions have been delivered
     */
    public boolean getAllPortionsBeenDelivered() { return (allPortionsDelivered); }
    
    
    /**
     * @return true if order has been completed
     */
    public boolean getHasOrderBeenCompleted() { return (orderCompleted); }

    /**
     * @return character that represents request type
     */
    public char getRequestType() { return (requestType); }

    /**
     * @return true if there aren't students at the restaurant
     */
    public boolean getStudentsAtRestaurant() { return (studentsAtRestaurant); }

    /**
     * @return id of the student
     */
    public int getStudentBeingAnswered() { return (studentBeingAnswered); }

    /**
     * @return true if all clients have been served
     */
    public boolean getAllClientsBeenServed() { return (allClientsBeenServed); }

    /**
     * @return true if everybody has chosen their preference
     */
    public boolean getEverybodyHasChosen() { return (everybodyHasChosen); }

    /**
     * @return true if everybody has eaten
     */
    public boolean getHasEverybodyFinishedEating() { return (everybodyHasEaten); }

    /**
     * @return true if all courses have been eaten
     */
    public boolean getAllCoursesEaten() { return (haveAllCoursesBeenEaten); }

    /**
     * @return value of shouldArrivedEarlier
     */
    public boolean getArrivedEarlier() { return (shouldArrivedEarlier); }

    /**
     * @return id of the student
     */
    public int getFirstToArrive() { return (firstToArrive); }

    /**
     * @return id of the student
     */
    public int getLastToEat() { return (lastToEat); }

    /**
     * @return id of the student
     */
    public int getLastToArrive() { return (lastToArrive); }

    /**
     * @return the value of the variable seatAtTable
     */
    public int getSeatAtTable() { return (seatAtTable); }

    /**
     * @return nCourses value
     */
    public int getNCourses() { return (nCourses); }

    /**
     * @return nPortions value
     */
    public int getNPortions() { return (nPortions); }

    /**
     * @return the value of hold variable used to specify if is necessary to print report status or not
     */
    public boolean getHold() { return (hold); }




    /**
     * For a given message type, get the entity that called it (chef, waiter or student) 
     * @param messageType type of the message
     * @return 1 if called by chef, 2 if called bye waiter and 3 if called by student
     */
    public int getEntitieFromMessageType(int messageType)
    {
            ///FALTAM AQUI MENSAGENS
            switch(messageType)
            {
                // Chef messages
                case MessageType.WTNREQ: 		case MessageType.WTNDONE:
                case MessageType.STPREQ: 		case MessageType.STPDONE:
                case MessageType.PTPREQ: 		case MessageType.PTPDONE:
                case MessageType.HAPBDREQ: 		case MessageType.HAPBDDONE:
                case MessageType.HOBCREQ: 		case MessageType.HOBCDONE:
                case MessageType.CPREQ: 		case MessageType.CPDONE :
                case MessageType.HNPRREQ:               case MessageType.HNPRDONE:
                case MessageType.CUREQ: 		case MessageType.CUDONE:
                case MessageType.ALREQ: 		case MessageType.ALDONE:
                        return 1;
                // Waiter messages
                case MessageType.HNTCREQ:		case MessageType.HNTCDONE:
                case MessageType.RTBREQ:                case MessageType.RTBDONE:
                case MessageType.CPORREQ: 		case MessageType.CPORDONE:
                case MessageType.LAREQ:                 case MessageType.LADONE:
                case MessageType.PBREQ: 		case MessageType.PBDONE:
                case MessageType.SGREQ: 		case MessageType.SGDONE:
                case MessageType.SCREQ: 		case MessageType.SCDONE:
                case MessageType.RBREQ:                 case MessageType.RBDONE:
                case MessageType.GBREQ:			case MessageType.GBDONE:
                case MessageType.HACBSREQ:              case MessageType.HACBSDONE:
                case MessageType.DPREQ:                 case MessageType.DPDONE:
                case MessageType.PREBREQ:		case MessageType.PREBDONE:
                        return 2;
                // Student messages
                case MessageType.ENTREQ: 		case MessageType.ENTDONE:
                case MessageType.CWREQ: 		case MessageType.CWDONE:
                case MessageType.SWREQ: 		case MessageType.SWDONE:
                case MessageType.EXITREQ: 		case MessageType.EXITDONE:
                case MessageType.SATREQ:		case MessageType.SATDONE:
                case MessageType.RMREQ:                 case MessageType.RMDONE:
                case MessageType.POREQ:                 case MessageType.PODONE:
                case MessageType.EHCREQ:                case MessageType.EHCDONE:
                case MessageType.AUOCREQ:		case MessageType.AUOCDONE:
                case MessageType.DOREQ:                 case MessageType.DODONE:
                case MessageType.JTREQ:                 case MessageType.JTDONE:
                case MessageType.ICREQ:                 case MessageType.ICDONE:
                case MessageType.SEREQ:                 case MessageType.SEDONE:
                case MessageType.EEREQ:                 case MessageType.EEDONE:
                case MessageType.HEFEREQ:               case MessageType.HEFEDONE:
                case MessageType.HBREQ:                 case MessageType.HBDONE:
                case MessageType.HACBEREQ:              case MessageType.HACBEDONE:
                case MessageType.SHAEREQ:               case MessageType.SHAEDONE:
                        return 3;
                //Additional Messages
                case MessageType.GSBAREQ:               case MessageType.GSBADONE:
                case MessageType.GFTAREQ:		case MessageType.GFTADONE:
                case MessageType.GLTEREQ:		case MessageType.GLTEDONE:
                case MessageType.SFTAREQ:		case MessageType.SFTADONE:
                case MessageType.SLTAREQ:		case MessageType.SLTADONE:
                case MessageType.KSHUTREQ:		case MessageType.KSHUTDONE:
                case MessageType.BSHUTREQ:		case MessageType.BSHUTDONE:
                case MessageType.TSHUTREQ:		case MessageType.TSHUTDONE:
                        return 4;
                //GeneralRepo Message
                case MessageType.STCSTREQ:		case MessageType.STCSTDONE:
                case MessageType.STWSTREQ:		case MessageType.STWSTDONE:
                case MessageType.USSTREQ1:		case MessageType.USSTDONE1:
                case MessageType.USSTREQ2:		case MessageType.USSTDONE2:
                case MessageType.SETNCREQ:              case MessageType.SETNCDONE:
                case MessageType.SETNPREQ:              case MessageType.SETNPDONE:
                case MessageType.USSEATREQ:             case MessageType.USSEATDONE:       
                case MessageType.USALREQ:               case MessageType.USALDONE:        
                case MessageType.GRSHUTREQ:             case MessageType.GRSHUTDONE:
                        return 5;
                default:
                        return -1;
            }
    }

    @Override
	public String toString ()
	{
            return ("Message type = " + msgType +
                    "\nChef State = " + chefState +
                    "\nWaiter State = " + waiterState +
                    "\nStudentId = " + studentID + " StudentState = " + studentState + 
                    "\nStudentIdBeingAnswered = " + studentIdBeingAnswered +
                    "\nNumber of studentsAtRestaurant = " + studentsAtRestaurant +
                    "\nAll Portions Been Delivered = " + allPortionsDelivered + 
                    "\nHas the Order been completed = " + orderCompleted + 
                    "\nRequest type = " + requestType + 
                    "\nHave all clients been served = " + allClientsBeenServed +
                    "\nEverybody has chosen = " + everybodyHasChosen +
                    "\nEverybody has eaten = " + everybodyHasEaten + 
                    "\nHave all courses been eaten = " + haveAllCoursesBeenEaten +
                    "\nShould have arrived earlier = " + shouldArrivedEarlier +
                    "\nFirst to arrive = " + firstToArrive + " Lat to arrive = " +lastToArrive +
                    "\nLast to eat = " + lastToEat +
                    "\nnCourses = " + nCourses + " nPortions = "+ nPortions +
                    "\nHold = " + hold + " Seat at the table = " + seatAtTable +
                    "");
	}

}