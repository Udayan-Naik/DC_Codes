package com.udayan;

import java.util.ArrayList;
import java.util.List;

public class Process {

    // Adjacency list that will contain the pair of process number and event number to which it sends
    private int clock;
    private int noOfEvents;


    private int[] clockTimeline;


    public Process(int eventCount) {
        noOfEvents = eventCount;
        clock = 0;
        clockTimeline = new int[eventCount];
        for (int i = 1; i <= eventCount; i++) {
            clockTimeline[i - 1] = i;
        }
    }

    public int[] getClockTimeline() {
        return clockTimeline;
    }

    public void setClockTimeline(int[] clockTimeline) {
        this.clockTimeline = clockTimeline;
    }

    @Override
    public String toString() {
        return "Process{" +
                "clock=" + clock +
                ", noOfEvents=" + noOfEvents +
                '}';
    }
}
