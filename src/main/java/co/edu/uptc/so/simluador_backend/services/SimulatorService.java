package co.edu.uptc.so.simluador_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.DTO.GraphicsDTO;
import co.edu.uptc.so.simluador_backend.process_module.Simulator;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SimulatorService {
    @Autowired
    Simulator simulator;

    public boolean start(int simulationTime) {
        simulator.setSimulationTime(simulationTime);
        return simulator.start();
    }

    public Data getResult(int clock) {
       return simulator.getData().get(clock);
    }

    public GraphicsDTO getGraphics() {
        return this.simulator.getGraphics();
    }

    public boolean isStarted() {
        return !this.simulator.getData().isEmpty();
    }
}
