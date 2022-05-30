package serverSide.sharedRegions;

import clientSide.entities.ChefState;
import clientSide.entities.StudentState;
import clientSide.entities.WaiterState;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.BarClientProxy;


/**
 *  Interface to the Bar
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Bar and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */

public class BarInterface {
	/**
	 * Reference to the Bar
	 */
	private final Bar bar;


	/**
	 * Instantiation of an interface to the Bar.
	 * 	@param bar reference to the bar
	 */
	public BarInterface(Bar bar)
	{
		this.bar = bar;
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
		//outGoing message
		Message outMessage = null;

		/* Validation of the incoming message */

		switch(inMessage.getMsgType())
		{
			// Chef Messages that require type and state verification
			case MessageType.ALREQ: 		// Alert the Waiter Request
				if (inMessage.getChefState() < ChefState.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefState.CLOSING_SERVICE)
					throw new MessageException ("Invalid Chef state!", inMessage);
				break;
			
			//Waiter Messages that require only type verification
			case MessageType.LAREQ: 		// Look around Request
			case MessageType.BSHUTREQ:		// Bar shutdown 
				break;
			// Waiter Messages that require type and state verification
			case MessageType.PBREQ: 		// Prepare the bill Request
			case MessageType.SGREQ: 		// Say goodbye Request
				if (inMessage.getWaiterState() < WaiterState.APPRAISING_SITUATION || inMessage.getWaiterState() > WaiterState.RECEIVING_PAYMENT)
					throw new MessageException("Inavlid Waiter state!", inMessage);
				break;
			
			//Student Messages that require only type and id verification (already done in Message Constructor)
			case MessageType.CWREQ:		// Call the waiter Request
				break;
			// Student Messages that require type, state and id verification (done in Message Constructor)
			case MessageType.ENTREQ:			// Enter Request
			case MessageType.SWREQ:			// Signal the waiter Request
			case MessageType.EXITREQ:			// exit Request
				if (inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
					throw new MessageException("Invalid Student state!", inMessage);
				break;
			
			//Additional Messages
			case MessageType.GSBAREQ:
				break;
			default:
				throw new MessageException ("Invalid message type!", inMessage);
		}

		/* Processing of the incoming message */

		switch(inMessage.getMsgType())
		{
			case MessageType.ALREQ:
				((BarClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
				bar.alertWaiter();
				outMessage = new Message(MessageType.ALDONE, ((BarClientProxy) Thread.currentThread()).getChefState());
				break;
			case MessageType.LAREQ:
				char c = bar.lookAround();
				outMessage = new Message(MessageType.LADONE, c);
				break;
			case MessageType.PBREQ:
				((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
				bar.prepareBill();
				outMessage = new Message(MessageType.PBDONE, ((BarClientProxy) Thread.currentThread()).getWaiterState());
				break;
			case MessageType.SGREQ:
				((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
				boolean b = bar.sayGoodbye();
				outMessage = new Message(MessageType.SGDONE, b);
				break;
			case MessageType.ENTREQ:
				((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				bar.enter();
				outMessage = new Message(MessageType.ENTDONE, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.CWREQ:
				((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				bar.callWaiter();
				outMessage = new Message(MessageType.CWDONE, ((BarClientProxy) Thread.currentThread()).getStudentId());
				break;
			case MessageType.SWREQ:
				((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				bar.signalWaiter();
				outMessage = new Message(MessageType.SWDONE, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.EXITREQ:
				((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
				((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentId());
				bar.exit();
				outMessage = new Message(MessageType.EXITDONE, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
				break;
			case MessageType.GSBAREQ:
				int id = bar.getStudentBeingAnswered();
				outMessage = new Message(MessageType.GSBADONE, id);
				break;
			case MessageType.BSHUTREQ:
				bar.shutdown();
				outMessage = new Message(MessageType.BSHUTDONE);
				break;
		}

		return (outMessage);
	}
}
