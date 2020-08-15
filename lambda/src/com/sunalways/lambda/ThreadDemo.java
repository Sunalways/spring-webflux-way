package com.sunalways.lambda;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 11:40
 * @Description:
 */
public class ThreadDemo {

    public static void main(String[] args) {
        Object ok = new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        };
        new Thread((Runnable) ok).start();

        // jdk8+ lambda
        Object runnable = (Runnable)() -> System.out.println("ok");
        Runnable runnable2 = () -> System.out.println("ok");
        System.out.println(runnable == runnable2);
        new Thread((Runnable) runnable).start();
    }
}
