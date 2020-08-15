package sunalways.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 17:41
 * @Description:
 */
public class StreamDemo4 {

    public static void main(String[] args) {
        String str = "my name is 007";

        // 使用并行流
        str.chars().parallel().forEach(i -> System.out.print((char) i));
        System.out.println();
        // 使用forEachOrdered保证顺序
        str.chars().parallel().forEachOrdered(i -> System.out.print((char) i));
        System.out.println();

        // 收集到list
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        // 使用reduce拼接字符串
        Optional<String> letters = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(letters.orElse(""));

        // 带初始化的reduce
        String reduce = Stream.of(str.split(" ")).reduce("", (s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce);

        // 计算所有单词总长度
        Integer sum = Stream.of(str.split(" ")).map(s -> s.length()).reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        Optional<String> max = Stream.of(str.split(" ")).max(Comparator.comparingInt(String::length));
        System.out.println(max.get());

        // 短路操作
        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());
    }
}
