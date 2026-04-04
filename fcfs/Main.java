package fcfs;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        // welcome message
		JOptionPane.showMessageDialog(
            null, """
            the FCFS (First-Come-First-Serve) Scheduling Simulation! \n\n
            Press OK to Start""",
			"Welcome to",
			JOptionPane.INFORMATION_MESSAGE
		);

        // process input
        int numProcesses = 0;
        while (numProcesses <= 0) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "Enter the number of processes",
                    "FCFS Scheduling Simulation",
                    JOptionPane.QUESTION_MESSAGE
            );

            // cancel
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Simulation cancelled :(");
                return;
            }

            try {
                numProcesses = Integer.parseInt(input.trim());
                if (numProcesses <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "Enter a positive number of processes",
                            "FCFS Scheduling Simulation - Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Invalid input \nEnter a whole number",
                        "FCFS Scheduling Simulation - Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }

        // process details
        Process[] processes = new Process[numProcesses];

        for (int i = 0; i < numProcesses; i++) {
            int processNumber = i + 1;
            int arrivalTime = -1;
            while (arrivalTime < 0) {
                String input = JOptionPane.showInputDialog(
                        null,
                        "Enter arrival time for process " + processNumber,
                        "FCFS Scheduling Simulation - Process",
                        JOptionPane.QUESTION_MESSAGE
                );
                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Simulation cancelled :(");
                    return;
                }
                try {
                    arrivalTime = Integer.parseInt(input.trim());
                    if (arrivalTime < 0) {
                        JOptionPane.showMessageDialog(null,
                                "Arrival time can't be negative",
                                "FCFS Scheduling Simulation - Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input \n Enter a whole number",
                            "FCFS Scheduling Simulation - Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            int burstTime = 0;
            while (burstTime <= 0) {
                String input = JOptionPane.showInputDialog(
                        null,
                        "Enter burst time for process " + processNumber,
                        "FCFS Scheduling Simulation - Burst Time",
                        JOptionPane.QUESTION_MESSAGE
                );


                if (input == null) {
                    JOptionPane.showMessageDialog(null, "Simulation cancelled :(");
                    return;
                }


                try {
                    burstTime = Integer.parseInt(input.trim());
                    if (burstTime <= 0) {
                        JOptionPane.showMessageDialog(null,
                                "Burst time has to be greater than 0",
                                "FCFS Scheduling Simulation - Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null,
                            "Invalid input \n Enter a whole number",
                            "FCFS Scheduling Simulation - Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

            processes[i] = new Process("P" + processNumber, arrivalTime, burstTime);
        }

        // run FCFS simulation (i hope it works :/ )
        QueueImplementation<Process> queue = new QueueImplementation<>();
        for (Process p : processes) {
            queue.enqueue(p);
        }
        int currentTime     = 0;
        int totalWaiting    = 0;
        int totalTurnaround = 0;


        String[] ids             = new String[numProcesses];


        int[]    arrivalTimes    = new int[numProcesses];
        int[]    waitingTimes    = new int[numProcesses];
        int[]    turnaroundTimes = new int[numProcesses];

        int idx = 0;
        while (!queue.isEmpty()) {
            Process p = queue.dequeue();

            if (currentTime < p.getArrivalTime()) {
                currentTime = p.getArrivalTime();
            }

            int waiting    = currentTime - p.getArrivalTime();
            int completion = currentTime + p.getBurstTime();
            int turnaround = completion - p.getArrivalTime();

            totalWaiting    += waiting;
            totalTurnaround += turnaround;
            currentTime      = completion;

            ids[idx]             = p.getProcessId();
            arrivalTimes[idx]    = p.getArrivalTime();
            waitingTimes[idx]    = waiting;
            turnaroundTimes[idx] = turnaround;
            idx++;
        }

        double avgWaiting    = (double) totalWaiting    / numProcesses;
        double avgTurnaround = (double) totalTurnaround / numProcesses;

        // build
        StringBuilder resultsMessage = new StringBuilder();
        resultsMessage.append(String.format("%-10s %-15s %-15s %-15s%n",
                "Process", "Arrival Time", "Waiting Time", "Turnaround Time"));
        resultsMessage.append("-".repeat(55)).append("\n");

        for (int i = 0; i < numProcesses; i++) {
            resultsMessage.append(String.format("%-10s %-15d %-15d %-15d%n",
                    ids[i], arrivalTimes[i], waitingTimes[i], turnaroundTimes[i]));
        }

        JOptionPane.showMessageDialog(
                null,
                resultsMessage.toString(),
                "FCFS Scheduling Simulation - Results",
                JOptionPane.INFORMATION_MESSAGE
        );

        // show averages
        String averagesMessage =
                String.format("Average Wait Time: %.1f%n", avgWaiting) +
                String.format("Average Turnaround Time: %.1f%n", avgTurnaround);

        JOptionPane.showMessageDialog(
                null,
                averagesMessage,
                "FCFS Scheduling Simulation - Average Results",
                JOptionPane.INFORMATION_MESSAGE
        );

        // print to console
        System.out.println("Process Execution Order:");
        System.out.printf("%-10s %-15s %-15s %-15s%n",
                "process", "arrival time", "waiting time", "turnaround time");
        for (int i = 0; i < numProcesses; i++) {
            System.out.printf("%-10s %-15d %-15d %-15d%n",
                    ids[i], arrivalTimes[i], waitingTimes[i], turnaroundTimes[i]);
        }
        System.out.printf("average wait time: %.1f%n", avgWaiting);
        System.out.printf("average turnaround time: %.1f%n", avgTurnaround);
    }
}