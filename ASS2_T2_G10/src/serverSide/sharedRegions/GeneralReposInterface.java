/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.sharedRegions;

import clientSide.entities.ChefState;
import clientSide.entities.StudentState;
import clientSide.entities.WaiterState;
import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;

/**
 *  Interface to the General Repository of Information.
 *
 *    It is responsible to validate and process the incoming message, execute the corresponding method on the
 *    General Repository and generate the outgoing message.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class GeneralReposInterface
{
  /**
   *  Reference to the general repository.
   */

   private final GeneralRepos repos;

  /**
   *  Instantiation of an interface to the general repository.
   *
   *    @param repos reference to the general repository
   */

   public GeneralReposInterface (GeneralRepos repos)
   {
      this.repos = repos;
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
   
   
    public Message processAndReply(Message inMessage) throws MessageException {
        //outGoing message
        Message outMessage = null;

        /* Validation of the incoming message */

        switch(inMessage.getMsgType())
        {
        // verify Chef state
        case MessageType.REQSETCHST:
                if (inMessage.getChefState() < ChefState.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefState.CLOSING_SERVICE)
                        throw new MessageException ("Invalid Chef state!", inMessage);
                break;
        // verify Waiter state
        case MessageType.REQSETWAIST:
                if (inMessage.getWaiterState() < WaiterState.APRAISING_SITUATION || inMessage.getWaiterState() > WaiterState.RECEIVING_PAYMENT)
                        throw new MessageException("Invalid Waiter state!", inMessage);
                break;
        // verify Student state
        case MessageType.REQUPDTSTUST1:
        case MessageType.REQUPDTSTUST2:
                if (inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
                        throw new MessageException("Invalid Student state!", inMessage);
                break;
        // verify only message type
        case MessageType.REQSETNCOURSES:
        case MessageType.REQSETNPORTIONS:
        case MessageType.REQUPDSEATSTABLE:
        case MessageType.REQUPDSEATSTABLELV:
        case MessageType.REQGENERALREPOSHUT:
                break;
        default:
                throw new MessageException ("Invalid message type!", inMessage);
        }

        /* Processing of the incoming message */

        switch(inMessage.getMsgType())
        {
        case MessageType.REQSETCHST:
                repos.setChefState(inMessage.getChefState());
                outMessage = new Message(MessageType.REPSETCHST);
                break;
        case MessageType.REQSETWAIST:
                repos.setWaiterState(inMessage.getWaiterState());
                outMessage = new Message(MessageType.REPSETWAIST);
                break;
        case MessageType.REQUPDTSTUST1:
        case MessageType.REQUPDTSTUST2:
                if (inMessage.getMsgType() == MessageType.REQUPDTSTUST1) {
                        repos.updateStudentState(inMessage.getStudentID(), inMessage.getStudentState());
                        outMessage = new Message(MessageType.REPUPDTSTUST1);
                        break;
                } else { 
                        repos.updateStudentState(inMessage.getStudentID(), inMessage.getStudentState(), inMessage.getHold());
                        outMessage = new Message(MessageType.REPUPDTSTUST2);
                }
                break;
        case MessageType.REQSETNCOURSES:
                ((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getNCourses());
                repos.setnCourses(((GeneralReposClientProxy) Thread.currentThread()).getValue());
                outMessage = new Message(MessageType.REPSETNCOURSES);
                break;
        case MessageType.REQSETNPORTIONS:
                ((GeneralReposClientProxy) Thread.currentThread()).setValue(inMessage.getNPortions());
                repos.setnPortions(((GeneralReposClientProxy) Thread.currentThread()).getValue());
                outMessage = new Message(MessageType.REPSETNPORTIONS);
                break;
        case MessageType.REQUPDSEATSTABLE:
                repos.updateSeatsAtTable(inMessage.getSeatAtTable(), inMessage.getStudentId());
                outMessage = new Message(MessageType.REPUPDSEATSTABLE);
                break;
        case MessageType.REQUPDSEATSTABLELV:
                repos.updateSeatsAtTable(inMessage.getStudentId(), -1);
                outMessage = new Message(MessageType.REPUPDSEATSTABLELV);
                break;
        case MessageType.REQGENERALREPOSHUT:
                repos.shutdown();
                outMessage = new Message(MessageType.REPGENERALREPOSHUT);
                break;
        }
        return (outMessage);
    }
   
}