package co.edu.uptc.so.simluador_backend.process_module;

import java.util.ArrayList;

public class MemoryData {
    private int clock;
    private ArrayList<Process> readyProceesses;
    private ArrayList<BlockMemory> memoryProccesses;
    private int totalTimes;

    public MemoryData(int clock, ArrayList<Process> readyProceesses, ArrayList<BlockMemory> memoryProccesses, int totalTimes) {
        this.clock = clock;
        this.readyProceesses = readyProceesses;
        this.memoryProccesses = memoryProccesses;
        this.totalTimes = totalTimes;
    }

    public int getClock() {
        return clock;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public ArrayList<Process> getReadyProceesses() {
        return readyProceesses;
    }

    public void setReadyProceesses(ArrayList<Process> readyProceesses) {
        this.readyProceesses = readyProceesses;
    }

    public ArrayList<BlockMemory> getMemoryProccesses() {
        return memoryProccesses;
    }

    public void setMemoryProccesses(ArrayList<BlockMemory> memoryProccesses) {
        this.memoryProccesses = memoryProccesses;
    }

}
