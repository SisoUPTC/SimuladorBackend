package co.edu.uptc.so.simluador_backend.process_module;

public class BlockMemory {
    private int size;
    private boolean isFree;
    private Process process;

    public BlockMemory(int size, boolean isFree, Process process) {
        this.size = size;
        this.isFree = isFree;
        this.process = process;
    }

    public BlockMemory(BlockMemory other) {
        this.size = other.size;
        this.isFree = other.isFree;
        this.process = (other.process != null) ? new Process(other.process) : null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "BlockMemory [size=" + size + ", isFree=" + isFree + ", process=" + process + "]";
    }

}
