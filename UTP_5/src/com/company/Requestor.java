package com.company;

public class Requestor extends Participant {

    private MessageQueue response;

    public Requestor(MessageQueue queue){
        super(queue);
        this.response = new MessageQueue();
    }
    public void run(){
        System.out.println("running" + this);
        while (true){
            queueRequest();
            boolean responseConsumed = false;
            while (!responseConsumed){
                Response response = dequeResponse();
                if(response != null){
                    queue.queueMessage(response);
                    responseConsumed = true;
                }
                sleep();
            }
        }
    }
    public MessageQueue responce(){
        return response;
    }
    private void queueRequest(){
        Request request = createRequest();
        queue.queueMessage(request);
    }
    private Request createRequest(){
        Request request = new Request(this);
        return  request;
    }
    private Response dequeResponse(){
        return response.dequeueResponse(this);
    }
}
