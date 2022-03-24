/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterveningEntities;

/**
 * Enum with the possible states of the student on his lifecycle.
 * @author Rafael Dias
 * @author Manuel Couto
 */
    
public enum StudentState { 
    /**
     * 
     */
    GOING_TO_THE_RESTAURANT ("GoR"),

    /**
     * 
     */
    TAKING_A_SEAT_AT_THE_TABLE ("TakS"),

    /**
     * 
     */
    SELECTING_THE_COURSES ("SelC"),

    /**
     * 
     */
    ORGANIZING_THE_ORDER ("OrgO"),

    /**
     * 
     */
    CHATTING_WITH_COMPANIONS ("ChatC"),
    
    /**
     * 
     */
    ENJOYING_THE_MEAL ("EnjoyM"),
    
    /**
     * 
     */
    PAYING_THE_MEAL ("PayM"),
    /**
     * 
     */
    GOING_HOME ("GoH");

    private final String description;

    private StudentState(String description){
        this.description = description;
    }

    @Override
    public String toString(){
        return this.description;
    }
}
