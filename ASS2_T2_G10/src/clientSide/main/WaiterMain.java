/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clientSide.main;

import clientSide.entities.Waiter;
import clientSide.stubs.BarStub;
import clientSide.stubs.KitchenStub;
import clientSide.stubs.TableStub;

/**
 *
 * @author rafae
 */
public class WaiterMain {
    /**
     *    Main method.
     *
     *    @param args runtime arguments
     */	
    public static void main(String[] args) {
        Waiter waiter;                                          //Reference to the Waiter Thread		
        
        BarStub bar;						//Reference to the Bar
        KitchenStub kitchen;					//Reference to the Kitchen
        TableStub table;                                        //Reference to the Table
        
        bar = new BarStub(" ", 22150);
        kitchen = new KitchenStub(" ", 22151);
        table = new TableStub(" ", 22152);
        
        waiter = new Waiter("Waiter_1", kitchen, bar, table);  
        
        /* start thread */
        waiter.start ();
        
        /* wait for the end */
        try
        { waiter.join ();
        }
        catch (InterruptedException e) {}
        System.out.println("The Waiter 1 just terminated");
        
        System.out.println("End of the Simulation");
        
	}
}
