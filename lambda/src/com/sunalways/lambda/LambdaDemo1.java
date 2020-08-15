package com.sunalways.lambda;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 11:45
 * @Description:
 */
@FunctionalInterface
interface Interface1 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface2 {
    int doubleNum(int i);

    default int add(int x, int y) {
        return x + y;
    }
}

@FunctionalInterface
interface Interface3 extends Interface1, Interface2 {

    int doubleNum(int i);

    @Override
    default int add(int x, int y) {
        return Interface1.super.add(x, y);
    }
}


public class LambdaDemo1 {

    public static void main(String[] args) {
        // 常见
        Interface1 i1 = (i) -> i * 2;

        System.out.println(i1.add(3, 66));

        Interface1 i2 = (int i) -> i * 2;

        Interface1 i3 = (int i) -> {
            System.out.println(i);
            return i * 2;
        };


    }
}
