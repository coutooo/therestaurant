/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sharedRegions;

import InterveningEntities.*;
import static java.lang.Thread.sleep;

/**
 *
 * @author couto
 */
public class Kitchen {
    
    private static int servedPortions;   // numero de porcoes servidas
    
    private static int NCourse;         // numero de pratos servidos

    public static synchronized void startPreparation() {
        ((Chief) Thread.currentThread ()).setChiefState(ChiefState.PREPARING_THE_COURSE);
    }

    public static void proceedToPresentation() {
        
        ((Chief) Thread.currentThread ()).setChiefState(ChiefState.DISHING_THE_PORTIONS);
    }

    public static void alertTheWaiter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void continuePreparation() {
        
    }

    public static boolean allPortionsDelivered() {
        if (servedPortions == 7)
        {
            servedPortions = 0;
            NCourse = NCourse + 1;
            return true;
        }
        return false;
    }

    public static void haveNextPortionReady() {
        servedPortions = servedPortions + 1;    
    }

    public static void cleanUp() {
        ((Chief) Thread.currentThread ()).setChiefState(ChiefState.CLOSING_SERVICE);
    }
    
    public static void watchTheNews(){
    try
    { sleep ((long) (1 + 100 * Math.random ()));
    }
    catch (InterruptedException e) {}
}
    
    public static int getNCourses(){
        return NCourse;
    }
    
}
