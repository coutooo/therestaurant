/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commInfra;

/**
 * 
 * Request Data type
 *
 */
public class Request {
	
	/**
	 * Id of the author of the request
	 */
	public int id;
	
	/**
	 * Request type
	 */
	public char type;
	
	
	/**
	 * Request Instantiation
	 * @param id of the request
	 * @param type of the request
	 */
	public Request(int id, char type)
	{
		this.id = id;
		this.type = type;
	}
}