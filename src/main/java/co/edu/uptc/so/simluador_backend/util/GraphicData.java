package co.edu.uptc.so.simluador_backend.util;

public class GraphicData {
    private int name;
    private int value;

    public GraphicData(int name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
