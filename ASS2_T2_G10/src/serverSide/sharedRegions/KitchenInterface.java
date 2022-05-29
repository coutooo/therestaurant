/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.sharedRegions;

import clientSide.entities.ChefState;
import clientSide.entities.WaiterState;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.KitchenClientProxy;

/**
 *  Interface to the Kitchen.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    Kitchen and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class KitchenInterface {
    /**
   *  Reference to the kitchen.
   */

   private final Kitchen kitchen;

  /**
   *  Instantiation of an interface to the kitchen.
   *
   *    @param kitchen reference to the kitchen
   */

   public KitchenInterface (Kitchen kitchen)
   {
      this.kitchen = kitchen;
   }
   
   /**
   *  Processing of the incoming messages.
   *
   *  Validation, execution of the corresponding method and generation of the outgoing message.
   *
   *    @param inMessage service request
   *    @return service reply
   *    @throws MessageException if the incoming message is not valid
   */
   

   public Message processAndReply (Message inMessage) throws MessageException
    {
        //outGoing message
        Message outMessage = null;

        /* Validation of the incoming message */
        switch(inMessage.getMsgType())
        {
        // Chef Messages that require type and state verification
        case MessageType.WTNREQ: 		// Watching the news request
        case MessageType.STPREQ: 			// Start preparation of a course request
        case MessageType.PTPREQ: 		// Proceed to presentation request
        case MessageType.HNPRREQ:	// Have next portion ready
        case MessageType.CPREQ: 		// Continue preparation
        case MessageType.CUREQ: 		// Clean up
                if ((inMessage.getChefState() < ChefState.WAITING_FOR_AN_ORDER) || (inMessage.getChefState() > ChefState.CLOSING_SERVICE))
                        throw new MessageException ("Invalid Chef state!", inMessage);
                break;

        // Chef Messages that require only type verification
        case MessageType.HAPBDREQ: 		// Have all portions been delivered
        case MessageType.HOBCREQ: 		// Has the order been completed
        case MessageType.KSHUTREQ:		//Kitchen shutdown
                break;

        // Waiter Messages that require type and state verification	
        case MessageType.HNTCREQ: 	// Hand note to chef
        case MessageType.RTBREQ: 	// Return to bar
        case MessageType.CPORREQ: 		// Collect portion
                if(inMessage.getWaiterState() < WaiterState.APPRAISING_SITUATION || inMessage.getWaiterState() > WaiterState.RECEIVING_PAYMENT)
                        throw new MessageException ("Invalid Waiter state!", inMessage);
                break;
        default:
                throw new MessageException ("Invalid message type!", inMessage);
        }

        /* Processing */
        switch(inMessage.getMsgType())
        {
        case MessageType.WTNREQ: //Watching the news request
                ((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
                kitchen.watchTheNews();
                outMessage = new Message(MessageType.WTNDONE, ((KitchenClientProxy) Thread.currentThread()).getChefState());
                break;
        case MessageType.STPREQ: //Start preparation of a course request
                ((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
                kitchen.startPreparation();
                outMessage = new Message(MessageType.STPDONE, ((KitchenClientProxy) Thread.currentThread()).getChefState());
                break;
        case MessageType.PTPREQ: //Proceed to presentation request
                ((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
                kitchen.proceedPreparation();
                outMessage = new Message(MessageType.PTPDONE, ((KitchenClientProxy) Thread.currentThread()).getChefState());
                break;	
        case MessageType.HAPBDREQ: //Have all portions been delivered request
                boolean portionsDelivered = kitchen.haveAllPortionsBeenDelivered();
                outMessage = new Message(MessageType.HAPBDDONE, portionsDelivered);
                break;
        case MessageType.HOBCREQ: //Has the order been completed request
                boolean orderCompleted = kitchen.hasOrderBeenCompleted();
                outMessage = new Message(MessageType.HOBCDONE, orderCompleted);
                break;
        case MessageType.CPREQ: //Continue preparation
                ((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
                kitchen.continuePreparation();
                outMessage = new Message(MessageType.CPDONE, ((KitchenClientProxy) Thread.currentThread()).getChefState());
                break;
        case MessageType.HNPRREQ: //Have next portion ready
                ((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
                kitchen.haveNextPortionReady();
                outMessage = new Message(MessageType.HNPRDONE, ((KitchenClientProxy) Thread.currentThread()).getChefState());
                break;
        case MessageType.CUREQ: //clean up
                ((KitchenClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
                kitchen.cleanUp();
                outMessage = new Message(MessageType.CUDONE, ((KitchenClientProxy) Thread.currentThread()).getChefState());
                break;
        case MessageType.HNTCREQ: //hand note to chef
                ((KitchenClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
                kitchen.handNoteToChef();
                outMessage = new Message(MessageType.HNTCDONE, ((KitchenClientProxy) Thread.currentThread()).getWaiterState());
                break;
        case MessageType.RTBREQ: //return to bar
                ((KitchenClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
                kitchen.returnToBar();
                outMessage = new Message(MessageType.RTBDONE, ((KitchenClientProxy) Thread.currentThread()).getWaiterState());
                break;
        case MessageType.CPORREQ: //collect portion
                ((KitchenClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
                kitchen.collectPortion();
                outMessage = new Message(MessageType.CPORDONE, ((KitchenClientProxy) Thread.currentThread()).getWaiterState());
                break;
        case MessageType.KSHUTREQ: //Kitchen shutdown
                kitchen.shutdown();
                outMessage = new Message(MessageType.KSHUTDONE);
                break;
        }

        return (outMessage);
    }
}
