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
        Message outMessage = null;                                     // outgoing message

        // validation of the incoming message 

        switch (inMessage.getMsgType ())
        {   case MessageType.REQCUTH -> {
            if ((inMessage.getStudentID()< 0) || (inMessage.getStudentID()>= ExecConst.Nstudents))
                throw new MessageException ("Invalid student id!", inMessage);
            else if ((inMessage.getStudentState()< StudentState.DAYBYDAYLIFE) || (inMessage.getStudentState()> StudentState.CUTTHEHAIR))
                throw new MessageException ("Invalid student state!", inMessage);
           }
            case MessageType.SLEEP -> {
                if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                    throw new MessageException ("Invalid barber id!", inMessage);
           }
            case MessageType.CALLCUST -> {
                if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                    throw new MessageException ("Invalid barber id!", inMessage);
                else if ((inMessage.getBarbState () < BarberStates.SLEEPING) || (inMessage.getBarbState () > BarberStates.INACTIVITY))
                    throw new MessageException ("Invalid barber state!", inMessage);
           }
            case MessageType.RECPAY -> {
                if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                    throw new MessageException ("Invalid barber id!", inMessage);
                else if ((inMessage.getBarbState () < BarberStates.SLEEPING) || (inMessage.getBarbState () > BarberStates.INACTIVITY))
                    throw new MessageException ("Invalid barber state!", inMessage);
                else if ((inMessage.getCustId () < 0) || (inMessage.getCustId () >= SimulPar.N))
                    throw new MessageException ("Invalid customer id!", inMessage);
           }
            case MessageType.ENDOP -> {
                if ((inMessage.getBarbId () < 0) || (inMessage.getBarbId () >= SimulPar.M))
                    throw new MessageException ("Invalid barber id!", inMessage);
           }
            case MessageType.SHUT -> {
           }
            default -> throw new MessageException ("Invalid message type!", inMessage);
        }
       // check nothing

        // processing 

        switch (inMessage.getMsgType ())

        {   case MessageType.ALREQ:  ((BarClientProxy) Thread.currentThread ()).setStudentState(inMessage.getStudentID());
                                   ((BarClientProxy) Thread.currentThread ()).setStudentState(inMessage.getStudentID());
                                    bar.alertWaiter();
                                    outMessage = new Message (MessageType.ALDONE,
                                                            ((BarClientProxy) Thread.currentThread ()).getStudentId(),
                                                            ((BarClientProxy) Thread.currentThread ()).getStudentId());
                                   break;
            case MessageType.SLEEP:    ((BarClientProxy) Thread.currentThread ()).setBarberId (inMessage.getBarbId ());
                                   if (bShop.goToSleep ())
                                      outMessage = new Message (MessageType.SLEEPDONE,
                                                                ((BarClientProxy) Thread.currentThread ()).getBarberId (), true);
                                      else outMessage = new Message (MessageType.SLEEPDONE,
                                                                     ((BarClientProxy) Thread.currentThread ()).getBarberId (), false);
                                   break;
            case MessageType.CALLCUST: ((BarberShopClientProxy) Thread.currentThread ()).setBarberId (inMessage.getBarbId ());
                                   ((BarberShopClientProxy) Thread.currentThread ()).setBarberState (inMessage.getBarbState ());
                                   int custId = bShop.callACustomer ();
                                   outMessage = new Message (MessageType.CCUSTDONE,
                                                             ((BarberShopClientProxy) Thread.currentThread ()).getBarberId (),
                                                             ((BarberShopClientProxy) Thread.currentThread ()).getBarberState (), custId);
                                   break;
            case MessageType.RECPAY:   ((BarClientProxy) Thread.currentThread ()).setBarberId (inMessage.getBarbId ());
                                   ((BarClientProxy) Thread.currentThread ()).setBarberState (inMessage.getBarbState ());
                                   bar.receivePayment (inMessage.getCustId ());
                                   outMessage = new Message (MessageType.RPAYDONE,
                                                             ((BarClientProxy) Thread.currentThread ()).getBarberId (),
                                                             ((BarClientProxy) Thread.currentThread ()).getBarberState ());
                                   break;
            case MessageType.ENDOP:    bar.endOperation (inMessage.getBarbId ());
                                   outMessage = new Message (MessageType.EOPDONE, inMessage.getBarbId ());
                                   break;
            case MessageType.SHUT:     bar.shutdown ();
                                   outMessage = new Message (MessageType.SHUTDONE);
                                   break;
        }

        return (outMessage);
    }
   
}

