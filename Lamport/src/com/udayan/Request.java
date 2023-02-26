package com.udayan;


public class Request {

    private int senderProcess;
    private int senderEvent;
    private int receiverProcess;
    private int receiverEvent;

    public Request(int senderProcess, int senderEvent, int receiverProcess, int receiverEvent) {
        this.senderProcess = senderProcess;
        this.senderEvent = senderEvent;
        this.receiverProcess = receiverProcess;
        this.receiverEvent = receiverEvent;
    }

    public int getSenderProcess() {
        return senderProcess;
    }

    public void setSenderProcess(int senderProcess) {
        this.senderProcess = senderProcess;
    }

    public int getSenderEvent() {
        return senderEvent;
    }

    public void setSenderEvent(int senderEvent) {
        this.senderEvent = senderEvent;
    }

    public int getReceiverProcess() {
        return receiverProcess;
    }

    public void setReceiverProcess(int receiverProcess) {
        this.receiverProcess = receiverProcess;
    }

    public int getReceiverEvent() {
        return receiverEvent;
    }

    public void setReceiverEvent(int receiverEvent) {
        this.receiverEvent = receiverEvent;
    }

    @Override
    public String toString() {
        return "Request{" +
                "senderProcess=" + senderProcess +
                ", senderEvent=" + senderEvent +
                ", receiverProcess=" + receiverProcess +
                ", receiverEvent=" + receiverEvent +
                '}';
    }
}
