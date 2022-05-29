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
 * 
 *  @author Rafael Dias
 *  @author Manuel Couto
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
  /**
   *   Set chef state.
   *
   *     @param state chef state
   */

   public void setChefState (int state)
   {
        ClientCom com;					//Client communication
        Message outMessage, inMessage; 	//outGoing and inGoing messages

        com = new ClientCom (serverHostName, serverPortNumb);
        //Wait for a connection to be established
        while(!com.open())
        {	
            try 
            { Thread.currentThread ().sleep ((long) (10));
            }
            catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.STCSTREQ, state);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.STCSTDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
   }
   
   /**
   *   Set waiter state.
   *
   *     @param state waiter state
   */
   
   public void setWaiterState (int state)
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

        outMessage = new Message (MessageType.STWSTREQ, state);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.STWSTDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
   }
   
    /**
    *   update who is seated at the table.
    *
    *     @param i number of students at the restaurant
    *     @param studentId student id
    */
   public void updateSeatsAtTable(int i, int studentId) {
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

        outMessage = new Message (MessageType.USATREQ, studentId, i);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.USATDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
    }
   
    /**
     * Update the leaving of a student in the seats of the table
     * @param id student id to leave table
     */
    public void updateSeatsAtLeaving(int id)
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

        outMessage = new Message (MessageType.USALREQ, id);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.USALDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();		
    }

    public void updateStudentState(int studentID, int studentState) {
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

        outMessage = new Message (MessageType.USSTREQ1, studentID, studentState);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.USSTDONE1)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();	
    }
    
    /**
     * Write in the logging file the updated student state
     * @param studentid student id
     * @param studentstate student state to be set
     * @param hold specifies if prints line of report status
     */
    public void updateStudentState(int studentid, int studentstate, boolean hold)
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

        outMessage = new Message (MessageType.USSTREQ2, studentid, studentstate, hold);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.USSTDONE2)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();		
    }
    /**
     * Set variable nCourses and report status in the logging file
     * @param value nCourses value to set
     */
    public void setnCourses(int value)
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

        outMessage = new Message (MessageType.SETNCREQ, value);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SETNCDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();		
    }
    /**
     * Set variable nPortions and report status in the logging file
     * @param value nCourses value to set
     */
    public void setnPortions(int value)
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

        outMessage = new Message (MessageType.SETNPREQ, value);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.SETNPDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
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

        com = new ClientCom (serverHostName, serverPortNumb);
        //Wait for a connection to be established
        while(!com.open())
        {	try 
                { Thread.currentThread ().sleep ((long) (10));
                }
                catch (InterruptedException e) {}
        }

        outMessage = new Message (MessageType.GRSHUTREQ);
        com.writeObject (outMessage); 			//Write outGoing message in the communication channel
        inMessage = (Message) com.readObject(); //Read inGoing message

        //Validate inGoing message type and arguments
        if(inMessage.getMsgType() != MessageType.GRSHUTDONE)
        {
                GenericIO.writelnString ("Thread " + Thread.currentThread ().getName () + ": Invalid message type!");
                GenericIO.writelnString (inMessage.toString ());
                System.exit (1);
        }
        //Close communication channel
        com.close ();
    }
}
