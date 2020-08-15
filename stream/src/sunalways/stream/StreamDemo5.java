package sunalways.stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 18:00
 * @Description:
 */
public class StreamDemo5 {

    public static void main(String[] args) {

//        IntStream.range(1, 100).peek(StreamDemo5::debug).count();
        // 调用 parallel产生并行流
//        IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

        // 先并行，再串行
        // 多次调用 parallel | sequential 以最后一次为准
//        IntStream.range(1, 100)
//                .parallel().peek(StreamDemo5::debug)
//                .sequential().peek(StreamDemo5::debug)
//                .count();

        // 并行流使用的线程池: ForkJoinPool.commonPool
        // 默认的线程数是当前机器的cpu个数
//        IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count();

        // 使用自己的线程池， 不使用默认线程池， 防止任务被阻塞
        ForkJoinPool pool = new ForkJoinPool(20);
        pool.submit(() -> IntStream.range(1, 100).parallel().peek(StreamDemo5::debug).count());
        pool.shutdown();

        synchronized (pool) {
            try {
                pool.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void debug(int i) {
        System.out.println(Thread.currentThread().getName() + "debug" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(int i) {
        System.err.println("debug" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
