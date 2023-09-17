package co.edu.uptc.so.simluador_backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiRespone {
    private String message;
    private boolean success;
    private int status;
    private Object data;
}
