package clientSide.stubs;

import clientSide.entities.Chef;
import clientSide.entities.ChefState;
import clientSide.entities.Waiter;
import clientSide.entities.WaiterState;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;

public class KitchenStub {
	/**
	 * Name of the platform where is located the kitchen Server
	 */
	private String serverHostName;
	
	/**
	 * Port number for listening to service requests
	 */
	private int serverPortNum;
	
	/**
	 * Instantiation of a stub to the kitchen
	 * @param serverHostName name of the platform where is located the kitchen Server
	 * @param serverPortNum port number for listening to service requests
	 */
	public KitchenStub(String serverHostName, int serverPortNum) {
		this.serverHostName = serverHostName;
		this.serverPortNum  = serverPortNum;
	}
	
	public void watchTheNews()	
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

            outMessage = new Message (MessageType.WTNREQ, ((Chef) Thread.currentThread()).getChefState());
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            if(inMessage.getMsgType() != MessageType.WTNDONE)
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
            com.close ();
	}
	
	public void startPreparation() 
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

            outMessage = new Message (MessageType.STPREQ, ((Chef) Thread.currentThread()).getChefState());
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            //Validate inGoing message type and arguments
            if(inMessage.getMsgType() != MessageType.STPDONE)
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
	
	public void proceedToPreparation() 
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

            outMessage = new Message (MessageType.PTPREQ, ((Chef) Thread.currentThread()).getChefState());
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            //Validate inGoing message type and arguments
            if(inMessage.getMsgType() != MessageType.PTPDONE)
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
	
	public void haveNextPortionReady() 
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
		
		outMessage = new Message (MessageType.HNPRREQ, ((Chef) Thread.currentThread()).getChefState());
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.HNPRDONE)
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
	
	public void continuePreparation() 
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
		
		outMessage = new Message (MessageType.CPREQ, ((Chef) Thread.currentThread()).getChefState());
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.CPDONE)
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
	
	public boolean haveAllPortionsBeenDelivered() 
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
		
		outMessage = new Message (MessageType.HAPBDREQ);
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.HAPBDDONE)
		{
			GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
			GenericIO.writelnString (inMessage.toString ());
			System.exit (1);
		}
		//Close communication channel
		com.close ();
		return inMessage.getAllPortionsBeenDelivered();
	}
	
	public boolean hasOrderBeenCompleted()
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
		
		outMessage = new Message (MessageType.HOBCREQ);
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.HOBCDONE)
		{
			GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
			GenericIO.writelnString (inMessage.toString ());
			System.exit (1);
		}
		
		//Close communication channel
		com.close ();
		return inMessage.getHasOrderBeenCompleted();
	}
	
	public void cleanUp() 
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
		
		outMessage = new Message (MessageType.CUREQ, ((Chef) Thread.currentThread()).getChefState());
		com.writeObject (outMessage); 			//Write outGoing message in the communication channel
		inMessage = (Message) com.readObject(); //Read inGoing message
		
		//Validate inGoing message type and arguments
		if(inMessage.getMsgType() != MessageType.CUDONE)
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
	
	
	
	
	
	public void returnToBar() 
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

            outMessage = new Message (MessageType.RTBREQ, ((Waiter) Thread.currentThread()).getWaiterState());
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            //Validate inGoing message type and arguments
            if(inMessage.getMsgType() != MessageType.RTBDONE)
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
	
	
	public void handNoteToChef() 
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

            outMessage = new Message (MessageType.HNTCREQ, ((Waiter) Thread.currentThread()).getWaiterState());
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            //Validate inGoing message type and arguments
            if(inMessage.getMsgType() != MessageType.HNTCDONE)
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
	
	public void collectPortion() 
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

            outMessage = new Message (MessageType.CPREQ, ((Waiter) Thread.currentThread()).getWaiterState());
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            //Validate inGoing message type and arguments
            if(inMessage.getMsgType() != MessageType.CPDONE)
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

            outMessage = new Message (MessageType.KSHUTREQ);
            com.writeObject (outMessage); 			//Write outGoing message in the communication channel
            inMessage = (Message) com.readObject(); //Read inGoing message

            //Validate inGoing message type and arguments
            if(inMessage.getMsgType() != MessageType.KSHUTDONE)
            {
                    GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                    GenericIO.writelnString (inMessage.toString ());
                    System.exit (1);
            }
            //Close communication channel
            com.close ();	
	}
}
