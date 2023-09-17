package co.edu.uptc.so.simluador_backend.process_module;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.stereotype.Component;

import lombok.Getter;

// Clase Scheduler
@Getter
@Component
public class Scheduler {
    private Queue<Process> readyQueue;
    private Queue<Process> blockedQueue;
    private Queue<Process> endedQueue;

    public Scheduler() {
        readyQueue = new LinkedList<>();
        blockedQueue = new LinkedList<>();
        endedQueue = new LinkedList<>();
    }

    public void toReady(Process process) {
        process.setStatus(ProcessStatus.READY);
        process.restartQuantum();
        readyQueue.add(process);
    }

    public void toBlock(Process process) {
        process.setStatus(ProcessStatus.BLOCKED);
        blockedQueue.add(process);
    }

    public void toEnded(Process process) {
        process.setStatus(ProcessStatus.ENDED);
        endedQueue.add(process);
    }

    public Process getNextProcess() {
        return readyQueue.poll();
    }

    public void unlockProcess(Process process) {
        blockedQueue.remove(process);
        toReady(process);
    }
}