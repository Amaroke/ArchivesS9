/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ws.soap.helloworld;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author hugoa
 */
@WebService(name = "helloworld")
public interface HelloWorldService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "HelloWorldSimple")
    public String HelloWorldSimple();

    @WebMethod(operationName = "HelloWorldParam")
    public String HelloWorldParam(@WebParam(name = "param") String param);
}
