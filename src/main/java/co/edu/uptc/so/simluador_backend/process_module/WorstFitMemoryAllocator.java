package co.edu.uptc.so.simluador_backend.process_module;

import java.util.ArrayList;
import java.util.List;

// worst-fit
public class WorstFitMemoryAllocator {
    List<Memory> memory;

    public WorstFitMemoryAllocator(int totalMemorySize) {
        memory = new ArrayList<>();
        memory.add(new Memory(0, totalMemorySize));
    }

    public void allocateProcess(int processSize) {
        Memory worstFitBlock = null;
        for (Memory block : memory) {
            if (!block.allocated && block.sizeLimit >= processSize) {
                if (worstFitBlock == null || block.sizeLimit > worstFitBlock.sizeLimit) {
                    worstFitBlock = block;
                }
            }
        }

        if (worstFitBlock != null) {
            // Allocate the process in the worst-fit block
            worstFitBlock.allocated = true;
            if (worstFitBlock.sizeLimit > processSize) {
                // Split the block if there is extra space
                int remainingSize = worstFitBlock.sizeLimit - processSize;
                Memory newBlock = new Memory(worstFitBlock.id + 1, remainingSize);
                memory.add(memory.indexOf(worstFitBlock) + 1, newBlock);
                worstFitBlock.sizeLimit = processSize;
            }
            System.out.println("Allocated process of size " + processSize + " in block " + worstFitBlock.id);
        } else {
            System.out.println("Insufficient memory to allocate process of size " + processSize);
        }
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
        info.append("Process Queue:\n"); // You can add information about the process queue if you have it
        info.append("Occupied Memory: ").append(calculateOccupiedMemory()).append(" cells\n");
        info.append("Free Memory: ").append(calculateFreeMemory()).append(" cells\n");
        return info.toString();
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
}

// public class WorstFitMemoryAllocation {

//     public static void main(String[] args) {
//         WorstFitMemoryAllocator memoryAllocator = new WorstFitMemoryAllocator(100);

//         memoryAllocator.allocateProcess(20);
//         memoryAllocator.allocateProcess(40);
//         memoryAllocator.allocateProcess(10);

//         System.out.println(memoryAllocator.getMemoryInfo());
//     }
// }