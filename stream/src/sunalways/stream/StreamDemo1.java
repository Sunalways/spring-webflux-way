package sunalways.stream;

import java.util.stream.IntStream;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 16:53
 * @Description:
 */
public class StreamDemo1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        // 外部迭代
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println("结果为: " + sum);

        // 使用stream内部迭代
        int sum2 = IntStream.of(nums).sum();
        System.out.println("结果为: " + sum2);

        // map 中间操作(返回stream的操作) sum 终止操作
        int sum1 = IntStream.of(nums).map(i -> i * 2).sum();
        System.out.println("结果为: " + sum1);

        int sum3 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println("结果为: " + sum2);

        System.out.println("惰性求值就是终止条件没有调用的情况下，中间操作不会执行");
        IntStream.of(nums).map(StreamDemo1::doubleNum);
    }

    public static int doubleNum(int i) {
        System.out.println("执行了乘以2");
        return i * 2;
    }
}
