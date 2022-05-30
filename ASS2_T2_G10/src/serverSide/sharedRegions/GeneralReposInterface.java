package serverSide.sharedRegions;

import clientSide.entities.ChefState;
import clientSide.entities.StudentState;
import clientSide.entities.WaiterState;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.GeneralReposClientProxy;


/**
 *  Interface to the General Repository of Information
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    General Repository and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class GeneralReposInterface {
	/**
	 * Reference to the General Repos
	 */
	private final GeneralRepos repos;

	/**
	 * Instantiation of an interface to the General Repos.
	 * 	@param repos reference to the General Repository
	 */
	public GeneralReposInterface(GeneralRepos repos) {
		this.repos = repos;
	}
	
	/**
	 * Processing of the incoming messages
	 * Validation, execution of the corresponding method and generation of the outgoing message.
	 * 
	 * 	@param inMessage service request
	 * 	@return service reply
	 * 	@throws MessageException if incoming message was not valid
	 */
	public Message processAndReply(Message inMessage) throws MessageException {
		//outGoing message
		Message outMessage = null;

		/* Validation of the incoming message */
		
		switch(inMessage.getMsgType())
		{
		// verify Chef state
		case MessageType.STCSTREQ:
			if (inMessage.getChefState() < ChefState.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefState.CLOSING_SERVICE)
				throw new MessageException ("Invalid Chef state!", inMessage);
			break;
		// verify Waiter state
		case MessageType.STWSTREQ:
			if (inMessage.getWaiterState() < WaiterState.APPRAISING_SITUATION || inMessage.getWaiterState() > WaiterState.RECEIVING_PAYMENT)
				throw new MessageException("Invalid Waiter state!", inMessage);
			break;
		// verify Student state
		case MessageType.USSTREQ1:
		case MessageType.USSTREQ2:
			if (inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
				throw new MessageException("Invalid Student state!", inMessage);
			break;
		// verify only message type
		case MessageType.SETNCREQ:
		case MessageType.SETNPREQ:
		case MessageType.USATREQ:
		case MessageType.USALREQ:
		case MessageType.GRSHUTREQ:
			break;
		default:
			throw new MessageException ("Invalid message type!", inMessage);
		}
		
		/* Processing of the incoming message */

		switch(inMessage.getMsgType())
		{
		case MessageType.STCSTREQ:
			repos.setChefState(inMessage.getChefState());
			outMessage = new Message(MessageType.STCSTDONE);
			break;
		case MessageType.STWSTREQ:
			repos.setWaiterState(inMessage.getWaiterState());
			outMessage = new Message(MessageType.STWSTDONE);
			break;
		case MessageType.USSTREQ1:
		case MessageType.USSTREQ2:
			if (inMessage.getMsgType() == MessageType.USSTREQ1) {
				repos.updateStudentState(inMessage.getStudentId(), inMessage.getStudentState());
				outMessage = new Message(MessageType.USSTDONE1);
				break;
			} else { 
				repos.updateStudentState(inMessage.getStudentId(), inMessage.getStudentState(), inMessage.getHold());
				outMessage = new Message(MessageType.USSTDONE2);
			}
			break;
		case MessageType.SETNCREQ:
			((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getNCourses());
			repos.setnCourses(((GeneralReposClientProxy) Thread.currentThread()).getValue());
			outMessage = new Message(MessageType.SETNCDONE);
			break;
		case MessageType.SETNPREQ:
			((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getNPortions());
			repos.setnPortions(((GeneralReposClientProxy) Thread.currentThread()).getValue());
			outMessage = new Message(MessageType.SETNPDONE);
			break;
		case MessageType.USATREQ:
			repos.updateSeatsAtTable(inMessage.getSeatAtTable(), inMessage.getStudentId());
			outMessage = new Message(MessageType.USATDONE);
			break;
		case MessageType.USALREQ:
			repos.updateSeatsAtTable(inMessage.getStudentId(), -1);
			outMessage = new Message(MessageType.USALDONE);
			break;
		case MessageType.GRSHUTREQ:
			repos.shutdown();
			outMessage = new Message(MessageType.GRSHUTDONE);
			break;
		}
		return (outMessage);
	}

}
