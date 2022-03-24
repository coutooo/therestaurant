/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

/**
 * Enum with the possible states of the chief on his lifecycle.
 * @author Rafael Dias
 * @author Manuel Couto
 */
    
public enum ChiefState { 
    /**
     * 
     */
    WAITING_FOR_AN_ORDER ("WaitO"),

    /**
     * 
     */
    PREPARING_THE_COURSE ("PrepC"),

    /**
     * 
     */
    DISHING_THE_PORTIONS ("DishP"),

    /**
     * 
     */
    DELIVERING_THE_PORTIONS ("DelivP"),

    /**
     * 
     */
    CLOSING_SERVICE ("ClosS");

    private final String description;

    private ChiefState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}

