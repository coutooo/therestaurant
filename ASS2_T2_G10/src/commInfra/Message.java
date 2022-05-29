package commInfra;

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

    private Boolean check = null;

    private Request request = null;

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
                if(msgType == MessageType.CWREQ || msgType == MessageType.CWDONE || msgType == MessageType.REQEVERYBDFINISHEAT)
                        studentID = state;
                else if(msgType == MessageType.POREQ || msgType == MessageType.PODONE || 
                            msgType == MessageType.JTREQ || msgType == MessageType.JTDONE)
                        studentState = state;
        }
        else if (entitie == 4) {  //Additional message
                if (msgType == MessageType.REPGETFRSTARR)
                        firstToArrive = state;
                else if (msgType == MessageType.REPGETLSTEAT)
                        lastToEat = state;
                else if (msgType == MessageType.REQSETFRSTARR)
                        firstToArrive = state;
                else if (msgType == MessageType.REQSETLSTARR)
                        lastToArrive = state;
                else if (msgType == MessageType.REPGETSTDBEIANSW)
                        studentBeingAnswered = state;
        }
        else if (entitie == 5) {	//General repository messages
                if (msgType == MessageType.REQSETCHST)
                        chefState = state;
                else if (msgType == MessageType.REQSETWAIST)
                        waiterState = state;
                else if (msgType == MessageType.REQSETNCOURSES) {
                        if ( state < 0 || state  > ExecConst.Ncourses) {	// Not a valid number of courses
                                GenericIO.writelnString ("Invalid number of courses");
                                System.exit (1);
                        } 
                        nCourses = state;
                }
                else if (msgType == MessageType.REQSETNPORTIONS) {
                        if ( state < 0 || state  > ExecConst.Nstudents) {	// Not a valid number of portions
                                GenericIO.writelnString ("Invalid number of portions");
                                System.exit (1);
                        }
                        nPortions = state;
                }
                else if (msgType == MessageType.REQUPDSEATSTABLELV){
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
        if (msgType == MessageType.REPHVPRTDLVD)
                allPortionsDelivered = bValue;
        else if (msgType == MessageType.REPHORDCOMPL)
                orderCompleted = bValue;   
        else if (msgType == MessageType.SGDONE)
                studentsAtRestaurant = bValue;
        else if (msgType == MessageType.REPALLCLISERVED)
                allClientsBeenServed = bValue;
        else if (msgType == MessageType.REPEVERYBDYCHO)
                everybodyHasChosen = bValue;
        else if (msgType == MessageType.REPALLCOURBEENEAT)
                haveAllCoursesBeenEaten = bValue;
        else if (msgType == MessageType.REPEVERYBDFINISHEAT)
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
            if (msgType == MessageType.REQUPDSEATSTABLE)
                    seatAtTable = stateOrSeat;
            //salute a client (waiter in the table)
            else if (msgType == MessageType.REQSALUTCLI || msgType == MessageType.REPSALUTCLI){
                    studentBeingAnswered = id;
                    waiterState = stateOrSeat;
                    return;
            }
            else 
            {
                    if ((entity != 3) && msgType != MessageType.REQUPDTSTUST1) {	// Not a Student entity Type Message
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
            if(msgType == MessageType.REPSHOULDARREARLY)
                    shouldArrivedEarlier = bValue;
            else if (msgType == MessageType.REQUPDTSTUST2)
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

    public Boolean getCheck() {
        return check;
    }

    public char getRequest() {
        return request.type;
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
                case MessageType.REQWATTNWS: 		case MessageType.REPWATTNWS:
                case MessageType.REQSTRPR: 		case MessageType.REPSTRPR:
                case MessageType.REQPROCPREP: 		case MessageType.REPPROCPREP:
                case MessageType.REQHVPRTDLVD: 		case MessageType.REPHVPRTDLVD:
                case MessageType.HOBCREQ: 		case MessageType.HOBCDONE:
                case MessageType.REQCONTPREP: 		case MessageType.REPCONTPREP :
                case MessageType.REQHAVNEXPORRD: 	case MessageType.REPHAVNEXPORRD:
                case MessageType.CUREQ: 		case MessageType.CUDONE:
                case MessageType.ALREQ: 		case MessageType.ALDONE:
                        return 1;
                // Waiter messages
                case MessageType.REQHNDNOTCHEF:		case MessageType.REPHNDNOTCHEF:
                case MessageType.REQRETURNTOBAR: 	case MessageType.REPRETURNTOBAR:
                case MessageType.REQCOLLPORT: 		case MessageType.REPCOLLPORT:
                case MessageType.REQLOOKARND:		case MessageType.REPLOOKARND:
                case MessageType.REQPRPREBILL: 		case MessageType.REPPRPREBILL:
                case MessageType.REQSAYGDBYE: 		case MessageType.REPSAYGDBYE:
                case MessageType.REQSALUTCLI: 		case MessageType.REPSALUTCLI:
                case MessageType.REQRTRNBAR:		case MessageType.REPRTRNBAR:
                case MessageType.REQGETPAD:			case MessageType.REPGETPAD:
                case MessageType.REQALLCLISERVED:	case MessageType.REPALLCLISERVED:
                case MessageType.REQDELPOR:			case MessageType.REPDELPOR:
                case MessageType.REQPRESBILL:		case MessageType.REPPRESBILL:
                        return 2;
                // Student messages
                case MessageType.ENTREQ: 			case MessageType.ENTDONE:
                case MessageType.CWREQ: 			case MessageType.CWDONE:
                case MessageType.SWREQ: 			case MessageType.SWDONE:
                case MessageType.EXITREQ: 			case MessageType.EXITDONE:
                case MessageType.SATREQ:			case MessageType.SATDONE:
                case MessageType.RMREQ:                         case MessageType.RMDONE:
                case MessageType.POREQ:                         case MessageType.PODONE:
                case MessageType.EHCREQ:                        case MessageType.EHCDONE:
                case MessageType.REQADDUP1CHOI:			case MessageType.REPADDUP1CHOI:
                case MessageType.DOREQ:                         case MessageType.DODONE:
                case MessageType.REQJOINTALK:			case MessageType.REPJOINTALK:
                case MessageType.REQINFORMCOMP:			case MessageType.REPINFORMCOMP:
                case MessageType.REQSRTEATING:			case MessageType.REPSRTEATING:
                case MessageType.REQENDEATING:			case MessageType.REPENDEATING:
                case MessageType.HEFEREQ:                       case MessageType.HEFEDONE:
                case MessageType.HBREQ:                         case MessageType.HBDONE:
                case MessageType.HACBEREQ:                      case MessageType.HACBEDONE:
                case MessageType.SHAEREQ:                       case MessageType.SHAEDONE:
                        return 3;
                //Additional Messages
                case MessageType.REQGETSTDBEIANSW:	case MessageType.REPGETSTDBEIANSW:
                case MessageType.REQGETFRSTARR:		case MessageType.REPGETFRSTARR:
                case MessageType.REQGETLSTEAT:		case MessageType.REPGETLSTEAT:
                case MessageType.REQSETFRSTARR:		case MessageType.REPSETFRSTARR:
                case MessageType.REQSETLSTARR:		case MessageType.REPSETLSTARR:
                case MessageType.REQKITSHUT:		case MessageType.REPKITSHUT:
                case MessageType.REQBARSHUT:		case MessageType.REPBARSHUT:
                case MessageType.REQTABSHUT:		case MessageType.REPTABSHUT:
                        return 4;
                //GeneralRepo Message
                case MessageType.REQSETCHST:		 case MessageType.REPSETCHST:
                case MessageType.REQSETWAIST:		 case MessageType.REPSETWAIST:
                case MessageType.REQUPDTSTUST1:		 case MessageType.REPUPDTSTUST1:
                case MessageType.REQUPDTSTUST2:		 case MessageType.REPUPDTSTUST2:
                case MessageType.REQSETNCOURSES:	 case MessageType.REPSETNCOURSES:
                case MessageType.REQSETNPORTIONS:	 case MessageType.REPSETNPORTIONS:
                case MessageType.REQUPDSEATSTABLE:	 case MessageType.REPUPDSEATSTABLE:
                case MessageType.REQUPDSEATSTABLELV: case MessageType.REPUPDSEATSTABLELV:
                case MessageType.REQGENERALREPOSHUT: case MessageType.REPGENERALREPOSHUT:
                        return 5;
                default:
                        return -1;
            }
    }

    @Override
    public String toString() {
        return ("Message type: " + msgType
                + "\nCheck: " + check
                + "\nRequest: " + request
                + "\nStudent ID: " + studentID
                + "\nStudent State: " + studentState
                + "\nWaiter State: " + waiterState
                + "\nChef State: " + chefState);
//                + "\nVar 1: " + var1
//                + "\nVar 2: " + var2);
    }

}