package co.edu.uptc.so.simluador_backend.process_module;

import java.util.ArrayList;

public class MemoryFinal {
    private ArrayList<BlockMemory> blockMemories;
    private int latestSavedIndex = 0;

    public MemoryFinal(int totalMemorySize) {
        this.blockMemories = new ArrayList<>();
        this.blockMemories.add(new BlockMemory(totalMemorySize, true, null));
    }

    public boolean assignFirstFit(Process process) {
        for (int i = 0; i < blockMemories.size(); i++) {
            BlockMemory bloque = blockMemories.get(i);
            if (bloque.isFree() && bloque.getSize() >= process.getSize()) {
                blockMemories.add(i, new BlockMemory(process.getSize(), false, process));
                bloque.setSize(bloque.getSize() - process.getSize());
                if (bloque.getSize() == 0) {
                    blockMemories.remove(i + 1);
                }
                return true;
            }
        }
        return false;
    }

    public boolean assignNextFit(Process process) {
        for (int i = latestSavedIndex; i < blockMemories.size(); i++) {
            BlockMemory bloque = blockMemories.get(i);
            if (bloque.isFree() && bloque.getSize() >= process.getSize()) {
                blockMemories.add(i, new BlockMemory(process.getSize(), false, process));
                latestSavedIndex = i;
                bloque.setSize(bloque.getSize() - process.getSize());
                if (bloque.getSize() == 0) {
                    blockMemories.remove(i + 1);
                }
                return true;
            }
        }
        return false;
    }

    public boolean assignBestFit(Process process) {
        int bestSize = 0; boolean assign = false;
        for (int i = 0; i < blockMemories.size(); i++) {
            if (blockMemories.get(i).isFree()
                    && (process.getSize() - blockMemories.get(i).getSize()) <= (process.getSize()
                            - blockMemories.get(bestSize).getSize())) {
                bestSize = i;
                assign = true;
            }
        }
        if (assign) {
            blockMemories.add(bestSize, new BlockMemory(process.getSize(), false, process));
            return true;
        }
        return false;
    }

    public boolean assignWorstFit(Process process) {
        int worstSize = 0; boolean assign = false;
        for (int i = 0; i < blockMemories.size(); i++) {
            if (blockMemories.get(i).isFree()
                    && (process.getSize() - blockMemories.get(i).getSize()) >= (process.getSize()
                            - blockMemories.get(worstSize).getSize())) {
                worstSize = i;
                assign = true;
            }
        }
        if (assign) {
            blockMemories.add(worstSize, new BlockMemory(process.getSize(), false, process));
            return true;
        }
        return false;
    }

    public void analizeAndRemove() {
        for (int i = 0; i < blockMemories.size(); i++) {
            if (blockMemories.get(i).getProcess() != null
                    && blockMemories.get(i).getProcess().getTimeToLive() == 0) {
                blockMemories.get(i).setFree(true);
                blockMemories.get(i).setProcess(null);
            }
        }
    }

    public ArrayList<BlockMemory> getBlockMemories() {
        return blockMemories;
    }
}
