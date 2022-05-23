/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.main;

import clientSide.entities.Student;
import clientSide.stubs.BarStub;
import clientSide.stubs.KitchenStub;
import clientSide.stubs.TableStub;

/**
 *    Client side of the Assignment 2 - Student.
 *    Static solution Attempt (number of threads controlled by global constants - ExecConst)
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */
public class StudentMain {
    
    /**
     *    Main method.
     *
     *    @param args runtime arguments
     */
    
    public static void main(String[] args) {
	Student[] student = new Student[ExecConst.Nstudents];   //array of references to the Students Threads
		
	BarStub bar;						//Reference to the Bar
        KitchenStub kitchen;					//Reference to the Kitchen
        TableStub table;                                        //Reference to the Table
        
        bar = new BarStub(" ", 22150);
        kitchen = new KitchenStub(" ", 22151);
        table = new TableStub(" ", 22152);
        
        for (int i = 0; i < ExecConst.Nstudents; i++)
            student[i] = new Student("Student_"+(i+1), i, bar, table);
        
        
        /* start threads */
        for (int i = 0; i < ExecConst.Nstudents; i++)
        	student[i].start ();
        
        
        /* wait for the end */
        for (int i = 0; i < ExecConst.Nstudents; i++)
        { try
        { student[i].join ();
        }
        catch (InterruptedException e) {}
        System.out.println("The Student "+(i+1)+" just terminated");
        }
        
        System.out.println("End of the Simulation");
        
	}

}
