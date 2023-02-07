package com.udayan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //
    public static void main(String[] args) {
	// write your code here

        int noOfProcess;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter total number of processes: ");
        noOfProcess = sc.nextInt();
        List<Process> processes = new ArrayList<>(noOfProcess);
        Map<Integer, Integer> indices = new HashMap<>();
        for(int i = 0; i < noOfProcess; i++) {

            int id;
            int state;
            System.out.print("Enter process id for process " + (i + 1) + ": ");
            id = sc.nextInt();
            System.out.print("Enter process state for process " + (i + 1) + ": ");
            state = sc.nextInt();
            processes.add(new Process(id, state));
            indices.put(i, id);
            System.out.println();
        }



        System.out.print("Which process should start the election(id): ");
        int electionProcess = sc.nextInt();

        List<Integer> references = new ArrayList<>();

        int start = -1;
        for(int index : indices.keySet()) {
            if(indices.get(index) == electionProcess){
                start = index;
                break;
            }
        }
        references.add(electionProcess);
        for(int i = (start + 1)%noOfProcess; i != start; i = (i+1) % noOfProcess){

            if(processes.get(i).state == 1){
                System.out.println("List passed on till process(id) " + indices.get(i) + ": " + references);
                references.add(indices.get(i));
                continue;
            }
            System.out.println("Process " + indices.get(i) + " is down... Checking ahead");
//            System.out.println(references);
        }

        System.out.println("List passed on till process(id) " + indices.get(electionProcess) + ": " + references);
        System.out.println("Process id "  + Collections.max(references) + " announced as coordinator");


    }
}
