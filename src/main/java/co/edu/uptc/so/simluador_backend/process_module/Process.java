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
}