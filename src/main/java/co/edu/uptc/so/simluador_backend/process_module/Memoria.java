package co.edu.uptc.so.simluador_backend.process_module;

public class Memoria {
    private MemoryBlock[] blocks;
    private int[] firstFitAllocate;
    private int[] bestFitAllocate;

    public Memoria(int[] blockSizes, int[] processSizes) {
        initializeBlocks(blockSizes);
        this.firstFitAllocate = new int[processSizes.length];
        this.bestFitAllocate = new int[processSizes.length];

        System.out.println("=== First Fit ===");
        implementFirstFit(processSizes);
        displayResults(processSizes, firstFitAllocate);

        initializeBlocks(blockSizes); // Reset blocks for Best Fit
        System.out.println("\n=== Best Fit ===");
        implementBestFit(processSizes);
        displayResults(processSizes, bestFitAllocate);
    }

    private void initializeBlocks(int[] blockSizes) {
        blocks = new MemoryBlock[blockSizes.length];
        for (int i = 0; i < blockSizes.length; i++) {
            blocks[i] = new MemoryBlock(blockSizes[i]);
        }
    }

    private void implementFirstFit(int[] processSizes) {
        for (int i = 0; i < processSizes.length; i++) {
            System.out.println("\nAllocating process with size " + processSizes[i] + " using First Fit");
            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j].getSize() >= processSizes[i]) {
                    firstFitAllocate[i] = j + 1;
                    blocks[j].setSize(blocks[j].getSize() - processSizes[i]);
                    System.out.println("Process " + (i + 1) + " allocated to Block " + firstFitAllocate[i]);
                    break;
                }
            }
        }
    }

    private void implementBestFit(int[] processSizes) {
        for (int i = 0; i < processSizes.length; i++) {
            System.out.println("\nAllocating process with size " + processSizes[i] + " using Best Fit");
            int cntrl = Integer.MAX_VALUE;
            int loc = -1;

            for (int j = 0; j < blocks.length; j++) {
                if (blocks[j].getSize() >= processSizes[i] && blocks[j].getSize() < cntrl) {
                    cntrl = blocks[j].getSize();
                    loc = j;
                }
            }

            if (loc == -1) {
                System.out.println("Space not available for process " + (i + 1));
            } else {
                bestFitAllocate[i] = loc + 1;
                blocks[loc].setSize(blocks[loc].getSize() - processSizes[i]);
                System.out.println("Process " + (i + 1) + " allocated to Block " + bestFitAllocate[i]);
            }
        }
    }

    private void displayResults(int[] processSizes, int[] allocate) {
        System.out.println("\nFinal Memory State:");
        for (int i = 0; i < blocks.length; i++) {
            System.out.println("Block " + (i + 1) + ": " + blocks[i].getSize());
        }

        System.out.println("\nProcess No.\tProcess Size\tBlock no.\n");
        for (int i = 0; i < processSizes.length; i++) {
            System.out.print(i + 1 + "\t\t\t" + processSizes[i] + "\t\t\t");
            if (allocate[i] != 0)
                System.out.println(allocate[i]);
            else
                System.out.println("Not Allocated");
        }
    }
}
