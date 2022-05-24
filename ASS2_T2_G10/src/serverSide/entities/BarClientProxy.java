/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverSide.entities;

import clientSide.entities.ChefCloning;
import clientSide.entities.StudentCloning;
import clientSide.entities.WaiterCloning;
import commInfra.ServerCom;
import serverSide.sharedRegions.BarInterface;

/**
 *  Service provider agent for access to the Bar.
 *
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class BarClientProxy extends Thread implements StudentCloning, WaiterCloning, ChefCloning  {
    
    /**
   *  Number of instantiayed threads.
   */

   private static int nProxy = 0;

  /**
   *  Communication channel.
   */

   private ServerCom sconi;

  /**
   *  Interface to the Bar.
   */

   private BarInterface barInter;

  /**
   *  Student identification.
   */

   private int studentId;

  /**
   *  Student state.
   */

   private int studentState;

  /**
   *  Chef state.
   */

   private int chefState;
   
   /**
   *  Waiter state.
   */

   private int WaiterState;
    
}

/**
   *  Instantiation of a client proxy.
   *
   *     @param sconi communication channel
   *     @param barInter interface to the bar
   */

   public BarClientProxy (ServerCom sconi, BarInterface barInter)
   {
      super ("BarProxy_" + BarClientProxy.getProxyId ());
      this.sconi = sconi;
      this.barInter = barInter;
   }
}
