package idealworld.train.graalvm.polyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Source;

import javax.script.ScriptException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Polyglot数据类型示例.
 *
 * @author gudaoxuri
 */
public class PolyglotDataTypeExample {

    public static void main(String[] args) throws ScriptException {
        var context = Context.newBuilder()
                .allowAllAccess(true)
                // 可以通过 targetTypeMapping 自定义转换类型
                /*.allowHostAccess(HostAccess.newBuilder(HostAccess.ALL)
                        .targetTypeMapping(
                                List.class,
                                Object.class,
                                Objects::nonNull,
                                v -> v,
                                HostAccess.TargetMappingPrecedence.HIGHEST)
                        .build())*/
                .allowHostClassLookup(s -> false)
                .build();
        // 添加测试方法，该方法中调用了Java函数
        var testFuns = "async function arr(){\n" +
                "  return ['1','2']\n" +
                "}";
        context.eval(Source.create("js", testFuns));
        // 执行测试
        var result = new Object[2];
        Consumer<Object> then = (v) -> result[0] = v;
        Consumer<Object> catchy = (v) -> result[1] = v;
        context.getBindings("js").getMember("arr").execute()
                .invokeMember("then", then).invokeMember("catch", catchy);
        if (result[1] != null && !result[1].toString().trim().isBlank()) {
            throw new ScriptException((String) result[1]);
        }
        System.out.println("Invoke Result : " + ((List)result[0]).get(1));
    }

}
