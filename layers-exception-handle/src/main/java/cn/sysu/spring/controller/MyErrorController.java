package cn.sysu.spring.controller;

import cn.sysu.spring.exception.MyErrorAttribute;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> body = getErrorAttributes(webRequest);
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(body, status);
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        Map<String, Object> map = new HashMap<>();
        MyErrorAttribute myErrorAttribute = (MyErrorAttribute) webRequest
                .getAttribute(MyErrorAttribute.ATTRIBUTE_NAME, RequestAttributes.SCOPE_REQUEST);

        if (myErrorAttribute == null) {
            Integer code = (Integer) webRequest.getAttribute("javax.servlet.error.status_code", RequestAttributes.SCOPE_REQUEST);;
            Object message = "default wrong message";
            if (code != null) {
                message = HttpStatus.valueOf(code).getReasonPhrase();
            }
            map.put("code", code);
            map.put("message", message);
        } else {
            map.put("code", myErrorAttribute.getCode());
            map.put("message", myErrorAttribute.getMessage());
        }

        return map;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
