/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws.soap.train;

import java.io.Serializable;

/**
 *
 * @author hugoa
 */
public class Train implements Serializable {
    private String number;
    private String departureCity;
    private String arrivalCity;
    private String date;
    private int departureTime;
    private double ticketPrice;
    private int availableSeats;
    private String state;

    public Train(String number, String departureCity, String arrivalCity, String date, int departureTime,
            double ticketPrice, int availableSeats, String state) {
        this.number = number;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
        this.departureTime = departureTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.state = state;
    }

    public Train(String departureCity, String arrivalCity, String date, int departureTime, double ticketPrice,
            int availableSeats, String state) {
        this("", departureCity, arrivalCity, date, departureTime, ticketPrice, availableSeats, state);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
