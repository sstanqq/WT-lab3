package main.java.client.dto;

import client.model.Response;

import java.io.Serializable;

public class StudentResponseDTO implements Serializable {
    private Response Response;
    private Object body;

    public client.model.Response getResponse() {
        return Response;
    }

    public void setResponse(Response Response) {
        this.Response = Response;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
