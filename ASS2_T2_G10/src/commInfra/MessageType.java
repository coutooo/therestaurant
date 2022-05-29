package commInfra;

/**
 *   Type of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType {
    /**
     * Initialization of the logging file name and the number of iterations (service
     * request).
     */

    public static final int SETNFIC = 1;

    /**
     * Logging file was initialized (reply).
     */

    public static final int NFICDONE = 2;

    ////////////// STUDENT TYPES /////////////// 3 -> 42

    /**
     * enter(request).
     */

    public static final int ENTREQ = 3;

    /**
     * enter done (reply).
     */

    public static final int ENTDONE = 4;

    /**
     * Call waiter (request).
     */

    public static final int CWREQ = 5;

    /**
     * Call waiter done (reply).
     */

    public static final int CWDONE = 6;

    /**
     * Signal waiter (request).
     */

    public static final int SWREQ = 7;

    /**
     * Signal waiter done (reply).
     */

    public static final int SWDONE = 8;

    /**
     * EXIT REQUEST (REQUEST).
     */

    public static final int EXITREQ = 9;

    /**
     * EXIT DONE (REPLY)
     */

    public static final int EXITDONE = 10;

    /**
     * seatAtTable (REQUEST)
     */

    public static final int SATREQ = 11;

    /**
     * seatAtTable (REPLY)
     */

    public static final int SATDONE = 12;

    /**
     * readMenu (REQUEST)
     */

    public static final int RMREQ = 13;

    /**
     * readMenu (REPLY)
     */

    public static final int RMDONE = 14;

    /**
     * getFirstToArrive (REQUEST)
     */

    public static final int GFTAREQ = 15;

    /**
     * getFirstToArrive (REPLY)
     */

    public static final int GFTADONE = 16;

    /**
     * prepareOrder (REQUEST)
     */

    public static final int POREQ = 17;

    /**
     * prepareOrder (REPLY)
     */

    public static final int PODONE = 18;

    /**
     * everybodyHasChosen (REQUEST)
     */

    public static final int EHCREQ = 19;

    /**
     * everybodyHasChosen (REPLY)
     */

    public static final int EHCDONE = 20;

    /**
     * addUpOnesChoices (REQUEST)
     */

    public static final int AUOCREQ = 21;

    /**
     * addUpOnesChoices (REPLY)
     */

    public static final int AUOCDONE = 22;

    /**
     * describeOrder (REQUEST)
     */

    public static final int DOREQ = 23;

    /**
     * describeOrder (REPLY)
     */

    public static final int DODONE = 24;

    /**
     * joinTalk (REQUEST)
     */

    public static final int JTREQ = 25;

    /**
     * joinTalk (REPLY)
     */

    public static final int JTDONE = 26;

    /**
     * informCompanion (REQUEST)
     */

    public static final int ICREQ = 27;

    /**
     * informCompanion (REPLY)
     */

    public static final int ICDONE = 28;

    /**
     * haveAllCoursesBeenEaten (REQUEST)
     */

    public static final int HACBEREQ = 29;

    /**
     * haveAllCoursesBeenEaten (REPLY)
     */

    public static final int HACBEDONE = 30;

    /**
     * startEating (REQUEST)
     */

    public static final int SEREQ = 31;

    /**
     * startEating (REPLY)
     */

    public static final int SEDONE = 32;

    /**
     * endEating (REQUEST)
     */

    public static final int EEREQ = 33;

    /**
     * endEating (REPLY)
     */

    public static final int EEDONE = 34;

    /**
     * hasEverybodyFinishedEating (REQUEST)
     */

    public static final int HEFEREQ = 35;

    /**
     * hasEverybodyFinishedEating (REPLY)
     */

    public static final int HEFEDONE = 36;

    /**
     * honourBill (REQUEST)
     */

    public static final int HBREQ = 37;

    /**
     * honourBill (REPLY)
     */

    public static final int HBDONE = 38;

    /**
     * shouldHaveArrivedEarlier (REQUEST)
     */

    public static final int SHAEREQ = 39;

    /**
     * shouldHaveArrivedEarlier (REPLY)
     */

    public static final int SHAEDONE = 40;

    /**
     * getLastToEat (REQUEST)
     */

    public static final int GLTEREQ = 41;

    /**
     * getLastToEat (REPLY)
     */

    public static final int GLTEDONE = 42;

    //////////// WAITER TYPES ///////////// 43 -> 66

    /**
     * LOOK AROUND (REQUEST)
     */

    public static final int LAREQ = 43;

    /**
     * LOOK AROUND (REPLY)
     */

    public static final int LADONE = 44;

    /**
     * SAY GOODBYE (REQUEST)
     */

    public static final int SGREQ = 45;

    /**
     * SAY GOODBYE (REPLY)
     */

    public static final int SGDONE = 46;

    /**
     * PREPARE BILL (REQUEST)
     */

    public static final int PBREQ = 47;

    /**
     * PREPARE BILL (REPLY)
     */

    public static final int PBDONE = 48;

    /**
     * saluteClient (REQUEST)
     */

    public static final int SCREQ = 49;

    /**
     * saluteClient (REPLY)
     */

    public static final int SCDONE = 50;

    /**
     * getStudentBeingAnswered (REQUEST)
     */

    public static final int GSBAREQ = 51;

    /**
     * getStudentBeingAnswered (REPLY)
     */

    public static final int GSBADONE = 52;

    /**
     * returnToBar (REQUEST)
     */

    public static final int RTBREQ = 53;

    /**
     * returnToBar (REPLY)
     */

    public static final int RTBDONE = 54;
    /**
     * getThePad (REQUEST)
     */

    public static final int GBREQ = 55;

    /**
     * getThePad (REPLY)
     */

    public static final int GBDONE = 56;

    /**
     * handNoteToChef (REQUEST)
     */

    public static final int HNTCREQ = 57;

    /**
     * handNoteToChef (REPLY)
     */

    public static final int HNTCDONE = 58;

    /**
     * haveAllClientsBeenServed (REQUEST)
     */

    public static final int HACBSREQ = 59;

    /**
     * haveAllClientsBeenServed (REPLY)
     */

    public static final int HACBSDONE = 60;

    /**
     * collectPortion (REQUEST)
     */

    public static final int CPORREQ = 61;

    /**
     * collectPortion (REPLY)
     */

    public static final int CPORDONE = 62;

    /**
     * deliverPortion (REQUEST)
     */

    public static final int DPREQ = 63;

    /**
     * deliverPortion (REPLY)
     */

    public static final int DPDONE = 64;

    /**
     * presentBill (REQUEST)
     */

    public static final int PREBREQ = 65;

    /**
     * presentBill (REPLY)
     */

    public static final int PREBDONE = 66;

    //////////// CHEF TYPES ///////////// 67 -> 86

    /**
     * watch the news (request)
     */

    public static final int WTNREQ = 67;

    /**
     * watch the news (reply)
     */

    public static final int WTNDONE = 68;

    /**
     * START PREPARATION (REQUEST)
     */

    public static final int STPREQ = 69;

    /**
     * START PREPARATION (REPLY)
     */

    public static final int STPDONE = 70;

    /**
     * PROCEED PRESENTATION (REQUEST)
     */

    public static final int PTPREQ = 71;

    /**
     * PROCEED PRESENTATION (REPLY)
     */

    public static final int PTPDONE = 72;

    /**
     * haveNextPortionReady (REQUEST)
     */

    public static final int HNPRREQ = 73;

    /**
     * haveNextPortionReady (REPLY)
     */

    public static final int HNPRDONE = 74;

    /**
     * continuePreparation (REQUEST)
     */

    public static final int CPREQ = 75;

    /**
     * continuePreparation (REPLY)
     */

    public static final int CPDONE = 76;

    /**
     * haveAllPortionsBeenDelivered (REQUEST)
     */

    public static final int HAPBDREQ = 77;

    /**
     * haveAllPortionsBeenDelivered (REPLY)
     */

    public static final int HAPBDDONE = 78;

    /**
     * hasOrderBeenCompleted (REQUEST)
     */

    public static final int HOBCREQ = 79;

    /**
     * hasOrderBeenCompleted (REPLY)
     */

    public static final int HOBCDONE = 80;

    /**
     * cleanUp (REQUEST)
     */

    public static final int CUREQ = 81;

    /**
     * cleanUp (REPLY)
     */

    public static final int CUDONE = 82;

    /**
     * Alert waiter (service request).
     */

    public static final int ALREQ = 83;

    /**
     * Waiter was alerted (reply).
     */

    public static final int ALDONE = 84;

    /**
     * returnBar (REQUEST)
     */

    public static final int RBREQ = 85;

    /**
     * returnBar (REPLY)
     */

    public static final int RBDONE = 86;

    /**
     * Server shutdown (service request).
     */

    public static final int SHUT = 87;

    /**
     * Server was shutdown (reply).
     */

    public static final int SHUTDONE = 88;

/////////// GENERAL REPOS ///////////
    
  /**
   *  setStudentState (REQUEST).
   */

   public static final int STSSTREQ = 89;
   
   /**
   *  setStudentState (REPLY).
   */

   public static final int STSSTDONE = 90;

  /**
   *  setWaiterState (REQUEST).
   */

   public static final int STWSTREQ = 91;

   /**
   *  setWaiterState (REPLY).
   */

   public static final int STWSTDONE = 92;
   
  /**
   *  setChefState (REQUEST).
   */

   public static final int STCSTREQ = 93;

   /**
   *  setChefState (REPLY).
   */

   public static final int STCSTDONE = 94;
  
   
   /**
   *  updateSeatsAtTable (REQUEST).
   */

   public static final int USSEATREQ = 95;
   
///////////// TABLE TYPES /////////////
	
    public static final int SFTAREQ = 94;

    public static final int SFTADONE = 95;

    public static final int SLTAREQ = 96;

    public static final int SLTADONE = 97;
}
