/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.sharedRegions;

import commInfra.Message;
import commInfra.MessageException;
import commInfra.MessageType;
import serverSide.entities.TableClientProxy;

/**
 *  Interface to the Table.
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
   *  Reference to the table.
   */

   private final Table table;

  /**
   *  Instantiation of an interface to the table.
   *
   *    @param table reference to the kitchen
   */

   public TableInterface (Table table)
   {
      this.table = table;
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

      { case MessageType.SCREQ:  ((TableClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState ());
                                   table.saluteClient(inMessage.getStudentID());
                                      outMessage = new Message (MessageType.SCDONE,
                                                                ((TableClientProxy) Thread.currentThread ()).getWaiterState ());
                                   break;

         case MessageType.RBREQ:  ((TableClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState ());
                                   table.returnBar ();
                                      outMessage = new Message (MessageType.RBDONE,
                                                                ((TableClientProxy) Thread.currentThread ()).getWaiterState ());
                                   break;

         case MessageType.GBREQ:  ((TableClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState ());
                                   table.getThePad ();
                                      outMessage = new Message (MessageType.GBDONE,
                                                                ((TableClientProxy) Thread.currentThread ()).getWaiterState ());
                                   break;

         case MessageType.HACBSREQ:  ((TableClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState ());
                                   if (table.haveAllClientsBeenServed ())
                                      outMessage = new Message (MessageType.HACBSDONE,
                                                                ((TableClientProxy) Thread.currentThread ()).getWaiterState ());
                                   break;

         case MessageType.DPREQ:  ((TableClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState ());
                                   table.deliverPortion ();
                                      outMessage = new Message (MessageType.DPDONE,
                                                                ((TableClientProxy) Thread.currentThread ()).getWaiterState ());
                                   break;    

         case MessageType.PBREQ:  ((TableClientProxy) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState ());
                                   table.presentBill ();
                                      outMessage = new Message (MessageType.PBDONE,
                                                                ((TableClientProxy) Thread.currentThread ()).getWaiterState ());
                                   break;

         case MessageType.GFTAREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.getFirstToArrive ();
                                       outMessage = new Message (MessageType.GFTADONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());  

                                   break;
         
         case MessageType.GLTEREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                   table.getLastToEat ();
                                      outMessage = new Message (MessageType.GLTEDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ()); 
                                   break;

         case MessageType.SATREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                   ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.seatAtTable ();
                                      outMessage = new Message (MessageType.SATDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());  
                                   break;

         case MessageType.RMREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.readMenu ();
                                      outMessage = new Message (MessageType.RMDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());    
                                   break;

         case MessageType.POREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.prepareOrder ();
                                      outMessage = new Message (MessageType.PODONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());    
                                   break;

         case MessageType.EHCREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.everybodyHasChosen ();
                                      outMessage = new Message (MessageType.EHCDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());    
                                   break;

         case MessageType.AUOCREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.addUpOnesChoices ();
                                      outMessage = new Message (MessageType.AUOCDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());    
                                   break;

         case MessageType.DOREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.describeOrder ();
                                      outMessage = new Message (MessageType.DODONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());    
                                   break;

         case MessageType.JTREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.joinTalk ();
                                      outMessage = new Message (MessageType.JTDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());  
                                   break;

         case MessageType.ICREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.informCompanion ();
                                      outMessage = new Message (MessageType.ICDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());   
                                   break;

         case MessageType.SEREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.startEating ();
                                      outMessage = new Message (MessageType.SEDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());   
                                   break;

         case MessageType.EEREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.endEating ();
                                      outMessage = new Message (MessageType.EEDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());   
                                   break;

         case MessageType.HEFEREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.hasEverybodyFinishedEating();
                                      outMessage = new Message (MessageType.HEFEDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());      
                                   break;

         case MessageType.HBREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                    table.honourBill ();
                                      outMessage = new Message (MessageType.HBDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());     
                                   break;

         case MessageType.HACBEREQ: ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                   if (table.haveAllCoursesBeenEaten ())
                                      outMessage = new Message (MessageType.HACBEDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());      
                                   break;

         case MessageType.SHAEREQ:  ((TableClientProxy) Thread.currentThread()).setStudentId(inMessage.getStudentID());
                                    ((TableClientProxy) Thread.currentThread ()).setStudentState (inMessage.getStudentState ());
                                   if (table.shouldHaveArrivedEarlier ())
                                      outMessage = new Message (MessageType.SHAEDONE,
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentId (),
                                                                  ((TableClientProxy) Thread.currentThread ()).getStudentState ());     
                                   break;

        case MessageType.SHUT:     table.shutdown ();
                                   outMessage = new Message (MessageType.SHUTDONE);
                                   break;
      }

     return (outMessage);
   }
   
}

