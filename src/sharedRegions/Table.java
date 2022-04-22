package sharedRegions;

import InterveningEntities.*;
import therestaurant.TheRestaurant;

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
 */

public class Table {
	
    /**
     * Id of the first student that arrived
     */
    private int firstToArrive;
	
    /**
     * Id of the last student that arrived
     */
    private int lastToArrive;
	
    /**
     * Number of orders made by students
     */
    private int nOrders;

    /**
     * Number of students that finished the course
     */
    private int nStudentsFinishedCourse;

    /**
     * Id of the student that ended last
     */
    private int lastToEat;

    /**
     * Number of courses eaten
     */
    private int nOfCoursesEaten;
	
    /**
     * Number of students served
     */
    private int nStudentsServed;	

    /**
     * Id of the student that the waiter is attending
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
     * Count number of students that woke up after last student to eat has signaled them to
     */
    private int nStudentsWokeUp;

    /**
     * Boolean variable to check if waiter is processing the bill
     */
    private boolean payingTheBill;

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
    private final Student [] students;

    /**
     * Reference to the General Repository.
     */
    private final GeneralRepos repos;
    
    /**
     * Table instantiation
     */
    public Table(GeneralRepos repos)
    {
    	this.firstToArrive = -1;
    	this.lastToArrive = -1;
    	this.nOrders = 0;
    	this.nStudentsFinishedCourse = 0;
    	this.lastToEat = -1;
    	this.nOfCoursesEaten = 0;
    	this.nStudentsServed = 0;
    	this.studentBeingAnswered = 0;
    	this.nStudentsWokeUp = 0;
    	this.presentingTheMenu = false;
    	this.takingTheOrder = false;
    	this.informingCompanion = false;
    	this.payingTheBill = false;
    	this.repos = repos;
    	
    	studentsSeated = new boolean[TheRestaurant.Nstudents];
    	studentsReadMenu = new boolean[TheRestaurant.Nstudents];
    	
        // initiliaze the booleans at false of seated and readmenu
    	for(int i = 0; i < TheRestaurant.Nstudents; i++)
    	{
            studentsSeated[i] = false;
            studentsReadMenu[i] = false;
    	}
    	
            //Initizalization of students thread
            students = new Student[TheRestaurant.Nstudents];
            for(int i = 0; i < TheRestaurant.Nstudents; i++ ) 
                    students[i] = null;
    }
    
    
    
    
    /**
     * @return id of the first student to arrive at the restaurant
     */
    public int getFirstToArrive() { return firstToArrive; }
    
    /**
     * 
     * @return id of the last student to finish eating a meal
     */
    public int getLastToEat() { return lastToEat; }
    
    /**
     * 
     * @param firstToArrive id of the first student to arrive
     */
    public void setFirstToArrive(int firstToArrive) { this.firstToArrive = firstToArrive; }
    
    /**
     * 
     * @param lastToArrive if of the last student to arrive to the restaurant
     */
    public void setLastToArrive(int lastToArrive) { this.lastToArrive = lastToArrive; }
    
    /**
     * 
     * It is called by the waiter when a student enters the restaurant
     */
    public synchronized void saluteClient(int studentIdBeingAnswered)
    {
    	studentBeingAnswered = studentIdBeingAnswered;
    	
    	//Update Waiter state
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterState.PRESENTING_THE_MENU);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    	
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
    	System.out.println("Saluting");
    	
    	//Waiter wakes student that has just arrived in order to greet him
    	notifyAll();
    	System.out.println("Waiter Saluting student "+studentBeingAnswered+ " "+presentingTheMenu);
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
     * 
     * waiter return to the bar appraising situation
     */
    public synchronized void returnBar()
    {
    	//Update Waiter state
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterState.APPRAISING_SITUATION);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());    	
    }
    
    
    
    
    /**
     * 
     * waiter calls it when an order is going to be asked by the first student to arrive
     * Waiter Blocks waiting for student to describe him the order
     */
    public synchronized void getThePad()
    {
    	//Update Waiter state
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterState.TAKING_THE_ORDER);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    	
    	takingTheOrder = true;
    	
    	//notify student that he can describe the order 
    	notifyAll();
    	
    	System.out.println("Waiter is now wainting for order description");
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
    	
    	System.out.println("Waiter Got the order");
    	
    }
    
    
    
    
    /**
     * 
     * check if all clients have been served or not
     * @return true if all clients have been served, false otherwise
     */
    public synchronized boolean haveAllClientsBeenServed()
    {    	
    	//If all clients have been served they must be notified
    	if(nStudentsServed == TheRestaurant.Nstudents) {
            System.out.println("I CHANGED numStudentsFinished");
            lastToEat = -1;
            nStudentsWokeUp = 0;
            notifyAll();
            return true;
    	}
    	return false;
    	
    }
    
    
    
    /**
     * 
     * portion is delivered at the table
     */
    public synchronized void deliverPortion()
    {
    	//Update number of Students served after portion delivery
    	nStudentsServed++; 
    }
    
    
    
    
    
    /**
     * 
     * waiter presents the bill to the last student to arrive
     */
    public synchronized void presentBill()
    {
    	payingTheBill = true;
    	
    	//Signal student the he can pay
    	notifyAll();
    	
    	((Waiter) Thread.currentThread()).setWaiterState(WaiterState.RECEIVING_PAYMENT);
    	repos.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    	//Block waiting for his payment
    	try {
            wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
    }
    
    
    
    /**
     * 
     * Student comes in the table and sits (blocks) waiting for waiter to bring him the menu
     * Called by the student (inside enter method in the bar)
     */
    public synchronized void seatAtTable()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentID();
    	
            students[studentId] = ((Student) Thread.currentThread());
            students[studentId].setStudentState(StudentState.TAKING_A_SEAT_AT_THE_TABLE);
    	
    	System.out.println("Student"+ studentId+" took a seat and blocked");
    	//Register that student took a seat
    	studentsSeated[studentId] = true;
    	//notify waiter that student took a seat (waiter may be waiting)
    	notifyAll();
    	
    	//Block waiting for waiter to bring menu specifically to him
    	// Student also blocks if he wakes up when waiter is bringing the menu to another student
    	while (true)
    	{
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("I student "+studentId+"was waken up ("+studentBeingAnswered+", "+takingTheOrder+")");
            if (studentId == studentBeingAnswered && presentingTheMenu == true) {
                System.out.println("I student "+studentId+" Can Proceed");
                break;
            }
        }
    	System.out.println("Student "+studentId+ " was presented with the menu ("+studentBeingAnswered+")");
    }
    
    
    
    /**
     * 
     * (student)wakes up waiter because already reed the menu
     */
    public synchronized void readMenu()
    {   
    	int studentId = ((Student) Thread.currentThread()).getStudentID();
    	
    	//Update student state
    	students[studentId].setStudentState(StudentState.SELECTING_THE_COURSES);
    	repos.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    	
    	studentsReadMenu[studentId] = true;
    	//Signal waiter that menu was already read
    	notifyAll();
    	
    	System.out.println("Student "+studentId+ " read the menu ("+studentBeingAnswered+")");
    }    
    
    
    
    
    
    
    /**
     * 
     * (student)begin the preparation of the order, 
     */
    public synchronized void prepareOrder()
    {    	
    	//Register that first student to arrive already choose his own option
    	nOrders++;
    	
    	//Update student state
    	students[firstToArrive].setStudentState(StudentState.ORGANIZING_THE_ORDER);
    	repos.setStudentState(firstToArrive, ((Student) Thread.currentThread()).getStudentState());
    	
    }
    
    

    
    /**
     * 
     * (1ststudent) check if all his companions have choose or not
     * Blocks if not.
     * @return true if has everybody choosen, false otherwise
     */
    public synchronized boolean everybodyHasChosen()
    {
    	if(nOrders == TheRestaurant.Nstudents)
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
     * 
     * (1ststudent) to add up a companions choice to the order
     */
    public synchronized void addUpOnesChoices()
    {
    	nOrders++;
    	informingCompanion = false;
    	//Notify sleeping student threads that order was registered
    	notifyAll();
    }
    
    
    
    
    /**
     * 
     * (1ststudent) describe the order to the waiter
     * Blocks waiting for waiter to come with pad
     * Wake waiter up so he can take the order
     */
    public synchronized void describeOrder()
    {
    	System.out.println("I reached here");
    	//After student just putted a request in the queue in the bar, now it must block
    	// in the table waiting for waiter to come with the pad
    	while(takingTheOrder == false) 
    	{
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    	}
    	
    	System.out.println("Student "+firstToArrive+" described the order");
    	takingTheOrder = false;
    	//Wake waiter to describe him the order
    	notifyAll();
    }
    
    
    
    
    
    /**
     * 
     * (1ststudent) to join his companions while waiting for the courses 
     */
    public synchronized void joinTalk()
    {
    	//Update student state
    	students[firstToArrive].setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
    	repos.setStudentState(firstToArrive, ((Student) Thread.currentThread()).getStudentState());   
    }
    
    
    
    
    
    /**
     * 
     * (student) inform the first student to arrive about his preferences 
     * Blocks waiting for courses
     */
    public synchronized void informCompanion()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentID();
    	
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
    	repos.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    	
    }
    
    
    
    
    /**
     * 
     * (student) to start eating the meal
     */    
    public synchronized void startEating()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentID();
    	 
    	//Update student state
    	students[studentId].setStudentState(StudentState.ENJOYING_THE_MEAL);
    	repos.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    	
    	//Enjoy meal during random time
        try
        { Thread.sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {}
    }



    /**
     * 
     * (student) to signal that he has finished eating his meal
     */
    public synchronized void endEating()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentID();
    	
    	//Update numstudents finished course
    	nStudentsFinishedCourse++;
    	System.out.println("I "+studentId+" finished"+nStudentsFinishedCourse);
    	
    	//If all students have finished means that one more course was eaten
    	if(nStudentsFinishedCourse == TheRestaurant.Nstudents)
    	{
            nOfCoursesEaten++;
            //register last to eat
            lastToEat = studentId;
    	}
    	
    	//Update student state
    	students[studentId].setStudentState(StudentState.CHATTING_WITH_COMPANIONS);
    	repos.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
    }
    
    
    
    
    
    /**
     * 
     * (student) to wait for his companions to finish eating
     */
    public synchronized boolean hasEverybodyFinishedEating()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentID();
    	
    	//Notify all students that the last one to eat has already finished
    	if(studentId == lastToEat)
    	{
            nStudentsFinishedCourse = 0;
            nStudentsServed = 0;
            nStudentsWokeUp++;
            notifyAll();
            while(nStudentsWokeUp != TheRestaurant.Nstudents)
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
    	while(nStudentsFinishedCourse != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Student "+studentId+" woke "+nStudentsFinishedCourse);
    	}
    	nStudentsWokeUp++;
    	if(nStudentsWokeUp == TheRestaurant.Nstudents)
            notifyAll();
    	
    	return true;
    }
    
    
    
    
    
    /**
     * 
     * (student) to pay the bill
     * Student blocks waiting for bill to be presented and signals waiter when it's time to pay it
     */
    public synchronized void honourBill()
    {    	
    	//Block waiting for waiter to present the bill
    	while(!payingTheBill)
    	{
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    	}
    	System.out.println("I PAYED THE MEAL");
	    	
    	//After waiter presents the bill, student signals waiter so he can wake up and receive it
    	notifyAll();
    }
    
    
    
    
    
    
    /**
     * 
     * (student) to check if there are more courses to be eaten
     * 	Student blocks waiting for the course to be served
     * 	@return true if all courses have been eaten, false otherwise
     */
    public synchronized boolean haveAllCoursesBeenEaten()
    {
    	if(nOfCoursesEaten == TheRestaurant.Ncourses)
            return true;
            else {
            //Student blocks waiting for all companions to be served
            while(nStudentsServed != TheRestaurant.Nstudents)
            {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("All served!!! Course "+ nOfCoursesEaten);
            return false;
    	}
    	
    }
    
    
    
    
    
    /**
     * 
     * (student) to check wich one was last to arrive
     * @return True if current student was the last to arrive, false otherwise
     */
    public synchronized boolean shouldHaveArrivedEarlier()
    {
    	int studentId = ((Student) Thread.currentThread()).getStudentID();

    	if(studentId == lastToArrive) {
            //Update student state
            students[studentId].setStudentState(StudentState.PAYING_THE_MEAL);
            repos.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
            return true;
    	}
    	else
            return false;
    }    
}
