package cn.sysu.spring.exception;

public class MyErrorAttribute {
    public static final String ATTRIBUTE_NAME = "cn.sysu.spring.error.attribute";
    private int code;
    private String message;

    public MyErrorAttribute(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
