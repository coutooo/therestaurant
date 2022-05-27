/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.stubs;

import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;

/**
 *  Stub to the general repository.
 *
 *    It instantiates a remote reference to the general repository.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class GeneralReposStub
{
  /**
   *  Name of the platform where is located the general repository server.
   */

   private String serverHostName;

  /**
   *  Port number for listening to service requests.
   */

   private int serverPortNumb;
   
    /**
    *  Number of portions that have been served, in each course;
    */
   private int nPortions;
   
    /**
    *  Number of courses that have been served
    */
   private int nCourses;

  /**
   *   Instantiation of a stub to the general repository.
   *
   *     @param serverHostName name of the platform where is located the barber shop server
   *     @param serverPortNumb port number for listening to service requests
   */

   public GeneralReposStub (String serverHostName, int serverPortNumb)
   {
      this.serverHostName = serverHostName;
      this.serverPortNumb = serverPortNumb;
   }
   
    public void initSimulation(String fileName, int nIter) {
        ClientCom com;                                                 // communication channel
        Message outMessage, // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom(serverHostName, serverPortNumb);
        while (!com.open()) {
            try {
                Thread.sleep((long) (1000));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(MessageType.SETNFIC, fileName, nIter);
        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();
        if (inMessage.getMsgType() != MessageType.NFICDONE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Invalid message type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        com.close();
    }
   
  /**
   *   Set student state.
   *
   *     @param id student id
   *     @param state student state
   */

   public void setStudentState (int id, int state)
   {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())
      { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.STSST, id, state);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if (inMessage.getMsgType() != MessageType.SACK)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
   }

  /**
   *   Set chef state.
   *
   *     @param state chef state
   */

   public void setChefState (int state)
   {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())
      { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.STCST, state);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if (inMessage.getMsgType() != MessageType.SACK)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
   }
   
   /**
   *   Set waiter state.
   *
   *     @param state waiter state
   */
   
   public void setWaiterState (int state)
   {
      ClientCom com;                                                 // communication channel
      Message outMessage,                                            // outgoing message
              inMessage;                                             // incoming message

      com = new ClientCom (serverHostName, serverPortNumb);
      while (!com.open ())
      { try
        { Thread.sleep ((long) (1000));
        }
        catch (InterruptedException e) {}
      }
      outMessage = new Message (MessageType.STWST, state);
      com.writeObject (outMessage);
      inMessage = (Message) com.readObject ();
      if (inMessage.getMsgType() != MessageType.SACK)
         { GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
           GenericIO.writelnString (inMessage.toString ());
           System.exit (1);
         }
      com.close ();
   }
   
   
   public void updateSeatsAtTable(int i, int studentId) {
        ClientCom com;                                                 // communication channel
        Message outMessage,                                            // outgoing message
                inMessage;                                             // incoming message

        com = new ClientCom(serverHostName, serverPortNumb);
        while(!com.open()){
            try{
                    Thread.sleep((long) (1000));
            }
            catch (InterruptedException e) {}
        }
        outMessage = new Message(MessageType.USSEATREQ, i, studentId);

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        if(inMessage.getMsgType() != MessageType.USSEATDONE) {
            GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid message type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        com.close ();
    }
   
    public void shutdown(){
		ClientCom com;                                                 // communication channel
		Message outMessage,                                            // outgoing message
				inMessage;                                             // incoming message
	
		com = new ClientCom(serverHostName, serverPortNumb);
		while (!com.open ()) {
			try {
				Thread.sleep((long) (1000));
	        }
	        catch (InterruptedException e) {}
		}
		
		//MESSAGES
	    outMessage = new Message(MessageType.SHUT);
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    if (inMessage.getMsgType() != MessageType.SHUTDONE) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid message type!");
	        GenericIO.writelnString(inMessage.toString());
	        System.exit(1);
	    }
	    com.close();
	}

    public void setnPortions(int numberOfPortionsDelivered) {
        nPortions = numberOfPortionsDelivered;
    }

    public void setnCourses(int i) {
        nCourses = i;
    }

    

}
