package com.udayan;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int noOfProcesses = sc.nextInt();
        List<Process> processes = new ArrayList<>();
        for(int i = 0; i < noOfProcesses; i++) {
            System.out.print("Events in process " + (i + 1)+ ": ");
            int events = sc.nextInt();
            Process p = new Process(events);
            processes.add(p);
        }


        Queue<Request> requests = new ArrayDeque<>();

        sc.nextLine();
        System.out.print("Do you want add requests (Y / N): ");
        boolean terminate = sc.nextLine().equalsIgnoreCase("Y") ? false: true;
        while(!terminate) {
            System.out.print("Sender Process: ");
            int senderP = sc.nextInt();
            System.out.print("Sender Event: ");
            int senderE = sc.nextInt();

            System.out.print("Receiver Process: ");
            int receiverP = sc.nextInt();
            System.out.print("Receiver Event: ");
            int receiverE = sc.nextInt();

            Request r = new Request(senderP, senderE, receiverP, receiverE);
            requests.offer(r);
            sc.nextLine();
            System.out.print("Do you want add requests (Y / N): ");
            terminate = sc.nextLine().equalsIgnoreCase("Y") ? false: true;
        }


        while(!requests.isEmpty()){
            Request r = requests.poll();
            processRequest(r, processes);
        }

        for(Process p : processes) {
            System.out.println(Arrays.toString(p.getClockTimeline()));
        }

    }

    public static void processRequest(Request r, List<Process> processes) {

        int recProcessIndex = r.getReceiverProcess() - 1;

        int[] recProcessTimeline = processes.get(recProcessIndex).getClockTimeline();

        int sendProcessIndex = r.getSenderProcess() - 1;

        int[] sendProcessTimeline = processes.get(sendProcessIndex).getClockTimeline();

        recProcessTimeline[r.getReceiverEvent() - 1] = Math.max(recProcessTimeline[r.getReceiverEvent() - 2], sendProcessTimeline[r.getSenderEvent() - 1]) + 1;

        cascadeUpdate(recProcessTimeline, r.getReceiverEvent() - 1);
    }

    public static void cascadeUpdate(int[] timeLine, int index) {

        for(int i = index + 1; i < timeLine.length; i++) {
            timeLine[i] = timeLine[i - 1] + 1;
        }
    }


}
