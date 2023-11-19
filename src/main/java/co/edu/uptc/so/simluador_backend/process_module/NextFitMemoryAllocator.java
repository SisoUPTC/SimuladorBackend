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
        Memory currentBlock = memory.get(nextFitPointer);
        int startIndex = nextFitPointer;

        do {
            if (!currentBlock.allocated && currentBlock.sizeLimit >= processSize) {
                // Allocate the process in the current block
                currentBlock.allocated = true;
                System.out.println("Allocated process of size " + processSize + " in block " + currentBlock.id);
                return;
            }
            nextFitPointer = (nextFitPointer + 1) % memory.size();
            currentBlock = memory.get(nextFitPointer);
        } while (nextFitPointer != startIndex);

        System.out.println("Insufficient memory to allocate process of size " + processSize);
    }

    public void deallocateProcess(int processSize) {
        for (Memory block : memory) {
            if (block.allocated && block.sizeLimit == processSize) {
                block.allocated = false;
                System.out.println("Deallocated process of size " + processSize + " from block " + block.id);
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
}

// public class NextFitMemoryAllocation {
// public static void main(String[] args) {
// NextFitMemoryAllocator memoryAllocator = new NextFitMemoryAllocator(100);

// memoryAllocator.allocateProcess(20);
// memoryAllocator.allocateProcess(40);
// memoryAllocator.allocateProcess(10);
// memoryAllocator.printMemoryStatus();

// memoryAllocator.deallocateProcess(40);
// memoryAllocator.printMemoryStatus();
// }
// }