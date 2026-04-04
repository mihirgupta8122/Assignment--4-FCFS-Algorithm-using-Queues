package fcfs;
// This program simulates the FCFS algorithm. 
// It uses a custom QueueImplementation to manage processes and calculates execution metrics like waiting and turnaround times.
public class FCFSAlgorithm {

    //Executes the FCFS scheduling simulation. The parameter contains an array of process objects to be scheduled.
    public void simulate(Process[] processes) {
        QueueImplementation<Process> queue = new QueueImplementation<>();
        
        // Adds all processes to the custom queue
        for (Process p : processes) {
            queue.enqueue(p);
        }

        // Variables used for time tracking
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int processCount = 0;

        System.out.println("Process Execution Order:");
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t\tArrival\t\tWaiting\t\tTurnaround");

        // Go over each element in the queue
        while (!queue.isEmpty()) {
            Process p = queue.dequeue();
            processCount++;

            // If CPU is idle then jump to arrival time
            if (currentTime < p.getArrivalTime()) {
                currentTime = p.getArrivalTime();
            }

            // Variables to calculate the different timing of the process
            int waitingTime = currentTime - p.getArrivalTime();
            int completionTime = currentTime + p.getBurstTime();
            int turnaroundTime = completionTime - p.getArrivalTime();

            // Accumulates the total for averages
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;

            // Update current time to when this process finishes
            currentTime = completionTime;

            System.out.println(p.getProcessId() + "\t\t" + p.getArrivalTime() + "\t\t" + waitingTime + "\t\t" + turnaroundTime);
        }

        // Calculates and displays the averages
        if (processCount > 0) {
            double avgWaiting = (double) totalWaitingTime / processCount;
            double avgTurnaround = (double) totalTurnaroundTime / processCount;

            System.out.println("-------------------------------------------------------");
            System.out.println("Average Waiting Time: " + avgWaiting);
            System.out.println("Average Turnaround Time: " + avgTurnaround);
        }
    }

    // Main method to test the algorithm
    public static void main(String[] args) {
        FCFSAlgorithm scheduler = new FCFSAlgorithm();

        // Sample process
        fcfs.Process[] sampleProcesses = {
            new fcfs.Process("P1", 0, 5),
            new fcfs.Process("P2", 2, 3),
            new fcfs.Process("P3", 4, 1),
            new fcfs.Process("P4", 6, 4)
        };

        scheduler.simulate(sampleProcesses);
    }
}