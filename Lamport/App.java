import java.util.*;

public class App {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("Enter the number of processes: ");
        int processes = scanner.nextInt();
        System.out.println("Enter the number of events in the process: ");
        int events = scanner.nextInt();

        List<List<Integer>> listOfProcesses = new ArrayList<>();
        List<List<Integer>> msgQueue = new ArrayList<>(); 

        for (int i = 0; i < processes; i++) {
            List<Integer> newProcess = new ArrayList<>();
            System.out.println("What is the clock cycle of Process " + Integer.valueOf(i+1));
            int clockCycle = scanner.nextInt();
            for (int j = 0; j < events; j++) {
                newProcess.add(clockCycle * j);
            }
            listOfProcesses.add(new ArrayList<>(newProcess));
        }

        //debugging 
        printProcesses(listOfProcesses);


        //Storing the info about message (which process sent it, to which process, from which event to which event)
        // in a list of lists (msgQueue) then each message can be processed 
        while(true){
            System.out.println("Do you wish to send a message ? 1 / 0");
            int sendFrom = 0;
            int sendEvent = 0;
            int recAt  = 0;
            int recEvent = 0;
            int choice = scanner.nextInt();
            if(choice == 1){
                System.out.println("From which process do you wish to send a message ?");
                sendFrom = scanner.nextInt();
                System.out.println("Which event from " + sendFrom + " should send this message ?");
                sendEvent = scanner.nextInt();
                System.out.println("Which process should receive this message ?");
                recAt = scanner.nextInt();
                System.out.println("Which event from " + recAt + " should receive this message ?");
                recEvent = scanner.nextInt();   
                msgQueue.add(new ArrayList<>(Arrays.asList(sendFrom,recAt,sendEvent,recEvent)));             
            }else{
                break;
            }
        }

        printProcesses(msgQueue);

        //Process these msg requests from the queue one at a time 
        for(int i =0;i<msgQueue.size();i++){
            processMsgReq(msgQueue.get(i).get(0), msgQueue.get(i).get(1), 
            msgQueue.get(i).get(2),msgQueue.get(i).get(3), listOfProcesses);
            System.out.println("Processes after sending the Message from Process "+  msgQueue.get(i).get(0) + 
            " event "+msgQueue.get(i).get(2) +" to Process" + msgQueue.get(i).get(1) +  
            " to event " + msgQueue.get(i).get(3));
            printProcesses(listOfProcesses);
        }


    }

    public static void processMsgReq(int sendFrom, int recAt, int sendEvent, int recEvent, List<List<Integer>> listOfProcess){
        if(listOfProcess.get(recAt).get(recEvent) < listOfProcess.get(sendFrom).get(sendEvent)){
            int clockShift = listOfProcess.get(sendFrom).get(sendEvent) - listOfProcess.get(recAt).get(recEvent) + 1;
            for(int i = recEvent; i< listOfProcess.get(recAt).size();i++){
                int updatedVal = listOfProcess.get(recAt).get(i) + clockShift;
                listOfProcess.get(recAt).set(i,updatedVal);
            }
        }
    }

    public static void printProcesses(List<List<Integer>> listOfProcesses){
        for (List<Integer> innerList : listOfProcesses) {
            System.out.print("[ ");
            for (Integer value : innerList) {
                System.out.print(value + " ");
            }
            System.out.println("]");
        } 
    }
}