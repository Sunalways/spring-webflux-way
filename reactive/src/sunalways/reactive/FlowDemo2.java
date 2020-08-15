package sunalways.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 20:53
 * @Description:
 */
class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer, String> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        // 保存订阅关系，需要用它来给发布者响应
        this.subscription = subscription;

        // 请求一个数据
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        // 接受到一个数据，处理
        System.out.println("接受到数据: " + item);

        // 过滤掉小于0的，然后发布出去
        if (item > 0) {
            this.submit("转换后的数据: " + item);
        }

        // 处理完调用request再请求一个数据
        this.subscription.request(1);

        // 达到目标，调用cancel告诉发布者不再接受数据了
//                this.subscription.cancel();

    }

    @Override
    public void onError(Throwable throwable) {
        // 出现异常
        throwable.printStackTrace();

        // 不再接收数据
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("处理完了!");
    }
}

public class FlowDemo2 {

    public static void main(String[] args) throws InterruptedException {
        // 1. 定义发布者
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2. 定义处理器，对数据进行过滤，并转换为String类型
        MyProcessor processor = new MyProcessor();

        // 3. 发布者和处理器建立订阅关系
        publisher.subscribe(processor);

        // 4. 定义最终订阅者, 消费String类型数据
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                // 保存订阅关系，需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                // 接受到一个数据，处理
                System.out.println("接受到数据: " + item);

                // 处理完调用request再请求一个数据
                this.subscription.request(1);

                // 达到目标，调用cancel告诉发布者不再接受数据了
//                this.subscription.cancel();

            }

            @Override
            public void onError(Throwable throwable) {
                // 出现异常
                throwable.printStackTrace();

                // 不再接收数据
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("处理完了!");
            }
        };

        // 5. 处理器 和 最终订阅者建立订阅关系
        processor.subscribe(subscriber);

        // 6. 生产数据，并发布
        publisher.submit(111);
        publisher.submit(-111);

        // 5. 结束后，关闭发布者
        // 正式环境放在finally 或者使用 try-resouce
        publisher.close();

        Thread.currentThread().join(100);
    }
}
