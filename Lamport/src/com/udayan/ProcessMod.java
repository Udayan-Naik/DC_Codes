package com.udayan;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ProcessMod extends Process {

    private Queue<Request> sendList;
    private Queue<Request> receiveList;
    private int clock;


    public ProcessMod(int eventCount) {
        super(eventCount);
        sendList = new PriorityQueue<>(Comparator.comparing(Request::getSenderEvent));
        receiveList = new PriorityQueue<>(Comparator.comparing(Request::getReceiverEvent));
        clock = 0;
    }


    public Queue<Request> getSendList() {
        return sendList;
    }

    public void setSendList(Queue<Request> sendList) {
        this.sendList = sendList;
    }

    public Queue<Request> getReceiveList() {
        return receiveList;
    }

    public void setReceiveList(Queue<Request> receiveList) {
        this.receiveList = receiveList;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }
}
