/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ws.soap.connection;

import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author hugoa
 */
@WebService(name = "ConnectionService")
public interface ConnectionService {

    /**
     * This is a sample web service operation
     * 
     * @param email
     * @param password
     * @return
     */
    @WebMethod(operationName = "Login")
    public boolean Login(String email, String password);

    @WebMethod(operationName = "createReservation")
    public boolean createReservation(String trainNumber, String email);
}
