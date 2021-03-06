package ${packageName}.model.api;

import ${packageName}.constant.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Api 通用返回格式实体类
 * @author ${author}
 * @since ${now?string("yyyy-MM-dd zzzz")}
 */
@ApiModel(description = "通用返回格式")
public class ApiResult<T> {

    @ApiModelProperty("接口返回实体")
    public T result;
    @ApiModelProperty("返回代码：200-成功 401-未登录 400-客户端错误 500-服务端错误")
    public int code;
    @ApiModelProperty("失败原因")
    public String message = "调用成功";
    @ApiModelProperty("错误详细")
    public Object errors = "";

    public ApiResult(T result, int code) {
        this.result = result;
        this.code = code;
    }

    public ApiResult(T result, int code, String message) {
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public ApiResult(T result, int code, String message, Object errors) {
        this(result, code, message);
        this.errors = errors;
    }

    public static <T> ApiResult<T> message(String message) {
        return new ApiResult<>(null, 200, message);
    }

    public static <T> ApiResult<T> success(T result) {
        return new ApiResult<>(result, 200);
    }

    public static <T> ApiResult<T> success(T result, String message) {
        return new ApiResult<>(result, 200, message);
    }

    public static <T> ApiResult<T> fail(ResultCode code) {
        return new ApiResult<>(null, code.code, code.message);
    }

    public static <T> ApiResult<T> fail(int code, String message) {
        return new ApiResult<>(null, code, message);
    }

    public static <T> ApiResult<T> failClient(String message) {
        return new ApiResult<>(null, 400, message);
    }

    public static <T> ApiResult<T> failServer(String message) {
        return new ApiResult<>(null, 500, message);
    }
}