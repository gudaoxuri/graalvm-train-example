package idealworld.train.graalvm.jsr223;

import javax.script.*;
import java.util.function.Predicate;

/**
 * Graal引擎示例.
 *
 * @author gudaoxuri
 */
public class GraalExample {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        var scriptEngineManager = new ScriptEngineManager();
        // 使用 Graal 引擎，在Java11下默认使用Graal引擎
        var jsEngine = scriptEngineManager.getEngineByName("js");
        var bindings = jsEngine.getBindings(ScriptContext.ENGINE_SCOPE);
        // 添加安全策略
        bindings.put("polyglot.js.allowHostAccess", true);
        bindings.put("polyglot.js.allowHostClassLookup", (Predicate<String>) s -> true);
        // 添加与Java交互的函数类
        var javaFuns = "var $ = Java.type('idealworld.train.graalvm.jsr223.GraalExample.JavaTools')\n";
        // 添加测试方法，该方法中调用了Java函数
        var testFuns = "function add(x, y){\n" +
                "  var result = x +y\n" +
                "  $.log('x + y = ' + result)\n" +
                "  return result\n" +
                "}";
        var script = ((Compilable) jsEngine).compile(javaFuns + testFuns);
        script.eval();
        // 执行测试
        var result = ((Invocable) script.getEngine()).invokeFunction("add", 10, 20);
        System.out.println("Invoke Result : " + result);

    }

    public static class JavaTools {

        public static void log(String message) {
            // 模拟在JS调用过程中调用Java的日志框架打印日志
            System.out.println("Log : " + message);
        }
    }

}
