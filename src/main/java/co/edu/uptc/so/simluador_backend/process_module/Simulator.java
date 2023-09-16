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
    public static final int QUANTUM = 3;
    private int simulationTime;
    private int delay;
    private int nextProcessTime;
    private int maxNextProcessTime;
    private int maxNextIOTime;
    private int maxProccessLifeTime;
    private int maxIOExecutionTime;
    private int clock;
    private Scheduler scheduler;
    private CPU cpu;
    private ArrayList<Data> data;
    private int processCount;

    public Simulator() {
        this.simulationTime = 50;
        this.delay = 1;
        this.maxNextIOTime = 4;
        this.maxIOExecutionTime = 4;
        this.maxNextProcessTime = 5;
        this.maxProccessLifeTime = 20;
        init();
    }

    private void init() {
        this.clock = 0;
        this.scheduler = new Scheduler();
        this.cpu = new CPU();
        this.data = new ArrayList<>();
        this.nextProcessTime = 0;
        this.processCount = 0;
    }

    public void start() {
        init();
        while (!endSimulation()) {
            addData();
            data.get(clock).getEvents().add("The next process will be created in " + nextProcessTime);
            createProcess();
            checkQueues();
            updateTimes();
            nextClock();
        }
    }

    private void updateTimes() {
        if (this.cpu.getStatus().equals(CPU_Status.BUSY)) {
            cpu.getRunningProcess().substractTTL();
            cpu.getRunningProcess().substracQuantum();
            cpu.getRunningProcess().substractNextIOTTL();
        }
        scheduler.getBlockedQueue().forEach(Process::substractIOTTL);
        nextProcessTime--;
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
                RandomUltil.random(maxIOExecutionTime),
                RandomUltil.random(maxNextIOTime), QUANTUM);
        data.get(clock).getEvents().add("Process " + processCount + " was created.");

        if (cpu.getStatus().equals(CPU_Status.IDLE)) {
            this.cpu.assignProcess(process);
            data.get(clock).getEvents().add("Process " + processCount + " was assigned to CPU.");
        } else {
            this.scheduler.toReady(process);
            process.setStatus(ProcessStatus.READY);
            data.get(clock).getEvents().add("Process " + processCount + " was assigned to ready processes queue.");
        }
    }

    private void nextClock() {
        clock++;
    }

    private void checkQueues() {

        // Desbloquear procesos que ya terminaron su operación de IO
        scheduler.getBlockedQueue().forEach(process -> {
            if (process.getIOTimeToLive() <= 0) {
                scheduler.unlockProcess(process);
                data.get(clock).getEvents().add("Process  " + process.getId() + " was moved to ready processes queue.");
                process.setNextIOTime(RandomUltil.random(maxNextIOTime));
                process.setIOTimeToLive(process.getIOTime());
            }
        });

        // Siguiente proceso en la cola de procesos listos si CPU esta disponible
        if (cpu.getStatus().equals(CPU_Status.IDLE)) {
            if (!scheduler.getReadyQueue().isEmpty()) {
                cpu.assignProcess(scheduler.getNextProcess());
                data.get(clock).getEvents()
                        .add("Process  " + cpu.getRunningProcess().getId() + " was assigned to CPU.");
            }
        } else {
            Process ruuningProcess = cpu.getRunningProcess();
            // Verificar TTL, QUANTUM y IO del proceso en la cpu
            if (ruuningProcess.getTimeToLive() <= 0) {
                scheduler.toEnded(ruuningProcess);
                data.get(clock).getEvents()
                        .add("Process  " + ruuningProcess.getId() + " was assigned to ended processes queue.");
                cpu.release();
                data.get(clock).getEvents().add("CPU is IDLE!");
            } else if (ruuningProcess.getNextIOTTL() <= 0) {
                scheduler.toBlock(ruuningProcess);
                data.get(clock).getEvents().add("Status of process " + ruuningProcess.getId() + " is BLOCKED.");
                cpu.release();
                data.get(clock).getEvents().add("CPU is IDLE!");
            } else if (ruuningProcess.getQuantum() <= 0) {
                scheduler.toReady(ruuningProcess);
                data.get(clock).getEvents()
                        .add("Process  " + ruuningProcess.getId() + " was assigned to ready processes queue.");
                cpu.release();
                data.get(clock).getEvents().add("CPU is IDLE!");
            }
        }
    }

    private void addData() {
        Data newData = new Data(clock, cpu.getStatus(),
                cpu.getStatus().equals(CPU_Status.BUSY) ? new Process(cpu.getRunningProcess()) : null,
                new LinkedList<>(scheduler.getReadyQueue()),
                new LinkedList<>(scheduler.getBlockedQueue()), new ArrayList<>());
        this.data.add(newData);
    }

    private boolean endSimulation() {
        return clock >= simulationTime;
    }

}