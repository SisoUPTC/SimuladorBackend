package co.edu.uptc.so.simluador_backend.controller;

import co.edu.uptc.so.simluador_backend.util.GraphicData;

public class GraphicsMemoryDTO {
    private int clock;
    private int quantyProcessMemory;
    private int quantyProcessWait;
    private int percentageUse;
    private int percentageFree;
    private int blocksFree;
    private int blocksUsed;
    private GraphicData[] promedioSizeProcess;
    private int totalTimes;

    public GraphicsMemoryDTO(){}

    public GraphicsMemoryDTO(int clock, int quantyProcessMemory, int quantyProcessWait, int percentageUse,
            int percentageFree, int blocksFree, int blocksUsed, GraphicData[] promedioSizeProcess, int totalTimes) {
        this.clock = clock;
        this.quantyProcessMemory = quantyProcessMemory;
        this.quantyProcessWait = quantyProcessWait;
        this.percentageUse = percentageUse;
        this.percentageFree = percentageFree;
        this.blocksFree = blocksFree;
        this.blocksUsed = blocksUsed;
        this.promedioSizeProcess = promedioSizeProcess;
        this.totalTimes = totalTimes;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getQuantyProcessMemory() {
        return quantyProcessMemory;
    }

    public void setQuantyProcessMemory(int quantyProcessMemory) {
        this.quantyProcessMemory = quantyProcessMemory;
    }

    public int getQuantyProcessWait() {
        return quantyProcessWait;
    }

    public void setQuantyProcessWait(int quantyProcessWait) {
        this.quantyProcessWait = quantyProcessWait;
    }

    public int getPercentageUse() {
        return percentageUse;
    }

    public void setPercentageUse(int percentageUse) {
        this.percentageUse = percentageUse;
    }

    public int getPercentageFree() {
        return percentageFree;
    }

    public void setPercentageFree(int percentageFree) {
        this.percentageFree = percentageFree;
    }

    public int getBlocksFree() {
        return blocksFree;
    }

    public void setBlocksFree(int blocksFree) {
        this.blocksFree = blocksFree;
    }

    public int getBlocksUsed() {
        return blocksUsed;
    }

    public void setBlocksUsed(int blocksUsed) {
        this.blocksUsed = blocksUsed;
    }

    public GraphicData[] getPromedioSizeProcess() {
        return promedioSizeProcess;
    }

    public void setPromedioSizeProcess(GraphicData[] promedioSizeProcess) {
        this.promedioSizeProcess = promedioSizeProcess;
    }

}
