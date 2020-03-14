package cn.sysu;

import java.util.concurrent.*;

// 线程池使用execute抛出Unchecked异常时，调用UncaughtExceptionHandler
public class CatchingPool {
    static class MyThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((t1, e) -> {
                System.out.println("UncaughtExceptionHandler捕获异常");
            });
            return t;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 10, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(10), new FuturePool.MyThreadFactory());
        executorService.execute(() -> {
            throw new ArithmeticException();
        });
        executorService.shutdown();
    }
}
