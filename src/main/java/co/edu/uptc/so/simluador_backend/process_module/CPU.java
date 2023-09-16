package co.edu.uptc.so.simluador_backend.process_module;

import org.springframework.stereotype.Component;

import lombok.Getter;

// Clase CPU
@Component
@Getter
public class CPU {
    private Process runningProcess;
    private CPU_Status status;

    public CPU() {
        status = CPU_Status.IDLE;
    }

    public void assignProcess(Process process) {
        runningProcess = process;
        status = CPU_Status.BUSY;
    }

    public void release() {
        runningProcess = null;
        status = CPU_Status.IDLE;
    }
}