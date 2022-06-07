package clientSide.entities;

import clientSide.stubs.*;
import interfaces.BarInterface;
import interfaces.TableInterface;
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
            barStub.enter();
            tableStub.readMenu();

            if(studentId == tableStub.getFirstToArrive())
            {
                    tableStub.prepareOrder();
                    while(!tableStub.everybodyHasChosen())
                            tableStub.addUpOnesChoices();
                    barStub.callWaiter();
                    tableStub.describeOrder();
                    tableStub.joinTalk();
            }
            else
                    tableStub.informCompanion();

            int numCoursesEaten = 0;
            while(!tableStub.haveAllCoursesBeenEaten())
            {
                    tableStub.startEating();
                    tableStub.endEating();
                    numCoursesEaten++;

                    while(!tableStub.hasEverybodyFinishedEating());
                    if(studentId == tableStub.getLastToEat() && numCoursesEaten != ExecConst.NCourses)
                            barStub.signalWaiter();
            }

            if(tableStub.shouldHaveArrivedEarlier()) 
            {
                    barStub.signalWaiter();
                    tableStub.honourBill();
            }
            barStub.exit();
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
     */
    private void walkABit()
    {
            try
            { sleep ((long) (1 + 50 * Math.random ()));
            }
            catch (InterruptedException e) {}
    }
	
}
