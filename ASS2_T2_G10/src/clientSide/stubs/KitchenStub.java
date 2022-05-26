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
	
	public void watchTheNews() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.WTNREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.WTNDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getChefState() != ChefState.WAITING_FOR_AN_ORDER) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	    
	}
	
	public void startPreparation() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.STPREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.STPDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getChefState() != ChefState.PREPARING_THE_COURSE) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	}
	
	public void proceedToPreparation() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.PTPREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.PTPDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getChefState() != ChefState.DISHING_THE_PORTIONS) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	}
	
	public void haveNextPortionReady() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.HNPRREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.HNPRDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if((inMessage.getChefState() != ChefState.DISHING_THE_PORTIONS) || (inMessage.getChefState() != ChefState.DELIVERING_THE_PORTIONS)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	}
	
	public void continuePreparation() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.CPREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.CPDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getChefState() != ChefState.PREPARING_THE_COURSE) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	}
	
	public boolean haveAllPortionsBeenDelivered() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.HAPBDREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.HAPBDDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	    
	    return (inMessage.getMsgType() == MessageType.HAPBDDONE);
	}
	
	public boolean hasOrderBeenCompleted() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.HOBCREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.HOBCDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	    
	    return (inMessage.getMsgType() == MessageType.HOBCDONE);
	}
	
	public void cleanUp() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.CUREQ, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.CUDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getChefState() != ChefState.CLOSING_SERVICE) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
	    com.close();
	}
	
	
	
	
	
	public void returnToBar() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.RTBREQ, ((Waiter) Thread.currentThread()).getWaiterState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.RTBDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getWaiterState() != WaiterState.APPRAISING_SITUATION) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Waiter State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
	    com.close();
	}
	
	public void handNoteToChef() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.HNTCREQ, ((Waiter) Thread.currentThread()).getWaiterState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.HNTCDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getWaiterState() != WaiterState.PLACING_THE_ORDER) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Waiter State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
	    com.close();
	}
	
	public void collectPortion() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom(serverHostName, serverPortNum);
	    while(!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.CPORREQ, ((Waiter) Thread.currentThread()).getWaiterState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.CPORDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getWaiterState() != WaiterState.WAITING_FOR_PORTION) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Waiter State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
	    com.close();
	}
	
	/**
	 *   Operation server shutdown.
	 *
	 *   New operation.
	 */
	public void shutdown(){
		ClientCom com;                                                 // communication channel
		Message outMessage,                                            // outgoing message
				inMessage;                                             // incoming message
	
		com = new ClientCom(serverHostName, serverPortNum);
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
}
