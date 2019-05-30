package cn.sysu.spring;

import cn.sysu.spring.annotation.ChangeValue;
import cn.sysu.spring.annotation.PrintMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(MyAspect.class.getName());

    @Pointcut("execution(public * cn.sysu.spring.controller..*(..))")
    public void pointCut(){
        System.out.println("in pointCut");
    }

    //声明前置通知
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        // 获得类名，方法名，参数和参数名称。
        Signature signature = joinPoint.getSignature(); // 获取签名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); // 连接点是方法，所以可转化为方法签名

        String[] argumentNames = methodSignature.getParameterNames();

        StringBuilder sb = new StringBuilder(className + "." + methodName + "(");

        for (int i = 0; i< arguments.length; i++) {
            Object argument = arguments[i];
            sb.append(argumentNames[i] + "->");
            sb.append(argument != null ? argument.toString() : "null ");
        }
        sb.append(")");

        LOGGER.info(sb.toString());
    }

    /**
     * 注解了{@link PrintMessage}的对象，在调用方法的前后会打印字符串。
     * @param joinPoint
     * @param print
     * @return 被调用方法的实际返回值。
     * @throws Throwable
     */
    @Around(value = "@annotation(print)")
    public Object printMessage(ProceedingJoinPoint joinPoint, PrintMessage print) throws Throwable {
        System.out.println("print Message: around method before");
        Object result = joinPoint.proceed();
        System.out.println("print Message: around method after");
        return result;
    }

    /**
     * 注解了{@link ChangeValue}的对象，其调用方法时，若有接受方法参数，
     * 则第一个参数会被改为{@code "hello world!"}。
     * @param joinPoint 调用方法的连接点
     * @param change 注解
     * @return 被调用方法的实际返回值。
     * @throws Throwable
     */
    @Around(value = "@annotation(change)")
    public Object changeValue(ProceedingJoinPoint joinPoint, ChangeValue change) throws Throwable {
        System.out.println("change value: around method before");
        Object[] args = joinPoint.getArgs();
        if (args.length > 0)
            return joinPoint.proceed(new Object[]{"hello world!"});
        else
            return joinPoint.proceed();
    }

}
