package com.udayan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreadMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int noOfProcesses = sc.nextInt();
        List<ProcessMod> processes = new ArrayList<>();
        for (int i = 0; i < noOfProcesses; i++) {
            System.out.print("Events in process " + (i + 1) + ": ");
            int events = sc.nextInt();
            ProcessMod p = new ProcessMod(events);
            processes.add(p);
        }
        sc.nextLine();
        for (int i = 0; i < noOfProcesses; i++) {

            System.out.print("Do you want add sent requests for process " + (i + 1) + " (Y / N): ");
            boolean terminate = sc.nextLine().equalsIgnoreCase("Y") ? false : true;
            ProcessMod processLoop = processes.get(i);
            while (!terminate) {
                System.out.print("Sender Event: ");
                int senderE = sc.nextInt();
                System.out.print("Receiver Process: ");
                int receiverP = sc.nextInt();
                System.out.print("Receiver Event: ");
                int receiverE = sc.nextInt();

                Request r = new Request((i + 1), senderE, receiverP, receiverE);
                processLoop.getSendList().add(r);
                sc.nextLine();
                System.out.print("Do you want add more requests: ");
                terminate = sc.nextLine().equalsIgnoreCase("Y") ? false : true;
            }
            System.out.print("Do you want add received requests (Y / N): ");
            terminate = sc.nextLine().equalsIgnoreCase("Y") ? false : true;
            while (!terminate) {
                System.out.print("Sender Process: ");
                int senderP = sc.nextInt();
                System.out.print("Sender Event: ");
                int senderE = sc.nextInt();
                System.out.print("Receiver Event: ");
                int receiverE = sc.nextInt();

                Request r = new Request(senderP, senderE, (i + 1), receiverE);
                processLoop.getReceiveList().add(r);
                sc.nextLine();
                System.out.print("Do you want add more requests: ");
                terminate = sc.nextLine().equalsIgnoreCase("Y") ? false : true;
            }

        }

        // reference printing
        for (ProcessMod p : processes) {
            System.out.println(p.getSendList());
            System.out.println(p.getReceiveList());
        }

        Thread[] threads = new Thread[noOfProcesses];

        for (int it = 0; it < noOfProcesses; it++) {

            final int processId = it;
            threads[it] = new Thread(() -> {
                ProcessMod p = processes.get(processId);
                int clock = p.getClock();
                for (int i = 0; i < p.getNoOfEvents(); i++) {

                    // check if there are any receiving requests on current event
                    int[] clockTimeline = p.getClockTimeline();
                    if (p.getReceiveList().peek().getReceiverEvent() == (i + 1)) {
                        Request r = p.getReceiveList().poll();
                        ProcessMod senderProcess = processes.get(r.getSenderProcess() - 1);
                        while (senderProcess.getClock() <= clock) {
                            // wait will be called here
                        }
                        clock = Math.max(clock, clockTimeline[i]) + 1;
                    } else {
                        clock++;
                    }

                    // update the running clock and the timeline
                    clockTimeline[i] = clock;
                    p.setClock(clock);

                    // check if there are any sending requests on current event
                    if (p.getSendList().peek().getSenderEvent() == (i + 1)) {
                        Request r = p.getSendList().poll();
                        ProcessMod receiverProcess = processes.get(r.getReceiverProcess() - 1);
                        int receiverEvent = r.getReceiverEvent() - 1;
                        receiverProcess.getClockTimeline()[receiverEvent] = clock;
                    }
                }
            });
        }

        // starting the threads
        for(Thread t : threads) {
            t.start();
        }

        // wait for the threads to complete execution
        for(Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // print the final output
        for (ProcessMod p : processes) {
            System.out.println(Arrays.toString(p.getClockTimeline()));
        }


    }
}
