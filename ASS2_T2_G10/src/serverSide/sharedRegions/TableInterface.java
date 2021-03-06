package serverSide.sharedRegions;

import clientSide.entities.StudentState;
import clientSide.entities.WaiterState;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.TableClientProxy;

/**
 *  Interface to the Table
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Table and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class TableInterface {

	/**
	 * Reference to the Table
	 */
	private final Table tab;


	/**
	 * Instantiation of an interface to the Table.
	 * 	@param tab reference to the table
	 */
	public TableInterface(Table tab)
	{
		this.tab = tab;
	}


	/**
	 * Processing of the incoming messages
	 * Validation, execution of the corresponding method and generation of the outgoing message.
	 * 
	 * 	@param inMessage service request
	 * 	@return service reply
	 * 	@throws MessageException if incoming message was not valid
	 */
	public Message processAndReply (Message inMessage) throws MessageException
	{
		// outGoing message
		Message outMessage = null;

		/* Validation of the incoming message */

		switch(inMessage.getMsgType())
		{
			//Waiter Messages that require only type verification
			case MessageType.HACBSREQ:		// Have all clients been served
			case MessageType.DPREQ:				// Deliver portion
			case MessageType.TSHUTREQ:			// Table shutdown
				break;
			// Waiter Messages that require type and state verification
			case MessageType.SCREQ:			// Salute the clients
			case MessageType.RBREQ:			// Return to the bar
			case MessageType.GPREQ:				// Get the pad
			case MessageType.PREBREQ:			// Present the bill
				if (inMessage.getWaiterState() < WaiterState.APPRAISING_SITUATION || inMessage.getWaiterState() > WaiterState.RECEIVING_PAYMENT)
					throw new MessageException("Invalid Waiter state!", inMessage);
				break;
			//Student Messages that require only type verification
			case MessageType.EHCREQ:		// Has everybody chosen
			case MessageType.AUOCREQ:			// Add up ones choices
			case MessageType.DOREQ:			// Describe order
			case MessageType.HEFEREQ:	// Has everybody finished eating
			case MessageType.HBREQ:			// Honour the bill
			case MessageType.HACBEREQ:		// Have all courses been eaten
				break;
			// Student Messages that require type, state and id verification (done in Message Constructor)
			case MessageType.SATREQ:			// Seat at table
			case MessageType.RMREQ:				// Read menu
			case MessageType.POREQ:			// Prepare the order
			case MessageType.JTREQ:			// Join the talk
			case MessageType.ICREQ:			// Inform companion
			case MessageType.SEREQ:			// Start eating
			case MessageType.EEREQ:			// End eating
			case MessageType.SHAEREQ:		// Should have arrived earlier
				if (inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
					throw new MessageException("Inavlid Student state!", inMessage);
				break;
			//Aditional messages that require type verification
			case MessageType.GFTAREQ:			//Get first to arrive
			case MessageType.GLTEREQ:			//Get last to eat
			case MessageType.SFTAREQ:			//Set first to arrive
			case MessageType.SLTAREQ:			//Set last to arrive
				break;
			default:
				throw new MessageException ("Invalid message type!", inMessage);
		}

		/* Processing of the incoming message */
		
		switch(inMessage.getMsgType())
		{
			case MessageType.SCREQ:
				((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
				((TableClientProxy) Thread.currentThread()).setStudentBeingAnswered(inMessage.getStudentIdBeingAnswered());
				int i = inMessage.getStudentBeingAnswered();
				tab.saluteClient(i);
				outMessage = new Message(MessageType.SCDONE,  ((TableClientProxy) Thread.currentThread()).getStudentBeingAnswered(), ((TableClientProxy) Thread.currentThread()).getWaiterState());
				break;
			case MessageType.RBREQ:
				((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
				tab.returnBar();
				outMessage = new Message(MessageType.RBDONE, ((TableClientProxy) Thread.currentThread()).getWaiterState());
				break;
			case MessageType.GPREQ:
				((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
				tab.getThePad();
				outMessage = new Message(MessageType.GPDONE, ((TableClientProxy) Thread.currentThread()).getWaiterState());
				break;
			case MessageType.HACBSREQ:
				boolean b = tab.haveAllClientsBeenServed();
				outMessage = new Message(MessageType.HACBSDONE, b);
				break;
			case MessageType.DPREQ:
				tab.deliverPortion();
				outMessage = new Message(MessageType.DPDONE);
				break;
			case MessageType.PREBREQ:
				((TableClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
				tab.presentBill();
				outMessage = new Message(MessageType.PREBDONE, ((TableClientProxy) Thread.currentThread()).getWaiterState());
				break;
			case MessageType.SATREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				tab.seatAtTable();
				outMessage = new Message(MessageType.SATDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.RMREQ:
				((TableClientProxy) Thread.currentThread()).setStudentId( inMessage.getStudentId() );
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				tab.readMenu();
				outMessage = new Message(MessageType.RMDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.POREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				tab.prepareOrder();
				outMessage = new Message(MessageType.PODONE, ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.EHCREQ:
				boolean everyBodyChose = tab.everybodyHasChosen();
				outMessage = new Message(MessageType.EHCDONE, everyBodyChose);
				break;
			case MessageType.AUOCREQ:
				tab.addUpOnesChoices();
				outMessage = new Message(MessageType.AUOCDONE);
				break;
			case MessageType.DOREQ:
				tab.describeOrder();
				outMessage = new Message(MessageType.DODONE);
				break;
			case MessageType.JTREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				tab.joinTalk();
				outMessage = new Message(MessageType.JTDONE, ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.ICREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				tab.informCompanion();
				outMessage = new Message(MessageType.ICDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.SEREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				tab.startEating();
				outMessage = new Message(MessageType.SEDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.EEREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				tab.endEating();
				outMessage = new Message(MessageType.EEDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.HEFEREQ:
				((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				boolean everybodyEaten = tab.hasEverybodyFinishedEating();
				outMessage = new Message(MessageType.HEFEDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), everybodyEaten);
				break;
			case MessageType.HBREQ:
				tab.honourBill();
				outMessage = new Message(MessageType.HBDONE);
				break;
			case MessageType.HACBEREQ:
				boolean haveAllCoursesBeenEaten = tab.haveAllCoursesBeenEaten();
				outMessage = new Message(MessageType.HACBEDONE, haveAllCoursesBeenEaten);
				break;
			case MessageType.SHAEREQ:
				((TableClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				boolean shouldHaveArrived = tab.shouldHaveArrivedEarlier();
				outMessage = new Message(MessageType.SHAEDONE, ((TableClientProxy) Thread.currentThread()).getStudentId(), ((TableClientProxy) Thread.currentThread()).getStudentState(), shouldHaveArrived);
				break;
			case MessageType.GFTAREQ:
				int idFirst = tab.getFirstToArrive();
				outMessage = new Message(MessageType.GFTADONE, idFirst);
				break;
			case MessageType.GLTEREQ:
				int idLast = tab.getFirstToArrive();
				outMessage = new Message(MessageType.GLTEDONE, idLast);
				break;
			case MessageType.SFTAREQ:
				tab.setFirstToArrive(inMessage.getFirstToArrive());
				outMessage = new Message(MessageType.SFTADONE);
				break;
			case MessageType.SLTAREQ:	
				tab.setLastToArrive(inMessage.getLastToArrive());
				outMessage = new Message(MessageType.SLTADONE);
				break;
			case MessageType.TSHUTREQ:
				tab.shutdown();
				outMessage = new Message(MessageType.TSHUTDONE);
				break;				
		}
		
		return (outMessage);
	}
}
