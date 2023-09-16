package co.edu.uptc.so.simluador_backend.process_module;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Clase Process
@Getter
@Setter
@ToString
@Component
@NoArgsConstructor
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
}