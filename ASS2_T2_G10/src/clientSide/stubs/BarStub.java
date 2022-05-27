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
        outMessage = new Message(MessageType.ENTREQ, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.ENTDONE)) {
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

    /**
     * Operation alert the waiter
     */
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
        outMessage = new Message(MessageType.CWREQ, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.CWDONE)) {
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

        com.close();   // rever porque só ha 1 waiter
    }

    /**
     * Operation signal the waiter
     */
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
        outMessage = new Message(MessageType.SWREQ, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.SWDONE)) {
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
    /**
     * Operation exit the restaurant
     */
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
        outMessage = new Message(MessageType.EXITREQ, ((Student) Thread.currentThread()).getStudentID(), ((Student) Thread.currentThread()).getStudentState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.EXITDONE)) {
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

    /**
    * Operation look Around
    */
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
        outMessage = new Message(MessageType.LAREQ, ((Waiter) Thread.currentThread()).getWaiterState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.LADONE)) {
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
    
    /**
     * Operation say Goodbye
     */
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
        outMessage = new Message(MessageType.SGREQ, ((Waiter) Thread.currentThread()).getWaiterState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.SGDONE)) {
            GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
        com.close();

        return (inMessage.getMsgType() == MessageType.SGDONE);
    }
    /**
    * Operation prepare the Bill
    */
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
        outMessage = new Message(MessageType.PBREQ, ((Waiter) Thread.currentThread()).getWaiterState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.PBDONE)) {
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
    /**
     * Operation alert the waiter
     */
    public boolean alertWaiter() {
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
        outMessage = new Message(MessageType.ALREQ, ((Chef) Thread.currentThread()).getChefState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.ALDONE)) {
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

        return (inMessage.getMsgType() == MessageType.ALDONE);
    }
    
    /**
     * @return Id of the student whose request is being answered
     */
    public int getStudentBeingAnswered() {
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
        outMessage = new Message(MessageType.GSBAREQ, ((Waiter) Thread.currentThread()).getWaiterState());

        com.writeObject(outMessage);
        inMessage = (Message) com.readObject();

        //TODO Message Types - enter
        if((inMessage.getMsgType() != MessageType.GSBADONE)) {
            GenericIO.writelnString("Thread "+Thread.currentThread().getName()+": Invalid Message Type!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }

        ((Waiter) Thread.currentThread()).setWaiterState(inMessage.getWaiterState());
        com.close();

        return inMessage.getStudentID();
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
