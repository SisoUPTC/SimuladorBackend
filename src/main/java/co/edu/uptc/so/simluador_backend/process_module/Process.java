package co.edu.uptc.so.simluador_backend.process_module;
// Clase Process
public class Process {
    private int lifeTime;
    private int IOTime;
    private int nextIOTime;
    private int timeToLive;
    private int nextIOTTL;
    private int IOTimeToLive;
    private ProcessStatus status;
    private int quantum;

    public Process(int lifeTime, int IOTime, int quantum) {
        this.lifeTime = lifeTime;
        this.IOTime = IOTime;
        this.nextIOTime = IOTime;
        this.timeToLive = lifeTime;
        this.nextIOTTL = IOTime;
        this.IOTimeToLive = IOTime;
        this.status = ProcessStatus.READY;
        this.quantum = quantum;
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