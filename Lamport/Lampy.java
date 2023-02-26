import java.util.Arrays;


public class Lampy {
    private int[] clocks;

    public Lampy(int numProcesses) {
        // Initialize each process with a logical clock value of 0
        clocks = new int[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            clocks[i] = 0;
        }
    }

    // Function to increment the logical clock of a process
    public void incrementClock(int processId) {
        clocks[processId]++;
    }

    // Function to synchronize clocks after a message is sent
    public void synchronizeClocks(int senderId, int receiverId) {
        // Increment the sender's logical clock
        incrementClock(senderId);
        // Update the receiver's logical clock to be the maximum of its current value
        // and the sender's logical clock plus 1
        clocks[receiverId] = Math.max(clocks[receiverId], clocks[senderId] + 1);
    }

    public static void main(String[] args) {
        // Create a clock object with 3 processes
        Lampy clock = new Lampy(3);

        // Create three threads to represent three processes
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            final int processId = i;
            threads[i] = new Thread(() -> {
                // Increment the process's logical clock
                clock.incrementClock(processId);

                // Simulate sending a message to another process
                int receiverId = (processId + 1) % 3; // send message to the next process in a ring topology
                clock.synchronizeClocks(processId, receiverId);

                // Print the updated logical clocks for all processes
                System.out.println("Process " + processId + ": " + Arrays.toString(clock.clocks));
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}