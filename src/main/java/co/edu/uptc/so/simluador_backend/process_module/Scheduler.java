package co.edu.uptc.so.simluador_backend.process_module;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

// Clase Scheduler
@Component
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

    public Queue<Process> getBlockedQueue() {
        return blockedQueue;
    }

    public Queue<Process> getReadyQueue() {
        return readyQueue;
    }
}