package idealworld.train.graalvm.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

/**
 * Polyglot基础示例.
 *
 * @author gudaoxuri
 */
public class PolyglotBasicExample {

    public static void main(String[] args) {
        var context = Context.newBuilder().allowAllAccess(true).build();
        // 添加与Java交互的函数类
        var javaFuns = "var $ = Java.type('idealworld.train.graalvm.polyglot.JavaTools')\n";
        // 添加测试方法，该方法中调用了Java函数
        var testFuns = "function add(x, y){\n" +
                "  var result = x +y\n" +
                "  $.log('x + y = ' + result)\n" +
                "  return result\n" +
                "}";
        context.eval(Source.create("js", javaFuns + testFuns));
        // 执行测试
        var result = context.getBindings("js").getMember("add").execute(10, 20).asLong();
        System.out.println("Invoke Result : " + result);

    }

}
