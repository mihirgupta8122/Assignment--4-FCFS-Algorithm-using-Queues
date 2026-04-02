package fcfs;

/**
 * The Process class represents a CPU process used in the
 * First-Come, First-Served (FCFS) scheduling algorithm.
 * Each process has a process ID, arrival time, and burst time.
 */
public class Process {

    // Unique identifier for the process (e.g., P1, P2, P3)
    private String processId;

    // Time at which the process arrives
    private int arrivalTime;

    // Time required by the CPU to execute the process
    private int burstTime;

    /**
     * Constructor to initialize a Process object.
     *
     * @param processId   The ID of the process
     * @param arrivalTime The arrival time of the process
     * @param burstTime   The burst time of the process
     */
    public Process(String processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    /**
     * @return the process ID
     */
    public String getProcessId() {
        return processId;
    }

    /**
     * @return the arrival time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return the burst time
     */
    public int getBurstTime() {
        return burstTime;
    }

    /**
     * Returns the process details as a string.
     */
    @Override
    public String toString() {
        return "Process " + processId +
                " [Arrival Time = " + arrivalTime +
                ", Burst Time = " + burstTime + "]";
    }
}
