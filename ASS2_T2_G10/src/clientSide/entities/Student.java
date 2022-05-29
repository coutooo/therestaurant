/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.entities;

import clientSide.stubs.BarStub;
import clientSide.stubs.TableStub;
import serverSide.main.ExecConst;


/**
 *    Student thread.
 *
 *      It simulates the student life cycle.
 *      Implementation of a client-server model of type 2 (server replication).
 *      Communication is based on a communication channel under the TCP protocol.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Student extends Thread {
    
    /**
     *   Student identification
     */
    private int student_id;
    
    /**
     * 	Student state
    */
    private int currentState;
    
    /**
     *  Reference to the stub of the bar.
     */
    private final BarStub barStub;
    
    /**
     *  Reference to the stub of the table.
     */
    private final TableStub tableStub;
    
    /**
     *   Instantiation of a Student thread.
     *
     *     @param name thread name
     *     @param student_id student id
     *     @param barStub reference to the Bar
     *     @param tableStub reference to the Table
     */
    public Student(String name, int student_id, BarStub barStub, TableStub tableStub){
        super(name);
        this.student_id = student_id;
        this.currentState = StudentState.GOING_TO_THE_RESTAURANT;
        this.barStub = barStub;
        this.tableStub = tableStub;
    }
    /**
     *   Life cycle of the Student.
     */
    @Override
	public void run (){
            walkABit();
            barStub.enter();
            tableStub.readMenu();

            if(student_id == tableStub.getFirstToArrive()){
                    tableStub.prepareOrder();
                    while(!tableStub.everybodyHasChosen())
                            tableStub.addUpOnesChoices();
                    barStub.callWaiter();
                    tableStub.describeOrder();
                    tableStub.joinTalk();
            }
            else{tableStub.informCompanion();}
 
            int numCoursesEaten = 0;
            while(!tableStub.haveAllCoursesBeenEaten()){
                    tableStub.startEating();
                    tableStub.endEating();
                    numCoursesEaten++;

                    while(!tableStub.hasEverybodyFinishedEating());
                    if(student_id == tableStub.getLastToEat() && numCoursesEaten != ExecConst.Ncourses) {barStub.signalWaiter();}
            }

            if(tableStub.shouldHaveArrivedEarlier()) {
                    barStub.signalWaiter();
                    tableStub.honourBill();
            }
            barStub.exit();
	}
    
    private void walkABit() {
        try
        { sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {}
    }
    
    
    /**
     *   Set Student id.
     *
     *     @param id Student id
     */
    public void setStudentId(int id){
        student_id = id;
    }
    
    /**
     *   Get Student id.
     *
     *     @return Student id
     */
    public int getStudentId() {
        return student_id;
    }
    
    /**
     *   Set Student state.
     *
     *     @param state Student state
     */
    public void setStudentState (int state)
    {
        currentState = state;
    }

    /**
     *   Get Student state.
     *
     *     @return Student state.
     */
    public int getStudentState ()
    {
        return currentState;
    }
}