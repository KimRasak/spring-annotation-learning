package cn.sysu;


import java.util.concurrent.*;

// 线程池使用Future.get抛出异常时，不调用UncaughtExceptionHandler
public class FuturePool {
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
        ExecutorService executorService = new ThreadPoolExecutor(5, 5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10), new MyThreadFactory());

        Future<String> future = executorService.submit(() -> {
            throw new RuntimeException("异常");
        });

        try {
            future.get();

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Future捕获异常");
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}