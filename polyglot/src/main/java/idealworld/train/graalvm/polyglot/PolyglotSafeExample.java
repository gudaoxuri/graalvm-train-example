package idealworld.train.graalvm.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Polyglot安全示例.
 *
 * @author gudaoxuri
 */
public class PolyglotSafeExample {

    public static void main(String[] args) {
        var context = Context.newBuilder()
                .allowAllAccess(true)
                // 开启Java函数过滤以保障安全
                //.allowHostClassLookup(s -> s.equalsIgnoreCase(JavaTools.class.getName()))
                .build();
        // 添加与Java交互的函数类
        var javaFuns = "var $ = Java.type('idealworld.train.graalvm.polyglot.JavaTools')\n" +
                "var sys = Java.type('idealworld.train.graalvm.polyglot.PolyglotSafeExample.SystemHelper')\n";
        // 添加测试方法，该方法中调用了Java函数
        var testFuns = "function invade(){\n" +
                "  $.log('\\n---------------\\n'+sys.getProps()+'\\n---------------')\n" +
                "}";
        context.eval(Source.create("js", javaFuns + testFuns));
        // 执行测试
        context.getBindings("js").getMember("invade").execute();
    }

    public static class SystemHelper {

        // Java代码常见的辅助类
        public static Map<Object, Object> getProps() {
            return System.getProperties().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

    }

}
