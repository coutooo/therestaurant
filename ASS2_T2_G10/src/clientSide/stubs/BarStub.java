package clientSide.stubs;

import clientSide.entities.*;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;

/**
 *  Stub to the bar.
 *
 *    It instantiates a remote reference to the bar.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 *  @author Rafael Dias
 *  @author Manuel Couto
 */
public class BarStub {
	
    /**
     * Name of the platform where is located the bar Server
     */
    private String serverHostName;

    /**
     * Port number for listening to service requests
     */
    private int serverPortNum;


    /**
     * Instantiation of a stub to the Bar
     * @param serverHostName name of the platform where is located the bar Server
     * @param serverPortNum port number for listening to service requests
     */
    public BarStub(String serverHostName, int serverPortNum) {
        this.serverHostName = serverHostName;
        this.serverPortNum  = serverPortNum;
    }

    /**
    * Operation enter the restaurant
    */
    public void enter() {
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.ENTREQ, ((Student) Thread.currentThread()).getStudentID(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message		

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.ENTDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentID()!= ((Student) Thread.currentThread()).getStudentID())
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student id!");
                GenericIO.writelnString (inMessage.toString ());
        System.exit (1);
        }
        if(inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student state!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }

        ((Student) Thread.currentThread ()).setStudentState (inMessage.getStudentState());
        //Close communication channel
        com.close ();
    }

    /**
     * Operation alert the waiter
     */
    public void callWaiter() {
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.CWREQ, ((Student) Thread.currentThread()).getStudentID());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.CWDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID())
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student id!");
                GenericIO.writelnString (inMessage.toString ());
        System.exit (1);
        }
        //Close communication channel
        com.close ();
    }

    /**
     * Operation signal the waiter
     */
    public void signalWaiter() {
        ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SWREQ, ((Student) Thread.currentThread()).getStudentID(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SWDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID())
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student id!");
                GenericIO.writelnString (inMessage.toString ());
        System.exit (1);
        }
        if(inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student state!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        ((Student) Thread.currentThread ()).setStudentState (inMessage.getStudentState());
        //Close communication channel
        com.close ();
    }
    /**
     * Operation exit the restaurant
     */
    public void exit() {
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.EXITREQ, ((Student) Thread.currentThread()).getStudentID(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.EXITDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID())
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student id!");
                GenericIO.writelnString (inMessage.toString ());
        System.exit (1);
        }
        if(inMessage.getStudentState() < StudentState.GOING_TO_THE_RESTAURANT || inMessage.getStudentState() > StudentState.GOING_HOME)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student state!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        ((Student) Thread.currentThread ()).setStudentState (inMessage.getStudentState());
        //Close communication channel
        com.close ();
    }

    /**
    * Operation look Around
    * 
    * It is called by the waiter, he checks for pending service requests and if not waits for them
    * 	@return Character that represents the service to be executed
    * 		'c' : means a client has arrived therefore needs to be presented with the menu by the waiter
    * 		'o' : means that the waiter will hear the order and deliver it to the chef
    * 		'p' : means that a portion needs to be delivered by the waiter
    * 		'b' : means that the bill needs to be prepared and presented by the waiter
    * 		'g' : means that some student wants to leave and waiter needs to say goodbye 
    */
   public char lookAround() 
   {
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.LAREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.LADONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();	
        return inMessage.getRequestType();
   }
    
    /**
     * Operation say Goodbye
     */
    public boolean sayGoodbye() {
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.SGREQ, ((Waiter) Thread.currentThread()).getWaiterState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SGDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }

        //Close communication channel
        com.close ();
        return inMessage.getStudentsAtRestaurant();
    }
    /**
    * Operation prepare the Bill
    */
    public void prepareBill() 
    { 
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.PBREQ, ((Waiter) Thread.currentThread()).getWaiterState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.PBDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getWaiterState() < WaiterState.APPRAISING_SITUATION || inMessage.getWaiterState() > WaiterState.RECEIVING_PAYMENT)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid waiter state!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        ((Waiter) Thread.currentThread ()).setWaiterState (inMessage.getWaiterState());
        //Close communication channel
        com.close ();
    }
    /**
     * Operation alert the waiter
     */
    public void alertWaiter() {
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.ALREQ, ((Chef) Thread.currentThread()).getChefState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.ALDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getChefState() < ChefState.WAITING_FOR_AN_ORDER || inMessage.getChefState() > ChefState.CLOSING_SERVICE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid chef state!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        ((Chef) Thread.currentThread ()).setChefState (inMessage.getChefState());
        //Close communication channel
        com.close ();
    }
    
    /**
     * @return Id of the student whose request is being answered
     */
    public int getStudentBeingAnswered(){
    	ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.GSBAREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.GSBADONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        return inMessage.getStudentBeingAnswered();
    }


    /**
     *   Operation server shutdown.
     *
     *   New operation.
     */
    public void shutdown(){
        ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNum);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.BSHUTREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.BSHUTDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();			
    }
}
