package cn.sysu.spring;

import cn.sysu.spring.A;

public class B extends A {
    public static int b = 3;

    static {
        System.out.println("loading cn.sysu.spring.B");
    }
}
