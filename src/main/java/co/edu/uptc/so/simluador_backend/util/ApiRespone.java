package co.edu.uptc.so.simluador_backend.util;


public class ApiRespone {
    private String message;
    private boolean success;
    private int status;
    private Object data;

    public ApiRespone(String message, boolean success, int status, Object data) {
        this.message = message;
        this.success = success;
        this.status = status;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    
}
