package co.edu.uptc.so.simluador_backend.process_module;


public class Memory {
    // ArrayList<Process> processeList;
    // ArrayList<Process> inputList;
    int id;
    int sizeLimit;
    boolean allocated;
    // int option = 1; // 1

    public Memory(int id, int sizeLimit) {
        this.id = id;
        this.sizeLimit = sizeLimit;
        this.allocated = false;
    }

    public int getId(){
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

// First-fit, best-fit
