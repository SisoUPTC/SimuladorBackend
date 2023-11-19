package co.edu.uptc.so.simluador_backend.process_module;

import java.util.ArrayList;
import java.util.List;
//ArrayList<Process>processeList;
//ArrayList<Process>inputList;

public class NextFitMemoryAllocator {
    List<Memory> memory;
    int nextFitPointer;

    public NextFitMemoryAllocator(int totalMemorySize) {
        memory = new ArrayList<>();
        memory.add(new Memory(0, totalMemorySize));
        nextFitPointer = 0;
    }

    public void allocateProcess(int processSize) {
        int startIndex = nextFitPointer;
        int remainingSize = processSize;

        do {
            Memory currentBlock = memory.get(nextFitPointer);

            if (!currentBlock.allocated && currentBlock.sizeLimit >= remainingSize) {
                // Allocate the process in the current block
                currentBlock.allocated = true;
                System.out.println("Allocated process of size " + processSize + " in block " + currentBlock.id);
                // Update the remaining size and reset the pointer
                remainingSize = 0;
                nextFitPointer = (nextFitPointer + 1) % memory.size();
                break;
            }

            nextFitPointer = (nextFitPointer + 1) % memory.size();
        } while (nextFitPointer != startIndex);

        if (remainingSize > 0) {
            System.out.println("Insufficient memory to allocate process of size " + processSize);
        }

        // Update the memory information
        updateMemoryInfo();
    }

    private void updateMemoryInfo() {
        // Merge adjacent unallocated blocks
        List<Memory> mergedMemory = new ArrayList<>();
        Memory currentBlock = memory.get(0);

        for (int i = 1; i < memory.size(); i++) {
            Memory nextBlock = memory.get(i);

            if (!currentBlock.allocated && !nextBlock.allocated) {
                currentBlock.sizeLimit += nextBlock.sizeLimit;
            } else {
                mergedMemory.add(currentBlock);
                currentBlock = nextBlock;
            }
        }

        mergedMemory.add(currentBlock);
        memory = mergedMemory;
    }

    public void deallocateProcess(int processSize) {
        for (Memory block : memory) {
            if (block.allocated && block.sizeLimit == processSize) {
                block.allocated = false;
                System.out.println("Deallocated process of size " + processSize + " from block " + block.id);
                mergeAdjacentBlocks();
                return;
            }
        }
        System.out.println("Process of size " + processSize + " not found for deallocation.");
    }

    public void printMemoryStatus() {
        System.out.println("Memory Status:");
        for (Memory block : memory) {
            System.out.println("Block " + block.id + ": Size=" + block.sizeLimit + " Allocated=" + block.allocated);
        }
    }

    public String getMemoryInfo() {
        StringBuilder info = new StringBuilder("Memory Information:\n");
        info.append("Process Queue:\n"); // Add information about the process queue if available
        info.append("Occupied Memory: ").append(calculateOccupiedMemory()).append(" cells\n");
        info.append("Free Memory: ").append(calculateFreeMemory()).append(" cells\n");
        return info.toString();
    }

    private void mergeAdjacentBlocks() {
        // Merge adjacent unallocated blocks
        List<Memory> mergedMemory = new ArrayList<>();
        Memory currentBlock = memory.get(0);
        for (int i = 1; i < memory.size(); i++) {
            Memory nextBlock = memory.get(i);
            if (!currentBlock.allocated && !nextBlock.allocated) {
                currentBlock.sizeLimit += nextBlock.sizeLimit;
            } else {
                mergedMemory.add(currentBlock);
                currentBlock = nextBlock;
            }
        }
        mergedMemory.add(currentBlock);
        memory = mergedMemory;
    }

    private int calculateOccupiedMemory() {
        int occupiedMemory = 0;
        for (Memory block : memory) {
            if (block.allocated) {
                occupiedMemory += block.sizeLimit;
            }
        }
        return occupiedMemory;
    }

    private int calculateFreeMemory() {
        int freeMemory = 0;
        for (Memory block : memory) {
            if (!block.allocated) {
                freeMemory += block.sizeLimit;
            }
        }
        return freeMemory;
    }
}

