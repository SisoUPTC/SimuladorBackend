package co.edu.uptc.so.simluador_backend.process_module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.LinkedList;
import org.springframework.stereotype.Component;
import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.DTO.GraphicsDTO;
import co.edu.uptc.so.simluador_backend.util.GraphicData;
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

    public boolean start() {
        init();
        while (!endSimulation()) {
            addData();
            data.get(clock).getEvents().add("The next process will be created in " + nextProcessTime);
            createProcess();
            checkQueues();
            updateTimes();
            nextClock();
        }
        this.data.forEach(data -> data.setTotalTimes(this.data.size() - 1));
        return true;
    }

    private void updateTimes() {
        if (this.cpu.getStatus().equals(CPU_Status.BUSY)) {
            if (cpu.getRunningProcess().getTimeToLive() > 0)
                cpu.getRunningProcess().substractTTL();
            if (cpu.getRunningProcess().getQuantum() > 0)
                cpu.getRunningProcess().substracQuantum();
            if (cpu.getRunningProcess().getNextIOTTL() > 0)
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
                data.get(clock).setEndedProcesses(data.get(clock).getEndedProcesses() + 1);
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
        if (!this.data.isEmpty()) {
            Data newData = new Data(clock, cpu.getStatus(),
                    cpu.getStatus().equals(CPU_Status.BUSY) ? new Process(cpu.getRunningProcess()) : null,
                    new LinkedList<>(), new LinkedList<>(), new ArrayList<>(), 0,
                    this.data.get(clock - 1).getEndedProcesses());
            this.data.add(newData);
        } else {
            Data newData = new Data(clock, cpu.getStatus(),
                    cpu.getStatus().equals(CPU_Status.BUSY) ? new Process(cpu.getRunningProcess()) : null,
                    new LinkedList<>(), new LinkedList<>(), new ArrayList<>(), 0, 0);
            this.data.add(newData);
        }
        for (Process process : scheduler.getReadyQueue()) {
            data.get(clock).getReadyProceesses().add(new Process(process));
        }
        for (Process process : scheduler.getBlockedQueue()) {
            data.get(clock).getBlockProcesses().add(new Process(process));
        }
    }

    private boolean endSimulation() {
        return clock >= simulationTime;
    }

    public GraphicsDTO getGraphics() {
        int[] quantyProcessQueues = getQuantyProcessQueues();
        GraphicData[] quantyEndendCPU = getQuantyEndendCPU();
        int totalEndedProccess = this.data.get(this.data.size() - 1).getEndedProcesses();
        Object[][] times = getTimes();
        GraphicData[] timesTTL = (GraphicData[]) times[0];
        GraphicData[] timesNextIO = (GraphicData[]) times[1];
        GraphicData[] timesIO = (GraphicData[]) times[2];
        GraphicsDTO graphicsDTO = new GraphicsDTO(quantyProcessQueues, quantyEndendCPU, timesTTL, timesNextIO, timesIO,
                totalEndedProccess);
        return graphicsDTO;
    }

    private Object[][] getTimes() {
        GraphicData[] timesTTL = new GraphicData[this.maxProccessLifeTime + 1];
        GraphicData[] timesNextIO = new GraphicData[this.maxNextIOTime + 1];
        GraphicData[] timesIO = new GraphicData[this.maxIOExecutionTime + 1];

        for (int i = 0; i < timesTTL.length; i++) {
            timesTTL[i] = new GraphicData(i, 0);
        }

        for (int i = 0; i < timesNextIO.length; i++) {
            timesNextIO[i] = new GraphicData(i, 0);
        }

        for (int i = 0; i < timesIO.length; i++) {
            timesIO[i] = new GraphicData(i, 0);
        }

        this.data.forEach(data -> {
            if (data.getCpuProcess() != null) {
                timesTTL[data.getCpuProcess().getTimeToLive()]
                        .setValue(timesTTL[data.getCpuProcess().getTimeToLive()].getValue() + 1);
                timesNextIO[data.getCpuProcess().getNextIOTime()]
                        .setValue(timesNextIO[data.getCpuProcess().getNextIOTime()].getValue() + 1);
                timesIO[data.getCpuProcess().getIOTime()]
                        .setValue(timesIO[data.getCpuProcess().getIOTime()].getValue() + 1);
            }
        });
        return new Object[][] { timesTTL, timesNextIO, timesIO };
    }

    private GraphicData[] getQuantyEndendCPU() {
        GraphicData[] quantyEndendCPU = new GraphicData[data.size()];
        for (int i = 0; i < quantyEndendCPU.length; i += 10) {
            quantyEndendCPU[i] = new GraphicData(i, this.data.get(i).getEndedProcesses());
        }
        return quantyEndendCPU;
    }

    private int[] getQuantyProcessQueues() {
        int[] quantyProcessQueues = new int[2];
        quantyProcessQueues[0] = scheduler.getReadyQueue().size();
        quantyProcessQueues[1] = scheduler.getBlockedQueue().size();
        return quantyProcessQueues;
    }

}