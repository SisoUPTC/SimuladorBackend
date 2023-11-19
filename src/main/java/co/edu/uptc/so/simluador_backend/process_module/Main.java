package co.edu.uptc.so.simluador_backend.process_module;

public class Main {
    public static void main(String[] args) {
        int blockSizes[] = {60, 20, 40, 70, 300};
        int processSizes[] = {20, 60, 70, 40};

        new Memoria(blockSizes, processSizes);
    }
}
