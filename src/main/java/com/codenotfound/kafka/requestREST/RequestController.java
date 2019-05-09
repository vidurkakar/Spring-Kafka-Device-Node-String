package com.codenotfound.kafka.requestREST;

import com.codenotfound.kafka.model.Request;
import com.codenotfound.kafka.nodeMakeRequest.SendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RequestController {
    private static final String template = "Hello, %s!";
    private final AtomicLong service1Counter = new AtomicLong();
    private final AtomicLong service2Counter = new AtomicLong();

    @Autowired
    SendRequest sender;
//    @RequestMapping("/greeting")
//    public String greeting(@RequestParam(value="request", defaultValue="cloudNodeReq#DeviceNode#HelloFromDeviceNode#deviceNodeResp1") String request) {
////        return new Greeting(counter.incrementAndGet(),
////                String.format(template, name));
//        sender.send("cloudNodeReq","cloudNodeReq#DeviceNode#HelloFromDeviceNode#deviceNodeResp1" );
//        return ("Received Request" + request);
//    }
//
//    @RequestMapping("/deviceNodeReq2")
//    public String deviceNodereq2(@RequestParam(value="request", defaultValue = "cloudNodeReq#DeviceNode2#HelloFromDeviceNode2#deviceNodeResp2") String request2){
//        sender.send("cloudNodeReq", "cloudNodeReq#DeviceNode2#HelloFromDeviceNode2#deviceNodeResp2");
//        return ("Received Request" + request2);
//    }
    @RequestMapping("/service1")
    public String service1(@RequestParam(value = "request", defaultValue = "cloudNodeReq#DeviceNode2#HelloFromDeviceNode2#deviceNodeResp2") String requestQuery){
        System.out.println("requestQuery: "+ requestQuery);
        Request request =new Request();
        String payload[] = requestQuery.split("#");
        if(payload.length ==4) {
            request.setrequestSentTo(payload[0]);
            request.setRequestSentBy(payload[1]);
            request.setRequestValue(payload[2]);
            request.setResponseGivenBackTo(payload[3]);
            request.setRequestNumber(String.valueOf(service1Counter.getAndIncrement()));
        }
        sender.send(request);
        return ("Device Node Makes a request to cloud node. The request is " + requestQuery);
        //Sending To
        //Sent By
        //Value
        //ResponseToBeGivenTo
    }

    @RequestMapping("/service2")
    public String service2(@RequestParam(value = "request", defaultValue = "edgeNodeReq1#DeviceNode2#HelloFromDeviceNode2#deviceNodeResp2") String requestQuery){

        Request request =new Request();
        String payload[] = requestQuery.split("#");
        if(payload.length ==4) {
            request.setrequestSentTo(payload[0]);
            request.setRequestSentBy(payload[1]);
            request.setRequestValue(payload[2]);
            request.setResponseGivenBackTo(payload[3]);
            request.setRequestNumber(String.valueOf(service2Counter.getAndIncrement()));
        }
        sender.send(request);
        return ("Device Node Makes a request to edge node. The request is " + requestQuery);
        //Sending To
        //Sent By
        //Value
        //ResponseToBeGivenTo
    }
    @RequestMapping("/service3")
    public String service3(@RequestParam(value = "request", defaultValue = "deviceNodeReq1#DeviceNode2#HelloFromDeviceNode2#deviceNodeResp2") String requestQuery){
        Request request =new Request();
        String payload[] = requestQuery.split("#");
        if(payload.length ==4) {
            request.setrequestSentTo(payload[0]);
            request.setRequestSentBy(payload[1]);
            request.setRequestValue(payload[2]);
            request.setResponseGivenBackTo(payload[3]);
            request.setRequestNumber(String.valueOf(service2Counter.getAndIncrement()));
        }
        sender.send(request);
        return ("Device Node Makes a request to edge node. The request is " + requestQuery);
        //Sending To
        //Sent By
        //Value
        //ResponseTooBeGivenT
    }


}
