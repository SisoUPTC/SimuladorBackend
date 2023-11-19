package co.edu.uptc.so.simluador_backend.process_module;

public class Memory {
    protected int id;
    protected int sizeLimit;
    protected boolean allocated;

    public Memory(int id, int sizeLimit) {
        this.id = id;
        this.sizeLimit = sizeLimit;
        this.allocated = false;
    }

    public Memory() {
    }

    public int getId() {
        return id;
    }

    public int getSizeLimit() {
        return sizeLimit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }
}