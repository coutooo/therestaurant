package clientSide.entities;

import interfaces.*;
import java.rmi.RemoteException;
import genclass.GenericIO;
import serverSide.main.ExecConst;

/**
 *    Student thread.
 *
 *      It simulates the student life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 *   @author Rafael Dias
 *   @author Manuel Couto
 */

public class Student extends Thread{
	
    /**
     * 	Student id
     */
    private int studentId;

    /**
     * 	Student state
     */
    private int currentState;

    /**
     * Reference to the stub of the bar
     */

    private final BarInterface barStub;

    /**
     * Reference to the stub of the table
     */
    private final TableInterface tableStub;



    /**
     * Instantiation of a Student thread.
     *  
     * 	@param name thread name
     * 	@param studentId student id
     * 	@param barStub reference to the stub of the bar
     * 	@param tableStub reference to the stub of the table
     */
    public Student(String name, int studentId, BarInterface barStub, TableInterface tableStub) {
            super(name);
            this.studentId = studentId;
            this.currentState = StudentState.GOING_TO_THE_RESTAURANT;
            this.barStub = barStub;
            this.tableStub = tableStub;
    }	
	
    /**
     *	Life cycle of the student
     */
    @Override
    public void run ()
    {
            walkABit();
            enter();
            readMenu();

            if(studentId == getFirstToArrive())
            {
                    prepareOrder();
                    while(!everybodyHasChosen())
                            addUpOnesChoices();
                    callWaiter();
                    describeOrder();
                    joinTalk();
            }
            else
                    informCompanion();

            int numCoursesEaten = 0;
            while(!haveAllCoursesBeenEaten())
            {
                    startEating();
                    endEating();
                    numCoursesEaten++;

                    while(!hasEverybodyFinishedEating());
                    if(studentId == getLastToEat() && numCoursesEaten != ExecConst.NCourses)
                            signalWaiter();
            }

            if(shouldHaveArrivedEarlier()) 
            {
                    signalWaiter();
                    honourBill();
            }
            exit();
    }

    /**
     * Set a new student id
     *
     * @param studentId id of the student to be set
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Get the student id
     *
     * @return student id
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Set a new student state
     *
     * @param state new state to be set
     */
    public void setStudentState(int state) {
        this.currentState = state;
    }

    /**
     * Get the student state
     *
     * @return student state
     */
    public int getStudentState() {
        return currentState;
    }

    /**
     * Sleep for a random time
     * Internal operation.
     */
    private void walkABit()
    {
            try
            { sleep ((long) (1 + 50 * Math.random ()));
            }
            catch (InterruptedException e) {}
    }

    /**
     * Operation enter the restaurant Remote operation.
     */
    private void enter() {
        try {
            currentState = barStub.enter(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on enter: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation read the menu Remote Operation. 
     */
    private void readMenu() {
        try {
            currentState = tableStub.readMenu(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on readMenu: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation call the waiter Remote operation.
     *
     */
    private void callWaiter() {
        try {
            barStub.callWaiter(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on callWaiter: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Obtain id of the first student to arrive Remote operation.
     *
     * @return id of the first student to arrive at the restaurant
     */
    private int getFirstToArrive() {
        int firstToArrive = -1;

        try {
            firstToArrive = tableStub.getFirstToArrive();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on getFirstToArrive: " + e.getMessage());
            System.exit(1);
        }
        if (firstToArrive == -1) {
            GenericIO.writelnString("Invalid id received in getFirstToArrive");
            System.exit(1);
        }
        return firstToArrive;
    }

    /**
     * Operation prepare the order Remote operation. 
     */
    private void prepareOrder() {
        try {
            currentState = tableStub.prepareOrder();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on prepareOrder: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation everybody has chosen Remote operation. 
     *
     * @return true if everybody choose their course choice, false otherwise
     */
    private boolean everybodyHasChosen() {
        boolean everybodyHasChosen = false;

        try {
            everybodyHasChosen = tableStub.everybodyHasChosen();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on everybodyHasChosen: " + e.getMessage());
            System.exit(1);
        }

        return everybodyHasChosen;
    }

    /**
     * Operation add up ones choices Remote operation. 
     */
    private void addUpOnesChoices() {
        try {
            tableStub.addUpOnesChoices();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on addUpOnesChoices: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation describe the order Remote operation. 
     */
    private void describeOrder() {
        try {
            tableStub.describeOrder();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on describeOrder: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation join the talk Remote operation. 
     */
    private void joinTalk() {
        try {
            currentState = tableStub.joinTalk();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on joinTalk: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation inform companion Remote Operation. 
     */
    private void informCompanion() {
        try {
            currentState = tableStub.informCompanion(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on informCompanion: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation have all courses been eaten Remote operation.
     *
     * @return true if all courses have been eaten, false otherwise
     */
    private boolean haveAllCoursesBeenEaten() {
        boolean haveAllCoursesEaten = false;

        try {
            haveAllCoursesEaten = tableStub.haveAllCoursesBeenEaten();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on haveAllCoursesBeenEaten: " + e.getMessage());
            System.exit(1);
        }
        return haveAllCoursesEaten;
    }

    /**
     * Operation start eating Remote operation. 
     */
    private void startEating() {
        try {
            currentState = tableStub.startEating(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on startEating: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation end eating Remote operation. 
     */
    private void endEating() {
        try {
            currentState = tableStub.endEating(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on endEating: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation has everybody finished eating Remote operation. 
     * 
     * @return true if everybody has finished eating, false otherwise
     */
    private boolean hasEverybodyFinishedEating() {
        boolean hasEverybodyFinished = false;

        try {
            hasEverybodyFinished = tableStub.hasEverybodyFinishedEating(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on hasEverybodyFinishedEating: " + e.getMessage());
            System.exit(1);
        }
        return hasEverybodyFinished;
    }

    /**
     * Obtain id of the last student to arrive Remote operation.
     *
     * @return id of the last student to finish eating a meal
     */
    private int getLastToEat() {
        int lastToEat = -1;

        try {
            lastToEat = tableStub.getLastToEat();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on getLastToEat: " + e.getMessage());
            System.exit(1);
        }
        return lastToEat;
    }

    /**
     * Operation signal the waiter Remote operation.
     */
    private void signalWaiter() {
        try {
            barStub.signalWaiter(studentId, currentState);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on signalWaiter: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation should have arrived earlier Remote operation.
     *
     * @return True if current student was the last to arrive, false otherwise
     */
    private boolean shouldHaveArrivedEarlier() {
        ReturnBoolean res = null;

        try {
            res = tableStub.shouldHaveArrivedEarlier(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on shouldHaveArrivedEarlier: " + e.getMessage());
            System.exit(1);
        }

        currentState = res.getIntStateVal();
        return res.getBooleanVal();
    }

    /**
     * Operation honour the bill Remote operation.
     */
    private void honourBill() {
        try {
            tableStub.honourBill();
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on honourBill: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Operation exit the restaurant Remote operation.
     */
    private void exit() {
        try {
            currentState = barStub.exit(studentId);
        } catch (RemoteException e) {
            GenericIO.writelnString("Student " + studentId + " remote exception on exit: " + e.getMessage());
            System.exit(1);
        }
    }
}

