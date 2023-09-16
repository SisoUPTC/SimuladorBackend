package co.edu.uptc.so.simluador_backend.process_module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedList;
import org.springframework.stereotype.Component;
import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.util.RandomUltil;

// Clase Simulator

@Getter
@Setter
@ToString
@AllArgsConstructor
@Component
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
    private ArrayList<String> events;

    public Simulator() {
        this.simulationTime = 50;
        this.delay = 1;
        this.maxNextIOTime = 4;
        this.maxIOExecutionTime = 4;
        this.maxNextProcessTime = 5;
        this.maxProccessLifeTime = 20;
        this.quantum = 3;
        init();
    }

    private void init() {
        this.clock = 0;
        this.scheduler = new Scheduler();
        this.cpu = new CPU();
        this.data = new ArrayList<>();
        this.nextProcessTime = 0;
        this.processCount = 0;
        this.events = new ArrayList<>();
    }

    public void start() {
        init();
        while (!endSimulation()) {
            events.add("The next process will be created in " + nextProcessTime);
            checkQueues();
            createProcess();
            updateTimes();
            addData();
            nextClock();
            nextProcessTime--;
        }
    }

    private void updateTimes() {
        if (this.cpu.getStatus().equals(CPU_Status.BUSY)) {
            cpu.getRunningProcess().substractTTL();
            cpu.getRunningProcess().substracQuantum();
            cpu.getRunningProcess().substractNextIOTTL();
        } else if (1 == 0) {
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
        Process process = new Process(processCount, RandomUltil.random(maxProccessLifeTime),
                RandomUltil.random(maxNextIOTime), quantum);
        events.add("Process " + processCount + " was created.");

        if (cpu.getStatus().equals(CPU_Status.IDLE)) {
            this.cpu.assignProcess(process);
            process.setStatus(ProcessStatus.RUNNING);
            events.add("Process  " + processCount + " was assigned to CPU.");
        } else {
            this.scheduler.addToReadyQueue(process);
            process.setStatus(ProcessStatus.READY);
            events.add("Process " + processCount + " was assigned to ready processes queue.");
        }
    }

    private void nextClock() {
        clock++;
    }

    private void checkQueues() {
        // TODO
    }

    private void addData() {
        Data newData = new Data(clock, cpu.getStatus(), new Process(cpu.getRunningProcess()),
                new LinkedList<>(scheduler.getReadyQueue()),
                new LinkedList<>(scheduler.getBlockedQueue()), new ArrayList<>(events));
        this.data.add(newData);
        events.clear();
    }

    private boolean endSimulation() {
        return clock >= simulationTime;
    }

}