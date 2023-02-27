package com.udayan;

import java.util.ArrayList;
import java.util.List;

public class Process {

    // Adjacency list that will contain the pair of process number and event number to which it sends
    private int noOfEvents;


    private int[] clockTimeline;


    public Process(int eventCount) {
        noOfEvents = eventCount;
        clockTimeline = new int[eventCount];

//        Comment this loop if running Modified program
//        for (int i = 1; i <= eventCount; i++) {
//            clockTimeline[i - 1] = i;
//        }
    }

    public int[] getClockTimeline() {
        return clockTimeline;
    }

    public void setClockTimeline(int[] clockTimeline) {
        this.clockTimeline = clockTimeline;
    }

    public int getNoOfEvents() {
        return noOfEvents;
    }

    public void setNoOfEvents(int noOfEvents) {
        this.noOfEvents = noOfEvents;
    }

    @Override
    public String toString() {
        return "Process{" + "clock=" + ", noOfEvents=" + noOfEvents + '}';
    }
}
