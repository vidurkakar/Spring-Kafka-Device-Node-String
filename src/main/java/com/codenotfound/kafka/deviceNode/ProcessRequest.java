package com.codenotfound.kafka.deviceNode;

import com.codenotfound.kafka.model.Request;
import com.codenotfound.kafka.model.Response;

public class ProcessRequest {
    public Response requestProcess (Request request){
        Response response = new Response();
        response.setResult(request.getRequestValue() + "Device Node1 Key deviceNode1");
        response.setProcessedBy("DeviceNode1");
        response.setSendingTo(request.getResponseGivenBackTo());
        return response;
    }
}
