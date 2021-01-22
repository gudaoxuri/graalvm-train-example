package idealworld.train.graalvm.nashorn;

import javax.script.Compilable;
import javax.script.Invocable;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn引擎示例.
 *
 * @author gudaoxuri
 */
public class NashornExample {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        var scriptEngineManager = new ScriptEngineManager();
        // 使用 Nashorn 引擎
        var jsEngine = scriptEngineManager.getEngineByName("nashorn");
        // 添加与Java交互的函数类
        var javaFuns = "var $ = Java.type('idealworld.train.graalvm.nashorn.NashornExample.JavaTools')\n";
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
