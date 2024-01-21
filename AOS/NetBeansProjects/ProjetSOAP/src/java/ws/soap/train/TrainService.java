/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ws.soap.train;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;

/**
 *
 * @author hugoa
 */
@WebService(name = "trainservice")
public interface TrainService {

        /**
         * This is a sample web service operation
         */
        @WebMethod(operationName = "GetAllTrains")
        public List<Train> GetAllTrains();

        @WebMethod(operationName = "SearchTrains")
        public List<Train> SearchTrains(String departure, String destination, String departureDate, int departureTime);

        @WebMethod(operationName = "SearchTrainsAdmin")
        public List<Train> searchTrainsAdmin(String numeroTrain, String departure, String destination,
                        String departureDate, int departureTime);

        @WebMethod(operationName = "AddTrain")
        public void AddTrain(String departureCity, String arrivalCity, String date, int departureTime,
                        double ticketPrice,
                        int availableSeats, String state);

        @WebMethod(operationName = "UpdateTrain")
        public void UpdateTrain(String number, String departureCity, String arrivalCity, String date, int departureTime,
                        double ticketPrice, int availableSeats, String state);

        @WebMethod(operationName = "CancelTrain")
        public void CancelTrain(String trainNumber);

        @WebMethod(operationName = "DeleteTrain")
        public void DeleteTrain(String trainNumber);
}
