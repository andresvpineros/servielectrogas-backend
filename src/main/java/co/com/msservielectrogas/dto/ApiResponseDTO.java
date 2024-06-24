package co.com.msservielectrogas.dto;

public class ApiResponseDTO<T> {
    private String message;
    private int code;
    private T data;

    public ApiResponseDTO(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}