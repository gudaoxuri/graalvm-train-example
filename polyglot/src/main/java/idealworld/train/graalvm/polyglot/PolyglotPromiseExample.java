package idealworld.train.graalvm.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import javax.script.ScriptException;
import java.util.function.Consumer;

/**
 * Polyglot Promise示例.
 *
 * @author gudaoxuri
 */
public class PolyglotPromiseExample {

    public static void main(String[] args) throws ScriptException {
        var context = Context.newBuilder()
                .allowAllAccess(true)
                .allowHostClassLookup(s -> false)
                .build();
        // 添加测试方法，该方法中调用了Java函数
        var testFuns = "async function add(x, y){\n" +
                "  return x + y\n" +
                "}";
        context.eval(Source.create("js", testFuns));
        // 执行测试
        var result = new Object[2];
        Consumer<Object> then = (v) -> result[0] = v;
        Consumer<Object> catchy = (v) -> result[1] = v;
        context.getBindings("js").getMember("add").execute(10, 20)
                .invokeMember("then", then).invokeMember("catch", catchy);
        if (result[1] != null && !result[1].toString().trim().isBlank()) {
            throw new ScriptException((String) result[1]);
        }
        System.out.println("Invoke Result : " + result[0]);
    }

}
