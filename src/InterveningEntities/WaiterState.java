/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

/**
 * Enum with the possible states of the waiter on his lifecycle.
 * @author Rafael Dias
 * @author Manuel Couto
 */
    
public enum WaiterState { 
    /**
     * 
     */
    APPRAISING_SITUATION ("ApprS"),

    /**
     * 
     */
    PRESENTING_THE_MENU ("PresM"),

    /**
     * 
     */
    TAKING_THE_ORDER ("TakO"),

    /**
     * 
     */
    PLACING_THE_ORDER ("PlacO"),

    /**
     * 
     */
    WAITING_FOR_PORTION ("WaitP"),
    
    /**
     * 
     */
    PROCESSING_THE_BILL ("ProcesB"),
    
    /*
    *
    */
    RECEIVING_PAYMENT ("RecP");
    
    private final String description;

    private WaiterState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
