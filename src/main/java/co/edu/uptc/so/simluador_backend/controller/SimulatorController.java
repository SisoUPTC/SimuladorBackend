package co.edu.uptc.so.simluador_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.DTO.GraphicsDTO;
import co.edu.uptc.so.simluador_backend.services.SimulatorService;
import co.edu.uptc.so.simluador_backend.util.ApiRespone;

@RestController
@RequestMapping("/simulator")
@CrossOrigin(origins = "https://simulatorcpu.netlify.app/")
public class SimulatorController {

    @Autowired
    SimulatorService simulatorService;

    @PostMapping("/start/{time}")
    public ResponseEntity<ApiRespone> start(@PathVariable int time) {
        try {
            simulatorService.start(time);
            ApiRespone apiRespone = new ApiRespone("simulacion creada exitosamente ", true, 200, "simulacion creada");
            return new ResponseEntity<>(apiRespone, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ApiRespone apiRespone = new ApiRespone("Error al crear la simulacion", false, 400, e);
            return new ResponseEntity<>(apiRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/results/{clock}")
    public ResponseEntity<ApiRespone> results(@PathVariable int clock) {
        try {
            Data result = simulatorService.getResult(clock);
            ApiRespone apiRespone = new ApiRespone("resultado de la simulacion", true, 200, result);
            return new ResponseEntity<>(apiRespone, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            ApiRespone apiRespone = new ApiRespone("Ha ocurrido un error al traer resultados de la simulacion", false,
                    400, e);
            return new ResponseEntity<>(apiRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/graphics")
    public ResponseEntity<ApiRespone> graphics() {
        try {
            GraphicsDTO result = simulatorService.getGraphics();
            ApiRespone apiRespone = new ApiRespone("Graficas de la simulacion", true, 200, result);
            return new ResponseEntity<>(apiRespone, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            ApiRespone apiRespone = new ApiRespone("Ha ocurrido un error al traer graficas de la simulacion", false,
                    400, e);
            return new ResponseEntity<>(apiRespone, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/isStarted")
    public ResponseEntity<ApiRespone> isStarted(){
        try {
            boolean result = simulatorService.isStarted();
            ApiRespone apiRespone = new ApiRespone("Estado de la simulacion", true, 200, result);
            return new ResponseEntity<>(apiRespone, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            ApiRespone apiRespone = new ApiRespone("Ha ocurrido un error al traer el estado de la simulacion", false,
                    400, e);
            return new ResponseEntity<>(apiRespone, HttpStatus.BAD_REQUEST);
        }
    }
}
