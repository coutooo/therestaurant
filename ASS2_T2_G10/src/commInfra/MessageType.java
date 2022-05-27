package commInfra;

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
   *  saluteClient (REQUEST)
   */

   public static final int SCREQ = 41;
   
   /**
   *  saluteClient (REPLY)
   */

   public static final int SCDONE = 42;
   
   /**
   *  returnBar (REQUEST)
   */

   public static final int RBREQ = 43;
   
   /**
   *  returnBar (REPLY)
   */

   public static final int RBDONE = 44;
   
   /**
   *  getThePad (REQUEST)
   */

   public static final int GBREQ = 45;
   
   /**
   *  getThePad (REPLY)
   */

   public static final int GBDONE = 46;
   
   /**
   *  haveAllClientsBeenServed (REQUEST)
   */

   public static final int HACBSREQ = 47;
   
   /**
   *  haveAllClientsBeenServed (REPLY)
   */

   public static final int HACBSDONE = 48;
   
   /**
   *  deliverPortion (REQUEST)
   */

   public static final int DPREQ = 49;
   
   /**
   *  deliverPortion (REPLY)
   */

   public static final int DPDONE = 50;
   
   /**
   *  presentBill (REQUEST)
   */

   public static final int PREBREQ = 51;
   
   /**
   *  presentBill (REPLY)
   */

   public static final int PREBDONE = 52;
   
   /**
   *  getFirstToArrive (REQUEST)
   */

   public static final int GFTAREQ = 53;
   
   /**
   *  getFirstToArrive (REPLY)
   */

   public static final int GFTADONE = 54;
   
   /**
   *  getLastToEat (REQUEST)
   */

   public static final int GLTEREQ = 55;
   
   /**
   *  getLastToEat (REPLY)
   */

   public static final int GLTEDONE = 56;
   
   /**
   *  seatAtTable (REQUEST)
   */

   public static final int SATREQ = 57;
   
   /**
   *  seatAtTable (REPLY)
   */

   public static final int SATDONE = 58;
   
   /**
   *  readMenu (REQUEST)
   */

   public static final int RMREQ = 59;
   
   /**
   *  readMenu (REPLY)
   */

   public static final int RMDONE = 60;
   
   /**
   *  prepareOrder (REQUEST)
   */

   public static final int POREQ = 61;
   
   /**
   *  prepareOrder (REPLY)
   */

   public static final int PODONE = 62;
   
   /**
   *  everybodyHasChosen (REQUEST)
   */

   public static final int EHCREQ = 63;
   
   /**
   *  everybodyHasChosen (REPLY)
   */

   public static final int EHCDONE = 64;
   
   /**
   *  addUpOnesChoices (REQUEST)
   */

   public static final int AUOCREQ = 65;
   
   /**
   *  addUpOnesChoices (REPLY)
   */

   public static final int AUOCDONE = 66;
   
   /**
   *  describeOrder (REQUEST)
   */

   public static final int DOREQ = 67;
   
   /**
   *  describeOrder (REPLY)
   */

   public static final int DODONE = 68;

   /**
   *  joinTalk (REQUEST)
   */

   public static final int JTREQ = 69;
   
   /**
   *  joinTalk (REPLY)
   */

   public static final int JTDONE = 70;

   /**
   *  informCompanion (REQUEST)
   */

   public static final int ICREQ = 71;
   
   /**
   *  informCompanion (REPLY)
   */

   public static final int ICDONE = 72;

   /**
   *  startEating (REQUEST)
   */

   public static final int SEREQ = 73;
   
   /**
   *  startEating (REPLY)
   */

   public static final int SEDONE = 74;
   
   /**
   *  endEating (REQUEST)
   */

   public static final int EEREQ = 75;
   
   /**
   *  endEating (REPLY)
   */

   public static final int EEDONE = 76;
   
   /**
   *  hasEverybodyFinishedEating (REQUEST)
   */

   public static final int HEFEREQ = 77;
   
   /**
   *  hasEverybodyFinishedEating (REPLY)
   */

   public static final int HEFEDONE = 78;
   
   /**
   *  honourBill (REQUEST)
   */

   public static final int HBREQ = 79;
   
   /**
   *  honourBill (REPLY)
   */

   public static final int HBDONE = 80;
   
   /**
   *  haveAllCoursesBeenEaten (REQUEST)
   */

   public static final int HACBEREQ = 81;
   
   /**
   *  haveAllCoursesBeenEaten (REPLY)
   */

   public static final int HACBEDONE = 82;
   
   /**
   *  shouldHaveArrivedEarlier (REQUEST)
   */

   public static final int SHAEREQ = 83;
   
   /**
   *  shouldHaveArrivedEarlier (REPLY)
   */

   public static final int SHAEDONE = 84;
   
   /**
   *  getStudentBeingAnswered (REQUEST)
   */

   public static final int GSBAREQ = 85;
   
   /**
   *  getStudentBeingAnswered (REPLY)
   */

   public static final int GSBADONE = 86;
  
  /**
   *  Server shutdown (service request).
   */

   public static final int SHUT = 87;

  /**
   *  Server was shutdown (reply).
   */

   public static final int SHUTDONE = 88;

  /**
   *  setStudentState (REQUEST).
   */

   public static final int STSST = 89;

  /**
   *  setWaiterState (REQUEST).
   */

   public static final int STWST = 90;

  /**
   *  setChefState (REQUEST).
   */

   public static final int STCST = 91;

  /**
   *  Setting acknowledged (reply).
   */

   public static final int SACK = 92;
   
   /**
   *  updateSeatsAtTable (REQUEST).
   */

   public static final int USSEATREQ = 93;
   
   /**
   *  updateSeatsAtTable (REPLY).
   */

   public static final int USSEATDONE = 94;
}
