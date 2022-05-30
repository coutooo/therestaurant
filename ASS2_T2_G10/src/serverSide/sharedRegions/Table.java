package serverSide.sharedRegions;

import serverSide.main.*;
import clientSide.entities.WaiterState;
import clientSide.entities.StudentState;
import serverSide.entities.TableClientProxy;
import clientSide.stubs.GeneralReposStub;

/**
 * Table
 *	Synchronisation points include:
 *		Waiter waits for students to read the menu
 *		First student to arrive blocks waiting for others to arrive and describe him the order
 *		Waiter has to wait for first student to arrive to describe him the order
 *		Student blocks waiting for the course to be served
 *		Last student to arrive blocks waiting for bill to be presented
 *		Waiter blocks waiting for student to pay the bill
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */

public class Table {
	
    /**
     * Id of student first to arrive at restaurant
     */
    private int firstToArrive;

    /**
     * Id of student last to arrive at restaurant
     */
    private int lastToArrive;

    /**
     * Used to count number of orders made by students
     */
    private int numOrders;

    /**
     * Used to count number of students that finished the course
     */
    private int numStudentsFinishedCourse;

    /**
     * Id of last student to eat
     */
    private int lastToEat;

    /**
     * Used to count number of courses eaten
     */
    private int numOfCoursesEaten;

    /**
     * Used to count number of students served
     */
    private int numStudentsServed;	

    /**
     * Id of the student whose request the waiter is taking care of
     */
    private int studentBeingAnswered;

    /**
     * Boolean variable to check if waiter is presenting the menu or not
     */
    private boolean presentingTheMenu;

    /**
     * Boolean variable to check if waiter is taking the order
     */
    private boolean takingTheOrder;

    /**
     * Boolean variable to check if a student is informing his companion about the order
     */
    private boolean informingCompanion;

    /**
     * Used to count number of students that woke up after last student to eat has signalled them to
     */
    private int numStudentsWokeUp;

    /**
     * Boolean variable to check if waiter is processing the bill
     */
    private boolean processingBill;

    /**
     * Boolean array to check which students have seated already
     */
    private boolean studentsSeated[];

    /**
     * Boolean array to check which students have already read the menu 
     */
    private boolean studentsReadMenu[];

    /**
     * Reference to the student threads
     */
    private final TableClientProxy [] students;

    /**
     * Reference to the stub of the General Repository.
     */
    private final GeneralReposStub reposStub;
    
    /**
     * Number of entities that must make shutdown
     */
    private int nEntities;   
    
    
    /**
     * Table instantiation
     * 
     * @param reposStub reference to the stub of the general repository
     */    
    public Table(GeneralReposStub reposStub)
    {
    	//Initialisation of attributes
    	this.firstToArrive = -1;
    	this.lastToArrive = -1;
    	this.numOrders = 0;
    	this.numStudentsFinishedCourse = 0;
    	this.lastToEat = -1;
    	this.numOfCoursesEaten = 0;
    	this.numStudentsServed = 0;
    	this.studentBeingAnswered = 0;
    	this.numStudentsWokeUp = 0;
    	this.presentingTheMenu = false;
    	this.takingTheOrder = false;
    	this.informingCompanion = false;
    	this.processingBill = false;
    	this.reposStub = reposStub;
    	this.nEntities = 0;
    	
    	//initialisation of the boolean arrays
    	studentsSeated = new boolean[ExecConst.Nstudents];
    	studentsReadMenu = new boolean[ExecConst.Nstudents];
    	for(int i = 0; i < ExecConst.Nstudents; i++)
    	{
    		studentsSeated[i] = false;
    		studentsReadMenu[i] = false;
    	}
    	
		//Initialisation of students thread
		students = new TableClientProxy[ExecConst.Nstudents];
		for(int i = 0; i < ExecConst.Nstudents; i++ ) 
			students[i] = null;
    	
    }
 
    /**
     * Obtain id of the first student to arrive
     * @return id of the first student to arrive at the restaurant
     */
    public int getFirstToArrive() { return firstToArrive; }
    
    /**
     * Obtain id of the last student to arrive
     * @return id of the last student to finish eating a meal
     */
    public int getLastToEat() { return lastToEat; }
    
    /**
     * Set id of the first student to arrive
     * @param firstToArrive id of the first student to arrive
     */
    public synchronized void setFirstToArrive(int firstToArrive) { this.firstToArrive = firstToArrive; }
    
    /**
     * Set id of the last student to arrive
     * @param lastToArrive if of the last student to arrive to the restaurant
     */
    public synchronized void setLastToArrive(int lastToArrive) { this.lastToArrive = lastToArrive; }
    

    /**
     * Operation salute the client
     */
    public synchronized void saluteClient(int studentIdBeingAnswered)
    {
    	studentBeingAnswered = studentIdBeingAnswered;
    	//Update Waiter state
    	((TableClientProxy) Thread.currentThread()).setWaiterState(WaiterState.PRESENTING_THE_MENU);
    	reposStub.setWaiterState(((TableClientProxy) Thread.currentThread()).getWaiterState());
    	
    	presentingTheMenu = true;
    	
    	
    	//Waiter must wait while student hasn't taken a seat
    	while(studentsSeated[studentBeingAnswered] == false)
    	{
			try {
				wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    	
    	//Waiter wakes student that has just arrived in order to greet him
    	notifyAll();
    	//Block waiting for student to read the menu
    	while(studentsReadMenu[studentBeingAnswered] == false)
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
    	}
    	//When student has finished reading the menu his request was completed
    	studentBeingAnswered = -1;
    	presentingTheMenu  = false;
    }
    
    /**
     * Operation return to the bar
     */
    public synchronized void returnBar()
    {
    	//Update Waiter state
    	((TableClientProxy) Thread.currentThread()).setWaiterState(WaiterState.APPRAISING_SITUATION);
    	reposStub.setWaiterState(((TableClientProxy) Thread.currentThread()).getWaiterState());    	
    }
    
    /**
     * Operation get the pad
     */
    public synchronized void getThePad()
    {
    	//Update Waiter state
    	((TableClientProxy) Thread.currentThread()).setWaiterState(WaiterState.TAKING_THE_ORDER);
    	reposStub.setWaiterState(((TableClientProxy) Thread.currentThread()).getWaiterState());
    	
    	takingTheOrder = true;
    	
    	//notify student that he can describe the order 
    	notifyAll();
    	
    	//Waiter blocks waiting for first student to arrive to describe him the order
    	while(takingTheOrder)
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}    	
    }
    
    
    /**
     * Operation have all clients been served
     * @return true if all clients have been served, false otherwise
     */
    public synchronized boolean haveAllClientsBeenServed()
    {    	
    	//If all clients have been served they must be notified
    	if(numStudentsServed == ExecConst.Nstudents) {
    		//Reset lastToEat and number of students who woke up
    		lastToEat = -1;
    		numStudentsWokeUp = 0;
    		notifyAll();
    		return true;
    	}
    	return false;
    	
    }
    
    /**
     * Operation deliver portion
     */
    public synchronized void deliverPortion()
    {
    	//Update number of Students server after portion delivery
    	numStudentsServed++; 
    }
    
    /**
     * Operation present the bill
     */
    public synchronized void presentBill()
    {
    	processingBill = true;
    	
    	//Signal student the he can pay
    	notifyAll();
    	
    	((TableClientProxy) Thread.currentThread()).setWaiterState(WaiterState.RECEIVING_PAYMENT);
    	reposStub.setWaiterState(((TableClientProxy) Thread.currentThread()).getWaiterState());
    	//Block waiting for his payment
    	try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    /**
     * Operation siting at the table
     */
    public synchronized void seatAtTable()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();

		students[studentId] = (TableClientProxy) Thread.currentThread();
		students[studentId].setStudentState(StudentState.TAKING_A_SEAT_AT_THE_TABLE);

    	//Register that student took a seat
    	studentsSeated[studentId] = true;
    	//notify waiter that student took a seat (waiter may be waiting)
    	notifyAll();
    	
    	//Block waiting for waiter to bring menu specifically to him
    	// Student also blocks if he wakes up when waiter is bringing the menu to another student
    	do
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
    	while(studentId != studentBeingAnswered && presentingTheMenu == false);
    	
    	
    }
    
    /**
     * Operation read the menu
     */
    public synchronized void readMenu()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();
    	
    	//Update student state
    	students[studentId].setStudentState(StudentState.SELECTING_THE_COURSES);
		((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.SELECTING_THE_COURSES);
    	reposStub.updateStudentState(studentId, students[studentId].getStudentState());
		studentsReadMenu[studentId] = true;
    	//Signal waiter that menu was already read
    	notifyAll();
    }    
    
    
    /**
     * Operation prepare the order
     */
    public synchronized void prepareOrder()
    {    	
    	//Register that first student to arrive already choose his own option
    	numOrders++;

    	//Update student state
    	students[firstToArrive].setStudentState(StudentState.ORGANIZING_THE_ORDER);
		((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.ORGANIZING_THE_ORDER);
    	reposStub.updateStudentState(firstToArrive, students[firstToArrive].getStudentState());
    }
    
    

    
    /**
     * Operation everybody has chosen
     * @return true if everybody choose their course choice, false otherwise
     */
    public synchronized boolean everybodyHasChosen()
    {
    	if(numOrders == ExecConst.Nstudents)
    		return true;
    	else {
	    	//Block if not everybody has choosen and while companions are not describing their choices
	    	while(informingCompanion == false)
	    	{
	    		try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	return false;
    	}
    	
    }
    
    /**
     * Operation add up ones choices
     */
    public synchronized void addUpOnesChoices()
    {
    	numOrders++;
    	informingCompanion = false;
    	//Notify sleeping student threads that order was registered
    	notifyAll();
    }
    
    
    /**
     * Operation describe the order
     */
    public synchronized void describeOrder()
    {
    	//After student just putted a request in the queue in the bar
    	// now it must block waiting for waiter to come with the pad
    	while(takingTheOrder == false) 
    	{
	    	try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	takingTheOrder = false;
    	//Wake waiter to describe him the order
    	notifyAll();
    }
    
    /**
     * Operation join the talk
     */
    public synchronized void joinTalk()
    {
    	//Update student state
    	students[firstToArrive].setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
    	((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
		reposStub.updateStudentState(firstToArrive, students[firstToArrive].getStudentState());
	}
    
    
    /**
     * Operation inform companion
     */
    public synchronized void informCompanion()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();
    	
    	//If some other student is informing about his order then wait must be done
    	while(informingCompanion)
    	{
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	informingCompanion = true;
    	//notify first student to arrive, so that he can register ones preference
    	notifyAll();
    	
    	//Update student state
    	students[studentId].setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
		((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
    	reposStub.updateStudentState(studentId, students[studentId].getStudentState());
    }
    
    /**
     * Operation start eating
     */    
    public void startEating()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();
    	//Update student state
    	students[studentId].setStudentState(StudentState.ENJOYING_THE_MEAL);
		((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.ENJOYING_THE_MEAL);
    	reposStub.updateStudentState(studentId, students[studentId].getStudentState());
    	
    	//Enjoy meal during random time
        try
        { Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {}
    }
    
    /**
     * Operation end eating
     */
    public synchronized void endEating()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();
    	
    	//Update number of students that finished course
    	numStudentsFinishedCourse++;
    	
    	//If all students have finished means that one more course was eaten
    	if(numStudentsFinishedCourse == ExecConst.Nstudents)
    	{
    		numOfCoursesEaten++;
    		//register last to eat
    		lastToEat = studentId;
    	}
    	//Update student state
    	students[studentId].setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
		((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
    	reposStub.updateStudentState(studentId, students[studentId].getStudentState());
    }
    
    /**
     * Operation has everybody finished eating
     */
    public synchronized boolean hasEverybodyFinishedEating()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();
    	
    	//Notify all students that the last one to eat has already finished
    	if(studentId == lastToEat)
    	{
    		//Reset number of students that finished the course
    		numStudentsFinishedCourse = 0;
    		//Reset number of students served
    		numStudentsServed = 0;
    		numStudentsWokeUp++;
    		notifyAll();
    		
    		//Last student to eat must wait for every companion to wake up from waiting for everybody to finish eating
    		//before he can proceed to signal waiter
    		while(numStudentsWokeUp != ExecConst.Nstudents)
    		{
    			try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	//Wait while not all students have finished
    	while(numStudentsFinishedCourse != 0) {
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//Update that a student woke up
    	numStudentsWokeUp++;
    	//If all students have woken up from last to eat signal, then student that was last to eat
    	//must be notified
    	if(numStudentsWokeUp == ExecConst.Nstudents)
    		notifyAll();
    	
    	return true;
    }
    
    
    
    
    
    /**
     * Operation honour the bill
     */
    public synchronized void honourBill()
    {    	
    	//Block waiting for waiter to present the bill
    	while(!processingBill)
    	{
    		try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	    	
    	//After waiter presents the bill, student signals waiter so he can wake up and receive it
    	notifyAll();
    }
    
    
    /**
     * 	Operation have all courses been eaten
     * 	@return true if all courses have been eaten, false otherwise
     */
    public synchronized boolean haveAllCoursesBeenEaten()
    {
    	if(numOfCoursesEaten == ExecConst.NCourses)
			return true;
		else {
    		//Student blocks waiting for all companions to be served
    		while(numStudentsServed != ExecConst.Nstudents)
    		{
	    		try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
	    	return false;
    	}
    	
    }
    
    /**
     * Operation should have arrived earlier
     * @return True if current student was the last to arrive, false otherwise
     */
    public synchronized boolean shouldHaveArrivedEarlier()
    {
    	int studentId = ((TableClientProxy) Thread.currentThread()).getStudentId();

    	if(studentId == lastToArrive) {
	    	//Update student state
	    	students[studentId].setStudentState(StudentState.PAYING_THE_MEAL);
			((TableClientProxy) Thread.currentThread()).setStudentState(StudentState.PAYING_THE_MEAL);
			reposStub.updateStudentState(studentId, students[studentId].getStudentState());
	    	return true;
    	}
    	else
    		return false;
    }
    
    /**
     * Operation Table server shutdown
     */
    public synchronized void shutdown()
    {
            nEntities += 1;
            if(nEntities >= ExecConst.NShutKBT)
                    ServerRestaurantTable.waitConnection = false;
            notifyAll ();
    }
    
}  
