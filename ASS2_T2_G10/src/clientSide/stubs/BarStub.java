package clientSide.stubs;

import clientSide.entities.*;
import commInfra.ClientCom;
import commInfra.Message;
import commInfra.MessageType;
import genclass.GenericIO;

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
	
	
	public void enter() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQENT, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.ENTDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID()) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Student ID!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if((inMessage.getStudentState() != StudentState.GOING_TO_THE_RESTAURANT)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Student State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Student) Thread.currentThread()).setStudentState(inMessage.getStudentState());
	    com.close();
		
		//TODO table.seatAtTable
	}
	
	public void callWaiter() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQCW, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.CWDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID()) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Student ID!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Student) Thread.currentThread()).setStudentState(inMessage.getStudentState());
	    com.close();
	}
	
	public void signalWaiter() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQSW, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.SWDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID()) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Student ID!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Student) Thread.currentThread()).setStudentState(inMessage.getStudentState());
	    com.close();
	}
	
	public void exit() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQEXIT, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.EXITDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if(inMessage.getStudentID() != ((Student) Thread.currentThread()).getStudentID()) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Student ID!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if((inMessage.getStudentState() != StudentState.GOING_HOME)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Student State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Student) Thread.currentThread()).setStudentState(inMessage.getStudentState());
	    com.close();
	}
	
	
	
	
	
	
	
	public char lookAround() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQLA, ((Waiter) Thread.currentThread()).getWaiterState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.LADONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    //if((inMessage.getWaiterState() < WaiterStates.APPRAISING_SITUATION && inMessage.getWaiterState() > WaiterStates.RECEIVING_PAYMENT)) {
	    if(inMessage.getWaiterState() != WaiterState.APPRAISING_SITUATION) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Waiter State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
	    com.close();
	    
	    return inMessage.getRequest();
	}
	
	public boolean sayGoodbye() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQSG, ((Waiter) Thread.currentThread()).getWaiterState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.SGDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
	    com.close();
	    
	    return (inMessage.getMsgType() == MessageType.SGDONE);
	}
	
	public void preprareBill() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQPB, ((Waiter) Thread.currentThread()).getWaiterState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.PBDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if((inMessage.getWaiterState() != WaiterState.PROCESSING_THE_BILL)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Waiter State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
	    com.close();
	}
	
	
	
	
	
	
	
	
	public void alertWaiter() {
		ClientCom com;                                                 // communication channel
	    Message outMessage,                                            // outgoing message
	            inMessage;                                             // incoming message

	    com = new ClientCom (serverHostName, serverPortNum);
	    while (!com.open()) {
	    	try {
	    		Thread.currentThread().sleep((long)(10));
	    	} catch(InterruptedException e) {}
	    }
	    
	    //MESSAGES
	    outMessage = new Message(MessageType.REQAL, ((Chef) Thread.currentThread()).getChefState());
	    
	    com.writeObject(outMessage);
	    inMessage = (Message) com.readObject();
	    
	    //TODO Message Types - enter
	    if((inMessage.getMsgType() != MessageType.ALDONE) && (inMessage.getMsgType() != MessageType.FALTA_DAR_NOME_A_ESTA_MERDA)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    if((inMessage.getChefState() != ChefState.DELIVERING_THE_PORTIONS)) {
	    	GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Chef State!");
	    	GenericIO.writelnString(inMessage.toString());
	    	System.exit(1);
	    }
	    
	    ((Chef) Thread.currentThread()).setChefState(inMessage.getChefState());
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
