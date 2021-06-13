package com.company;


import java.security.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    private static final int serviceCount = 5;
    private static final int requestorCount = 5;

    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        List<Service> services = services(queue,serviceCount); 
        List<Requestor> requestors = requestors(queue,requestorCount);
        start(services);
        start(requestors);
    }
    private static <TParticipant extends Participant> List<TParticipant> participants(
            MessageQueue queue, int participantCount, Function<MessageQueue,TParticipant> create){
        List<TParticipant> participants = new ArrayList<>();
        for(int i =0; i<participantCount; i++){
            TParticipant participant = create.apply(queue);
            participant.add(participant);
        }
        return participants;
    }

    private static List<Service> services(MessageQueue queue,int serviceCount){
        return participants(queue,serviceCount, Provider.Service::new);
    }
    private  static  List<Requestor> requestors(MessageQueue queue,int requestorCount){
        return participants(queue,requestorCount,Requestor::new);
    }
    private  static <TParticipant extends  Participant> void start(List<TParticipant> participants){
        participants.stream().forEach(Main::start);
    }
    private static <TParticipant extends Participant> void start(TParticipant participant){

    }

}
