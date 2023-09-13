import java.util.LinkedList;
import java.util.Queue;

// Clase Scheduler

public class Scheduler {
    private Queue<Process> readyQueue;
    private Queue<Process> blockedQueue;

    public Scheduler() {
        readyQueue = new LinkedList<>();
        blockedQueue = new LinkedList<>();
    }

    public void addToReadyQueue(Process process) {
        readyQueue.add(process);
    }

    public void addToBlockedQueue(Process process) {
        blockedQueue.add(process);
    }

    public Process getNextProcess() {
        return readyQueue.poll();
    }
}