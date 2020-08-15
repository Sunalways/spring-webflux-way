package com.sunalways.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 15:14
 * @Description:
 */


class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat) {
        System.out.println("我的存款：" + moneyFormat.apply(this.money));
    }
}

public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney me = new MyMoney(9999999);
        Function<Integer, String> integerStringFunction = i -> new DecimalFormat("#,###").format(i);
        me.printMoney(integerStringFunction.andThen(s -> "人民币:" + s));
    }
}
