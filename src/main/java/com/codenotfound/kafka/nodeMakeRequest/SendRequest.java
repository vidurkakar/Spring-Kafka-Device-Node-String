package com.codenotfound.kafka.nodeMakeRequest;

import com.codenotfound.kafka.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendRequest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SendRequest.class);

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void send(Request request){
    String requestPayload;
    requestPayload = request.getrequestSentTo()+"#"+request.getRequestSentBy()+"#"+request.getRequestValue()+"#"+request.getResponseGivenBackTo()+"#"+request.getRequestNumber();
    kafkaTemplate.send(request.getrequestSentTo(), requestPayload);
  }
}
