package sunalways.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 17:28
 * @Description:
 */
public class StreamDemo2 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // 从集合创建
        list.stream();
        list.parallelStream();

        // 从数组创建
        Arrays.stream(new int[]{2, 3, 4});

        // 创建数字流
        IntStream.of(1, 2, 3);
        IntStream.range(1, 10);

        // 使用Random创建无限流
        new Random().ints().limit(3);

        // 自己产生流
        Random random = new Random();
        Stream.generate(() -> random.nextInt()).limit(20);
    }
}
