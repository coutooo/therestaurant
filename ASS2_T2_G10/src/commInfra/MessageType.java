package commInfra;

/**
 *   Type of the exchanged messages.
 *
 *   Implementation of a client-server model of type 2 (server replication).
 *   Communication is based on a communication channel under the TCP protocol.
 */

public class MessageType {
    
    ////////////// KITCHEN TYPES /////////////// 

    /**
     * watch the news (request)
     */

    public static final int WTNREQ = 1;

    /**
     * watch the news (reply)
     */

    public static final int WTNDONE = 2;

    /**
     * START PREPARATION (REQUEST)
     */

    public static final int STPREQ = 3;

    /**
     * START PREPARATION (REPLY)
     */

    public static final int STPDONE = 4;

    /**
     * PROCEED PRESENTATION (REQUEST)
     */

    public static final int PTPREQ = 5;

    /**
     * PROCEED PRESENTATION (REPLY)
     */
    
    public static final int PTPDONE = 6;
    
    /**
     * haveAllPortionsBeenDelivered (REQUEST)
     */
    public static final int HAPBDREQ = 7;

    /**
     * haveAllPortionsBeenDelivered (REPLY)
     */
    public static final int HAPBDDONE = 8;

    /**
     * hasOrderBeenCompleted (REQUEST)
     */
    public static final int HOBCREQ = 9;

    /**
     * hasOrderBeenCompleted (REPLY)
     */
    public static final int HOBCDONE = 10;    

    /**
     * haveNextPortionReady (REQUEST)
     */
    public static final int HNPRREQ = 11;

    /**
     * haveNextPortionReady (REPLY)
     */
    public static final int HNPRDONE = 12;

    /**
     * continuePreparation (REQUEST)
     */
    public static final int CPREQ = 13;

    /**
     * continuePreparation (REPLY)
     */
    public static final int CPDONE = 14;

    /**
     * cleanUp (REQUEST)
     */
    public static final int CUREQ = 15;

    /**
     * cleanUp (REPLY)
     */
    public static final int CUDONE = 16;

    /**
     * handNoteToChef (REQUEST)
     */
    public static final int HNTCREQ = 17;

    /**
     * handNoteToChef (REPLY)
     */
    public static final int HNTCDONE = 18;

    /**
     * returnToBar (REQUEST)
     */
    public static final int RTBREQ = 19;

    /**
     * returnToBar (REPLY)
     */
    public static final int RTBDONE = 20;

    /**
     * collectPortion (REQUEST)
     */
    public static final int CPORREQ = 21;

    /**
     * collectPortion (REPLY)
     */
    public static final int CPORDONE = 22;
    
    /**
     * SHUTDOWN KITCHEN (REQUEST).
     */
    
    public static final int KSHUTREQ = 23;

    /**
     * SHUTDOWN KITCHEN  (REPLY).
     */
    
    public static final int KSHUTDONE = 24;
    
    ////////////// BAR TYPES /////////////// 

    /**
     * Alert waiter (service request).
     */
    public static final int ALREQ = 25;

    /**
     * Waiter was alerted (reply).
     */
    public static final int ALDONE = 26;

    /**
     * LOOK AROUND (REQUEST)
     */
    public static final int LAREQ = 27;

    /**
     * LOOK AROUND (REPLY)
     */
    public static final int LADONE = 28;

    /**
     * SAY GOODBYE (REQUEST)
     */
    public static final int SGREQ = 29;

    /**
     * SAY GOODBYE (REPLY)
     */
    public static final int SGDONE = 30;

    /**
     * PREPARE BILL (REQUEST)
     */
    public static final int PBREQ = 31;

    /**
     * PREPARE BILL (REPLY)
     */
    public static final int PBDONE = 32;
    
    /**
     * enter(request).
     */

    public static final int ENTREQ = 33;

    /**
     * enter done (reply).
     */

    public static final int ENTDONE = 34;

    /**
     * Call waiter (request).
     */

    public static final int CWREQ = 35;

    /**
     * Call waiter done (reply).
     */

    public static final int CWDONE = 36;

    /**
     * Signal waiter (request).
     */

    public static final int SWREQ = 37;

    /**
     * Signal waiter done (reply).
     */

    public static final int SWDONE = 38;

    /**
     * EXIT REQUEST (REQUEST).
     */

    public static final int EXITREQ = 39;

    /**
     * EXIT DONE (REPLY)
     */

    public static final int EXITDONE = 40;

    /**
     * getStudentBeingAnswered (REQUEST)
     */
    
    public static final int GSBAREQ = 41;

    /**
     * getStudentBeingAnswered (REPLY)
     */
    
    public static final int GSBADONE = 42;    

    /**
     * SHUTDOWN BAR (REQUEST).
     */
    public static final int BSHUTREQ = 43;

    /**
     * SHUTDOWN BAR (REPLY).
     */
    public static final int BSHUTDONE = 44;

    ////////////// TABLE TYPES /////////////// 

    /**
     * saluteClient (REQUEST)
     */

    public static final int SCREQ = 45;

    /**
     * saluteClient (REPLY)
     */

    public static final int SCDONE = 46;
    
    /**
     * returnBar (REQUEST)
     */
    
    public static final int RBREQ = 47;

    /**
     * returnBar (REPLY)
     */
    
    public static final int RBDONE = 48;

    /**
     * getThePad (REQUEST)
     */
    public static final int GBREQ = 49;

    /**
     * getThePad (REPLY)
     */
    public static final int GBDONE = 50;

    /**
     * haveAllClientsBeenServed (REQUEST)
     */
    public static final int HACBSREQ = 51;

    /**
     * haveAllClientsBeenServed (REPLY)
     */
    public static final int HACBSDONE = 52;

    /**
     * deliverPortion (REQUEST)
     */
    public static final int DPREQ = 53;

    /**
     * deliverPortion (REPLY)
     */
    public static final int DPDONE = 54;

    /**
     * presentBill (REQUEST)
     */
    public static final int PREBREQ = 55;

    /**
     * presentBill (REPLY)
     */
    public static final int PREBDONE = 56;
    
    /**
     * seatAtTable (REQUEST)
     */

    public static final int SATREQ = 57;

    /**
     * seatAtTable (REPLY)
     */

    public static final int SATDONE = 58;

    /**
     * readMenu (REQUEST)
     */

    public static final int RMREQ = 59;

    /**
     * readMenu (REPLY)
     */

    public static final int RMDONE = 60;

    /**
     * getFirstToArrive (REQUEST)
     */

    public static final int GFTAREQ = 61;

    /**
     * getFirstToArrive (REPLY)
     */

    public static final int GFTADONE = 62;
   
    /**
     * setFirstToArrive (REQUEST)
     */
    public static final int SFTAREQ = 63;

    /**
     * setFirstToArrive (REPLY)
     */
    public static final int SFTADONE = 64;

    /**
     * prepareOrder (REQUEST)
     */

    public static final int POREQ = 65;

    /**
     * prepareOrder (REPLY)
     */

    public static final int PODONE = 66;

    /**
     * everybodyHasChosen (REQUEST)
     */

    public static final int EHCREQ = 67;

    /**
     * everybodyHasChosen (REPLY)
     */

    public static final int EHCDONE = 68;

    /**
     * addUpOnesChoices (REQUEST)
     */

    public static final int AUOCREQ = 69;

    /**
     * addUpOnesChoices (REPLY)
     */

    public static final int AUOCDONE = 70;

    /**
     * describeOrder (REQUEST)
     */

    public static final int DOREQ = 71;

    /**
     * describeOrder (REPLY)
     */

    public static final int DODONE = 72;

    /**
     * joinTalk (REQUEST)
     */

    public static final int JTREQ = 73;

    /**
     * joinTalk (REPLY)
     */

    public static final int JTDONE = 74;

    /**
     * informCompanion (REQUEST)
     */

    public static final int ICREQ = 75;

    /**
     * informCompanion (REPLY)
     */

    public static final int ICDONE = 76;

    /**
     * haveAllCoursesBeenEaten (REQUEST)
     */

    public static final int HACBEREQ = 77;

    /**
     * haveAllCoursesBeenEaten (REPLY)
     */

    public static final int HACBEDONE = 78;

    /**
     * startEating (REQUEST)
     */

    public static final int SEREQ = 79;

    /**
     * startEating (REPLY)
     */

    public static final int SEDONE = 80;

    /**
     * endEating (REQUEST)
     */

    public static final int EEREQ = 81;

    /**
     * endEating (REPLY)
     */

    public static final int EEDONE = 82;

    /**
     * hasEverybodyFinishedEating (REQUEST)
     */

    public static final int HEFEREQ = 83;

    /**
     * hasEverybodyFinishedEating (REPLY)
     */

    public static final int HEFEDONE = 84;

    /**
     * honourBill (REQUEST)
     */

    public static final int HBREQ = 85;

    /**
     * honourBill (REPLY)
     */

    public static final int HBDONE = 86;

    /**
     * shouldHaveArrivedEarlier (REQUEST)
     */

    public static final int SHAEREQ = 87;

    /**
     * shouldHaveArrivedEarlier (REPLY)
     */

    public static final int SHAEDONE = 88;

    /**
     * getLastToEat (REQUEST)
     */

    public static final int GLTEREQ = 89;

    /**
     * getLastToEat (REPLY)
     */

    public static final int GLTEDONE = 90;
    
    /**
     * setLastArrive (REQUEST)
     */
    public static final int SLTAREQ = 91;

    /**
     * setLastToArrive (REPLY)
     */
    public static final int SLTADONE = 92;

    /**
     * SHUTDOWN TABLE (REQUEST).
     */
    public static final int TSHUTREQ = 93;

    /**
     * SHUTDOWN TABLE (REPLY).
     */
    public static final int TSHUTDONE = 94;

    /////////// GENERAL REPOS ///////////
    
  /**
   *  updateStudentState1 (REQUEST).
   */

   public static final int USSTREQ1 = 95;
   
   /**
   *  updateStudentState1 (REPLY).
   */

   public static final int USSTDONE1 = 96;
   
   /**
   *  updateStudentState2 (REQUEST).
   */

   public static final int USSTREQ2 = 97;
   
   /**
   *  updateStudentState2 (REPLY).
   */

   public static final int USSTDONE2 = 98;

  /**
   *  setWaiterState (REQUEST).
   */

   public static final int STWSTREQ = 99;

   /**
   *  setWaiterState (REPLY).
   */

   public static final int STWSTDONE = 100;
   
  /**
   *  setChefState (REQUEST).
   */

   public static final int STCSTREQ = 101;

   /**
   *  setChefState (REPLY).
   */

   public static final int STCSTDONE = 102;
  
   
   /**
   *  updateSeatsAtTable (REQUEST).
   */

   public static final int USSEATREQ = 103;
   
    /**
     * updateSeatsAtTable (REPLY).
     */
    public static final int USSEATDONE = 104;
   
   /**	
   *  setNCourses (REQUEST).
   */
   
    public static final int SETNCREQ = 105;
    
   /**	
   *  setNCourses (REPLY).
   */
    
    public static final int SETNCDONE = 106;
	
   /**
   * setNPortions (REQUEST).
   */
    
    public static final int SETNPREQ = 107;
    
   /**
    * setNPortions (REPLY).
    */
    
    public static final int SETNPDONE = 108;

   /**
   * seatAtTable (REQUEST).
   */
    
    public static final int USATREQ = 109;
    
   /**
    * seatAtTable (REPLY).
    */
    
    public static final int USATDONE = 110;    
    
    /**
     * seatAtLeaving (REQUEST).
     */
    public static final int USALREQ = 111;

    /**
     * seatAtLeaving (REPLY).
     */
    public static final int USALDONE = 112;
	
   /**
   * SHUTDOWN GENERAL REPOSITORY (REQUEST).
   */
    
    public static final int GRSHUTREQ = 113;
    
   /**
    * SHUTDOWN GENERAL REPOSITORY (REPLY).
    */
    
    public static final int GRSHUTDONE = 114;  

}
