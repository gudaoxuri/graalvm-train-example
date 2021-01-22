package idealworld.train.graalvm.performance;

import io.vertx.core.Vertx;

/**
 * 性能测试.
 *
 * @author gudaoxuri
 */
public class PerformanceMain {

    public static void main(String[] args) {
        Vertx.vertx().createHttpServer()
                .requestHandler(res -> res.response().end("Hello Vertx"))
                .listen(8080, event -> System.out.println("Server listening at: http://localhost:8080/"));
    }

}
