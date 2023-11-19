package co.edu.uptc.so.simluador_backend.process_module;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Component;

import co.edu.uptc.so.simluador_backend.DTO.Data;
import co.edu.uptc.so.simluador_backend.DTO.DataMemory;
import co.edu.uptc.so.simluador_backend.controller.GraphicsMemoryDTO;
import co.edu.uptc.so.simluador_backend.util.GraphicData;

@Component
public class SimulatorMemory {
    private int clock;
    private ArrayList<Data> dataProccesses;
    private ArrayList<MemoryData> dataMemory;
    private ArrayList<GraphicsMemoryDTO> dataGraphics;
    private MemoryFinal memory;

    public SimulatorMemory() {
        this.clock = 0;
        this.dataProccesses = new ArrayList<>();
        this.dataMemory = new ArrayList<>();
        this.dataGraphics = new ArrayList<>();
    }

    public boolean start(DataMemory dataMemory, Simulator simulator) {
        clearData();
        init(dataMemory, simulator);
        switch (dataMemory.getTypeAlghoritm()) {
            case FIRST_FIT -> runFirstFit();
            case BEST_FIT -> runBestFit();
            case WORST_FIT -> runWorstFit();
            case NEXT_FIT -> runNextFit();
        }
        return true;
    }

    private void clearData() {
        dataMemory.clear();
        dataProccesses.clear();
        dataGraphics.clear();
    }

    private void init(DataMemory dataMemory, Simulator simulator) {
        this.clock = dataMemory.getTime();
        this.memory = new MemoryFinal(dataMemory.getSizeMemory());
        this.dataProccesses = simulator.getData();
        for (int i = 0; i < dataProccesses.size(); i++) {
            if (dataProccesses.get(i).getCpuProcess() != null) {
                dataProccesses.get(i).getCpuProcess()
                        .setSize(new Random().nextInt(1024));
            }
        }
    }

    private void runWorstFit() {
        int actualClock = 0;
        ArrayList<Process> processes = new ArrayList<>();
        while (actualClock < this.clock) {

            // verificar si hay procesos nuevos en la CPU en el tiempo de reloj actual
            if (someProccessToMemory(actualClock, this.memory.getBlockMemories())) {
                // si hay agregar a la cola de procesos
                processes.add(this.dataProccesses.get(actualClock).getCpuProcess());
                // intentar añadir cada proceso de la lista a la memoria

                for (int i = 0; i < processes.size(); i++) {
                    if (this.memory.assignWorstFit(processes.get(i))) {
                        // si lo agrega lo remueve de la cola de procesos
                        processes.remove(i);
                    }
                }
            }

            // agregar la data general para front
            ArrayList<BlockMemory> copyMemory = new ArrayList<>();
            for (int i = 0; i < this.memory.getBlockMemories().size(); i++) {
                copyMemory.add(new BlockMemory(this.memory.getBlockMemories().get(i)));
            }

            ArrayList<Process> copyProccesses = new ArrayList<>();
            for (int i = 0; i < processes.size(); i++) {
                copyProccesses.add(new Process(processes.get(i)));
            }

            // Agregar data para graficas
            GraphicsMemoryDTO graphicsMemoryDTO = createDataGraphics(actualClock, copyProccesses, copyMemory, clock - 1);
            this.dataGraphics.add(graphicsMemoryDTO);

            this.dataMemory.add(new MemoryData(actualClock, copyProccesses, copyMemory, clock - 1));

            // reducir tiempos de vida de procesos
            reduceTTL(this.memory.getBlockMemories());

            // verificar si tiempos de vida han acabado para quitarlos de la memoria
            this.memory.analizeAndRemove();
            actualClock++;
        }
    }

    private void runNextFit() {
        int actualClock = 0;
        ArrayList<Process> processes = new ArrayList<>();
        while (actualClock < this.clock) {

            // verificar si hay procesos nuevos en la CPU en el tiempo de reloj actual
            if (someProccessToMemory(actualClock, this.memory.getBlockMemories())) {
                // si hay agregar a la cola de procesos
                processes.add(this.dataProccesses.get(actualClock).getCpuProcess());
                // intentar añadir cada proceso de la lista a la memoria

                for (int i = 0; i < processes.size(); i++) {
                    if (this.memory.assignNextFit(processes.get(i))) {
                        // si lo agrega lo remueve de la cola de procesos
                        processes.remove(i);
                    }
                }
            }

            // agregar la data general para front
            ArrayList<BlockMemory> copyMemory = new ArrayList<>();
            for (int i = 0; i < this.memory.getBlockMemories().size(); i++) {
                copyMemory.add(new BlockMemory(this.memory.getBlockMemories().get(i)));
            }

            ArrayList<Process> copyProccesses = new ArrayList<>();
            for (int i = 0; i < processes.size(); i++) {
                copyProccesses.add(new Process(processes.get(i)));
            }

             // Agregar data para graficas
            GraphicsMemoryDTO graphicsMemoryDTO = createDataGraphics(actualClock, copyProccesses, copyMemory, clock - 1);
            this.dataGraphics.add(graphicsMemoryDTO);

            this.dataMemory.add(new MemoryData(actualClock, copyProccesses, copyMemory, clock - 1));

            // reducir tiempos de vida de procesos
            reduceTTL(this.memory.getBlockMemories());

            // verificar si tiempos de vida han acabado para quitarlos de la memoria
            this.memory.analizeAndRemove();
            actualClock++;
        }
    }

    private void runFirstFit() {
        int actualClock = 0;
        ArrayList<Process> processes = new ArrayList<>();
        while (actualClock < this.clock) {

            // verificar si hay procesos nuevos en la CPU en el tiempo de reloj actual
            if (someProccessToMemory(actualClock, this.memory.getBlockMemories())) {
                // si hay agregar a la cola de procesos
                processes.add(this.dataProccesses.get(actualClock).getCpuProcess());
                // intentar añadir cada proceso de la lista a la memoria

                for (int i = 0; i < processes.size(); i++) {
                    if (this.memory.assignFirstFit(processes.get(i))) {
                        // si lo agrega lo remueve de la cola de procesos
                        processes.remove(i);
                    }
                }
            }

            // agregar la data general para front
            ArrayList<BlockMemory> copyMemory = new ArrayList<>();
            for (int i = 0; i < this.memory.getBlockMemories().size(); i++) {
                copyMemory.add(new BlockMemory(this.memory.getBlockMemories().get(i)));
            }

            ArrayList<Process> copyProccesses = new ArrayList<>();
            for (int i = 0; i < processes.size(); i++) {
                copyProccesses.add(new Process(processes.get(i)));
            }

            // Agregar data para graficas
            GraphicsMemoryDTO graphicsMemoryDTO = createDataGraphics(actualClock, copyProccesses, copyMemory, clock - 1);
            this.dataGraphics.add(graphicsMemoryDTO);

            this.dataMemory.add(new MemoryData(actualClock, copyProccesses, copyMemory, clock - 1));

            // reducir tiempos de vida de procesos
            reduceTTL(this.memory.getBlockMemories());

            // verificar si tiempos de vida han acabado para quitarlos de la memoria
            this.memory.analizeAndRemove();
            actualClock++;
        }
    }

    private void reduceTTL(ArrayList<BlockMemory> blockMemories) {
        for (BlockMemory blockMemory : blockMemories) {
            if (blockMemory.getProcess() != null && blockMemory.getProcess().getTimeToLive() > 0) {
                blockMemory.getProcess().setTimeToLive(blockMemory.getProcess().getTimeToLive() - 1);
            }
        }
    }

    private void runBestFit() {
        int actualClock = 0;
        ArrayList<Process> processes = new ArrayList<>();
        while (actualClock < this.clock) {

            // verificar si hay procesos nuevos en la CPU en el tiempo de reloj actual
            if (someProccessToMemory(actualClock, this.memory.getBlockMemories())) {
                // si hay agregar a la cola de procesos
                processes.add(this.dataProccesses.get(actualClock).getCpuProcess());
                // intentar añadir cada proceso de la lista a la memoria

                for (int i = 0; i < processes.size(); i++) {
                    if (this.memory.assignBestFit(processes.get(i))) {
                        // si lo agrega lo remueve de la cola de procesos
                        processes.remove(i);
                    }
                }
            }

            // agregar la data general para front
            ArrayList<BlockMemory> copyMemory = new ArrayList<>();
            for (int i = 0; i < this.memory.getBlockMemories().size(); i++) {
                copyMemory.add(new BlockMemory(this.memory.getBlockMemories().get(i)));
            }

            ArrayList<Process> copyProccesses = new ArrayList<>();
            for (int i = 0; i < processes.size(); i++) {
                copyProccesses.add(new Process(processes.get(i)));
            }

             // Agregar data para graficas
            GraphicsMemoryDTO graphicsMemoryDTO = createDataGraphics(actualClock, copyProccesses, copyMemory, clock - 1);
            this.dataGraphics.add(graphicsMemoryDTO);

            this.dataMemory.add(new MemoryData(actualClock, copyProccesses, copyMemory, clock - 1));

            // reducir tiempos de vida de procesos
            reduceTTL(this.memory.getBlockMemories());

            // verificar si tiempos de vida han acabado para quitarlos de la memoria
            this.memory.analizeAndRemove();
            actualClock++;
        }
    }

    private boolean someProccessToMemory(int actualClock, ArrayList<BlockMemory> blockMemories) {
        boolean pass = false;
        if (this.dataProccesses.get(actualClock).getCpuProcess() != null) {
            pass = true;
            for (int i = 0; i < blockMemories.size(); i++) {
                if (blockMemories.get(i).getProcess() != null && this.dataProccesses.get(actualClock).getCpuProcess()
                        .getId() == blockMemories.get(i).getProcess().getId()) {
                    pass = false;
                }
            }
        }
        return pass;
    }

    public ArrayList<MemoryData> getDataMemory() {
        return dataMemory;
    }

    public ArrayList<GraphicsMemoryDTO> getDataGraphics() {
        return this.dataGraphics;
    }

    private GraphicsMemoryDTO createDataGraphics(int clock, ArrayList<Process> copyProccesses,
            ArrayList<BlockMemory> copyMemory, int totalTimes) {
        GraphicsMemoryDTO graphicsMemoryDTO = new GraphicsMemoryDTO();

        graphicsMemoryDTO.setClock(clock);
        graphicsMemoryDTO.setTotalTimes(totalTimes);

        int quantyProcessMemory = 0, totalSpace = 0, spaceUsed = 0, spaceFree = 0, blocksFree = 0, blocksUsed = 0;
        ArrayList<GraphicData> promedioSizeProccessList = new ArrayList<>();

        for (BlockMemory blockMemory : copyMemory) {
            if (!blockMemory.isFree()) {
                quantyProcessMemory++;
                blocksUsed++;
                spaceUsed += blockMemory.getProcess().getSize();
                promedioSizeProccessList
                        .add(new GraphicData(blockMemory.getProcess().getSize(), blockMemory.getProcess().getSize()));
            } else {
                blocksFree++;
                spaceFree += blockMemory.getSize();
            }
            totalSpace += blockMemory.getProcess() != null ? blockMemory.getProcess().getSize() : blockMemory.getSize();
        }
        graphicsMemoryDTO.setQuantyProcessMemory(quantyProcessMemory);

        int percentageUse = spaceUsed * 100 / totalSpace;
        int percentageFree = spaceFree * 100 / totalSpace;
        GraphicData[] pomedioSizeProccess = new GraphicData[promedioSizeProccessList.size()];
        pomedioSizeProccess = promedioSizeProccessList.toArray(pomedioSizeProccess);

        graphicsMemoryDTO.setPercentageUse(percentageUse);
        graphicsMemoryDTO.setPercentageFree(percentageFree);
        graphicsMemoryDTO.setBlocksFree(blocksFree);
        graphicsMemoryDTO.setBlocksUsed(blocksUsed);
        graphicsMemoryDTO.setPromedioSizeProcess(pomedioSizeProccess);

        int quantyProcessWait = copyProccesses.size();
        graphicsMemoryDTO.setQuantyProcessWait(quantyProcessWait);

        return graphicsMemoryDTO;
    }
}