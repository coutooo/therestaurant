/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sharedRegions;

import genclass.GenericIO;
import genclass.TextFile;
import java.util.Objects;
/**
 * 
 * 
 * @author Rafael Dias
 * @author Manuel Couto
 */
class GeneralRepos {
    
    
    
    
    
    public synchronized void setChefState (int state){
        switch(state) {
            
        }
        chefState = state;
        reportStatus();
    }

    private void reportStatus() {
        
    }
}
