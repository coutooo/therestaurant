package clientSide.stubs;

import clientSide.entities.*;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;

/**
*  Stub to the Table.
 *
 *    It instantiates a remote reference to the barber shop.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 *  @author Rafael Dias
 *  @author Manuel Couto
 */
public class TableStub {
	/**
	 * Name of the platform where is located the table server
	 */
	private String serverHostName;
	/**
	 * Port number for listening to service requests
	 */
	private int serverPortNumb;
		
	/**
	 * Instantiation of a stub to the Table. 
	 * 
	 * @param serverHostName name of the platform where is located the kitchen server
	 * @param serverPortNumb port number for listening to service requests
	 */
	public TableStub(String serverHostName, int serverPortNumb)
	{
		this.serverHostName = serverHostName;
		this.serverPortNumb = serverPortNumb;
	}

	
    /**
     * Obtain id of the first student to arrive
     * @return id of the first student to arrive at the restaurant
     */
    public int getFirstToArrive() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
		if(inMessage.getMsgType() != MessageType.GFTADONE)
		{
			GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
			GenericIO.writelnString (inMessage.toString ());
			System.exit (1);
		}
		//Close communication channel
		com.close ();
		return inMessage.getFirstToArrive();
    }  
    
    /**
     * Obtain id of the last student to arrive
     * @return id of the last student to finish eating a meal
     */
    public int getLastToEat() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    /**
     * Set id of the first student to arrive
     * @param firstToArrive id of the first student to arrive
     */
    public void setFirstToArrive(int firstToArrive) 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    /**
     * Set id of the last student to arrive
     * @param lastToArrive if of the last student to arrive to the restaurant
     */
    public void setLastToArrive(int lastToArrive)
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    

    /**
     * Operation salute the client
     */
    public void saluteClient(int studentIdBeingAnswered) 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    /**
     * Operation return to the bar
     */
    public  void returnBar() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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

    /**
     * Operation get the pad
     */
    public  void getThePad() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
		//Wait for a connection to be established
		while(!com.open())
		{	try 
		  	{ Thread.currentThread ().sleep ((long) (10));
		  	}
			catch (InterruptedException e) {}
		}
		
		outMessage = new Message (MessageType.GPREQ, ((Waiter) Thread.currentThread()).getWaiterState());
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.GPDONE)
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
     * Operation have all clients been served
     * @return true if all clients have been served, false otherwise
     */
    public  boolean haveAllClientsBeenServed() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    /**
     * Operation deliver portion
     */
    public  void deliverPortion() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    
    /**
     * Operation present the bill
     */
    public  void presentBill() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
		//Wait for a connection to be established
		while(!com.open())
		{	try 
		  	{ Thread.currentThread ().sleep ((long) (10));
		  	}
			catch (InterruptedException e) {}
		}
		
		outMessage = new Message (MessageType.PREBREQ, ((Waiter) Thread.currentThread()).getWaiterState());
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.PREBDONE)
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
     * Operation siting at the table
     */
    public  void seatAtTable() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		com = new ClientCom (serverHostName, serverPortNumb);
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

    /**
     * Operation read the menu
     */
    public  void readMenu() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		com = new ClientCom (serverHostName, serverPortNumb);
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
        
    
    /**
     * Operation prepare the order
     */
    public  void prepareOrder() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    /**
     * Operation everybody has chosen
     * @return true if everybody choose their course choice, false otherwise
     */
    public  boolean everybodyHasChosen() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    /**
     * Operation add up ones choices
     */
    public  void addUpOnesChoices()
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    /**
     * Operation describe the order
     */
    public  void describeOrder() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    /**
     * Operation join the talk
     */
    public  void joinTalk() 
    { 
       	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    /**
     * Operation inform companion
     */
    public  void informCompanion() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    
    /**
     * Operation start eating
     */    
    public void startEating() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages

		com = new ClientCom (serverHostName, serverPortNumb);
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

    
    /**
     * Operation end eating
     */
    public  void endEating() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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

    /**
     * Operation has everybody finished eating
     */
    public  boolean hasEverybodyFinishedEating() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
    
    
    
    /**
     * Operation honour the bill
     */
    public  void honourBill() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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

    /**
     * 	Operation have all courses been eaten
     * 	@return true if all courses have been eaten, false otherwise
     */
    public  boolean haveAllCoursesBeenEaten() 
    {
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
  
    
    /**
     * Operation should have arrived earlier
     * @return True if current student was the last to arrive, false otherwise
     */
    public  boolean shouldHaveArrivedEarlier() 
    { 
    	ClientCom com;					//Client communication
		Message outMessage, inMessage; 	//outGoing and inGoing messages
		
		com = new ClientCom (serverHostName, serverPortNumb);
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
     * Operation server shutdown
     */
    public void shutdown()
    {
            ClientCom com;					//Client communication
            Message outMessage, inMessage; 	//outGoing and inGoing messages

            com = new ClientCom (serverHostName, serverPortNumb);
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
