package com.sunalways.lambda;

import java.util.function.Function;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 16:31
 * @Description: 级联表达式喝柯里化
 * 柯里化：把多个参数的函数转换为只有一个参数的函数
 * 目的: 函数标准化
 * 高阶函数: 返回函数的函数
 */
public class CurryDemo {

    public static void main(String[] args) {
        // x + y 级联表达式
        Function<Integer, Function<Integer, Integer>> function = x -> y -> x + y;
        System.out.println(function.apply(2).apply(3));

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function1 = x -> y -> z -> x + y + z;
        System.out.println(function1.apply(2).apply(3).apply(4));


    }
}
