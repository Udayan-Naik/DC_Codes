package com.udayan;

import java.util.Scanner;


public class Bully {

    // User enters no of processes.
    // User enters the state of the processes.
    // User is asked who wants to initiate the election.
    // Selected server sends request to all the servers who are greater than it.
    // The response is noted down and if one server is down then it cannot be the coordinator
    // This process continuous recursively until the greatest server is not found.
    // That server is made the coordinator.
    public static void main(String[] args) {
	// write your code here

        int totalProcesses;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total number of processes: ");
        totalProcesses = sc.nextInt();

        boolean areAllProcessesDown = true;
        int[] stateOfProcesses = new int[totalProcesses];
        System.out.println("Enter the state of all processes: 0 -> Down, 1 -> Up");

        for(int i = 0; i < totalProcesses; i++) {
            System.out.print("State of process " + (i + 1) + ": ");
            stateOfProcesses[i] = sc.nextInt();
            if(stateOfProcesses[i] == 1) areAllProcessesDown = false;
        }
        if(areAllProcessesDown){
            System.out.println("All servers are down");
            return;
        }

        int electionProcess = 1;

        while(true) {
            System.out.print("Which process should start the election process(1-indexed): ");
            electionProcess = sc.nextInt() - 1;
            if(stateOfProcesses[electionProcess] == 1) break;
            System.out.println("Process to start an election must be UP..\n");

        }


//        int coordinator = -1;
        while(isHigherServerCandidate(totalProcesses, stateOfProcesses, electionProcess++) && (electionProcess < totalProcesses)){
            System.out.println("Checking higher servers....\n");
        }

        System.out.println("No higher servers found for coordinator candidate");

        electionProcess = downServerCheck(stateOfProcesses, electionProcess - 1) + 1;
        System.out.println("Server " + electionProcess + " assigned as coordinator");

        coordinatorMessage(electionProcess, totalProcesses);



        sc.close();
    }


    private static void coordinatorMessage(int coordinator, int totalProcesses) {

        for(int i = 0; i < coordinator - 1; i++) {
            System.out.println("Coordinator message from process " + coordinator + " to process " + (i + 1));
        }
    }
    private static int downServerCheck(int[] stateOfProcesses, int electionProcess) {

        for(int i = electionProcess; i >= 0; i--) {
            if(stateOfProcesses[i] == 1) return i;
        }
        return -1;
    }

    private static boolean isHigherServerCandidate(int totalProcesses, int[] stateOfProcesses, int electionProcess) {

        if(stateOfProcesses[electionProcess] == 0) {
            System.out.println("Process " + (electionProcess + 1) + " is down... Checking further processes");
            return true;
        }
        boolean higherServer = false;

        System.out.println("Process " + (electionProcess + 1) + " sending requests....");
        for(int i = electionProcess + 1; i < totalProcesses; i++) {

            System.out.println("Request sent to process " + (i + 1) + " from process " + (electionProcess + 1));
        }

        for(int i = electionProcess + 1; i < totalProcesses; i++) {

            if(stateOfProcesses[i] == 1){
                higherServer = true;
                System.out.println("OK Response received from process " + (i + 1));
            }

        }

        System.out.println();

        return higherServer;
    }
}

