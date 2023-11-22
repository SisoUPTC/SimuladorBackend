package co.edu.uptc.so.simluador_backend.DTO;

import java.util.ArrayList;
import java.util.Queue;

import co.edu.uptc.so.simluador_backend.process_module.CPU_Status;
import co.edu.uptc.so.simluador_backend.process_module.Process;

public class Data {
    private int clock;
    private CPU_Status cpuStatus;
    private Process cpuProcess;
    private Queue<Process> readyProceesses;
    private Queue<Process> blockProcesses;
    private ArrayList<String> events;
    private int totalTimes;
    private int endedProcesses = 0;

    public Data(int clock, CPU_Status cpuStatus, Process cpuProcess, Queue<Process> readyProceesses,
            Queue<Process> blockProcesses, ArrayList<String> events, int totalTimes, int endedProcesses) {
        this.clock = clock;
        this.cpuStatus = cpuStatus;
        this.cpuProcess = cpuProcess;
        this.readyProceesses = readyProceesses;
        this.blockProcesses = blockProcesses;
        this.events = events;
        this.totalTimes = totalTimes;
        this.endedProcesses = endedProcesses;
    }

    public int getClock() {
        return clock;
    }

    public CPU_Status getCpuStatus() {
        return cpuStatus;
    }

    public Process getCpuProcess() {
        return cpuProcess;
    }

    public Queue<Process> getReadyProceesses() {
        return readyProceesses;
    }

    public Queue<Process> getBlockProcesses() {
        return blockProcesses;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public int getEndedProcesses() {
        return endedProcesses;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public void setCpuStatus(CPU_Status cpuStatus) {
        this.cpuStatus = cpuStatus;
    }

    public void setCpuProcess(Process cpuProcess) {
        this.cpuProcess = cpuProcess;
    }

    public void setReadyProceesses(Queue<Process> readyProceesses) {
        this.readyProceesses = readyProceesses;
    }

    public void setBlockProcesses(Queue<Process> blockProcesses) {
        this.blockProcesses = blockProcesses;
    }

    public void setEvents(ArrayList<String> events) {
        this.events = events;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public void setEndedProcesses(int endedProcesses) {
        this.endedProcesses = endedProcesses;
    }

    
}
