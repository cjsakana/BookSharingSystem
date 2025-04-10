package kg.us.sakanatang.common;

import lombok.Data;
import lombok.Getter;

//@Data
@Getter
class Res<T>{
    private int code;
    private String msg;
    private T data;

    public Res(T data) {
        this(200,"success",data);
    }
    public Res(int code, String msg) {
        this(code,msg,null);
    }
    public Res(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}


@Data
public class ApiResponse<T> {
    private  int code=200;
    private Res<T> data;

    public static <T> ApiResponse<T> success() {
        return success(null);
    }

    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setData(new Res<>(data));
        return response;
    }

    public static <T> ApiResponse<T> fail(int code,String msg) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(200);
        response.setData(new Res<>(code,msg));
        return response;
    }
}