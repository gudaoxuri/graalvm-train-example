package idealworld.train.graalvm.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.util.concurrent.CountDownLatch;

/**
 * Polyglot并发示例.
 *
 * @author gudaoxuri
 */
public class PolyglotThreadExample {

    public static void main(String[] args) throws InterruptedException {
        var context = Context.newBuilder().allowAllAccess(true).build();
        var testFuns = "function add(x, y){\n" +
                "  var result = x +y\n" +
                "  return result\n" +
                "}";
        context.eval(Source.create("js", testFuns));
        // 执行测试
        new Thread(() -> {
            while (true) {
                try {
                    context.getBindings("js").getMember("add").execute(10, 20).asLong();
                } catch (IllegalStateException e) {
                    System.err.println("Thread 1 Invoke Error : " + e.getMessage());
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    context.getBindings("js").getMember("add").execute(10, 20).asLong();
                } catch (IllegalStateException e) {
                    System.err.println("Thread 2 Invoke Error : " + e.getMessage());
                }
            }
        }).start();

        new CountDownLatch(1).await();
    }

}
