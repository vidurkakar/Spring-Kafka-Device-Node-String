package com.codenotfound.kafka.Receiver;

import com.codenotfound.kafka.deviceNode.ProcessRequest;
import com.codenotfound.kafka.model.Request;
import com.codenotfound.kafka.model.Response;
import com.codenotfound.kafka.nodeMakeResponse.SendResponseForReceivedRequest;
import com.codenotfound.kafka.osdkLinker.LLib;
import com.sun.jna.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

public class ReceiveRequestReceiveResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveRequestReceiveResponse.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }


    @Autowired
    SendResponseForReceivedRequest sendResponseForReceivedRequest;

    private static final String VOICE_NAME_KEVIN = "kevin16";

    @KafkaListener(topics = "deviceNodeReq2")
    public void receive(String query) {
        DisplayRequest displayRequest = new DisplayRequest();
        displayRequest.createWindow(query);
        System.out.println(query);
        String payload[] = query.split("#");
        Request request = new Request();
        ProcessRequest processRequest = new ProcessRequest();
        Response response = new Response();
        if (payload.length == 5) {
            request.setrequestSentTo(payload[0]);
            request.setRequestSentBy(payload[1]);
            request.setRequestValue(payload[2]);
            request.setResponseGivenBackTo(payload[3]);
            request.setRequestNumber(payload[4]);
        }
        LOGGER.info("Request Received from = '{}' with paylaod '{}' and response sent back to = '{}'",request.requestSentBy,request.getRequestValue(),request.getResponseGivenBackTo());
        //Added to process OSDK Commands
        String commandPayload[]=request.requestValue.split(":");
        System.out.println("commandPayload[0]"+commandPayload[0]);
        System.out.println("commandPayload[1]"+commandPayload[1]);
        if(commandPayload[0].equalsIgnoreCase("OSDK"))
        {
            System.out.println("IN OSDK IF STATEMENT");
           if(commandPayload[1].equalsIgnoreCase("initialize"))
           {System.out.println("Drone Before Initalized in JAVA");
               LLib.instance.intialize_C();
           System.out.println("Drone Initalized in JAVA");}
           if(commandPayload[1].equalsIgnoreCase("takeoff"))
           {LLib.instance.droneTakeOff_C();
           System.out.println("Drone Takeoff in JAVA");}
           if(commandPayload[1].equalsIgnoreCase("move"))
           {LLib.instance.droneMove_C(Float.parseFloat(commandPayload[2]),Float.parseFloat(commandPayload[3]),Float.parseFloat(commandPayload[4]),Float.parseFloat(commandPayload[5]));
           System.out.println("Drone Move in JAVA with arguments: "+Float.parseFloat(commandPayload[2])+","+Float.parseFloat(commandPayload[3])+","+Float.parseFloat(commandPayload[4])+","+Float.parseFloat(commandPayload[5]));}
           if(commandPayload[1].equalsIgnoreCase("land"))
           {LLib.instance.droneLand_C();
           System.out.println("Drone Land in JAVA");}

        }




        response = processRequest.requestProcess(request);


        sendResponseForReceivedRequest.send(response);

    }
    @KafkaListener(topics = "deviceNodeResp2")
  public void receive3(String responseResult ){

        String responsePayload[] = responseResult.split("#");

        //LOGGER.info("received payload = '{}'", responseResult);
        DisplayResponse displayResponse = new DisplayResponse();
        displayResponse.createWindow("Response Received from " +responsePayload[2] +" for RequestNumber { " + responsePayload[3] + " }" + " and response is = " + responsePayload[1] );

        LOGGER.info("Response Received from = '{}' for RequestNumber '{}' and response is = '{}'",responsePayload[2],responsePayload[3],responsePayload[1]);
  }

}
