package sunalways.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 20:10
 * @Description:
 * 1. 所有操作是链式调用，一个元素只迭代一次
 * 2. 每个中间操作返回一个新的流，流里面有一个属性sourceStage执行同一个地方就是Head
 * 3. Head -> nextStage -> nextStage -> .. -> null
 * 4. 有状态操作会把无状态操作截断，单独处理
 */
public class RunStream {

    public static void main(String[] args) {
        Random random = new Random();
        Stream.generate(() -> random.nextInt())
                .limit(500)
                .peek(s -> System.out.println("peek1: " + s))
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s > 1000000;
                })
                .peek(s -> System.out.println("peek2: " + s))
                .count();

    }
}
