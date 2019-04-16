package cn.sysu.spring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    // 处理除0异常(运行时异常)
    @ExceptionHandler
    public String testError(ArithmeticException e, HttpServletRequest request) {
        log.error("出现了除零异常", e);
        request.setAttribute("javax.servlet.error.status_code", 500);
        request.setAttribute(MyErrorAttribute.ATTRIBUTE_NAME, new MyErrorAttribute(66, "出现了除零异常"));
        return "forward:/error";
    }

    // 处理错误参数格式的异常
    @ExceptionHandler
    public String methodArgumentNotValid(BindException e) {
        log.error("参数校验失败", e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        StringBuilder errorMessage = new StringBuilder("|");
        allErrors.forEach(error -> {
            errorMessage.append(error.getDefaultMessage()).append("|");
        });
        return generateErrorInfo(1, errorMessage.toString(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    public String ageDeleteException(InvalidNameException e) {
        log.error("非法的用户名: ", e);
        return generateErrorInfo(-1, "使用了非法的用户名", HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(value = Exception.class)
    public String unknownException(Exception e) {
        log.error("发生了未知异常: ", e);
        return generateErrorInfo(-99, "系统故障, 请稍后再试!");
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public String noHandlerFound(NoHandlerFoundException e) {
        log.error("404:: ", e);
        return generateErrorInfo(-404, "找不到页面！");
    }

    /**
     * 生成错误信息, 放到 request 域中.
     * @param code          错误码
     * @param message       错误信息
     * @param httpStatus    HTTP 状态码
     * @return              SpringBoot 默认提供的 /error Controller 处理器
     */
    private String generateErrorInfo(int code, String message, int httpStatus) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute(MyErrorAttribute.ATTRIBUTE_NAME, new MyErrorAttribute(code, message));
        request.setAttribute("javax.servlet.error.status_code", httpStatus);
        return "forward:/error";
    }

    private String generateErrorInfo(int code, String message) {
        return generateErrorInfo(code, message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
