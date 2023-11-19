package co.edu.uptc.so.simluador_backend.DTO;

public class DataMemory {
    private int time;
    private int sizeMemory;
    private TypeAlghoritm typeAlghoritm;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSizeMemory() {
        return sizeMemory;
    }

    public void setSizeMemory(int sizeMemory) {
        this.sizeMemory = sizeMemory;
    }

    public TypeAlghoritm getTypeAlghoritm() {
        return typeAlghoritm;
    }

    public void setTypeAlghoritm(TypeAlghoritm typeAlghoritm) {
        this.typeAlghoritm = typeAlghoritm;
    }

    public DataMemory(){}

    public DataMemory(int time, int sizeMemory, TypeAlghoritm typeAlghoritm) {
        this.time = time;
        this.sizeMemory = sizeMemory;
        this.typeAlghoritm = typeAlghoritm;
    }

}
