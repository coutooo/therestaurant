/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.stubs;

import clientSide.entities.Student;
import clientSide.entities.StudentCloning;
import clientSide.entities.StudentState;
import clientSide.entities.Waiter;
import clientSide.entities.WaiterState;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;
import serverSide.entities.BarClientProxy;

/**
 *  Stub to the Table.
 *
 *    It instantiates a remote reference to the table.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class TableStub {
	/**
	 * Name of the platform where is located the table Server
	 */
	private String serverHostName;
	
	/**
	 * Port number for listening to service requests
	 */
	private int serverPortNum;
	
	
	/**
	 * Instantiation of a stub to the Table
	 * @param serverHostName name of the platform where is located the table Server
	 * @param serverPortNum port number for listening to service requests
	 */
	public TableStub(String serverHostName, int serverPortNum) {
		this.serverHostName = serverHostName;
		this.serverPortNum  = serverPortNum;
	}
	
        public void setFirstToArrive(int firstToArrive) 
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

        outMessage = new Message (MessageType.SFTAREQ, firstToArrive);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SFTADONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        }
	
	public void setLastToArrive(int lastToArrive)
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
		
		outMessage = new Message (MessageType.SLTAREQ, lastToArrive);
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.SLTADONE)
		{
			GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
			GenericIO.writelnString (inMessage.toString ());
			System.exit (1);
		}
		//Close communication channel
		com.close ();
        }
        
        
	public void saluteClient(int studentIdBeingAnswered) 
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
        outMessage = new Message (MessageType.SCREQ, studentIdBeingAnswered, ((Waiter) Thread.currentThread()).getWaiterState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SCDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentBeingAnswered() != -1)
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
	
	public  void returnBar() 
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

        outMessage = new Message (MessageType.RBREQ, ((Waiter) Thread.currentThread()).getWaiterState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.RBDONE)
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
	
	public  void getThePad() 
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

        outMessage = new Message (MessageType.GBREQ, ((Waiter) Thread.currentThread()).getWaiterState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.GBDONE)
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
	
	public  boolean haveAllClientsBeenServed() 
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

        outMessage = new Message (MessageType.HACBSREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.HACBSDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }

        //Close communication channel
        com.close ();
        return inMessage.getAllClientsBeenServed();
        }
	
	public  void deliverPortion() 
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

        outMessage = new Message (MessageType.DPREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.DPDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        }
	
	public  void presentBill() 
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
	
	
	
	
	
	public int getFirstToArrive() 
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

        outMessage = new Message (MessageType.GFTAREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.GFTAREQ)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        return inMessage.getFirstToArrive();
        }
	
	public int getLastToEat() 
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

        outMessage = new Message (MessageType.GLTEREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.GLTEDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        return inMessage.getLastToEat();
        }
	
	public  void seatAtTable() 
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

        outMessage = new Message (MessageType.SATREQ, ((StudentCloning) Thread.currentThread()).getStudentId(),((StudentCloning) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SATDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((StudentCloning) Thread.currentThread()).getStudentId())
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
        ((StudentCloning) Thread.currentThread ()).setStudentState (inMessage.getStudentState());

        //Close communication channel
        com.close ();
        }
    
	
	public  void readMenu() 
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

        outMessage = new Message (MessageType.RMREQ, ((Student) Thread.currentThread()).getStudentId(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.RMDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((Student) Thread.currentThread()).getStudentId())
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
	
	public  void prepareOrder() 
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
        outMessage = new Message (MessageType.POREQ,((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.PODONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
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
	
	public  boolean everybodyHasChosen() 
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

        outMessage = new Message (MessageType.EHCREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.EHCDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        return inMessage.getEverybodyHasChosen();
        }
	
	public  void addUpOnesChoices()
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

        outMessage = new Message (MessageType.AUOCREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.AUOCDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        }
	
	public  void describeOrder() 
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

        outMessage = new Message (MessageType.DOREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.DODONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        }
	
	public  void joinTalk() 
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

        outMessage = new Message (MessageType.JTREQ,((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.JTDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
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
	
	public  void informCompanion() 
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

        outMessage = new Message (MessageType.ICREQ, ((Student) Thread.currentThread()).getStudentId(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.ICDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((Student) Thread.currentThread()).getStudentId())
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
	
	public void startEating() 
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

        outMessage = new Message (MessageType.SEREQ, ((Student) Thread.currentThread()).getStudentId(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SEDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((Student) Thread.currentThread()).getStudentId())
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
	
	public  void endEating() 
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

        outMessage = new Message (MessageType.EEREQ, ((Student) Thread.currentThread()).getStudentId(),((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.EEDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((Student) Thread.currentThread()).getStudentId())
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
	
	public  boolean hasEverybodyFinishedEating() 
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

        outMessage = new Message (MessageType.HEFEREQ, ((Student) Thread.currentThread()).getStudentId());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.HEFEDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((Student) Thread.currentThread()).getStudentId())
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid student id!");
                GenericIO.writelnString (inMessage.toString ());
        System.exit (1);
        }
        //Close communication channel
        com.close ();
        return inMessage.getHasEverybodyFinishedEating();
        }
	
	public  void honourBill() 
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

        outMessage = new Message (MessageType.HBREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.HBDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        }
	
	public  boolean haveAllCoursesBeenEaten() 
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

        outMessage = new Message (MessageType.HACBEREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.HACBEDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
        return inMessage.getAllCoursesEaten();
        }
	
	public  boolean shouldHaveArrivedEarlier() 
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

        outMessage = new Message (MessageType.SHAEREQ, ((Student) Thread.currentThread()).getStudentId(), ((Student) Thread.currentThread()).getStudentState());
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SHAEDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        if(inMessage.getStudentId() != ((Student) Thread.currentThread()).getStudentId())
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
        return inMessage.getArrivedEarlier();
        }
	
	/**
	 *   Operation server shutdown.
	 *
	 *   New operation.
	 */
	public void shutdown()
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
		
		outMessage = new Message (MessageType.TSHUTREQ);
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.TSHUTDONE)
		{
			GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
			GenericIO.writelnString (inMessage.toString ());
			System.exit (1);
		}
		//Close communication channel
		com.close ();			
	}
}