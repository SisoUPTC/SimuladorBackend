package co.edu.uptc.so.simluador_backend.DTO;

import java.util.ArrayList;
import java.util.Queue;

import co.edu.uptc.so.simluador_backend.process_module.CPU_Status;
import co.edu.uptc.so.simluador_backend.process_module.Process;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Data {
    int clock;
    CPU_Status cpuStatus;
    Process cpuProcess;
    Queue<Process> readyProceesses;
    Queue<Process> blockProcesses;
    ArrayList<String> events;
}
