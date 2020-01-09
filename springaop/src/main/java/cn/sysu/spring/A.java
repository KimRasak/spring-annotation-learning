package cn.sysu.spring;

public class A {
    public static int foo = 3;

    static {
        System.out.println("load A");
    }

    public void bar() {
        System.out.println("foo");
    }
}
