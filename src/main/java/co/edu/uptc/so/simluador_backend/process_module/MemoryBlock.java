package co.edu.uptc.so.simluador_backend.process_module;

public class MemoryBlock {
    private int size;

    public MemoryBlock(int size) {
        this.size = size;
    }

    public MemoryBlock() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}