package commInfra;

import javax.print.DocFlavor;

/**
 *   Type of the exchanged messages.
 *the
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType
{
  /**
   *  Initialization of the logging file name and the number of iterations (service request).
   */

   public static final int SETNFIC = 1;

  /**
   *  Logging file was initialized (reply).
   */

   public static final int NFICDONE = 2;

  /**
   *  Alert waiter (service request).
   */

   public static final int ALREQ = 3;

  /**
   *  Waiter was alerted (reply).
   */

   public static final int ALDONE = 4;

  /**
   *  enter(request).
   */

   public static final int ENTREQ = 5;

  /**
   *  enter done (reply).
   */

   public static final int ENTDONE = 6;

  /**
   *  Call waiter (request).
   */

   public static final int CWREQ = 7;

  /**
   *  Call waiter done (reply).
   */

   public static final int CWDONE = 8;

  /**
   *  Signal waiter (request).
   */

   public static final int SWREQ = 9;

  /**
   *  Signal waiter done (reply).
   */

   public static final int SWDONE = 10;

  /**
   *  EXIT REQUEST (REQUEST).
   */

   public static final int EXITREQ = 11;

  /**
   *  EXIT DONE (REPLY)
   */

   public static final int EXITDONE = 12;
   
   /**
   *  LOOK AROUND  (REQUEST)
   */

   public static final int LAREQ = 13;
   
   /**
   *  LOOK AROUND (REPLY)
   */

   public static final int LADONE = 14;
   
   /**
   *  SAY GOODBYE (REQUEST)
   */

   public static final int SGREQ = 15;
   
   /**
   *  SAY GOODBYE (REPLY)
   */

   public static final int SGDONE = 16;
   
   /**
   *  PREPARE BILL (REQUEST)
   */

   public static final int PBREQ = 17;
   
   /**
   *  PREPARE BILL (REPLY)
   */

   public static final int PBDONE = 18;
   
   /**
   *  WHAT THE NEWS (REQUEST)
   */

   public static final int WTNREQ = 19;
   
   /**
   *  WHAT THE NEWS (REPLY)
   */

   public static final int WTNDONE = 20;

   /**
   *  START PREPARATION (REQUEST)
   */

   public static final int STPREQ = 21;
   
   /**
   *  START PREPARATION (REPLY)
   */

   public static final int STPDONE = 22;
   
   /**
   *  PROCEED PREPARATION (REQUEST)
   */

   public static final int PTPREQ = 23;
   
   /**
   *  PROCEED PREPARATION (REPLY)
   */

   public static final int PTPDONE = 24;
   
   /**
   *  haveNextPortionReady (REQUEST)
   */

   public static final int HNPRREQ = 25;
   
   /**
   *  haveNextPortionReady (REPLY)
   */

   public static final int HNPRDONE = 26;
   
   /**
   *  continuePreparation (REQUEST)
   */

   public static final int CPREQ = 27;
   
   /**
   *  continuePreparation (REPLY)
   */

   public static final int CPDONE = 28;
   
   /**
   *  haveAllPortionsBeenDelivered (REQUEST)
   */

   public static final int HAPBDREQ = 29;
   
   /**
   *  haveAllPortionsBeenDelivered (REPLY)
   */

   public static final int HAPBDDONE = 30;
   
   /**
   *  hasOrderBeenCompleted (REQUEST)
   */

   public static final int HOBCREQ = 31;
   
   /**
   *  hasOrderBeenCompleted (REPLY)
   */

   public static final int HOBCDONE = 32;
   
   /**
   *  cleanUp (REQUEST)
   */

   public static final int CUREQ = 33;
   
   /**
   *  cleanUp (REPLY)
   */

   public static final int CUDONE = 34;
   
   /**
   *  returnToBar (REQUEST)
   */

   public static final int RTBREQ = 35;
   
   /**
   *  returnToBar (REPLY)
   */

   public static final int RTBDONE = 36;   
   /**
   *  handNoteToChef (REQUEST)
   */

   public static final int HNTCREQ = 37;
   
   /**
   *  handNoteToChef (REPLY)
   */

   public static final int HNTCDONE = 38;
   
   /**
   *  collectPortion (REQUEST)
   */

   public static final int CPORREQ = 39;
   
   /**
   *  collectPortion (REPLY)
   */

   public static final int CPORDONE = 40;
   
  /**
   *  Barber goes home (reply).
   */

   public static final int EOPDONE = 41;

  /**
   *  Server shutdown (service request).
   */

   public static final int SHUT = 42;

  /**
   *  Server was shutdown (reply).
   */

   public static final int SHUTDONE = 43;

  /**
   *  Set barber state (service request).
   */

   public static final int STBST = 44;

  /**
   *  Set customer state (service request).
   */

   public static final int STCST = 45;

  /**
   *  Set barber and customer states (service request).
   */

   public static final int STBCST = 46;

  /**
   *  Setting acknowledged (reply).
   */

   public static final int SACK = 47;
}
