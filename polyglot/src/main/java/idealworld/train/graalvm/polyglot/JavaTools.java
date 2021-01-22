package idealworld.train.graalvm.polyglot;

/**
 * @author gudaoxuri
 */
public class JavaTools {

    public static void log(Object message) {
        // 模拟在JS调用过程中调用Java的日志框架打印日志
        System.out.println("Log : " + message);
    }

}
