package co.edu.uptc.so.simluador_backend.services;

import org.springframework.stereotype.Service;
import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.process_module.Simulator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SimulatorService {
    Simulator simulator;

    public void start(int simulationTime) {
        simulator.setSimulationTime(simulationTime);
        simulator.start();
    }

    public Data getResult(int clock) {
       return simulator.getData().get(clock);
    }
}
