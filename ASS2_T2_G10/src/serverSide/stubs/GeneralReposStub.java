/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.stubs;

import commInfra.ClientCom;
import commInfra.Message;
import serverSide.entities.*;

/**
 *  Stub to the general repository.
 *
 *    It instantiates a remote reference to the general repository.
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class GeneralReposStub {
    
    /**
    *  Name of the computational system where the server is located.
    */
    private String serverHostName;

    /**
    *  Number of the listening port at the computational system where the server is located.
    */
    private int serverPortNumb;

    /**
    *  Instantiation of a remote reference
    *
    *    @param hostName name of the computational system where the server is located
    *    @param port number of the listening port at the computational system where the server is located
    */
    public GeneralReposStub (String hostName, int port)
    {
       serverHostName = hostName;
       serverPortNumb = port;
    }
    
    /**
    *   Set Chef state.
    *
    *     @param state Chef state
    */
    public void setChefState(ChefState state)
    {
    	ClientCom com = new ClientCom (serverHostName, serverPortNumb);
    	Object[] params = new Object[1];
    	Object[] state_fields = new Object[0];
    	params[0] = state;
    	
        //Message m_toServer = new Message(16, params, 1, state_fields, 0, null);                                                          
        Message m_fromServer;            
        
        while (!com.open ())                                                      
        { try
          { Thread.currentThread ().sleep ((long) (10));
          }
          catch (InterruptedException e) {}
        }
        
        com.writeObject (m_toServer);
        
        m_fromServer = (Message) com.readObject();                 
        
        com.close ();                                                             
    }
    
    /**
    *   Set Waiter state.
    *
    *     @param state Waiter state
    */
    public void setWaiterState(WaiterState state)
    {
    	ClientCom com = new ClientCom (serverHostName, serverPortNumb);
    	Object[] params = new Object[1];
    	Object[] state_fields = new Object[0];
    	params[0] = state;
    	
        // Message m_toServer = new Message(18, params, 1, state_fields, 0, null);                                                          
        Message m_fromServer;            
        
        while (!com.open ())                                                      
        { try
          { Thread.currentThread ().sleep ((long) (10));
          }
          catch (InterruptedException e) {}
        }
        
        com.writeObject (m_toServer);
        
        m_fromServer = (Message) com.readObject();                 
        
        com.close ();                                                                  
    }
    
    /**
    *   Set Student state.
    *
    *     @param state Student state
    */
    public void setStudentState(StudentState state)
    {
    	ClientCom com = new ClientCom (serverHostName, serverPortNumb);
    	Object[] params = new Object[2];
    	Object[] state_fields = new Object[0];
    	params[0] = studentId;
    	params[1] = state;
    	
        // Message m_toServer = new Message(17, params, 2, state_fields, 0, null);                                                        
        Message m_fromServer;            
        
        while (!com.open ())                                                      
        { try
          { Thread.currentThread ().sleep ((long) (10));
          }
          catch (InterruptedException e) {}
        }
        
        com.writeObject (m_toServer);
        
        m_fromServer = (Message) com.readObject();                 
        
        com.close ();                                                                                            
    }
    
    /**
    *   Send shutdown message to the GeneraRepos server
    *
    */
    public void shutdown() {
        ClientCom com = new ClientCom (serverHostName, serverPortNumb);
        Object[] params = new Object[0];
        Object[] state_fields = new Object[0];

	     //Message m_toServer = new Message(24, params, 0, state_fields, 0, null);
	     Message m_fromServer;
	
	     while (!com.open ())
	     { try
	       { Thread.currentThread ().sleep ((long) (10));
	       }
	       catch (InterruptedException e) {}
	     }
	
	     com.writeObject (m_toServer);
	
	     com.close ();
    }

}
