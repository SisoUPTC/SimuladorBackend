package co.edu.uptc.so.simluador_backend.util;

import java.util.Random;

public final class RandomUltil {
    private static Random random = new Random();

    public static int random(int value) {
        return random.nextInt(value)+1;
    }

    private RandomUltil() {
        throw new IllegalStateException("Utility class");
    }

}
