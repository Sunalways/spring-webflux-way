package sunalways.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @Auther: 62458
 * @Date: 2020/8/15 20:31
 * @Description:
 */
public class FlowDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1. 定义发布者， 发布者的数据类型
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        // 2. 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>() {

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

        // 3. 发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        // 4. 生产数据，并发布
        int data = 111;
        publisher.submit(data);

        // 5. 结束后，关闭发布者
        // 正式环境放在finally 或者使用 try-resouce
        publisher.close();

        Thread.currentThread().join(100);
    }
}
