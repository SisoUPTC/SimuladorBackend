package co.edu.uptc.so.simluador_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.so.simluador_backend.process_module.Simulator;
import co.edu.uptc.so.simluador_backend.services.SimulatorService;

@RestController
@RequestMapping("/simulator")
public class SimulatorController {
    
    @Autowired
    SimulatorService simulatorService;

    @PostMapping("/newSimulator")
    public ResponseEntity<Simulator> newSimulator(@RequestBody Simulator simulator) {
        
        return new ResponseEntity<>(simulator, HttpStatus.OK);
    }
}
