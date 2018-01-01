package com.codenotfound.kafka.Receiver;

import com.codenotfound.kafka.deviceNode.ProcessRequest;
import com.codenotfound.kafka.model.Request;
import com.codenotfound.kafka.model.Response;
import com.codenotfound.kafka.nodeMakeResponse.SendResponseForReceivedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

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

    @KafkaListener(topics = "deviceNodeReq1")
    public void receive(String query) {
        System.out.println(query);
        String payload[] = query.split("#");
        Request request = new Request();
        ProcessRequest processRequest = new ProcessRequest();
        Response response = new Response();
        if (payload.length == 4) {
            request.setrequestSentTo(payload[0]);
            request.setRequestSentBy(payload[1]);
            request.setRequestValue(payload[2]);
            request.setResponseGivenBackTo(payload[3]);
        }
        response = processRequest.requestProcess(request);
        sendResponseForReceivedRequest.send(response);

    }
    @KafkaListener(topics = "deviceNodeResp1")
  public void receive3(String payload){
    LOGGER.info("received payload = '{}'", payload);
  }

}
