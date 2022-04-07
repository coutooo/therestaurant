/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package therestaurant;
import java.util.Date;
import sharedRegions.*;
import InterveningEntities.*;

/**
 *
 * @author Couto and Rafinha
 */
public class TheRestaurant {

    /**
     * Number of Students
     */
    public static final int Nstudents = 7;
    
    /**
     * Number of Courses  (three courses per participant in the
dinner: a starter, a main course and a dessert.)
     */
    public static final int Ncourses = 3;
    
    /**
    * Repository log filename
    */
    public static final String filename = "log_" + new Date().toString().replace(' ', '_') + ".txt";
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Shared memory regions
        Bar bar = new Bar();
        Kitchen kitchen = new Kitchen();
        Table table = new Table();
        
        // Entities
        Student[] students = new Student[Nstudents];
        
        
        
    }
    
}
