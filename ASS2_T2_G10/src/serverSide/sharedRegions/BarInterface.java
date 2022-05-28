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
        Message outMessage = null; // mensagem de resposta

        /* validation of the incoming message */

        /* processing */
        /*
        switch (inMessage.getMsgType()) {
            case MessageType.LAREQ:
                if ((inMessage.getWaiterState() < 0) || (inMessage.getWaiterState() > 6))
                    throw new MessageException("Invalid waiter state!", inMessage);
                break;
            case MessageType.ENTREQ:
                if ((inMessage.getStudentState() < 0) || (inMessage.getStudentState() > 7))
                    throw new MessageException("Invalid student state!", inMessage);
                if ((inMessage.getStudentID() < 0) || (inMessage.getStudentID() > 6))
                    throw new MessageException("Invalid student ID!", inMessage);
                break;
            case MessageType.CWREQ:
                if ((inMessage.getStudentState() < 0) || (inMessage.getStudentState() > 7))
                    throw new MessageException("Invalid student state!", inMessage);
                if ((inMessage.getStudentID() < 0) || (inMessage.getStudentID() > 6))
                    throw new MessageException("Invalid student ID!", inMessage);
                break;
            case MessageType.ALREQ:
                if ((inMessage.getChefState() < 0) || (inMessage.getChefState() > 4))
                    throw new MessageException("Invalid chef state!", inMessage);
                break;
            case MessageType.SWREQ:
                if ((inMessage.getStudentState() < 0) || (inMessage.getStudentState() > 7))
                    throw new MessageException("Invalid student state!", inMessage);
                if ((inMessage.getStudentID() < 0) || (inMessage.getStudentID() > 6))
                    throw new MessageException("Invalid student ID!", inMessage);
                break;
            case MessageType.GSBAREQ:
                if ((inMessage.getStudentState() < 0) || (inMessage.getStudentState() > 7))
                    throw new MessageException("Invalid student state!", inMessage);
                if ((inMessage.getStudentID() < 0) || (inMessage.getStudentID() > 6))
                    throw new MessageException("Invalid student ID!", inMessage);
                break;
            case MessageType.PBREQ:
                if ((inMessage.getWaiterState() < 0) || (inMessage.getWaiterState() > 6))
                    throw new MessageException("Invalid waiter state!", inMessage);
                break;
            case MessageType.SGREQ:
                if ((inMessage.getWaiterState() < 0) || (inMessage.getWaiterState() > 6))
                    throw new MessageException("Invalid waiter state!", inMessage);
                break;
            case MessageType.EXITREQ:
                if ((inMessage.getStudentState() < 0) || (inMessage.getStudentState() > 7))
                    throw new MessageException("Invalid student state!", inMessage);
                if ((inMessage.getStudentID() < 0) || (inMessage.getStudentID() > 6))
                    throw new MessageException("Invalid student ID!", inMessage);
                break;
            case MessageType.SHUT:
                break;
            default:
                throw new MessageException("Invalid message type!", inMessage);
        }
        */
       // check nothing

        // processing 

        switch(inMessage.getMsgType()) {
        	case MessageType.ENTREQ:  
        		((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                ((BarClientProxy) Thread.currentThread()).setStudentState(inMessage.getStudentState());
                bar.enter();
            	outMessage = new Message(MessageType.ENTDONE,
                        ((BarClientProxy) Thread.currentThread()).getStudentId(),
                        ((BarClientProxy) Thread.currentThread()).getStudentState());
            	System.out.println("OUT:\n"+outMessage.toString());
                break;
                
            case MessageType.CWREQ:
            	((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentState());
                bar.callWaiter();
            	outMessage = new Message(MessageType.CWDONE,
            			((BarClientProxy) Thread.currentThread()).getStudentId(),
                        ((BarClientProxy) Thread.currentThread()).getStudentState());
                //nao sei se falta alguma coisa
                break;

            case MessageType.SWREQ:
            	((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentState());
                bar.signalWaiter();
            	outMessage = new Message(MessageType.SWDONE,
            			((BarClientProxy) Thread.currentThread()).getStudentId(),
                        ((BarClientProxy) Thread.currentThread()).getStudentState());
                //nao sei se falta alguma coisa
                break;
                
            case MessageType.EXITREQ:
            	((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                ((BarClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentState());
                bar.exit();
            	outMessage = new Message(MessageType.EXITDONE,
                        ((BarClientProxy) Thread.currentThread()).getStudentId(),
                        ((BarClientProxy) Thread.currentThread()).getStudentState());
                //nao sei se falta alguma coisa
                break;

            case MessageType.LAREQ:
            	((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
            	char request = bar.lookAround();
            	outMessage = new Message(MessageType.LADONE, 
                        ((BarClientProxy)Thread.currentThread()).getWaiterState(), request);
                //nao sei se falta alguma coisa
                break;

            case MessageType.SGREQ:
            	((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
                if(bar.sayGoodbye()) {
                	outMessage = new Message(MessageType.SGDONE,
                            ((BarClientProxy) Thread.currentThread()).getWaiterState());
                }
                //nao sei se falta alguma coisa
                break;
                
            case MessageType.PBREQ:
            	((BarClientProxy) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
            	bar.preprareBill();
                outMessage = new Message(MessageType.PBDONE,
                		((BarClientProxy) Thread.currentThread()).getWaiterState());
                //nao sei se falta alguma coisa
                break;
            
            case MessageType.ALREQ:
            	((BarClientProxy) Thread.currentThread()).setChefState(inMessage.getChefState());
            	bar.alertWaiter();
            	outMessage = new Message(MessageType.ALDONE,
                        ((BarClientProxy) Thread.currentThread()).getChefState());
                //nao sei se falta alguma coisa
                break;

            case MessageType.SHUT:
            	bar.shutdown();
                outMessage = new Message(MessageType.SHUTDONE);
                break;
        }

        return (outMessage);
    }
}