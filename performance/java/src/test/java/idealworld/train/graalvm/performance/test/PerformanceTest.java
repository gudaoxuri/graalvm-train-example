package idealworld.train.graalvm.performance.test;

import idealworld.train.graalvm.performance.PerformanceMain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 用于生成Native Image.
 *
 * @author gudaoxuri
 */
public class PerformanceTest {

    @Test
    public void testAll() {
        PerformanceMain.main(null);
        Assertions.assertTrue(true);
    }

}
