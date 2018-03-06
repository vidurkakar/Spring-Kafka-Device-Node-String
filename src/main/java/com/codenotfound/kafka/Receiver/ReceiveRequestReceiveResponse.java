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

import static com.codenotfound.kafka.Base64.Java8Base64Image.decoder;

public class ReceiveRequestReceiveResponse {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiveRequestReceiveResponse.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }


    public static long timerStart =System.currentTimeMillis();


    @Autowired
    SendResponseForReceivedRequest sendResponseForReceivedRequest;

    //private static final String VOICE_NAME_KEVIN = "kevin16";


    @KafkaListener(topics = "deviceNodeReq2")
    public void receive(String query) {

        //DisplayRequest displayRequest = new DisplayRequest();
        //displayRequest.createWindow(query);
        System.out.println(query);
        String payload[] = query.split("#");
        Request request = new Request();
        ProcessRequest processRequest = new ProcessRequest();
        Response response = new Response();
        //decoder(payload[2], "C:\\Users\\Anand J Bangad\\Documents\\DecodedImage\\" + Integer.toString(counter) + ".jpg");
        if (payload.length == 5) {
            request.setrequestSentTo(payload[0]);
            request.setRequestSentBy(payload[1]);
            request.setRequestValue(payload[2]);
            request.setResponseGivenBackTo(payload[3]);
            request.setRequestNumber(payload[4]);
        }
        response = processRequest.requestProcess(request);
        //TextToSpeechConvertor textToSpeechConvertor = new TextToSpeechConvertor();
        //textToSpeechConvertor.speak("Got Request on Edge Node 1" + request.getRequestValue());
        sendResponseForReceivedRequest.send(response);


    }
    @KafkaListener(topics = "deviceNodeResp2")
  public void receive3(String responseResult ){
       // TextToSpeechConvertor textToSpeechConvertor = new TextToSpeechConvertor();
        String responsePayload[] = responseResult.split("#");
        //
        // textToSpeechConvertor.speak("Response Got Back" + payload[1] + "Processed By" + payload[2]);
        //LOGGER.info("received payload = '{}'", responseResult);
        //DisplayResponse displayResponse = new DisplayResponse();
        //displayResponse.createWindow("Response Received from " +responsePayload[2] +" for RequestNumber { " + responsePayload[3] + " }" + " and response is = " + responsePayload[1] );

        LOGGER.info("Response Received from = '{}' for RequestNumber '{}' and response is = '{}'",responsePayload[2],responsePayload[3],responsePayload[1]);
  }

}
