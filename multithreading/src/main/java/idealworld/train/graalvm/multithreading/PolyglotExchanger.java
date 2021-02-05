package idealworld.train.graalvm.multithreading;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 与JS的交互类.
 *
 * @author gudaoxuri
 */
public class PolyglotExchanger {

    // 创建Vertx实例
    private static Vertx vertx = Vertx.vertx();
    // 创建EventBus实例
    private static EventBus eventBus = vertx.eventBus();

    /**
     * 由Java侧发起JS调用请求.
     *
     * @param funName 函数名
     * @param args    函数参数
     * @param promise 执行回调
     */
    public static void request(String funName, List<Object> args, Promise<Object> promise) {
        // 向JS中发起地址为"__js_invoke__"的事件
        eventBus.request("__js_invoke__", new JsonObject().put("funName", funName).put("args", args).toString(),
                (Handler<AsyncResult<Message<String>>>) event -> {
                    // 执行返回处理
                    if (event.failed()) {
                        promise.fail(event.cause());
                    } else {
                        promise.complete(event.result().body());
                    }
                });
    }

    /**
     * 由JS侧调用事件订阅.
     *
     * @param processFun 订阅处理函数
     */
    public static void consumer(Consumer<Message<String>> processFun) {
        eventBus.consumer("__js_invoke__", processFun::accept);
    }

    /**
     * 模拟JS调用Java发起HTTP请求.
     *
     * @param httpMethod HTTP方法
     * @param url        URL
     * @param body       Body
     * @param header     Header
     * @param fun        回调函数
     */
    public static void http(String httpMethod, String url, String body, Map<String, String> header, Consumer<String> fun) {
        // 模拟调用，这里仅返回请求的URL
        vertx.setTimer(1000, i -> fun.accept("<div>Hello:" + url + "</div>"));
    }

}
