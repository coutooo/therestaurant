/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import commInfra.*;


/**
 *  Interface to the Bar.
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
   *  Reference to the bar.
   */

   private final Bar bar;

  /**
   *  Instantiation of an interface to the bar.
   *
   *    @param bar reference to the bar
   */

   public BarInterface (Bar bar)
   {
      this.bar = bar;
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
                case MessageType.ALREQ: 		// Alert the Waiter Request
                        if (inMessage.getChefState() < ChefState.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefState.CLOSING_SERVICE)
                                throw new MessageException ("Invalid Chef state!", inMessage);
                        break;

                //Waiter Messages that require only type verification
                case MessageType.LAREQ: 		// Look around Request
                case MessageType.REQBARSHUT:		// Bar shutdown 
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
                case MessageType.REQGETSTDBEIANSW:
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
            case MessageType.REQPRPREBILL:
                    ((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
                    bar.prepareBill();
                    outMessage = new Message(MessageType.REPPRPREBILL, ((BarClientProxy) Thread.currentThread()).getWaiterState());
                    break;
            case MessageType.REQSAYGDBYE:
                    ((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
                    boolean b = bar.sayGoodbye();
                    outMessage = new Message(MessageType.REPSAYGDBYE, b);
                    break;
            case MessageType.REQENTER:
                    ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                    ((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
                    bar.enter();
                    outMessage = new Message(MessageType.REPENTER, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
                    break;
            case MessageType.REQCALLWAI:
                    ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                    bar.callWaiter();
                    outMessage = new Message(MessageType.REPCALLWAI, ((BarClientProxy) Thread.currentThread()).getStudentId());
                    break;
            case MessageType.REQSIGWAI:
                    ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                    ((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
                    bar.signalWaiter();
                    outMessage = new Message(MessageType.REPSIGWAI, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
                    break;
            case MessageType.REQEXIT:
                    ((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
                    ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                    bar.exit();
                    outMessage = new Message(MessageType.REPEXIT, ((BarClientProxy) Thread.currentThread()).getStudentId(), ((BarClientProxy) Thread.currentThread()).getStudentState());
                    break;
            case MessageType.REQGETSTDBEIANSW:
                    int id = bar.getStudentBeingAnswered();
                    outMessage = new Message(MessageType.REPGETSTDBEIANSW, id);
                    break;
            case MessageType.REQBARSHUT:
                    bar.shutdown();
                    outMessage = new Message(MessageType.REPBARSHUT);
                    break;
        }

        return (outMessage);
    }
}