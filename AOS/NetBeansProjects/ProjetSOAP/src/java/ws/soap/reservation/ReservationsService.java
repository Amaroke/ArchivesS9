/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ws.soap.reservation;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author hugoa
 */
@WebService(name = "ReservationService")
public interface ReservationsService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "getAllReservations")
    List<Reservation> getAllReservations(String memberEmail);
}
