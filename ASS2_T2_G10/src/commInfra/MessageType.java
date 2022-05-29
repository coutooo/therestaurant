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
     * continuePreparation (REQUEST)
     */
    public static final int CPREQ = 11;

    /**
     * continuePreparation (REPLY)
     */
    
    public static final int CPDONE = 12;
    /**
     * haveNextPortionReady (REQUEST)
     */
    public static final int HNPRREQ = 13;

    /**
     * haveNextPortionReady (REPLY)
     */
    public static final int HNPRDONE = 14;

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
    public static final int ALREQ = 30;

    /**
     * Waiter was alerted (reply).
     */
    public static final int ALDONE = 31;

    /**
     * LOOK AROUND (REQUEST)
     */
    public static final int LAREQ = 32;

    /**
     * LOOK AROUND (REPLY)
     */
    public static final int LADONE = 33;

    /**
     * SAY GOODBYE (REQUEST)
     */
    public static final int SGREQ = 34;

    /**
     * SAY GOODBYE (REPLY)
     */
    public static final int SGDONE = 35;

    /**
     * PREPARE BILL (REQUEST)
     */
    public static final int PBREQ = 36;

    /**
     * PREPARE BILL (REPLY)
     */
    public static final int PBDONE = 37;
    
    /**
     * enter(request).
     */

    public static final int ENTREQ = 38;

    /**
     * enter done (reply).
     */

    public static final int ENTDONE = 39;

    /**
     * Call waiter (request).
     */

    public static final int CWREQ = 40;

    /**
     * Call waiter done (reply).
     */

    public static final int CWDONE = 41;

    /**
     * Signal waiter (request).
     */

    public static final int SWREQ = 42;

    /**
     * Signal waiter done (reply).
     */

    public static final int SWDONE = 43;

    /**
     * EXIT REQUEST (REQUEST).
     */

    public static final int EXITREQ = 44;

    /**
     * EXIT DONE (REPLY)
     */

    public static final int EXITDONE = 45;

    /**
     * getStudentBeingAnswered (REQUEST)
     */
    
    public static final int GSBAREQ = 46;

    /**
     * getStudentBeingAnswered (REPLY)
     */
    
    public static final int GSBADONE = 47;    

    /**
     * SHUTDOWN BAR (REQUEST).
     */
    public static final int BSHUTREQ = 48;

    /**
     * SHUTDOWN BAR (REPLY).
     */
    public static final int BSHUTDONE = 49;

    ////////////// TABLE TYPES /////////////// 

    /**
     * saluteClient (REQUEST)
     */

    public static final int SCREQ = 60;

    /**
     * saluteClient (REPLY)
     */

    public static final int SCDONE = 61;
    
    /**
     * returnBar (REQUEST)
     */
    
    public static final int RBREQ = 62;

    /**
     * returnBar (REPLY)
     */
    
    public static final int RBDONE = 63;

    /**
     * getThePad (REQUEST)
     */
    public static final int GBREQ = 64;

    /**
     * getThePad (REPLY)
     */
    public static final int GBDONE = 65;

    /**
     * haveAllClientsBeenServed (REQUEST)
     */
    public static final int HACBSREQ = 66;

    /**
     * haveAllClientsBeenServed (REPLY)
     */
    public static final int HACBSDONE = 67;

    /**
     * deliverPortion (REQUEST)
     */
    public static final int DPREQ = 68;

    /**
     * deliverPortion (REPLY)
     */
    public static final int DPDONE = 69;

    /**
     * presentBill (REQUEST)
     */
    public static final int PREBREQ = 70;

    /**
     * presentBill (REPLY)
     */
    public static final int PREBDONE = 71;
    
    /**
     * seatAtTable (REQUEST)
     */

    public static final int SATREQ = 72;

    /**
     * seatAtTable (REPLY)
     */

    public static final int SATDONE = 73;

    /**
     * readMenu (REQUEST)
     */

    public static final int RMREQ = 74;

    /**
     * readMenu (REPLY)
     */

    public static final int RMDONE = 75;

    /**
     * getFirstToArrive (REQUEST)
     */

    public static final int GFTAREQ = 76;

    /**
     * getFirstToArrive (REPLY)
     */

    public static final int GFTADONE = 77;
   
    /**
     * setFirstToArrive (REQUEST)
     */
    public static final int SFTAREQ = 78;

    /**
     * setFirstToArrive (REPLY)
     */
    public static final int SFTADONE = 79;

    /**
     * prepareOrder (REQUEST)
     */

    public static final int POREQ = 80;

    /**
     * prepareOrder (REPLY)
     */

    public static final int PODONE = 81;

    /**
     * everybodyHasChosen (REQUEST)
     */

    public static final int EHCREQ = 82;

    /**
     * everybodyHasChosen (REPLY)
     */

    public static final int EHCDONE = 83;

    /**
     * addUpOnesChoices (REQUEST)
     */

    public static final int AUOCREQ = 84;

    /**
     * addUpOnesChoices (REPLY)
     */

    public static final int AUOCDONE = 85;

    /**
     * describeOrder (REQUEST)
     */

    public static final int DOREQ = 86;

    /**
     * describeOrder (REPLY)
     */

    public static final int DODONE = 87;

    /**
     * joinTalk (REQUEST)
     */

    public static final int JTREQ = 88;

    /**
     * joinTalk (REPLY)
     */

    public static final int JTDONE = 89;

    /**
     * informCompanion (REQUEST)
     */

    public static final int ICREQ = 90;

    /**
     * informCompanion (REPLY)
     */

    public static final int ICDONE = 91;

    /**
     * haveAllCoursesBeenEaten (REQUEST)
     */

    public static final int HACBEREQ = 92;

    /**
     * haveAllCoursesBeenEaten (REPLY)
     */

    public static final int HACBEDONE = 93;

    /**
     * startEating (REQUEST)
     */

    public static final int SEREQ = 94;

    /**
     * startEating (REPLY)
     */

    public static final int SEDONE = 95;

    /**
     * endEating (REQUEST)
     */

    public static final int EEREQ = 96;

    /**
     * endEating (REPLY)
     */

    public static final int EEDONE = 97;

    /**
     * hasEverybodyFinishedEating (REQUEST)
     */

    public static final int HEFEREQ = 98;

    /**
     * hasEverybodyFinishedEating (REPLY)
     */

    public static final int HEFEDONE = 99;

    /**
     * honourBill (REQUEST)
     */

    public static final int HBREQ = 100;

    /**
     * honourBill (REPLY)
     */

    public static final int HBDONE = 101;

    /**
     * shouldHaveArrivedEarlier (REQUEST)
     */

    public static final int SHAEREQ = 102;

    /**
     * shouldHaveArrivedEarlier (REPLY)
     */

    public static final int SHAEDONE = 103;

    /**
     * getLastToEat (REQUEST)
     */

    public static final int GLTEREQ = 104;

    /**
     * getLastToEat (REPLY)
     */

    public static final int GLTEDONE = 105;
    
    /**
     * setLastArrive (REQUEST)
     */
    public static final int SLTAREQ = 106;

    /**
     * setLastToArrive (REPLY)
     */
    public static final int SLTADONE = 107;

    /**
     * SHUTDOWN TABLE (REQUEST).
     */
    public static final int TSHUTREQ = 108;

    /**
     * SHUTDOWN TABLE (REPLY).
     */
    public static final int TSHUTDONE = 109;

    /////////// GENERAL REPOS ///////////
    
  /**
   *  updateStudentState1 (REQUEST).
   */

   public static final int USSTREQ1 = 120;
   
   /**
   *  updateStudentState1 (REPLY).
   */

   public static final int USSTDONE1 = 121;
   
   /**
   *  updateStudentState2 (REQUEST).
   */

   public static final int USSTREQ2 = 122;
   
   /**
   *  updateStudentState2 (REPLY).
   */

   public static final int USSTDONE2 = 123;

  /**
   *  setWaiterState (REQUEST).
   */

   public static final int STWSTREQ = 124;

   /**
   *  setWaiterState (REPLY).
   */

   public static final int STWSTDONE = 125;
   
  /**
   *  setChefState (REQUEST).
   */

   public static final int STCSTREQ = 126;

   /**
   *  setChefState (REPLY).
   */

   public static final int STCSTDONE = 127;   
   /**	
   *  setNCourses (REQUEST).
   */
   
    public static final int SETNCREQ = 128;
    
   /**	
   *  setNCourses (REPLY).
   */
    
    public static final int SETNCDONE = 129;
	
   /**
   * setNPortions (REQUEST).
   */
    
    public static final int SETNPREQ = 130;
    
   /**
    * setNPortions (REPLY).
    */
    
    public static final int SETNPDONE = 131;

   /**
   * update seatAtTable (REQUEST).
   */
    
    public static final int USATREQ = 132;
    
   /**
    * update seatAtTable (REPLY).
    */
    
    public static final int USATDONE = 133;    
    
    /**
     * seatAtLeaving (REQUEST).
     */
    public static final int USALREQ = 134;

    /**
     * seatAtLeaving (REPLY).
     */
    public static final int USALDONE = 135;
	
   /**
   * SHUTDOWN GENERAL REPOSITORY (REQUEST).
   */
    
    public static final int GRSHUTREQ = 136;
    
   /**
    * SHUTDOWN GENERAL REPOSITORY (REPLY).
    */
    
    public static final int GRSHUTDONE = 137;  

}
