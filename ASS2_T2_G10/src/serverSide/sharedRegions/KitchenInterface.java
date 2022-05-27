/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.sharedRegions;

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
      Message outMessage = null;                                     // outgoing message

     // validation of the incoming message 
     /*
      switch (inMessage.getMsgType ())
      { case MessageType.REQCUTH:  if ((inMessage.getCustId () < 0) || (inMessage.getCustId () >= SimulPar.N))
                                      throw new MessageException ("Invalid customer id!", inMessage);
                                      else if ((inMessage.getCustState () < CustomerStates.DAYBYDAYLIFE) || (inMessage.getCustState () > CustomerStates.CUTTHEHAIR))
                                              throw new MessageException ("Invalid customer state!", inMessage);
                                   break;
        case MessageType.SLEEP:    if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                                      throw new MessageException ("Invalid barber id!", inMessage);
                                   break;
        case MessageType.CALLCUST: if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                                      throw new MessageException ("Invalid barber id!", inMessage);
                                      else if ((inMessage.getBarbState () < BarberStates.SLEEPING) || (inMessage.getBarbState () > BarberStates.INACTIVITY))
                                              throw new MessageException ("Invalid barber state!", inMessage);
                                   break;
        case MessageType.RECPAY:   if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                                      throw new MessageException ("Invalid barber id!", inMessage);
                                      else if ((inMessage.getBarbState () < BarberStates.SLEEPING) || (inMessage.getBarbState () > BarberStates.INACTIVITY))
                                              throw new MessageException ("Invalid barber state!", inMessage);
                                              else if ((inMessage.getCustId () < 0) || (inMessage.getCustId () >= SimulPar.N))
                                                      throw new MessageException ("Invalid customer id!", inMessage);
                                   break;
        case MessageType.ENDOP:    if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                                      throw new MessageException ("Invalid barber id!", inMessage);
                                   break;
        case MessageType.SHUT:     // check nothing
                                   break;
        default:                   throw new MessageException ("Invalid message type!", inMessage);
      }
     */
     // processing 

      switch (inMessage.getMsgType ())

      { case MessageType.WTNREQ:  ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState ());
                                    kitchen.watchTheNews ();
                                      outMessage = new Message (MessageType.WTNDONE,
                                                                ((KitchenClientProxy) Thread.currentThread ()).getChefState ());
                                   break;
         case MessageType.STPREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    kitchen.startPreparation ();
                                       outMessage= new Message (MessageType.STPDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;
         case MessageType.PTPREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    kitchen.proceedPreparation ();
                                       outMessage= new Message (MessageType.PTPDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;

         case MessageType.HNPRREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    kitchen.haveNextPortionReady ();
                                       outMessage= new Message (MessageType.HNPRDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;
                  
         case MessageType.CPREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    kitchen.continuePreparation ();
                                       outMessage= new Message (MessageType.CPDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;
                        
         case MessageType.HAPBDREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    if (kitchen.haveAllPortionsBeenDelivered ())
                                       outMessage= new Message (MessageType.HAPBDDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;

         case MessageType.HOBCREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    if (kitchen.hasOrderBeenCompleted ())
                                       outMessage= new Message (MessageType.HOBCDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;

         case MessageType.CUREQ: ((KitchenClientProxy) Thread.currentThread ()).setChefState (inMessage.getChefState());
                                    kitchen.cleanUp ();
                                       outMessage= new Message (MessageType.CUDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getChefState());
                                    break;

         case MessageType.RTBREQ: ((KitchenClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState());
                                    kitchen.returnToBar ();
                                       outMessage= new Message (MessageType.RTBDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getWaiterState());
                                    break;
                              
         case MessageType.HNTCREQ: ((KitchenClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState());
                                    kitchen.handNoteToChef ();
                                       outMessage= new Message (MessageType.HNTCDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getWaiterState());
                                    break;
         
         case MessageType.CPORREQ: ((KitchenClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState());
                                   kitchen.collectPortion ();
                                       outMessage= new Message (MessageType.CPORDONE,
                                                               ((KitchenClientProxy)Thread.currentThread ()).getWaiterState());
                                    break;
        
        case MessageType.SHUT:     kitchen.shutdown ();
                                   outMessage = new Message (MessageType.SHUTDONE);
                                   break;
      }

     return (outMessage);
   }
}
