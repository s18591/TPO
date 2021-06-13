package com.company;

import java.util.PriorityQueue;
import java.util.Queue;

public class MessageQueue {
    private final Queue<Message> priorityQueue;

    public MessageQueue(){
        priorityQueue = new PriorityQueue<Message>();
    }

    public synchronized void queueMessage(Message message){
        priorityQueue.offer(message);
    }
    public synchronized Request dequeueRequest(){
        Request request = null;
        Message message = priorityQueue.peek();
        if(message instanceof Request){
            request = (Request) priorityQueue.poll();
        }
        return request;
    }

    public synchronized Response dequeueResponse(Requestor requestor){
        Response response = null;
        Message message = priorityQueue.peek();
        if(message instanceof Response && message.getRequestor() == requestor){
            response = (Response) priorityQueue.poll();
        }
        return response;
    }
    public synchronized Response dequeueResponse(){
        return (Response) priorityQueue.poll();
    }
}
