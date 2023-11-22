package co.edu.uptc.so.simluador_backend.DTO;

import co.edu.uptc.so.simluador_backend.util.GraphicData;

public class GraphicsDTO {
    private int[] quantyProcessQueues;
    private GraphicData[] quantyEndendCPU;
    private GraphicData[] timesTTL;
    private GraphicData[] timesNextIO;
    private GraphicData[] timesIO;
    private int totalEndedProccess;

    public GraphicsDTO(int[] quantyProcessQueues, GraphicData[] quantyEndendCPU, GraphicData[] timesTTL,
            GraphicData[] timesNextIO, GraphicData[] timesIO, int totalEndedProccess) {
        this.quantyProcessQueues = quantyProcessQueues;
        this.quantyEndendCPU = quantyEndendCPU;
        this.timesTTL = timesTTL;
        this.timesNextIO = timesNextIO;
        this.timesIO = timesIO;
        this.totalEndedProccess = totalEndedProccess;
    }

    public int[] getQuantyProcessQueues() {
        return quantyProcessQueues;
    }

    public GraphicData[] getQuantyEndendCPU() {
        return quantyEndendCPU;
    }

    public GraphicData[] getTimesTTL() {
        return timesTTL;
    }

    public GraphicData[] getTimesNextIO() {
        return timesNextIO;
    }

    public GraphicData[] getTimesIO() {
        return timesIO;
    }

    public int getTotalEndedProccess() {
        return totalEndedProccess;
    }

    public void setQuantyProcessQueues(int[] quantyProcessQueues) {
        this.quantyProcessQueues = quantyProcessQueues;
    }

    public void setQuantyEndendCPU(GraphicData[] quantyEndendCPU) {
        this.quantyEndendCPU = quantyEndendCPU;
    }

    public void setTimesTTL(GraphicData[] timesTTL) {
        this.timesTTL = timesTTL;
    }

    public void setTimesNextIO(GraphicData[] timesNextIO) {
        this.timesNextIO = timesNextIO;
    }

    public void setTimesIO(GraphicData[] timesIO) {
        this.timesIO = timesIO;
    }

    public void setTotalEndedProccess(int totalEndedProccess) {
        this.totalEndedProccess = totalEndedProccess;
    }

    

}
