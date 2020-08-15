package com.sunalways.lambda;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 16:20
 * @Description:
 */
@FunctionalInterface
interface IMath {
    int add(int x, int y);
}

@FunctionalInterface
interface IMath2 {
    int add(int x, int y);
}

public class TypeDemo {

    public static void main(String[] args) {
        // 变量类型定义
        IMath lambda = (x, y) -> x + y;

        // 数组定义
        IMath[] lambdas = {(x, y) -> x + y};

        // 强转
        Object lambda2 = (IMath) (x, y) -> x + y;

        // 通过返回类型
        IMath createLambda = createLambda();

        TypeDemo demo = new TypeDemo();
        demo.test((IMath) (x, y) -> x + y);
    }

    public void test(IMath imath) {

    }

    public void test(IMath2 imath2) {

    }


    public static IMath createLambda() {
        return (x, y) -> x + y;
    }
}
