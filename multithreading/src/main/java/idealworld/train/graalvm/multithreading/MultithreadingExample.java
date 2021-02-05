package idealworld.train.graalvm.multithreading;

import io.vertx.core.Promise;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 多线程示例.
 *
 * @author gudaoxuri
 */
public class MultithreadingExample {

    private static final String TASK_JS = new BufferedReader(new InputStreamReader(MultithreadingExample.class.getResourceAsStream("/task.js")))
            .lines().collect(Collectors.joining("\n"));

    public static void main(String[] args) throws InterruptedException {
        var context = Context.newBuilder()
                .allowAllAccess(true)
                // 开启Java函数过滤以保障安全
                .allowHostClassLookup(s -> s.equalsIgnoreCase(PolyglotExchanger.class.getName()))
                .build();
        // 添加与Java交互的函数类
        context.eval(Source.create("js", TASK_JS));

        // 执行测试
        new Thread(() -> {
            while (true) {
                var promise = Promise.promise();
                PolyglotExchanger.request("fun1", new ArrayList<>(), promise);
                promise.future()
                        .onSuccess(resp -> System.out.println("result : " + resp))
                        .onFailure(e -> System.err.println("result : " + e.getMessage()));
            }
        }).start();
        new Thread(() -> {
            while (true) {
                var promise = Promise.promise();
                PolyglotExchanger.request("fun2", new ArrayList<>(), promise);
                promise.future()
                        .onSuccess(resp -> System.out.println("result : " + resp))
                        .onFailure(e -> System.err.println("result : " + e.getMessage()));
            }
        }).start();

        new CountDownLatch(1).await();
    }

}
