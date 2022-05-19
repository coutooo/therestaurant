/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package therestaurant;
import java.util.Date;
import sharedRegions.*;
import InterveningEntities.*;
import genclass.GenericIO;

/**
 *  Simulation of the Problem of The restaurant
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
public class TheRestaurant {

    /**
     * Number of Students
     */
    public static final int Nstudents = 7;
    
    /**
     * Number of Courses  (three courses per participant in the
     * dinner: a starter, a main course and a dessert.)
     */
    public static final int Ncourses = 3;
    
    /**
    * Repository log filename
    */
    public static final String filename = "log_" + new Date().toString().replace(' ', '_') + ".txt";
    
    /**
   *    Main method.
   *
   *    @param args runtime arguments
   */
    public static void main(String[] args) {
        
        Chef chef;						//Reference to the Chef Thread
        Waiter waiter;                                          //Reference to the Waiter Thread
        Student[] student = new Student[Nstudents];    		//array of references to the Students Threads
        Bar b;							//Reference to the Bar
        Kitchen k;						//Reference to the Kitchen
        Table t;						//Reference to the Table
        GeneralRepos repos;                                     //Reference to the General Repository

        
        /* problem initialization */
        GenericIO.writelnString ("\n" + "      Problem of the restaurant\n");
      
        repos = new GeneralRepos("logger");
        t = new Table(repos);
        k = new Kitchen(repos);
        b = new Bar(repos,t);

        chef = new Chef("Chef_1", k, b);
        waiter = new Waiter("Waiter_1", k, b, t);
        for (int i = 0; i < Nstudents; i++)
            student[i] = new Student("Student_"+(i+1), i, b, t);
        
        /* start of the simulation */
        chef.start();
        waiter.start();
        for (int i = 0; i < Nstudents; i++)
        	student[i].start ();
        
        /* waiting for the end of the simulation */
        
        GenericIO.writelnString ();
        for (int i = 0; i < Nstudents; i++)
        { try
            { student[i].join ();
            }
        catch (InterruptedException e) {}
        GenericIO.writelnString ("The Student "+(i+1)+" just terminated");
        }
        
        try {
			chef.join();
		} catch (InterruptedException e) {}
        GenericIO.writelnString ("The chef has terminated");
        
        try {
        	waiter.join();	
		} catch (InterruptedException e) {}
        GenericIO.writelnString ("The waiter has terminated");

        GenericIO.writelnString("End of the Simulation");
    }
    
}
