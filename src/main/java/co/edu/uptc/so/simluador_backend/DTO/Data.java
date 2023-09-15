package co.edu.uptc.so.simluador_backend.DTO;

public class Data {
    int clock;
    String cpuStatus;
    String cpuProcess;
    String readyProceesses;
    String blockProcesses;
    String[] events;

    public Data(int clock, String cpuStatus, String cpuProcess, String readyProceesses, String blockProcesses,
            String[] events) {
        this.clock = clock;
        this.cpuStatus = cpuStatus;
        this.cpuProcess = cpuProcess;
        this.readyProceesses = readyProceesses;
        this.blockProcesses = blockProcesses;
        this.events = events;
    }
}
