/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

import sharedRegions.*;


/**
 * Student thread:
 * Implements the life-cycle of a student and stores his internal variables
 * and his state during his lifecycle.
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Student extends Thread {
    
    /**
     *   Chief identification
     */
    private int student_id;
    
    /**
     * 	Student state
    */
    private StudentState currentState;
    
    /**
     *   Reference to Bar
     */
    private final Bar b;
    
    /**
     *   Reference to Table
     */
    private final Table t;
    
    /**
     *   Instantiation of a Student thread.
     *
     *     @param name thread name
     *     @param student_id student id
     *     @param b reference to the Bar
     *     @param k reference to the Kitchen
     */
    public Student(String name, int student_id, Bar b, Table t){
        super(name);
        this.student_id = student_id;
        this.currentState = StudentState.GOING_TO_THE_RESTAURANT;
        this.b = b;
        this.t = t;
    }
    /**
     *   Life cycle of the Chief.
     */
    @Override
	public void run (){
            walkABit();
            b.enter();
            t.readMenu();

            if(studentId == t.getFirstToArrive()){
                    t.prepareOrder();
                    while(!t.everybodyHasChosen())
                            t.addUpOnesChoices();
                    b.callWaiter();
                    t.describeOrder();
                    t.joinTalk();
            }
            else{t.informCompanion();}
 
            int numCoursesEaten = 0;
            while(!t.haveAllCoursesBeenEaten()){
                    t.startEating();
                    t.endEating();
                    numCoursesEaten++;

                    while(!t.hasEverybodyFinishedEating());
                    System.out.println("I FINISHED MY MEAL");
                    if(studentId == t.getLastToEat() && numCoursesEaten != ExecuteConst.M) {b.signalWaiter();}
            }

            if(t.shouldHaveArrivedEarlier()) {
                    b.signalWaiter();
                    t.honourBill();
            }
            b.exit();
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
    public void setStudentID(StudentState state){
        currentState = state;
    }
    
    /**
     *   Get Student id.
     *
     *     @return Student id
     */
    public int getStudentID() {
        return student_id;
    }
    
    /**
     *   Set Student state.
     *
     *     @param state Student state
     */
    public void setStudentState (StudentState state)
    {
        currentState = state;
    }

    /**
     *   Get Student state.
     *
     *     @return Student state.
     */
    public StudentState getStudentState ()
    {
        return currentState;
    }
}