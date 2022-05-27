/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.main;

import clientSide.entities.Student;
import clientSide.stubs.BarStub;
import clientSide.stubs.GeneralReposStub;
import clientSide.stubs.TableStub;
import genclass.GenericIO;

/**
 *    Client side of the Assignment 2 - Student.
 *    Static solution Attempt (number of threads controlled by global constants - ExecConst)
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 * 
 *  @author Rafael Dias
 *  @author Manuel Couto
 */
public class StudentMain {
		
    public static void main(String[] args) {	
        String barServerHostName;                               // name of the platform where is located the barber shop server
        int barServerPortNum = -1;                             // port number for listening to service requests
        String tableServerHostName;
        int tableServerPortNum = -1;
        String genReposServerHostName;                                 // name of the platform where is located the general repository server
        int genReposServerPortNum = -1;                               // port number for listening to service requests                                    
        BarStub bar;													// remote reference to the bar
        TableStub table;
        Student[] students = new Student[ExecConst.Nstudents];
        GeneralReposStub genReposStub;								// remote reference to the general repository


        /* getting problem runtime parameters */

        if (args.length != 6)
            { GenericIO.writelnString("Wrong number of parameters!");
              System.exit (1);
            }
        barServerHostName = args[0];
        try
        { barServerPortNum = Integer.parseInt (args[1]);
        }
        catch (NumberFormatException e)
        { GenericIO.writelnString ("args[1] is not a number!");
          System.exit (1);
        }
        if ((barServerPortNum < 4000) || (barServerPortNum >= 65536))
           { GenericIO.writelnString ("args[1] is not a valid port number!");
             System.exit(1);
           }

        tableServerHostName = args[2];
        try {
            tableServerPortNum = Integer.parseInt(args[3]);
        } catch(NumberFormatException e) {
            GenericIO.writelnString("args[3] is not a valid port number!");
            System.exit(1);
        }
        if ((tableServerPortNum < 4000) || (tableServerPortNum >= 65536)) {
                  GenericIO.writelnString ("args[3] is not a valid port number!");
                  System.exit (1);
        }

        genReposServerHostName = args[4];
        try
        { genReposServerPortNum = Integer.parseInt (args[5]);
        }
        catch (NumberFormatException e)
        { GenericIO.writelnString ("args[5] is not a number!");
          System.exit (1);
        }
        if ((genReposServerPortNum < 4000) || (genReposServerPortNum >= 65536)) {
            GenericIO.writelnString ("args[5] is not a valid port number!");
            System.exit (1);
        }

        //Initialization

        bar = new BarStub(barServerHostName, barServerPortNum);
        table = new TableStub(tableServerHostName, tableServerPortNum);
        genReposStub = new GeneralReposStub(genReposServerHostName, genReposServerPortNum);

        for(int i=0; i<ExecConst.Nstudents; i++){
            students[i] = new Student("Student_"+i, i, bar, table);
        }


        // Start of simulation
        for(int i=0; i<ExecConst.Nstudents; i++){
            students[i].start();
        }

        for (int i=0; i<ExecConst.Nstudents; i++){
            while(students[i].isAlive()) {
//                  bar.endOperation();
//                  table.endOperation();
                Thread.yield();
                try {
                        students[i].join();
                } catch (InterruptedException e) {
                        System.out.print("Error occured while executing Student "+i);
                }
                GenericIO.writelnString("The student "+(i+1)+" has terminated.");
                }
                GenericIO.writelnString();
                bar.shutdown();
                table.shutdown();
                genReposStub.shutdown();
        }
    }
}