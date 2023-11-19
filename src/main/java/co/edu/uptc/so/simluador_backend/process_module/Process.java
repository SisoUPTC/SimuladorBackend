package co.edu.uptc.so.simluador_backend.process_module;

import org.springframework.stereotype.Component;

@Component
public class Process {
    private int id;
    private int lifeTime;
    private int IOTime;
    private int nextIOTime;
    private int timeToLive;
    private int nextIOTTL;
    private int IOTimeToLive;
    private ProcessStatus status;
    private int quantum;

    public Process(){}

    public Process(int id, int lifeTime, int IOTime, int nextIOTime, int quantum) {
        this.id = id;
        this.lifeTime = lifeTime;
        this.timeToLive = lifeTime;
        this.IOTime = IOTime;
        this.IOTimeToLive = IOTime;
        this.nextIOTime = nextIOTime;
        this.nextIOTTL = nextIOTime;
        this.status = ProcessStatus.READY;
        this.quantum = quantum;
    }

    public Process(Process process) {
        this.id = process.id;
        this.lifeTime = process.lifeTime;
        this.IOTime = process.IOTime;
        this.nextIOTime = process.nextIOTime;
        this.timeToLive = process.timeToLive;
        this.nextIOTTL = process.nextIOTTL;
        this.IOTimeToLive = process.IOTimeToLive;
        this.status = process.status;
        this.quantum = process.quantum;
    }

    public void substractTTL() {
        timeToLive--;
    }

    public void substracQuantum() {
        quantum--;
    }

    public void substractNextIOTTL() {
        nextIOTTL--;
    }

    public void substractIOTTL() {
        IOTimeToLive--;
    }

    public void restartQuantum() {
        quantum = Simulator.QUANTUM;
    }

    public int getId() {
        return id;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getIOTime() {
        return IOTime;
    }

    public int getNextIOTime() {
        return nextIOTime;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public int getNextIOTTL() {
        return nextIOTTL;
    }

    public int getIOTimeToLive() {
        return IOTimeToLive;
    }

    public ProcessStatus getStatus() {
        return status;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public void setIOTime(int iOTime) {
        IOTime = iOTime;
    }

    public void setNextIOTime(int nextIOTime) {
        this.nextIOTime = nextIOTime;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public void setNextIOTTL(int nextIOTTL) {
        this.nextIOTTL = nextIOTTL;
    }

    public void setIOTimeToLive(int iOTimeToLive) {
        IOTimeToLive = iOTimeToLive;
    }

    public void setStatus(ProcessStatus status) {
        this.status = status;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}