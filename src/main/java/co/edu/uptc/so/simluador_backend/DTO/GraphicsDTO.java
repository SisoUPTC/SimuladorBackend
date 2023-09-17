package co.edu.uptc.so.simluador_backend.DTO;

import co.edu.uptc.so.simluador_backend.util.GraphicData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphicsDTO {
    private int[] quantyProcessQueues;
    private GraphicData[] quantyEndendCPU;
    private GraphicData[] timesTTL;
    private GraphicData[] timesNextIO;
    private GraphicData[] timesIO;
    private int totalEndedProccess;

}
