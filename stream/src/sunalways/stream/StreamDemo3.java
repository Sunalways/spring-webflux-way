package sunalways.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 17:33
 * @Description:
 */
public class StreamDemo3 {

    public static void main(String[] args) {
        String str = "my name is 007";

        // map
        Stream.of(str.split(" ")).filter(s -> s.length() > 2).map(String::length)
                .forEach(System.out::println);

        // flatMap a->b属性(b属性是个集合), 最终得到所有A元素的所有B属性集合
        // IntSream/LongStream并不是stream的子类，所以要进行装箱boxed
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(
                i -> System.out.println((char)i.intValue()));

        // peek 用于 debug, 是个中间操作, forEach是个中间操作
        Stream.of(str.split(" ")).peek(System.out::println).forEach(System.out::println);

        // limit 使用，用于无限流
        new Random().ints().filter(i -> i > 100 && i < 1000).limit(10).forEach(System.out::println);
    }
}
