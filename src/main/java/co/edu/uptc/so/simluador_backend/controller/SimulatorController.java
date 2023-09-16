package co.edu.uptc.so.simluador_backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.services.SimulatorService;

@RestController
@RequestMapping("/simulator")
public class SimulatorController {

    @Autowired
    SimulatorService simulatorService;

    @PostMapping("/start/{time}")
    public ResponseEntity<String> start(@PathVariable int time) {
        simulatorService.start(time);
        return ResponseEntity.ok("Simulación creada exitosamente");
    }

    @GetMapping("/results/{clock}")
    public ResponseEntity<Data> results(@PathVariable int clock) {
        Data result = simulatorService.getResult(clock);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }
}
