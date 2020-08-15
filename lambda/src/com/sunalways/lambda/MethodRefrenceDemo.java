package com.sunalways.lambda;

import java.util.function.*;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 15:41
 * @Description:
 */
class Dog {
    private String name = "哮天犬";

    private int food = 10;

    public Dog() {
    }

    /**
     * 带参数的构造函数
     * @param name
     */
    public Dog(String name) {
        this.name = name;
    }

    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    /**
     * JDK默认会把当前实例传入到非静态方法，参数名为this, 位置是第一个
     * @param num
     * @return
     */
    public int eat(Dog this, int num) {
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodRefrenceDemo {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(1);

        // 方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("接受的数据");

        // 静态方法的方法引用
        Consumer<Dog> consumer1 = Dog::bark;

        consumer1.accept(dog);

        // 非静态方法，使用对象实例的方法引用
//        Function<Integer, Integer> function = dog::eat;
//        UnaryOperator<Integer> function = dog::eat;
        IntUnaryOperator function = dog::eat;
//        System.out.println(function.apply(3));
        System.out.println(function.applyAsInt(3));

        // Dog::ear 使用类名来方法引用
        BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
        System.out.println(eatFunction.apply(dog, 2));

        // 构造函数的方法引用
        Supplier<Dog> supplier = Dog::new;
        System.out.println(supplier.get().eat(3));

        // 带参数的构造函数的方法引用
        Function<String, Dog> function1 = Dog::new;
        System.out.println(function1.apply("111").toString());
    }
}