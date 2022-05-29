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
        BarStub bar;													// remote reference to the bar
        TableStub table;
        Student[] students = new Student[ExecConst.Nstudents];
        GeneralReposStub genReposStub;						//remote reference to the general repository
        int genRepoServerPortNumb = -1;
        String genRepoServerHostName;
        


        /* Getting problem runtime parameters */
        if(args.length != 6) {
                GenericIO.writelnString ("Wrong number of parameters!");
                System.exit(1);
        }
        //Get bar parameters
        barServerHostName = args[0];
        try {
                barServerPortNum = Integer.parseInt (args[1]);
        } catch (NumberFormatException e) {
                GenericIO.writelnString ("args[1] is not a number!");
                System.exit(1);
        }
        if( (barServerPortNum < 22110) || (barServerPortNum > 22119) ) {
                GenericIO.writelnString ("args[1] is not a valid port number!");
                System.exit(1);			
        }

        //Get tab parameters
        tableServerHostName = args[2];
        try {
                tableServerPortNum = Integer.parseInt (args[3]);
        } catch (NumberFormatException e) {
                GenericIO.writelnString ("args[3] is not a number!");
                System.exit(1);
        }
        if( (tableServerPortNum < 22110) || (tableServerPortNum > 22119) ) {
                GenericIO.writelnString ("args[3] is not a valid port number!");
                System.exit(1);			
        }

        //Get general repo parameters
        genRepoServerHostName = args[4];
        try {
                genRepoServerPortNumb = Integer.parseInt (args[5]);
        } catch (NumberFormatException e) {
                GenericIO.writelnString ("args[5] is not a number!");
                System.exit(1);
        }
        if( (genRepoServerPortNumb < 22110) || (genRepoServerPortNumb > 22119) ) {
                GenericIO.writelnString ("args[5] is not a valid port number!");
                System.exit(1);			
        }


        /* problem initialisation */
        bar = new BarStub(barServerHostName, barServerPortNum);
        table = new TableStub(tableServerHostName, tableServerPortNum);
        genReposStub = new GeneralReposStub(genRepoServerHostName, genRepoServerPortNumb);
        for (int i = 0; i < ExecConst.Nstudents; i++)
                students[i] = new Student ("student_" + (i+1), i, bar, table);

        /* start simulation */
        for (int i = 0; i < ExecConst.Nstudents; i++) {
                GenericIO.writelnString ("Launching Student Thread "+i);
                students[i].start();
        }

        /* waiting for the end of the simulation */
        for(int i = 0; i < ExecConst.Nstudents; i++)
        {
                try {
                        students[i].join();
                }catch(InterruptedException e) {}
                GenericIO.writelnString ("The student"+(i+1)+" thread has terminated.");
        }
        genReposStub.shutdown();

    }
}