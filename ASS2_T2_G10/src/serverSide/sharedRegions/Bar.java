package serverSide.sharedRegions;

import serverSide.main.*;
import serverSide.entities.*;
import clientSide.entities.*;
import clientSide.stubs.*;
import commInfra.*;
import genclass.GenericIO;

/**
 * 
 * Bar
 *
 *	It is responsible for keeping track of the several requests that must be fullfilled by the waiter
 *	Implemented as an implicit monitor
 *	Public methods executed in mutual exclusion
 *	Synchronisation points include: 
 *         - When the waiter needs to wait for a pending request
 *         - When a student waits for the waiter to say goodbye to him to leave the restaurant
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class Bar 
{
    /**
     *	Number of students in the restaurant
     */
    private int numberOfStudentsAtRestaurant;

    /**
     *  Number of pending requests to be answered 
     */
    private int numberOfPendingRequests;

    /**
     * Boolean variable used to store if a course was finished or not
     */
    private boolean courseFinished;

    /**
     * Queue of pending Requests
     */
    private MemFIFO<Request> pendingServiceRequestQueue;

    /**
     * Reference to the student threads
     */
    private final Student [] students;

    /**
     * Reference to the general repository
     */
    private final GeneralReposStub repo;

    /**
     * Auxiliary variable of the id of the student whose request is being answered
     */
    private int studentBeingAnswered;

    /**
     * Array of booleans of the students which the waiter has already said goodbye
     */
    private boolean[] studentsGreeted;

    /**
     * Reference to the table
     */
    private final Table tab;
    
    /**
    *   Number of entity groups requesting the shutdown.
    */
    private int nEntities;

    /**
     * Bar instantiation
     * 
     * @param repo reference to the general repository 
     * @param tab reference to the Table
     */
    public Bar(GeneralReposStub repo, Table tab) 
    {
        this.nEntities = 0;
        //Initizalization of students thread
        this.students = new Student[ExecConst.Nstudents];
        for(int i = 0; i < ExecConst.Nstudents; i++ ) 
            this.students[i] = null;

        //Initialization of the queue of pending requests
        try {
            this.pendingServiceRequestQueue = new MemFIFO<> (new Request [serverSide.main.ExecConst.Nstudents * serverSide.main.ExecConst.Ncourses]);
        } catch (MemException e) {
            this.pendingServiceRequestQueue = null;
            System.exit (1);
        }

        this.courseFinished = true;
        this.studentBeingAnswered = -1;
        this.repo = repo;
        this.tab = tab;

        this.studentsGreeted = new boolean[serverSide.main.ExecConst.Nstudents];
        for(int i = 0 ;i < serverSide.main.ExecConst.Nstudents; i++)
            this.studentsGreeted[i] = false;
    }


    /**
     * @return Id of the student whose request is being answered
     */
    public int getStudentBeingAnswered() { return studentBeingAnswered; }

    /**
     * Operation alert the waiter
     */
    public synchronized void alertWaiter()
    {
        while(!courseFinished)
        {
            try {
                wait();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        Request r = new Request(serverSide.main.ExecConst.Nstudents+1,'p');

        //Add a new service request to queue of pending requests (portion to be collected)
        try {
            pendingServiceRequestQueue.write(r);
        } catch (MemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        numberOfPendingRequests++;
        courseFinished = false;

        //Update chefs state
        ((Chef) Thread.currentThread()).setChefState(ChefState.DELIVERING_THE_PORTIONS);
        repo.setChefState(((Chef) Thread.currentThread()).getChefState());

        //Signal waiter that there is a pending request
        notifyAll();
    }

    /**
     * Operation look Around
     * 
     * It is called by the waiter, he checks for pending service requests and if not waits for them
     * 	@return Character the represents the service to be executed
     * 		'c' : means a client has arrived therefore needs to be presented with the menu by the waiter
     * 		'o' : means that the waiter will hear the order and deliver it to the chef
     * 		'p' : means that a portion needs to be delivered by the waiter
     * 		'b' : means that the bill needs to be prepared and presented by the waiter
     * 		'g' : means that some student wants to leave and waiter needs to say goodbye 
     */
    public synchronized char lookAround()
    {
        Request r;

        //While there are no pending request, waiter blocks
        while(numberOfPendingRequests == 0)
        {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        try 
        {
            r = pendingServiceRequestQueue.read();
            numberOfPendingRequests--;
        }
        catch (MemException e)
        {	
            e.printStackTrace();
            return 0;
        }		
        //Register student id in studentBeingAnswered
        System.out.println("Waiter attends student "+r.id+ " request");
        studentBeingAnswered = r.id;

        return r.type;
    }

    /**
     * Operation prepare the Bill
     */
    public synchronized void preprareBill()
    {
        //Update Waiter state
        ((Waiter) Thread.currentThread()).setWaiterState(WaiterState.PROCESSING_THE_BILL);
        repo.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());
    }

    /**
     * Operation say Goodbye
     */
    public synchronized boolean sayGoodbye()
    {
        //Student was greeted
        studentsGreeted[studentBeingAnswered] = true;
        //Wake up student that is waiting to be greeted by waiter
        notifyAll();

        numberOfStudentsAtRestaurant--;
        studentBeingAnswered = -1;

        repo.setWaiterState(((Waiter) Thread.currentThread()).getWaiterState());

        if(numberOfStudentsAtRestaurant == 0)
            return true;
        return false;
    }

    /**
     * Operation enter the restaurant
     */
    public void enter()
    {		
        synchronized(this)
        {
            int studentId = ((Student) Thread.currentThread()).getStudentID();
            students[studentId] = ((Student) Thread.currentThread());
            students[studentId].setStudentState(StudentState.GOING_TO_THE_RESTAURANT);

            numberOfStudentsAtRestaurant++;

            if(numberOfStudentsAtRestaurant == 1)
                tab.setFirstToArrive(studentId);
            else if (numberOfStudentsAtRestaurant == serverSide.main.ExecConst.Nstudents)
                tab.setLastToArrive(studentId);

            try {
                pendingServiceRequestQueue.write(new Request(studentId, 'c'));
            } catch (MemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            numberOfPendingRequests++;

            students[studentId].setStudentState(StudentState.TAKING_A_SEAT_AT_THE_TABLE);
            repo.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());
            repo.updateSeatsAtTable(numberOfStudentsAtRestaurant-1, studentId);


            //Signal waiter that there is a pending request
            notifyAll();
        }
        //Seat student at table and block it
        tab.seatAtTable();
    }

    /**
     * Operation call the waiter
     */
    public synchronized void callWaiter()
    {
        int studentId = ((Student) Thread.currentThread()).getStudentID();
        Request r = new Request(studentId,'o');

        //Add a new service request to queue of pending requests (portion to be collected)
        try {
            pendingServiceRequestQueue.write(r);
        } catch (MemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        numberOfPendingRequests++;	

        //Signal waiter that there is a pending request
        notifyAll();
    }

    /**
     * Operation signal the waiter
     */
    public synchronized void signalWaiter()
    {
        int studentId = ((Student) Thread.currentThread()).getStudentID();

        if(((Student) Thread.currentThread()).getStudentState() == StudentState.PAYING_THE_MEAL)
        {		
            //Add a new pending requests to the queue
            try {
                pendingServiceRequestQueue.write(new Request(studentId, 'b'));
            } catch (MemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            numberOfPendingRequests++;	

            //Signal waiter that there is a pending request
            notifyAll();

        }
        else
        {
            courseFinished = true;		
            //Wake chef up because he is waiting to tell waiter to collect portion and waiter so he can collect a new portion
            notifyAll();
        }
    }

    /**
     * Operation exit the restaurant
     */
    public synchronized void exit()
    {
        int studentId = ((Student) Thread.currentThread()).getStudentID();
        Request r = new Request(studentId,'g');

        //Add a new service request to queue of pending requests
        try {
            pendingServiceRequestQueue.write(r);
        } catch (MemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        numberOfPendingRequests++;
        //notify waiter that there is a pending request
        notifyAll();

        students[studentId].setStudentState(StudentState.GOING_HOME);
        repo.setStudentState(studentId, ((Student) Thread.currentThread()).getStudentState());


        //Block until waiter greets the student goodbye
        while(studentsGreeted[studentId] == false) {
            try {
                wait();
            } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Student "+studentId+" is leaving");
        }
        System.out.println("goodbye "+studentId);		
    }
    /**
    *   Operation server shutdown.
    *
    *   New operation.
    */
    public synchronized void shutdown() {
        nEntities += 1;
        if (nEntities >= ExecConst.Nstudents)
           ServerRestaurantBar.waitConnection = false;
    }
    
}

/**
 * 
 * Request Data type
 *
 */
class Request {
	
    /**
     * Id of the author of the request
     */
    public int id;

    /**
     * Request type
     */
    public char type;


    /**
     * Request Instantiation
     * @param id of the request
     * @param type of the request
     */
    public Request(int id, char type)
    {
        this.id = id;
        this.type = type;
    }
}


