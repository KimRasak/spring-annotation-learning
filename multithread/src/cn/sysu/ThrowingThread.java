package cn.sysu;

// 线程池使用execute时，调用UncaughtExceptionHandler
public class ThrowingThread extends Thread {
    @Override
    public void run() {
        throw new ArithmeticException();
    }

    public static void main(String[] args) {
	    Thread thread = new ThrowingThread();
	    thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("处理异常");
            }
        });
	    thread.start();
    }
}
