/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws.soap.helloworld;

import javax.jws.WebService;

/**
 *
 * @author hugoa
 */
@WebService(endpointInterface = "ws.soap.helloworld.HelloWorldService", serviceName = "helloworld", portName = "HelloWorldPort")
public class HelloWorldServiceImpl implements HelloWorldService {

    /**
     * Web service operation
     */
    @Override
    public String HelloWorldSimple() {
        return "Hello World !";
    }

    @Override
    public String HelloWorldParam(String param) {
        return "Hello " + param + " !";
    }
}