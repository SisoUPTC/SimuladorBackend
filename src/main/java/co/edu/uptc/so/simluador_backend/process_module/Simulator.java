package co.edu.uptc.so.simluador_backend.process_module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

// Clase Simulator
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Simulator {
    private int simulatonTime;
    private int delay;
    private int maxNextProcessTime;
    private int maxNextIOTime;
    private int maxProccessLifeTime;
    private int maxIOExecutionTime;
    private int quantum;
    private int clock;
    private Scheduler scheduler;
    private CPU cpu;

    public Simulator(int simulatonTime, int delay, int maxNextProcessTime, int maxNextIOTime, int maxIOExecutionTime,
            int quantum, int maxProccessLifeTime) {
        this.simulatonTime = simulatonTime;
        this.delay = delay;
        this.maxNextProcessTime = maxNextProcessTime;
        this.maxNextIOTime = maxNextIOTime;
        this.maxIOExecutionTime = maxIOExecutionTime;
        this.quantum = quantum;
        this.clock = 0;
        this.maxProccessLifeTime = maxProccessLifeTime;
        this.scheduler = new Scheduler();
        this.cpu = new CPU();
    }

    public Simulator() {
        this.simulatonTime = 50;
        this.delay = 1;
        this.maxNextProcessTime = 5;
        this.maxNextIOTime = 4;
        this.maxIOExecutionTime = 4;
        this.quantum = 3;
        this.clock = 0;
        this.maxProccessLifeTime = 20;
        this.scheduler = new Scheduler();
        this.cpu = new CPU();
    }

    
}