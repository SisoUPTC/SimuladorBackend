package co.edu.uptc.so.simluador_backend.process_module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.util.RandomUltil;
import jakarta.persistence.*;

// Clase Simulator
@Entity
@Getter
@Setter
@AllArgsConstructor
public class Simulator {
    private int simulationTime;
    private int delay;
    private int maxNextProcessTime;
    private int nextProcessTime;
    private int maxNextIOTime;
    private int maxProccessLifeTime;
    private int maxIOExecutionTime;
    private int quantum;
    private int clock;
    private Scheduler scheduler;
    private CPU cpu;
    private ArrayList<Data> data;
    private int processCount;

    public Simulator(int simulatonTime, int delay, int maxNextProcessTime, int maxNextIOTime, int maxIOExecutionTime,
            int quantum, int maxProccessLifeTime) {
        this.simulationTime = simulatonTime;
        this.delay = delay;
        this.maxNextProcessTime = maxNextProcessTime;
        this.maxNextIOTime = maxNextIOTime;
        this.maxIOExecutionTime = maxIOExecutionTime;
        this.quantum = quantum;
        this.clock = 0;
        this.maxProccessLifeTime = maxProccessLifeTime;
        this.scheduler = new Scheduler();
        this.cpu = new CPU();
        this.data = new ArrayList<>();
        this.nextProcessTime = RandomUltil.random(maxNextProcessTime);
        this.processCount = 0;
    }

    public Simulator() {
        this.simulationTime = 50;
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

    public void start() {
        while (!endSimulation()) {
            addData();
            checkQueues();
            nextClock();
            createProcess();
            updateTimes();
        }
    }

    private void updateTimes() {
        nextProcessTime--;
        if(this.cpu.getStatus().equals(CPU_Status.BUSY)){
            cpu.getRunningProcess().substractTTL();
            cpu.getRunningProcess().substracQuantum();
            cpu.getRunningProcess().substractNextIOTTL();
        }else if ( 1 == 0){
            // TODO
        }
    }

    private void createProcess() {
        if (nextProcessTime == 0) {
            processCount++;
            assignProcess();
            nextProcessTime = RandomUltil.random(maxNextProcessTime);
        }
    }

    private void assignProcess() {
        Process process = new Process(processCount, RandomUltil.random(maxProccessLifeTime), RandomUltil.random(maxNextIOTime), quantum);
        if (cpu.getStatus().equals(CPU_Status.IDLE)) {
            this.cpu.assignProcess(process);
            process.setStatus(ProcessStatus.RUNNING);
        } else {
            this.scheduler.addToReadyQueue(process);
            process.setStatus(ProcessStatus.READY);
        }
    }

    private void nextClock() {
        clock++;
    }

    private void checkQueues() {
        // TODO
    }

    private void addData() {
        String[] events = {};
        this.data.add(new Data(clock, cpu.getStatus().toString(), cpu.getRunningProcess().toString(),
                scheduler.getReadyQueue().toString(), scheduler.getBlockedQueue().toString(), events));
    }

    private boolean endSimulation() {
        return clock >= simulationTime;
    }
}