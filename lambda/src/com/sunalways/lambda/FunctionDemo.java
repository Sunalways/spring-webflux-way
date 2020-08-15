package com.sunalways.lambda;

import java.util.function.*;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 15:22
 * @Description:
 */
public class FunctionDemo {

    public static void main(String[] args) {
        // 断言函数接口
        Predicate<Integer> predicate = i -> i > 0;
        System.out.println(predicate.test(-9));

        // 消费函数接口
        Consumer<String> consumer = s -> System.out.println("s");
        consumer.accept("输入的接口");

        // 输入T 输出R 的函数
        Function<Integer, Integer> function = (i) -> i * 2;
        System.out.println(function.apply(3));

        // 提供一个数据
        Supplier<Integer> supplier = () -> 2;
        System.out.println(supplier.get());

        // 一元函数， 输入输出类型相同
        UnaryOperator<Integer> unaryOperator = i -> i * 2;
        System.out.println(unaryOperator.apply(3));

        // 两个输入的函数
        BiFunction<Integer, Integer, Integer> biFunction = (x, y) -> x + y;
        System.out.println(biFunction.apply(3, 5));

        // 二元函数
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(binaryOperator.apply(3, 5));
    }
}
